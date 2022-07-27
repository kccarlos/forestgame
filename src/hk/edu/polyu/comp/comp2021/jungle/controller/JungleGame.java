package hk.edu.polyu.comp.comp2021.jungle.controller;


import hk.edu.polyu.comp.comp2021.jungle.model.Board;
import hk.edu.polyu.comp.comp2021.jungle.model.GameState;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Jungle game.
 */
public class JungleGame {
    private Board board;
    private Player player1;
    private Player player2;
    private GameState currentGameState;
    private boolean isSavedFile ;
    private static final int CONVERT_ASCII_CHAR = 65;


    /**
     * Instantiates a new Jungle game.
     */
    public JungleGame()
    {
        this.currentGameState = GameState.PRESTART;
        this.board = new Board();
        this.isSavedFile = false;
    }

    /**
     * Gets current game state.
     *
     * @return the current game state
     */
    public GameState getCurrentGameState()
    {
        return this.currentGameState;
    }

    private void setCurrentGameState(GameState nextGameState)
    {
        this.currentGameState = nextGameState;
    }

    /**
     * Create new player boolean.
     *
     * @param playerName the player name
     * @param isPlayer2 true for player 2, false for player 1
     * @return the boolean
     */
    public boolean createNewPlayer(String playerName, boolean isPlayer2)
    {
        boolean createNewPlayerSuccess = false;
        if(isPlayer2  && player2 == null)
        {
            this.player2 = new Player(playerName, 2);
            createNewPlayerSuccess = true;
        }else if(player1 == null){
            this.player1 = new Player(playerName, 1);
            createNewPlayerSuccess = true;
        }

        if(player1 != null && player2 != null)
        {
            board.initNewGameBoard(player1,player2);
            setCurrentGameState(GameState.PLAYER1MOVE);
        }


        return createNewPlayerSuccess;
    }


    /**
     * Is win game game state.
     *
     * @return the game state
     */
    public GameState isWinGame()
    {
        if(this.player1 != null && this.player2 != null)
        {
            if(this.player1.getWinGame() || this.player2.getWinGame() )
            {
                setCurrentGameState(this.player1.getWinGame()  ? GameState.PLAYER1WIN : GameState.PLAYER2WIN);
            }else if(this.player1.getPieceNumber() == 0)
            {
                setCurrentGameState(GameState.PLAYER2WIN);
            }else if(this.player2.getPieceNumber() == 0)
            {
                setCurrentGameState(GameState.PLAYER1WIN);
            }
        }
        return this.currentGameState;
    }

    /**
     * Gets winner name.
     *
     * @return the winner name
     */
    public String getWinnerName()
    {
        if(this.player1.getWinGame() )
        {
            return this.player1.getName();
        }
        if(this.player2.getWinGame() )
        {
            return this.player2.getName();
        }
        return null;
    }

    /**
     * Move animal on board boolean.
     *
     * @param sourceLocation the source location
     * @param destLocation   the dest location
     * @return the boolean
     */
    public boolean moveAnimalOnBoard(String sourceLocation, String destLocation)
    {
        boolean movementSuccess;
        movementSuccess =  this.board.movePieceOnBoard(currentGameState == GameState.PLAYER1MOVE ? player1 : player2 , sourceLocation, destLocation);
        if(movementSuccess)
        {
            if(currentGameState == GameState.PLAYER1MOVE)
            {
                setCurrentGameState(GameState.PLAYER2MOVE);
            }else
            {
                setCurrentGameState(GameState.PLAYER1MOVE);
            }
        }
        return movementSuccess;
    }

    /**
     * Save jungle game boolean.
     *
     * @param filePath the file path
     * @return the boolean
     */
    public boolean saveJungleGame(Path filePath) {
        boolean saveJungleSuccess;
        List<String> jungleBoardState = new ArrayList<>();
        jungleBoardState.add(player1.getName() + (getCurrentGameState()==GameState.PLAYER1MOVE?"*":""));
        jungleBoardState.add(player2.getName()+ (getCurrentGameState()==GameState.PLAYER2MOVE?"*":""));
        jungleBoardState.addAll(this.board.saveGameBoard());

        try (PrintWriter saveFileOp = new PrintWriter((Paths.get(filePath.toString())).toString())) {
            for (String boardString : jungleBoardState)
                saveFileOp.println(boardString);
            saveJungleSuccess = true;
            this.isSavedFile = true;
        }catch (FileNotFoundException e)
        {
            saveJungleSuccess = false;
        }
        return saveJungleSuccess;
    }

    /**
     * Load jungle game boolean.
     *
     * @param filePath the file path
     * @return the boolean
     */
    public boolean loadJungleGame(Path filePath){
        boolean loadJungleGameSuccess = false;
        if(isSavedFile || (this.player2 == null && this.player1==null ) ) {
            try {
                Path loadFilePath = (Paths.get(filePath.toString()));
                List<String> jungleBoardState = Files.readAllLines(loadFilePath);

                String player1Info = jungleBoardState.remove(0);
                String player2Info = jungleBoardState.remove(0);
                if (player1Info.substring(player1Info.length() - 1).equals("*")) {
                    setCurrentGameState(GameState.PLAYER1MOVE);
                    player1Info = player1Info.substring(0, player1Info.length()-1);
                } else {
                    setCurrentGameState(GameState.PLAYER2MOVE);
                    player2Info = player2Info.substring(0, player2Info.length()-1);
                }
                List<String> p1animalPiecesList = new ArrayList<>();
                List<String> p2animalPiecesList = new ArrayList<>();

                for (String boardState : jungleBoardState) {
                    String[] recordArray = boardState.split(" ");
                    if (recordArray[0].equals("1")) {
                        p1animalPiecesList.add(recordArray[2]);

                    } else if (recordArray[0].equals("2")) {
                        p2animalPiecesList.add(recordArray[2]);
                    }

                }

                this.player1 = new Player(player1Info, 1, p1animalPiecesList.toArray(new String[p1animalPiecesList.size()]));
                this.player2 = new Player(player2Info, 2, p2animalPiecesList.toArray(new String[p2animalPiecesList.size()]));
                loadJungleGameSuccess = this.board.loadGameBoard(jungleBoardState, this.player1, this.player2);
            } catch (IOException | NullPointerException e) {
                loadJungleGameSuccess = false;
            }
        }
        return loadJungleGameSuccess;
    }

    /**
     * Check input square exit boolean.
     *
     * @param squareString the square string
     * @return the boolean
     */
    public boolean checkInputSquareExit(String squareString)
    {
        return this.board.isSquare(squareString);
    }


    /**
     * Gets string game board.
     *
     * @return the string game board
     */
    public String getStringGameBoard()
    {
        String[][] stringBoard = this.board.printGameBoard(this.player2);
        StringBuilder builder = new StringBuilder();

        for(int curRowIndex = 0; curRowIndex < stringBoard.length; curRowIndex ++)
        {
            builder.append(stringBoard.length - curRowIndex + "\t");

            for(int curColIndex = 0 ; curColIndex < stringBoard[curRowIndex].length; curColIndex ++)
            {
                builder.append(stringBoard[curRowIndex][curColIndex]);
            }
            builder.append("\n");
        }

        builder.append("\n\t");
        for(int curColIndex = 0; curColIndex < stringBoard[stringBoard.length - 1].length ; curColIndex++)
        {
            builder.append((char)(CONVERT_ASCII_CHAR+curColIndex) + "\t");
        }

        return builder.toString();
    }
}
