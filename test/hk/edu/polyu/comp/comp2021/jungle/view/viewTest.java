package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.controller.JungleGame;
import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class viewTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    private final String basePath = System.getProperty("user.dir");

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }


    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * Test the exit "command" in order to terminate the current Jungle Board Game's Command Console.
     */
    @Test
    public void exit() {
        final String inputString = "exit\n";
        provideInput(inputString);
        JungleGame jungleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(jungleGame);
        console.startConsole();
        final String outString = "Input : Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    /**
     * Test the terminate situation after Player 2 is the winner in the current round of Jungle game.
     */
    @Test
    public void player2Win() {
        final String inputString = "hello\n"
                + "move\n"
                + "\n"
                + "save\n"
                + "start\n"
                + "Carlos\n"
                + "Lambert\n"
                + "move\n"
                + "move Z1 Z2\n"
                + "move A1 Z2\n"
                + "move A1 A3\n"
                + "move A1 A2\n"
                + "save\n"
                + "save " + basePath.concat("/wrongFolder/wrongFile.txt") + "\n"
                + "save " + basePath.concat("/resources/disposed.txt") + "\n"
                + "start\n"
                + "h\n"
                + "N\n"
                + "start\n"
                + "Y\n"
                + "open\n"
                + "open " + basePath.concat("/wrongFolder/wrongFile.txt") + "\n"
                + "open " + basePath.concat("/resources/player2win.txt") + "\n"
                + "move D2 D1\n";
        provideInput(inputString);
        JungleGame jungleGame2 = new JungleGame();
        JungleGameConsole console2 = new JungleGameConsole(jungleGame2);
        console2.startConsole();
        final String outString = "Input : The input command is not valid.\n"
                + "Input : The jungle game is not yet started. \n"
                + "Please use command 'start' or 'load' to create the new Jungle Game or load the previous game.\n"
                + "Input : Input : The Jungle Board Game is not started yet. Please type 'start' command to start the game first.\n"
                + "Input : Please enter the name for Player 1 : \n"
                + "Player 1 (Carlos) is created.\n"
                + "Please enter the name for Player 2 : \n"
                + "Player 2 (Lambert) is created.\n"
                + "===============================================\n"
                + "The Player 1 name : Carlos	The Player 2 name : Lambert\n"
                + "The following symbols are representing the pieces and squares of Jungle Board Game in the Command Prompt:\n"
                + "Pieces: \n"
                + "E : Elephant 	 Li : Lion 	 T : Tiger 	 Le : Leopard\n"
                + "W : Wolf 		 D : Dog 	 C : Cat 	 R : Rat\n"
                + "Square: \n"
                + "# : Trap 	 @ : Den 	 ^ : river\n"
                + "P.S. \"'\", behind the symbol is representing the piece for Player 2. \n"
                + "The new Jungle Game Board is created : \n"
                + "\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 1 input's : \n"
                + "The movement command is not valid. \n"
                + "Please enter the new movement command again. e.g move A1 A2\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 1 input's : \n"
                + "The inputted square(Z1) is/are not exist in the Jungle game board. \n"
                + "Please enter the new movement command again.\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 1 input's : \n"
                + "The inputted square(Z2) is/are not exist in the Jungle game board. \n"
                + "Please enter the new movement command again.\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 1 input's : \n"
                + "The movement command cannot completed.\n"
                + "Please try again or enter the new movement command.\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 1 input's : \n"
                + "The movement command is completed.\n"
                + "The current board state will be printed as follows: \n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	T 	C 	 	#	 	D 	 	\n"
                + "1	 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 2 input's : \n"
                + "The save command is not valid. \n"
                + "Please enter the new save command again. e.g save C:\\Users\\Username\\Desktop\\\n"
                + "Player 2 input's : \n"
                + "The inputted location for saving Jungle Game State does not exist.\n"
                + "Please enter the save command again.\n"
                + "Player 2 input's : \n"
                + "The current Jungle board game is already saved into your specific location.\n"
                + "Player 2 input's : \n"
                + "Do you want to restart the jungle game? (Y/N)\n"
                + "Your input is invalid. Please enter 'Y' or 'N' for permitting the restart of Jungle Game.\n"
                + "Player 2 input's : \n"
                + "Do you want to restart the jungle game? (Y/N)\n"
                + "Input : The open command is not valid. \n"
                + "Please enter the new open command again. e.g open C:\\Users\\Username\\Desktop\\\n"
                + "Input : The inputted location for opening Jungle Game State does not exist.\n"
                + "Please enter the open command again.\n"
                + "Input : The current Jungle board game is already loaded from your specific location.\n"
                + "The loaded Jungle Game Board : \n"
                + "\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	 	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	T 	C 	 	Le'	 	D 	 	\n"
                + "1	 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 2 input's : \n"
                + "The movement command is completed.\n"
                + "The current board state will be printed as follows: \n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	 	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	Le 	 	R 	\n"
                + "2	T 	C 	 	#	 	D 	 	\n"
                + "1	 	 	#	Le'	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "The winner is Player 2 : Lambert\n"
                + "Exit the current Jungle Board Game.\n";


        assertEquals(outString, getOutput());
    }

    /**
     * Test the terminate situation after Player 1 is the winner in the current round of Jungle game.
     */
    @Test
    public void player1Win() {
        final String inputString = "open " + basePath.concat("/resources/player1win.txt") + "\n"
                + "move D8 D9\n";
        provideInput(inputString);
        JungleGame jungleGame2 = new JungleGame();
        JungleGameConsole console2 = new JungleGameConsole(jungleGame2);
        console2.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                + "The loaded Jungle Game Board : \n"
                + "\n"
                + "9	Li'	 	#	@	#	 	T'	\n"
                + "8	 	D'	 	Le 	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "Player 1 input's : \n"
                + "The movement command is completed.\n"
                + "The current board state will be printed as follows: \n"
                + "9	Li'	 	#	Le 	#	 	T'	\n"
                + "8	 	D'	 	#	 	C'	 	\n"
                + "7	R'	 	Le'	 	W'	 	E'	\n"
                + "6	 	^	^	 	^	^	 	\n"
                + "5	 	^	^	 	^	^	 	\n"
                + "4	 	^	^	 	^	^	 	\n"
                + "3	E 	 	W 	 	 	 	R 	\n"
                + "2	 	C 	 	#	 	D 	 	\n"
                + "1	T 	 	#	@	#	 	Li 	\n"
                + "\n"
                + "	A	B	C	D	E	F	G	\n"
                + "The winner is Player 1 : Carlos\n"
                + "Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    @Test
    public void withoutSaved() {
        final String inputString = "start\n"
                + "Carlos\n"
                + "Lambert\n"
                + "move A1 A2\n"
                + "open " + basePath.concat("/resources/player1win.txt") + "\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame jungleGame2 = new JungleGame();
        JungleGameConsole console2 = new JungleGameConsole(jungleGame2);
        console2.startConsole();
        final String outString = "Input : Please enter the name for Player 1 : \n"
                +"Player 1 (Carlos) is created.\n"
                +"Please enter the name for Player 2 : \n"
                +"Player 2 (Lambert) is created.\n"
                +"===============================================\n"
                +"The Player 1 name : Carlos	The Player 2 name : Lambert\n"
                +"The following symbols are representing the pieces and squares of Jungle Board Game in the Command Prompt:\n"
                +"Pieces: \n"
                +"E : Elephant 	 Li : Lion 	 T : Tiger 	 Le : Leopard\n"
                +"W : Wolf 		 D : Dog 	 C : Cat 	 R : Rat\n"
                +"Square: \n"
                +"# : Trap 	 @ : Den 	 ^ : river\n"
                +"P.S. \"'\", behind the symbol is representing the piece for Player 2. \n"
                +"The new Jungle Game Board is created : \n"
                +"\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	T 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The current game is not saved yet/ There is a damage of your save file.\n"
                +"Please use the 'save' command to save your current Jungle game first.\n"
                +"Player 2 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }
}