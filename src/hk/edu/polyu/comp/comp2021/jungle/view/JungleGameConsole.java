package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.controller.JungleGame;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Jungle game console.
 */
public class JungleGameConsole {

    private JungleGame jungleGame;
    private boolean exitGame;
    private boolean gameStated;

    /**
     * The enum Command.
     */
    public enum Command
    {
        /**
         * Cmd start command.
         */
        CMD_START("start"),
        /**
         * Cmd move command.
         */
        CMD_MOVE("move"),
        /**
         * Cmd save command.
         */
        CMD_SAVE("save"),
        /**
         * Cmd opne command.
         */
        CMD_OPNE("open"),
        /**
         * Cmd exit command.
         */
        CMD_EXIT("exit");

        private String commandValue;

        Command (String commandValue)
        {
            this.commandValue = commandValue;
        }

        /**
         * Gets command value.
         *
         * @return the command value
         */
        public String getCommandValue()
        {
            return this.commandValue;
        }

        /**
         * Gets by user string input.
         *
         * @param commandValue the command value
         * @return the by user string input
         */
        public static Command getByUserStringInput(String commandValue)
        {
            for(Command cmdvalue : Command.values())
            {
                if(cmdvalue.getCommandValue().equalsIgnoreCase(commandValue.toLowerCase()))
                {
                    return cmdvalue;
                }
            }
            return null;
        }
    }


    /**
     * Instantiates a new Jungle game console.
     *
     * @param jungleGame the jungle game
     */
    public JungleGameConsole(JungleGame jungleGame)
    {
        this.jungleGame = jungleGame;
    }

    /**
     * Check exit boolean.
     *
     * @return the boolean
     */
    public boolean checkExit()
    {
        return this.exitGame;
    }

    /**
     * Exit.
     */
    public void exit()
    {
        this.exitGame = true;
    }

    /**
     * Check game started boolean.
     *
     * @return the boolean
     */
    public boolean checkGameStarted()
    {
        return this.gameStated;
    }

    /**
     * Reinitialize game board.
     */
    public void reinitializeGameBoard()
    {
        this.jungleGame = new JungleGame();
    }

    /**
     * Start console.
     */
    public void startConsole()
    {
        Scanner scanUserInput = new Scanner(System.in);
        String userInput = "";

        while(!checkExit())
        {
            jungleGame.isWinGame();
            switch(jungleGame.getCurrentGameState())
            {
                case PRESTART:
                    System.out.print("Input : ");
                    break;
                case PLAYER1MOVE:
                    System.out.println("Player 1 input's : ");
                    break;
                case PLAYER2MOVE:
                    System.out.println("Player 2 input's : ");
                    break;
                case PLAYER1WIN:
                    System.out.println("The winner is Player 1 : " + jungleGame.getWinnerName());
                    exit();
                    break;
                case PLAYER2WIN:
                    System.out.println("The winner is Player 2 : " + jungleGame.getWinnerName());
                    exit();
                    break;
            }
            if(!checkExit())
            {
                if(scanUserInput.hasNext())
                {
                    userInput = scanUserInput.nextLine().trim();
                }

                if(userInput.isEmpty())
                {
                    continue;
                }else
                {
                    executeCommand(userInput, scanUserInput);
                }
            }
        }
        scanUserInput.close();
        System.out.println("Exit the current Jungle Board Game.");
    }


    private void executeCommand(String userInput, Scanner scanUserInput)
    {
        String[] userInputArray = userInput.split(" ");
        Command usrCommand = Command.getByUserStringInput(userInputArray[0]);
        if(usrCommand != null)
        {
            switch(usrCommand)
            {
                case CMD_START:
                    if(!gameStated)
                    {
                        System.out.println("Please enter the name for Player 1 : ");
                        String player1Name = scanUserInput.nextLine();
                        if(jungleGame.createNewPlayer(player1Name, false))
                        {
                            System.out.printf("Player 1 (%s) is created.\n", player1Name);
                        }
                        System.out.println("Please enter the name for Player 2 : ");
                        String player2Name = scanUserInput.nextLine();
                        if(jungleGame.createNewPlayer(player2Name, true))
                        {
                            System.out.printf("Player 2 (%s) is created.\n", player2Name);
                        }
                        System.out.println("===============================================");
                        System.out.printf("The Player 1 name : %s\tThe Player 2 name : %s\n", player1Name,player2Name);
                        System.out.println("The following symbols are representing the pieces and squares of Jungle Board Game in the Command Prompt:");
                        System.out.println("Pieces: ");
                        System.out.println("E : Elephant \t Li : Lion \t T : Tiger \t Le : Leopard");
                        System.out.println("W : Wolf \t\t D : Dog \t C : Cat \t R : Rat");
                        System.out.println("Square: ");
                        System.out.println("# : Trap \t @ : Den \t ^ : river");
                        System.out.println("P.S. \"'\", behind the symbol is representing the piece for Player 2. ");
                        System.out.println("The new Jungle Game Board is created : \n");
                        System.out.println(jungleGame.getStringGameBoard());
                        this.gameStated = true;
                    }else
                    {
                        System.out.println("Do you want to restart the jungle game? (Y/N)");
                        String reStart = scanUserInput.nextLine();
                        while ((reStart.length() > 1 || reStart.length() < 1) || (!reStart.equals("Y") && !reStart.equals("N")))                        {
                            System.out.println("Your input is invalid. Please enter 'Y' or 'N' for permitting the restart of Jungle Game.");
                            reStart = scanUserInput.nextLine();
                        }
                        if(reStart.equals("Y"))
                        {
                            this.reinitializeGameBoard();
                            this.gameStated = false;
                        }
                    }
                    break;
                case CMD_MOVE:
                    if(!checkGameStarted())
                    {
                        System.out.println("The jungle game is not yet started. \nPlease use command 'start' or 'load' to create the new Jungle Game or load the previous game.");
                    }else
                    {
                        //Check the length of same input value.
                        if(userInputArray.length != 3 )
                        {
                            System.out.println("The movement command is not valid. \nPlease enter the new movement command again. e.g move A1 A2");

                        }else
                        {
                            if(!jungleGame.checkInputSquareExit(userInputArray[1]))
                            {
                                System.out.println("The inputted square("+userInputArray[1]+") is/are not exist in the Jungle game board. \nPlease enter the new movement command again.");
                            }else if(!jungleGame.checkInputSquareExit(userInputArray[2]))
                            {
                                System.out.println("The inputted square("+userInputArray[2]+") is/are not exist in the Jungle game board. \nPlease enter the new movement command again.");
                            }else
                            {
                                if(jungleGame.moveAnimalOnBoard(userInputArray[1], userInputArray[2]))
                                {
                                    System.out.println("The movement command is completed.\nThe current board state will be printed as follows: ");

                                }else
                                {
                                    System.out.println("The movement command cannot completed.\nPlease try again or enter the new movement command.");
                                }
                            }
                        }
                        System.out.println(jungleGame.getStringGameBoard());
                    }
                    break;
                case CMD_SAVE:
                    if(gameStated) {
                        if (userInputArray.length < 2) {
                            System.out.println("The save command is not valid. \nPlease enter the new save command again. e.g save C:\\Users\\Username\\Desktop\\");
                        } else {
                            //Check folder exist
                            Path saveFolderPath = Paths.get(String.join(" ",Arrays.copyOfRange(userInputArray,1,userInputArray.length)));
                            if (!Files.exists(saveFolderPath.getParent()!=null? saveFolderPath.getParent(): saveFolderPath)) {
                                System.out.println("The inputted location for saving Jungle Game State does not exist.\nPlease enter the save command again.");
                            } else {
                                if (jungleGame.saveJungleGame(saveFolderPath)) {
                                    System.out.println("The current Jungle board game is already saved into your specific location.");
                                }
                            }
                        }
                    }else
                    {
                        System.out.println("The Jungle Board Game is not started yet. Please type 'start' command to start the game first.");
                    }
                    break;
                case CMD_OPNE:
                    if(userInputArray.length < 2)
                    {
                        System.out.println("The open command is not valid. \nPlease enter the new open command again. e.g open C:\\Users\\Username\\Desktop\\");
                    }else
                    {
                        //Check folder exist
                        Path openFolderPath = Paths.get(String.join(" ",Arrays.copyOfRange(userInputArray,1,userInputArray.length)));
                        if(!Files.isRegularFile(openFolderPath))
                        {
                            System.out.println("The inputted location for opening Jungle Game State does not exist.\nPlease enter the open command again.");
                        }else
                        {
                            if(jungleGame.loadJungleGame(openFolderPath))
                            {
                                System.out.println("The current Jungle board game is already loaded from your specific location.");
                                System.out.println("The loaded Jungle Game Board : \n");
                                System.out.println(jungleGame.getStringGameBoard());
                                this.gameStated = true;
                            }
                            else
                            {
                                System.out.println("The current game is not saved yet/ There is a damage of your save file.\nPlease use the 'save' command to save your current Jungle game first.");
                            }
                        }
                    }

                    break;
                case CMD_EXIT:
                    exit();
                    break;
            }
        }else
        {
            System.out.println("The input command is not valid.");
        }
    }





}
