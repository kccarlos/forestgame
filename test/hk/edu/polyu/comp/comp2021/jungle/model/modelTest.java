package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.view.JungleGameConsole;
import hk.edu.polyu.comp.comp2021.jungle.controller.JungleGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class modelTest {
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

    @Test
    public void createNewPlayer() {

        String[] a = { "ELEPHANT", "LION", "TIGER", "LEOPARD", "WOLF", "DOG","CAT", "Rat" };

        Player player1 = new Player("Billy", 1);
        Player player2 = new Player("Karen", 2);
        Player player3 = new Player("Billy",3, a);
        Player player4 = new Player("Kate",4, a);


        assertEquals(player1.getName(),"Billy");
        assertEquals(player2.getName(),"Karen");
        assertEquals(player3.getName(),"Billy");
        assertEquals(player4.getName(),"Kate");
        assertEquals(player1.getPlayerID(),1);
        assertEquals(player2.getPlayerID(),2);
        assertEquals(player3.getPlayerID(),3);
        assertEquals(player4.getPlayerID(),4);

    }

    @Test
    public void testboard(){
        Board board = new Board();

        Player player1 = new Player("Billy", 1);
        Player player2 = new Player("Karen", 2);

        board.initNewGameBoard(player1,player2);
        List<String> jungleBoardState = new ArrayList<>();

        board.saveGameBoard();
        board.clearGameBoard();
        board.printGameBoard(player2);
        board.movePieceOnBoard(player1,"A1","A2");
        board.loadGameBoard(jungleBoardState,player1,player2);

    }

    // Case 1: Test the basic move
    @Test
    public void case1(){
        final String inputString = "open "+basePath.concat("/resources/case1.txt")+"\n"
                + "move A9 A8\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	C 	W 	 	Le 	 	R 	\n"
                +"2	T 	 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	C 	W 	 	Le 	 	R 	\n"
                +"2	T 	 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 2: Moving diagonally
    @Test
    public void case2(){
        final String inputString = "open "+basePath.concat("/resources/case2.txt")+"\n"
                + "move F2 F1\n"
                + "move A9 A8\n"
                + "move F1 E2\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	R'	D'	 	#	 	C'	 	\n"
                +"7	Li'	 	Le'	 	 	 	E'	\n"
                +"6	 	^	^	 	W'	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	T 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	 	 	R 	\n"
                +"2	 	 	 	#	D 	 	 	\n"
                +"1	 	C 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	R'	D'	 	#	 	C'	 	\n"
                +"7	Li'	 	Le'	 	 	 	E'	\n"
                +"6	 	^	^	 	W'	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	T 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	 	 	R 	\n"
                +"2	 	 	 	#	D 	 	 	\n"
                +"1	 	C 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	R'	D'	 	#	 	C'	 	\n"
                +"7	Li'	 	Le'	 	 	 	E'	\n"
                +"6	 	^	^	 	W'	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	T 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	 	 	R 	\n"
                +"2	 	 	 	#	D 	 	 	\n"
                +"1	 	C 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	R'	D'	 	#	 	C'	 	\n"
                +"7	Li'	 	Le'	 	 	 	E'	\n"
                +"6	 	^	^	 	W'	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	T 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	 	 	R 	\n"
                +"2	 	 	 	#	D 	 	 	\n"
                +"1	 	C 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 3 : Trying to move something other than piece
    @Test
    public void case3(){
        final String inputString = "open "+basePath.concat("/resources/case3.txt")+"\n"
                + "move C4 C5\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
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
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
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
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 4 : Testing the multiple steps
    @Test
    public void case4(){
        final String inputString = "open "+basePath.concat("/resources/case4.txt")+"\n"
                + "move A7 A4\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
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
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
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
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 5 : Try to walk to your own den
    @Test
    public void case5(){
        final String inputString = "open "+basePath.concat("/resources/case5.txt")+"\n"
                + "move G9 F9\n"
                + "move A1 B1\n"
                + "move F9 E9\n"
                + "move B1 C1\n"
                + "move E9 D9\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	Li'	 	#	@	T'	 	 	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	T 	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	T'	 	 	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	T 	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	T'	 	 	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	T 	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	T'	 	 	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	T 	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	T'	 	 	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	T 	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	T'	 	 	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	T 	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 6 : Test whether the rat is unable to catch in the river
    @Test
    public void case6(){
        final String inputString = "open "+basePath.concat("/resources/case6.txt")+"\n"
                + "move G3 F3\n"
                + "move G7 F7\n"
                + "move F3 F4\n"
                + "move C7 C8\n"
                + "move F4 F5\n"
                + "move E7 D7\n"
                + "move F5 F6\n"
                + "move A9 A8\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    @Test
    public void case6_1(){
        final String inputString = "open "+basePath.concat("/resources/case6.txt")+"\n"
                + "move F6 F7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	E'	 	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    @Test
    public void case6_2(){
        final String inputString = "open "+basePath.concat("/resources/case6.2.txt")+"\n"
                + "move A3 A4\n"
                + "move F7 G7\n"
                + "move F6 F7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	R 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    @Test
    public void case6_2_1(){
        final String inputString = "open "+basePath.concat("/resources/case6.2.txt")+"\n"
                + "move G7 F7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    @Test
    public void case6_2_3(){
        final String inputString = "open "+basePath.concat("/resources/case6.2.txt")+"\n"
                + "move G9 F9\n"
                + "move G7 G8\n"
                + "move G7 G8\n"
                + "move F9 E9\n"
                + "move G8 G9\n"
                + "move D7 D8\n"
                + "move G9 F9\n"
                + "move A6 A5\n"
                + "move F9 E9\n"

                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	Le'	#	 	C'	 	\n"
                +"7	R'	 	 	W'	 	 	E'	\n"
                +"6	 	^	^	 	^	R 	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 8 : Test whether Lion and Tiger can jump over the rivers
    @Test
    public void case8(){
        final String inputString = "open "+basePath.concat("/resources/case8.txt")+"\n"
                + "move B3 B7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	C 	W 	 	Le 	 	R 	\n"
                +"2	 	 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	C 	W 	 	Le 	 	R 	\n"
                +"2	 	 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 9 : Can Lion and Tiger catch the animals after they jump over the rivers.
    @Test
    public void case9(){
        final String inputString = "open "+basePath.concat("/resources/case9.txt")+"\n"
                + "move B3 B7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	Le'	 	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	C 	W 	 	Le 	 	R 	\n"
                +"2	 	 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	Le'	 	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	C 	W 	 	Le 	 	R 	\n"
                +"2	 	 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Case 10 : Test whether Lion and Tiger can jump over the rivers while rat is in the river
    @Test
    public void case10(){
        final String inputString = "open "+basePath.concat("/resources/save.txt")+"\n"
                + "move A3 A4\n"
                + "move A7 B7\n"
                + "move A1 A2\n"
                + "move B7 B6\n"
                + "move A2 A3\n"
                + "move B6 B5\n"
                + "move A3 B3\n"
                + "move A9 A8\n"
                + "move B3 B7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
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
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	T 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	R'	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	T 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	R'	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	T 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	R'	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	T 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	R'	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	T 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	R'	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	T 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	 	#	 	C'	 	\n"
                +"7	 	T 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	R'	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"Exit the current Jungle Board Game.\n";

        assertEquals(outString, getOutput());
    }

    // Case 11 : Test the trap
    @Test
    public void case11(){
        final String inputString = "open "+basePath.concat("/resources/case11.txt")+"\n"
                + "move E8 D8\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	Li'	 	#	@	#	C'	T'	\n"
                +"8	 	 	 	T 	W'	 	 	\n"
                +"7	R'	D'	Le'	 	 	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	C 	W 	 	 	D 	R 	\n"
                +"2	 	 	 	#	Le 	 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	C'	T'	\n"
                +"8	 	 	 	W'	 	 	 	\n"
                +"7	R'	D'	Le'	 	 	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	C 	W 	 	 	D 	R 	\n"
                +"2	 	 	 	#	Le 	 	 	\n"
                +"1	 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";

        assertEquals(outString, getOutput());
    }
    // Case 12: Test whether elephant can catch the animals
    @Test
    public void case12(){
        final String inputString = "open "+basePath.concat("/resources/save.txt")+"\n"
                + "move A3 A4\n"
                + "move A7 B7\n"
                + "move A4 A5\n"
                + "move A9 B8\n"
                + "move A8 A7\n"
                + "move A7 B7\n"
                + "move A7 A6\n"
                + "move A6 A7\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
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
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	R'	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	E 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	 	 	W 	 	Le 	 	R 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Test14: Test whether the mice can eat elephant or not
    @Test
    public void case13(){
        final String inputString = "open "+basePath.concat("/resources/save.txt")+"\n"
                + "move G3 G4\n"
                + "move G7 G6\n"
                + "move G4 G5\n"
                + "move G9 G8\n"
                + "move G5 G6\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
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
                +"4	 	^	^	 	^	^	R 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	 	\n"
                +"6	 	^	^	 	^	^	E'	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	R 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	R'	 	Le'	 	W'	 	 	\n"
                +"6	 	^	^	 	^	^	E'	\n"
                +"5	 	^	^	 	^	^	R 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	 	\n"
                +"8	 	D'	 	#	 	C'	T'	\n"
                +"7	R'	 	Le'	 	W'	 	 	\n"
                +"6	 	^	^	 	^	^	E'	\n"
                +"5	 	^	^	 	^	^	R 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	Li'	 	#	@	#	 	 	\n"
                +"8	 	D'	 	#	 	C'	T'	\n"
                +"7	R'	 	Le'	 	W'	 	 	\n"
                +"6	 	^	^	 	^	^	R 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 2 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    // Test14: Test whether the mice can enter the Den square
    @Test
    public void case14(){
        final String inputString = "open "+basePath.concat("/resources/case14.txt")+"\n"
                + "move D8 D9\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	 	 	#	@	#	 	T'	\n"
                +"8	Li'	D'	 	R 	W'	C'	 	\n"
                +"7	R'	 	Le'	 	 	 	 	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command is completed.\n"
                +"The current board state will be printed as follows: \n"
                +"9	 	 	#	R 	#	 	T'	\n"
                +"8	Li'	D'	 	#	W'	C'	 	\n"
                +"7	R'	 	Le'	 	 	 	 	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	 	^	^	 	^	^	 	\n"
                +"4	 	^	^	 	^	^	 	\n"
                +"3	E 	 	W 	 	Le 	 	 	\n"
                +"2	 	C 	 	#	 	D 	 	\n"
                +"1	T 	 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"The winner is Player 1 : Carlos\n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }

    /**
     * Case 15 : Test whether the elephant can eat rat or not
     */
    @Test
    public void case15(){
        final String inputString = "open "+basePath + ("/resources/case15.txt")+"\n"
                + "move A4 A5\n"
                + "exit\n";
        provideInput(inputString);
        JungleGame junleGame = new JungleGame();
        JungleGameConsole console = new JungleGameConsole(junleGame);
        console.startConsole();
        final String outString = "Input : The current Jungle board game is already loaded from your specific location.\n"
                +"The loaded Jungle Game Board : \n"
                +"\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	R'	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	C 	W 	 	Le 	 	R 	\n"
                +"2	 	 	 	#	 	D 	 	\n"
                +"1	 	T 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"The movement command cannot completed.\n"
                +"Please try again or enter the new movement command.\n"
                +"9	Li'	 	#	@	#	 	T'	\n"
                +"8	 	D'	 	#	 	C'	 	\n"
                +"7	 	 	Le'	 	W'	 	E'	\n"
                +"6	 	^	^	 	^	^	 	\n"
                +"5	R'	^	^	 	^	^	 	\n"
                +"4	E 	^	^	 	^	^	 	\n"
                +"3	 	C 	W 	 	Le 	 	R 	\n"
                +"2	 	 	 	#	 	D 	 	\n"
                +"1	 	T 	#	@	#	 	Li 	\n"
                +"\n"
                +"	A	B	C	D	E	F	G	\n"
                +"Player 1 input's : \n"
                +"Exit the current Jungle Board Game.\n";
        assertEquals(outString, getOutput());
    }


}