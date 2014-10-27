package View;

import Modal.GameConstant;
import XMLUtils.XMLDocument;
import XMLUtils.XMLExceptions;
import Modal.Soldier;
import Modal.BoardSlot;
import Modal.Player;
import Modal.Board;
import Modal.BoardSlot.Type;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI extends UI {

    //class parameters:
     private Scanner in; 
     private UI.gameModeOptions gameMode;

    
     private enum parameterID{
         BOARDSIZE,
         NUMOFBOARDMOVEMENTEELEMENTS,
         NUMOFPLAYERS,
         NUMOFSOLDIERSNEEDEDTOWIN,
         GAMENAME,
         GAMEMODE,
         PLAYERTYPE, 
         SAVEFILE, 
         NEWFILENAME,
         askForNewGameYesOrNo
     }     
         
     private parameterID inputParameterId;   
     
     public static final String ANSI_RESET = "\u001B[0m";
     public static final String ANSI_BLACK = "\u001B[30m";
     public static final String ANSI_RED = "\u001B[31m";
     public static final String ANSI_GREEN = "\u001B[32m";
     public static final String ANSI_YELLOW = "\u001B[33m";
     public static final String ANSI_BLUE = "\u001B[34m";
     public static final String ANSI_PURPLE = "\u001B[35m";
     public static final String ANSI_CYAN = "\u001B[36m";
     public static final String ANSI_WHITE = "\u001B[37m";
     public static final String ANSI_BOLD =  "\033[1m";
     
     
    @Override
    public void printGameFinish() {
        System.out.println(UIMessage.finishedGameAlert);
    }



    private String recieveFromUserTheGameName() {
        String gn;
        System.out.println(UIMessage.getGameNameMsg);
        gn = getInputStringNotAllWhiteSpaces(inputParameterId.GAMENAME);
        return gn;
    }

    private boolean isNameTaken(ArrayList<Player> allPlayersForNow, String playerName) {
        String s1, s2;
        s1 = playerName;
        s2 = s1.replaceAll("\\s","");
        
        for(Player p : allPlayersForNow)
        {
            if(playerName.replaceAll("\\s+","").equalsIgnoreCase(p.getPlayerName().replaceAll("\\s+","")))
            {
                return true;
            }
        }
        
        return false;
    }

    private String receiveFileName()
    {        
        System.out.println(UIMessage.saveAsAskForFileNameMessege);
        return getInputStringNotAllWhiteSpaces(inputParameterId.NEWFILENAME);                    
    }
    
    private String getInputStringNotAllWhiteSpaces(parameterID inputParameterDesired)
    {
        String inputString ;
       
        do
        {
               inputString = in.nextLine();
               if(inputString.trim().length()==0)
               {
                    System.out.println(UIMessage.inputStringAllWhiteSpace);
                    showGetInputMessageAndNote(inputParameterDesired);
               }
               else
               {
                  return inputString;
               }
        }while(true);
        
    }


     
     public ConsoleUI (){
          in = new Scanner(System.in);
     }
     
     
     @Override
     void printGameInstruction()
     {
         System.out.println(UIMessage.gameDetailsMessege);
     }
     //=============== start game mode initalization =======================================
    @Override
    public void initializeGameSettings(UI.gameModeOptions gameMode, Board []gameBoardInput) throws Exception {
        
        switch (gameMode) {
            case NEW:
                initializeNewGameSettings();
                break;
            case LOAD:
                gameBoardInput[0] = initializeLoadedGameSettings();
                break;
        }
    }
    
    @Override
    public  void initializeNewGameSettings() throws Exception {
              
       try{
            gameName = recieveFromUserTheGameName();
            numOfPlayers = recieveFromUserTheNumOfPlayers();
            boardSize    = recieveFromUserTheBoardSize();
            numOfBoardMovementElements = recieveFromUserTheNumOfBoardMovementElements(boardSize);
            numOfSoldiersNeededToWin = recieveFromUserTheNumOfSoldiersNeededToWin();
       } catch (Exception e){
           System.out.println("An error occured while getting the settings information, Aborting");
           throw e;         // Game engine should deal with this exception.
                            // if an error happened here, game engine should re-start the 
                            // set settings stage and not -kill- the program.
       }
       
       
    }

    
    @Override
    public Board initializeLoadedGameSettings()  {
        gamePlayers = new ArrayList<>();
        XMLDocument xml = new XMLDocument();
        
        short [] tempSoldeirArr = new short[1];
        ArrayList<String> gameNameTemp = new ArrayList<>();
        String fileName;
        
        while(true)
        {
            try{
                System.out.println(UIMessage.getFileNameMsg);
                fileName = in.nextLine();
                try{
                    gameBoard = xml.readXMLFile(fileName, gamePlayers,tempSoldeirArr,gameNameTemp);
                }catch(XMLExceptions e)
                  {
                      printXmlErrorException(e);
                      gameNameTemp.clear();
                      gamePlayers.clear();
                  }
                catch(Exception e)
                {
                    System.out.println(UIMessage.xmlLoadError); 
                    gameNameTemp.clear();
                    gamePlayers.clear();
                    continue;
                }
                    gameName = gameNameTemp.get(0);
                gameNameTemp.clear();
                numOfSoldiersNeededToWin = tempSoldeirArr[0];
                boardSize = gameBoard.getBoardSize();
                numOfBoardMovementElements = gameBoard.getNumberOfMovementElements();
                numOfPlayers = (short)gamePlayers.size();
                break;
            }catch(Exception e)
            {
                System.out.println(UIMessage.getFileNameErrorMsg);
            }
            
        }
        
        return gameBoard;
        
    }
    
    
    
    
    
     //=============== end game mode initialization =======================================
    
    // get game iteration from user for next move
    @Override
    public void gameIteration() throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //=============== recive  parameters of board from user =======================================
  
   
    private short recieveFromUserTheNumOfPlayers(){
        return getSettingParametr(inputParameterId.NUMOFPLAYERS, GameConstant.MaxPlayers, GameConstant.MinPlayers);
    }
    
    
    public String receiveFromUserFilePathToSave (String lastUsedFileName){
        String newFileName;
        short temp;
        
        
        temp = getSettingParametr(inputParameterId.SAVEFILE, GameConstant.maxOptionInSaveMenu, GameConstant.minOptionInSaveMenu);
        
        
        switch (temp) {
            //save
            case 1:
                if(lastUsedFileName == null)
                {
                     newFileName= receiveFileName();
                     return newFileName;
                }
                else
                {
                   return lastUsedFileName;   
                }

                
            //save as
            case 2:
                newFileName = receiveFileName();
                return newFileName;

            default:
                return null;
        }
    }
    
    private short recieveFromUserTheBoardSize(){
        return getSettingParametr(inputParameterId.BOARDSIZE, GameConstant.MaxBoardSize, GameConstant.MinBoardSize);
    }
    
    
     @Override
    public boolean reciveFromUserThePlayerTypeInfo() 
     {
         int type;
         short temp;
         temp = getSettingParametr(inputParameterId.PLAYERTYPE, GameConstant.computerPlayerType, GameConstant.humanPlayerType);
         
         type = (int)temp;
         
          if (type == 1)
          {
               return true;
          }

          if (type == 2)
          {
              return false;
          }
          
          return false; //cannot be here;
     }
     
    private short recieveFromUserTheNumOfBoardMovementElements(short legalBoardSize){
        short minAllowedNumberOfElementsOnBoard = 0;
        short maxAllowedNumberOfElementsOnBoard = 0;
        
        if(legalBoardSize == 5)
        {
            minAllowedNumberOfElementsOnBoard = 3;
            maxAllowedNumberOfElementsOnBoard = 4;
        }
        else if (legalBoardSize == 6)
        {
            minAllowedNumberOfElementsOnBoard = 3;
            maxAllowedNumberOfElementsOnBoard = 5;
        }
        else if (legalBoardSize == 7)
        {
            minAllowedNumberOfElementsOnBoard = 3;
            maxAllowedNumberOfElementsOnBoard = 7;
        }
        else
        {
            minAllowedNumberOfElementsOnBoard = 3;
            maxAllowedNumberOfElementsOnBoard = 8;
        }
        
        
        return getSettingParametr(inputParameterId.NUMOFBOARDMOVEMENTEELEMENTS, maxAllowedNumberOfElementsOnBoard, minAllowedNumberOfElementsOnBoard);
    }
    
    private void printIllegalNoteForBoardMovementElementsNumber()
    {
        if (boardSize == 5)
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsIllegalInputMessage5on5Board);
        }
        else if (boardSize == 6)
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsIllegalInputMessage6on6Board);
        }
        else if (boardSize ==7)
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsIllegalInputMessage7on7Board);
        }                 
        else
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsIllegalInputMessage8on8Board);
        }
    }

    
    private void printNumOfMovementElementAllowedAccordingBoardSize()
    {
        if (boardSize == 5)
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsNote5on5Board);
        }
        else if (boardSize == 6)
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsNote6on6Board);
        }
        else if (boardSize ==7)
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsNote7on7Board);
        }                 
        else
        {
            System.out.println(UIMessage.getNumOfBoardMovementElementsNote8on8Board);
        }
    }
    
    private short recieveFromUserTheNumOfSoldiersNeededToWin(){
        return getSettingParametr(inputParameterId.NUMOFSOLDIERSNEEDEDTOWIN, GameConstant.MaxNumOfSoldiersNeededToWin, GameConstant.MinNumOfSoldiersNeededToWin);
    }
    
    @Override
    public void printAskDetailsOfPlayerMsg(int i) {
        System.out.println(UIMessage.askDetailsMsg(i+1));
    }
   
    //===============================================================================
    
    private short getSettingParametr(parameterID inputParameterDesired, short maxAllowedValue, short minAllowedValue){
        short inputParameterFromUser = 0;
        String inputString;
        showGetInputMessageAndNote(inputParameterDesired);   
            
        try{
            while(true) {
                {
                    try{
                        
                          inputString = getInputStringNotAllWhiteSpaces(inputParameterDesired);
                          inputParameterFromUser = Short.parseShort(inputString);
                       }
                    catch (InputMismatchException | NumberFormatException nfe){
                            System.out.println(UIMessage.notValidIntegerInputMessage);
                           // in.nextLine();
                            continue;
                    }
                    

                    if((inputParameterFromUser>maxAllowedValue)||(inputParameterFromUser<minAllowedValue))
                    {
                        showIllegalInputMessage(inputParameterDesired);
                    }
                    else
                    {
                        break; 
                    }                   
                }
            }
        }
        
        catch (Exception e){
                System.out.println(UIMessage.exceptionMessage);
                in.close();
                throw e; 
                //need to take care in case that error had occured
        }
        
        return inputParameterFromUser;
    }
    
    //======================= get user decision =====================================
    @Override
    public gameModeOptions getGameModeOption() {
        
        short gameModeTemp = getSettingParametr(inputParameterId.GAMEMODE, GameConstant.maximalGameMode, GameConstant.minimumGameMode);
        int inputGamdeMode; 
        
        inputGamdeMode = (int)(gameModeTemp);
        
        switch(inputGamdeMode){
            case (1): {
                return UI.gameModeOptions.NEW;
            }
            case (2): {
                return UI.gameModeOptions.LOAD;
            } 
           
                
        }
        
        return null; // cannot be here 
    }
    
    //======================= show messages according to desired parameter input =====
    
    private void showGetInputMessageAndNote(parameterID inputParameterDesired){
        switch(inputParameterDesired)
        {   
                //note - GAMENAME is not taken care here, it is treated seperatly

                case BOARDSIZE:
                    System.out.println(UIMessage.getBoardSizeMessage);
                    System.out.println(UIMessage.getBoardSizeNote);
                    break;
                   
                case askForNewGameYesOrNo:
                    System.out.println(UIMessage.verifyStartOfANewGame);
                    break;
                    
                case NEWFILENAME:
                    System.out.println(UIMessage.saveAsAskForFileNameMessege);
                    break;
                    
                case SAVEFILE:
                    System.out.println(UIMessage.basicSaveMessege);
                    break;
                    
                case GAMEMODE:
                    System.out.println(UIMessage.chooseGameModeOptionMsg);
                    System.out.println(UIMessage.getGameModeNote);
                    break;
                    
                case PLAYERTYPE:
                    System.out.println(UIMessage.askForPlayerTypeMessage);
                    System.out.println(UIMessage.askForPlayerTypeNote);
                    break;
                    
                    
                    
                case NUMOFBOARDMOVEMENTEELEMENTS:
                    System.out.println(UIMessage.getNumOfBoardMovementElementsMessage);
                    printNumOfMovementElementAllowedAccordingBoardSize();
                    break;
                    
                case NUMOFPLAYERS:
                    System.out.println(UIMessage.getNumOfPlayersMessage);
                    System.out.println(UIMessage.getNumOfPlayersNote);
                    break;
                    
                case NUMOFSOLDIERSNEEDEDTOWIN:
                    System.out.println(UIMessage.getNumOfSoldiersNeededToWinMessage);
                    System.out.println(UIMessage.getNumOfSoldiersNeededToWinNote);
                    break;
                    
                default: //should throw error here - not supposed to be here
                    break;    
        }
    }
    
    private void showIllegalInputMessage(parameterID inputParameterDesired) {
        switch(inputParameterDesired)
        {   
            //note - GAMENAME is not taken care here, it is treated seperatly
                case BOARDSIZE:
                    System.out.println(UIMessage.getBoardSizeIllegalInputMessage);
                    break;
                   
                case NUMOFBOARDMOVEMENTEELEMENTS:
                    printIllegalNoteForBoardMovementElementsNumber();
                    break;
                    
                    
                case PLAYERTYPE:
                    System.out.println(UIMessage.askForPlayerTypeIllegalInputMessage);
                    break;
                        
                case NUMOFPLAYERS:
                    System.out.println(UIMessage.getNumOfPlayersIllegalInputMessage);
                    break;
                    
                case NUMOFSOLDIERSNEEDEDTOWIN:
                    System.out.println(UIMessage.getNumOfSoldiersNeededToWinIllegalInputMessage);
                    break;
                case SAVEFILE:
                    System.out.println(UIMessage.basicSaveMessegeErrorNote);
                    break;
                    
                case GAMENAME:
                    System.out.println(UIMessage.getGameNameIllegalMessege);
                    break;
                    
                default:  
                    break;    
        }
    }
    
    
    
    @Override
    public void printWelcomeAndGameDisclaimer()
    {
        System.out.println(UIMessage.welcomeGameMessage);
         System.out.println(UIMessage.gameDetailsMessege);
    }
    
    @Override
    public boolean recieveFromUserNewGameAnswer(){
        String userInput;
        
        System.out.println(UIMessage.verifyStartOfANewGame);
        
        do{
        userInput = getInputStringNotAllWhiteSpaces(inputParameterId.askForNewGameYesOrNo);

           if (userInput.equalsIgnoreCase("yes")){
               return false;
           } else if (userInput.equalsIgnoreCase("no")){
               return true;
           }
           else{
               System.out.println(UIMessage.inputStringAllWhiteSpace);
               System.out.println(UIMessage.verifyStartOfANewGame);
           }
        }while(true);
                  
   }
    
    @Override
    public void printPlayerTurn(Player player){
        System.out.println(UIMessage.printInfoForPlayersTurn(player.getPlayerName()));
    }
    
    @Override
    public void printCubeInformation(short cubeValue){
        System.out.println(UIMessage.printCubeRollNumber(cubeValue));
    }
    

        
        
    public TurnDecision recieveTurnDecisionFromPlayer(short[] soldier, Soldier[] allSoldiers){
               
           //gameInterface.recieveTurnDecisionFromPlayer(userInputSteps, this.getAllSoldiers());
            String strMsg = UIMessage.printSoldiersInfoMsg(allSoldiers);
            System.out.println(strMsg);
            String str;
            short soldierID; 

            do{
                str = in.nextLine();
                if (str.equalsIgnoreCase("save") == true){
                   return TurnDecision.SAVE;
                } else if (str.equalsIgnoreCase("quit") == true){
                   return TurnDecision.QUIT;
                } else if (str.equalsIgnoreCase("shutdown") == true){
                   return TurnDecision.SHUTDOWN;
                } else{
                    // Input should be an integer between 1-4. Verify that and handle it.
                   try{
                     soldierID = (short)((Short.parseShort(str))-1);
                     if (soldierID <= GameConstant.maxSoldierID && soldierID >= GameConstant.minSoldierID){
                         if (allSoldiers[soldierID].getSoldierActiveStatus() == false){
                             displayInvalidSoldierError();
                         } else{
                            soldier[0] = soldierID;
                            return TurnDecision.PLAY;
                         }
                     }
                     else{
                         System.out.println(UIMessage.soldierNotInRangeMessage);
                     }
                   } catch(NumberFormatException nfe){
                     displayInvalidTurnInformation(strMsg);
                   }

                }
            } while(true);
    }
    
    @Override
    void displayInvalidTurnInformation(String str){
        System.out.println(UIMessage.InvalidTurnInformationMessage(str));
    }
    
   @Override
   public void printGameStatus(Board gameBoard, ArrayList<Player> playerList) {
        printBoardToScreen(gameBoard, playerList);
        System.out.println("");
        printPlayersStatusToScreen(playerList);
   }
   
   @Override
   void printBoardToScreen(Board board, ArrayList<Player> players){
        
        short boardSize;
        boardSize = board.getBoardSize();
        
        ArrayList<ArrayList<BoardSlot>> gameBoard = board.getGameBoard();
        
        System.out.println("");
        for (short i = 0; i < boardSize; i++){
            for (short j = 0; j < boardSize; j++){
                printBoardSlotToScreen(gameBoard.get(i).get(j), players);
            }
            System.out.println("");
        }
//        System.out.println("");
        System.out.println("--------------------------------");
    }
   
    
    @Override
    void printBoardSlotToScreen(BoardSlot boardSlot, ArrayList<Player> players){
        // Slot example:               21|--|0000 
        // Complicated Slot example:   16|22|0000
        short slotNum = boardSlot.getSlotNumber();
        int movesTo = boardSlot.getMovesTo();
        String tmpMovesTo = "00";
        ArrayList<Integer> soldiersAmt = boardSlot.getSoldiersAmtPerPlayerArrayList();
        Type currSlotType;           
        
        currSlotType = boardSlot.getSlotType();
        if (currSlotType != Type.REG){
            if (boardSlot.getMovesTo()< 10){
                tmpMovesTo = Integer.toString(movesTo);
                tmpMovesTo = "0"+tmpMovesTo;
            } else{
              tmpMovesTo = Integer.toString(movesTo);
            }
            
            switch (currSlotType){
                case LADDER:
                    tmpMovesTo = ANSI_GREEN+tmpMovesTo+ANSI_RESET;
                    break;
                    
                case SNAKE:
                    tmpMovesTo = ANSI_RED+tmpMovesTo+ANSI_RESET;
                    break;
            } 
        } else{
           tmpMovesTo = "--";
        }
        

        if (slotNum < 10){
            System.out.print("0"+ slotNum + "|" + tmpMovesTo + "|" );
        } else{
            System.out.print(slotNum + "|" + tmpMovesTo + "|" );
        }
        
        for (int i = 0; i < soldiersAmt.size(); i++){
            if(players.get(i).getPlayingStatus()==false)
            {
                continue;
            }
            
            int tmpSoldierAmt = soldiersAmt.get(i);
            if (tmpSoldierAmt != 0){
                System.out.print( ANSI_BLUE + soldiersAmt.get(i) + ANSI_RESET );
            } else{
                System.out.print( soldiersAmt.get(i) + "" );
            }
        }
        System.out.print(" ");
    }
    
    @Override
    void printPlayersStatusToScreen(ArrayList<Player> playerList){
        int j = 0;
        for (int i=0; i < playerList.size(); i++){
            if(playerList.get(i).getPlayingStatus() == true ){
                 System.out.print("Player " + (j+1) + ":" + " " + (playerList.get(i).getPlayerName()) + " ");
                 j++;
            }
        }
        System.out.println("");
    }
    
    @Override
    public void alertFailedInitalize() {
        System.out.println(UIMessage.failInitializeMessage);
    }
    
    //========================= recieve player data from user ===================
    
    @Override
    public String reciveFromUserThePlayerName( ArrayList<Player> allPlayersForNow) 
    {
        String playerName;
        System.out.println(UIMessage.enterYourNameMessage);
         
        while(true)
        { 
            playerName = in.nextLine(); 
            if ((playerName.trim().length() > 0)&&(isNameTaken(allPlayersForNow, playerName) == false))
            {
                break;
            }
            else
            {
                System.out.println(UIMessage.illegalNameOfPlayerNote);
            }
        }
       // in.nextLine();
        return playerName;        
    }
    
    
    @Override
    void displayInvalidSoldierError(){
        System.out.println(UIMessage.invalidSoldierSelectedMessege);
    }
    
    @Override
     void displayOutOfRangeSelectionSoldier(){
        System.out.println(UIMessage.badSoldierSelctionNotInRange);
    }
    
    public void printErrorMsgWhileSaving()
    {
        System.out.println(UIMessage.errorMsgWhileSaving);
    }
    
    
    
    @Override
    public void printXmlErrorException(XMLExceptions e)
    {
        System.out.println(e);
    }
    
    @Override
    public void printSaveSuccess()
    {
        System.out.println(UIMessage.fileSaveSuccessMessege);
    }
}
