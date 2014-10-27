package View;

import Modal.Soldier;


public class UIMessage {

     private UIMessage() {
         //Class with private constructor is static.
         //http://stackoverflow.com/questions/3584113/why-are-you-not-able-to-declare-a-class-as-static-in-java
       
     }
     
     /*
      Note - no access modifier means that the field will be accessable only
      for the class and those who on the same package (i.e. world and subclass wont 
      be premeted). source - https://www.google.co.il/url?sa=i&rct=j&q=
      &esrc=s&source=images&cd=&cad=rja&uact=8&docid=ssAUNSPrP2m7dM&tbnid=
      CwsdmZJBpS0g_M:&ved=0CAUQjRw&url=http%3A%2F%2Fwww.mxtutorial.com
      %2F2011_08_01_archive.html&ei=f9jBU7HEDMTEPZzVgKgO&bvm=bv.70810081
      ,d.bG
     */
    final static String basicSaveMessege = "We understand you chose to save this game, you have two options:"
            + "                             \n1. Save - Overwrite current file."
            + "                             \n2. Save as - Save the game to a new file"
            + "                             \n   (Note - saving with same name as before will result in a overwrite)."
            + "                             \nChoose option 1 or 2.";
    
    final static String basicSaveMessegeErrorNote ="Illegal selection, press 1 or 2.";
    final static String successOnSaveMessege = "Game was successfully saved.";
    final static String saveAsAskForFileNameMessege = "Please enter the name of the file to be saved.";
    final static String inputStringAllWhiteSpace = "Illegal input string";
    final static String saveAsAskForFileNameNote = "Note - File name should not be all spaces.";
    final static String errorMsgWhileSaving = "Holly molly, We can't save the game, try again";
     final static String welcomeGameMessage = "\u001B[31mWelcome to Snakes & Ladders!!! "+ConsoleUI.ANSI_RESET;
     
     final static String chooseGameModeOptionMsg = " \nChoose one of the following options:"
             + "                               \n1. New Game.\u001B[0m"
             + "                               \n2. Load Game.\u001B[0m";
     final static String getGameModeNote = "Note - Game mode should be a number '1' or '2'";
     
     final static String xmlLoadError ="Failed to load, xml is not legal, check it up or try other";
      final static String gameDetailsMessege = "\n ---------------------------------------------------------------------------------"
            + "                              \n*"+ConsoleUI.ANSI_GREEN+" This is a brief description of the game and the functionality avillable with it:"+ConsoleUI.ANSI_RESET
            + "                              \n* On the start menu of the game you will have two options:"
            + "                              \n*" + ConsoleUI.ANSI_BLUE+" 1. Load game" + ConsoleUI.ANSI_RESET+" - load a previosly played game."
            + "                              \n*" + ConsoleUI.ANSI_BLUE+" 2. New game" +ConsoleUI.ANSI_RESET+" - start a new game."
            + "                              \n\n* In case you choose to play a new game, you will be prompted to enter the following to start the game:"
            + "                              \n*\t"+ConsoleUI.ANSI_BLUE+" a. Game name"+ConsoleUI.ANSI_RESET+" - will be used when the game is saved."
            + "                              \n*\t"+ConsoleUI.ANSI_BLUE+" b. board size"+ConsoleUI.ANSI_RESET+" - board can be one of the following: 5x5, 6x6, 7x7 or 8x8"
            + "                              \n*\t"+ConsoleUI.ANSI_BLUE+" c. number of players"+ConsoleUI.ANSI_RESET+" - their type (computer or human player) and their names (no two same names are allowed"
            + "                              \n*\t"+ConsoleUI.ANSI_BLUE+" d. number of board movement elements"+ConsoleUI.ANSI_RESET+" - we optimized the number according to the different board size, there could be 4,5 or 6 bme's of the same type, so overall between 8-12 bme's on board according to the size"
            + "                              \n*\t"+ConsoleUI.ANSI_BLUE+" e. number of soldiers needed to win."
            
            + "                              \n\n* In the begining of each turn, the current player will be displayed with the cube's value, that value is the number of steps he is allowed to take."
            + "                              \n  The player will have to pick one of his soldiers, and that soldier will be the one who moves on that turn."
            + "                              \n\n  " +ConsoleUI.ANSI_RED+ "Note:"+ ConsoleUI.ANSI_RESET+" The steps cannot be divided between different soldiers, once --"
            + "                              \n        On his turn, the player will be displayed with his current soldiers information - their ID and the board slot they are currently on."
            + "                              \n        If during the initalization phase, a computer player was picked, on his turn the computer will play and the board after the computer's movement will be displayed to all human players"
            
            + "                              \n*\n* On each turn, the human player could pick one of the following:"
            + "                              \n*\t a. "+ ConsoleUI.ANSI_BLUE+"Pick a soldier ID (between 1-4)"+ ConsoleUI.ANSI_RESET+ " - order him to move on this turn."
            + "                              \n*\t b. "+ ConsoleUI.ANSI_BLUE+"Save"+ConsoleUI.ANSI_RESET+" - save the current game (under this mode, the player will be preseneted with two options, Save or Save as..)"
            + "                              \n*\t c. "+ ConsoleUI.ANSI_BLUE+"Quit"+ ConsoleUI.ANSI_RESET+" - retire from this game and all others. a player that quits will no longer be able to participate in upcoming games" 
            + "                              \n*\t d. "+ ConsoleUI.ANSI_BLUE+"Shutdown"+ ConsoleUI.ANSI_RESET+" - Close the program."
            + "                               \n ---------------------------------------------------------------------------------"; 
          
     
     final static String getNumOfPlayersMessage = "Please enter the number of players";
     final static String getNumOfPlayersNote = "Note - Number of players shall be 2-4";
     final static String getNumOfPlayersIllegalInputMessage = "Illegal input - number of players shall be 2-4";
     
     final static String getBoardSizeMessage = "Please enter the board size";
     final static String getBoardSizeNote = "Note - board size shall be 5-8";
     final static String getBoardSizeIllegalInputMessage = "Illegal input - board size shall be 5-8";
     
     final static String getGameModeIllegalInputMessage = "Illegal input - game mode shall be 1 (for new game) or 2 (for loading)";
     final static String getNumOfSoldiersNeededToWinMessage = "Please enter the number of soldires needed to win";
     final static String getNumOfSoldiersNeededToWinNote = "Note - Number shall be 1-4";
     final static String getNumOfSoldiersNeededToWinIllegalInputMessage = "Illegal input - Num Of soldiers needed to win shall be 1-4";
     
     final static String getNumOfBoardMovementElementsMessage = "Please enter the number of board movement elements for each type";
      final static String soldierNotInRangeMessage = "The number you chose not represent legal soldier index, try again.";
     final static String getNumOfBoardMovementElementsNote5on5Board = "Note - Number shall be 3-4";
     final static String getNumOfBoardMovementElementsNote6on6Board = "Note - Number shall be 3-5";
     final static String getNumOfBoardMovementElementsNote7on7Board = "Note - Number shall be 3-7";
     final static String getNumOfBoardMovementElementsNote8on8Board = "Note - Number shall be 3-8";
     
     final static String getGameNameIllegalMessege = "This name is illegal for the game";
     final static String getNumOfBoardMovementElementsIllegalInputMessage5on5Board = "Illegal input - Num Of board movement elements shall be 3-4";
     final static String getNumOfBoardMovementElementsIllegalInputMessage6on6Board = "Illegal input - Num Of board movement elements shall be 3-5";
     final static String getNumOfBoardMovementElementsIllegalInputMessage7on7Board = "Illegal input - Num Of board movement elements shall be 3-7";
     final static String getNumOfBoardMovementElementsIllegalInputMessage8on8Board = "Illegal input - Num Of board movement elements shall be 3-8";

     final static String enterYourNameMessage = "Please enter your name:";
     final static String fileSaveSuccessMessege = "File saved!";
     final static String notValidIntegerInputMessage = "Insert a valid integer";
     final static String exceptionMessage = "Something went wrong.. bye bye"; 
          
     final static String failInitializeMessage = "Something went wrong while trying to initalize the game setting, try again later."; 

     final static String illegalNameOfPlayerNote = "Please enter valid name (can't be all whitespaces or name that already in use)";

     final static String askForPlayerTypeMessage = "Please enter the type of the player: ";
     final static String askForPlayerTypeNote = "1. Human, 2. Computer";
     final static String askForPlayerTypeIllegalInputMessage = "Illegal input - select 1 for human, 2 for computer";
     
     final static String verifyStartOfANewGame = "Do you wish to continue with another game?"
             + "                                  \nEnter 'Yes' or 'No'";

     final static String InvalidTurnInformationMessage(String legalSoldier)
     {
         String print;
        print = "Please make sure you enter one of the following:"
             + "\n1. Soldier number to move:\n"
                +"   "+ legalSoldier+""
             + "\n2. Save - to save your current game."
             + "\n3. Quit - to remove yourself from the game and let your friends continue"
             +"\n4. shutdown - close the application!";
        return print;
     }
     final static String invalidSoldierSelectedMessege = "The soldier with the ID provided has already finished the game, please choose another soldier.";
     final static String finishedGameAlert="Game Finished! here's your last status\n";
     public static String askDetailsMsg(int i)
     {
         String str = "Enter details for player number " + Integer.toString(i);
         return str;
     }
     
     public static String printSoldiersInfoMsg(Soldier[] playerSoldier){
         int i;
         String str;
         str = "Please choose one of your active soldiers:\n\t";
         for(i=0; i<playerSoldier.length; i++)
         {
             if (playerSoldier[i].getSoldierGameStatus() == false){
                 str += Integer.toString(i+1)+')' + " in slot "+Short.toString(playerSoldier[i].getBoardSlot()) +".\t";
             }
         }
         
         return str;
     }

     final static String getFileNameMsg = "Please enter the file name of the game you want to load";
     final static String getFileNameErrorMsg = "Can't load this file, try again and check that the name provided is legit";
     final static String getGameNameMsg = "Please enter the game name";
     final static String badSoldierSelctionNotInRange = "The soldier number you chose is illegal";
     public static String printInfoForPlayersTurn(String playerName){
         
         String str = "Player " + playerName + " it's your turn!";
         return str;
     }
     
     public static String printCubeRollNumber (short cubeValue){
         String str = ConsoleUI.ANSI_BOLD + "The cube rolled: " + Short.toString(cubeValue) + ConsoleUI.ANSI_RESET;
         return str;
     }
     
     
     

     
}
