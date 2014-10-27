package View;


import XMLUtils.XMLExceptions;
import Modal.BoardSlot;
import Modal.Player;
import Modal.Board;
import java.util.ArrayList;

abstract public class UI {
    
     protected String gameName;
     protected short boardSize;
     protected short numOfBoardMovementElements;
     protected short numOfPlayers;
     protected short numOfSoldiersNeededToWin;
     protected ArrayList<Player> gamePlayers;
     protected  Board gameBoard;
     
     public enum gameModeOptions{
            NEW,
            SAVE,
            LOAD
     };
     
     public enum TurnDecision{
        SAVE,
        QUIT,
        SHUTDOWN,
        PLAY
     };
     
    protected gameModeOptions gameMode;

    abstract void displayOutOfRangeSelectionSoldier();
    abstract public void printGameFinish();
    abstract public gameModeOptions getGameModeOption();  
    abstract public void initializeGameSettings(gameModeOptions gameMode,Board[] gameBoardInput ) throws Exception;
    abstract public void initializeNewGameSettings() throws Exception;
    abstract public Board initializeLoadedGameSettings() throws Exception;
    abstract public void alertFailedInitalize();
    abstract public void gameIteration() throws Exception;
    abstract public boolean reciveFromUserThePlayerTypeInfo();
    abstract public String reciveFromUserThePlayerName(ArrayList<Player> allPlayersForNow);
    abstract public void printWelcomeAndGameDisclaimer();
    abstract public  boolean recieveFromUserNewGameAnswer();
    abstract public void printGameStatus(Board gameBoard, ArrayList<Player> playerList);
    /* printGameStatus is dependent on the following three functions in order to succeed:
      1. printBoardToScreen
      2. printSlotToScreen
      3. printPlayersStatusToScreen
    */
    abstract void printBoardToScreen(Board gameBoard, ArrayList<Player> players);
    abstract void printBoardSlotToScreen(BoardSlot boardSlot, ArrayList<Player> players);
    abstract void printPlayersStatusToScreen(ArrayList<Player> playerList);
    abstract public void printSaveSuccess();
    abstract public void printPlayerTurn(Player player);
    abstract public void printCubeInformation(short cubeValue);
    abstract void displayInvalidTurnInformation(String str);
    abstract void displayInvalidSoldierError();
    abstract public void printAskDetailsOfPlayerMsg(int i);
    abstract void printGameInstruction();
    abstract public void printXmlErrorException(XMLExceptions e);
    
    public short getBoardSize()
    {
        return boardSize;
    }
    
    public String getGameName()
    {
        return this.gameName;
    }
    
    public ArrayList<Player> getPlayersList()
    {
        return this.gamePlayers;
    }
    public short getNumOfPlayers()
    {
        return numOfPlayers;
    }
    
    public short getNumOfBoardMovementElements()
    {
        return numOfBoardMovementElements;
    }    
    
    public short getNumOfSoldiersNeededToWin()
    {
        return numOfSoldiersNeededToWin;
    }      
    
}
