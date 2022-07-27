package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.*;

import java.util.HashMap;

/**
 * The type Player.
 */
public class Player{
    private String name;
    private int playerID;
    private HashMap<String, AnimalPieces> playerPiece;
    private boolean winGame;

    /**
     * Instantiates a new Player.
     *
     * @param name     the name
     * @param playerID the player id
     */
    public Player(String name, int playerID)
    {
        this.name = name;
        this.playerID = playerID ;
        this.winGame = false;
        playerPiece = new HashMap<String, AnimalPieces>();
        playerPiece.put("ELEPHANT", new ElephantPiece(this));
        playerPiece.put("LION", new LionPiece(this));
        playerPiece.put("TIGER", new TigerPiece(this));
        playerPiece.put("LEOPARD", new LeopardPiece(this));
        playerPiece.put("WOLF", new WolfPiece(this));
        playerPiece.put("DOG", new DogPiece(this));
        playerPiece.put("CAT", new CatPiece(this));
        playerPiece.put("RAT", new RatPiece(this));
    }

    /**
     * Instantiates a new Player.
     *
     * @param name         the name
     * @param playerID     the player id
     * @param animalPieces the animal pieces
     */
    public Player(String name, int playerID, String[] animalPieces)
    {
        this.name = name;
        this.playerID = playerID;
        this.winGame = false;
        playerPiece = new HashMap<String, AnimalPieces>();
        for(String animal : animalPieces)
        {
            if(animal.equals("ELEPHANT")){ playerPiece.put("ELEPHANT", new ElephantPiece(this));}
            else if(animal.equals("LION")) { playerPiece.put("LION", new LionPiece(this));}
            else if(animal.equals("TIGER")) {playerPiece.put("TIGER", new TigerPiece(this)); }
            else if(animal.equals("LEOPARD")) {playerPiece.put("LEOPARD", new LeopardPiece(this)); }
            else if(animal.equals("WOLF")) {playerPiece.put("WOLF", new WolfPiece(this)); }
            else if(animal.equals("DOG")) {playerPiece.put("DOG", new DogPiece(this)); }
            else if(animal.equals("CAT")) {playerPiece.put("CAT", new CatPiece(this));}
            else if(animal.equals("RAT")) {playerPiece.put("RAT", new RatPiece(this));}
        }

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Get win game boolean.
     *
     * @return the boolean
     */
    public boolean getWinGame (){return this.winGame;}

    /**
     * Get player id int.
     *
     * @return the int
     */
    public int getPlayerID(){return this.playerID;}

    /**
     * Set win game.
     *
     * @param winGame the win game
     */
    public void setWinGame (boolean winGame){this.winGame = winGame;}

    /**
     * Gets piece number.
     *
     * @return the piece number
     */
    public int getPieceNumber()
    {
        return this.playerPiece.size();
    }

    /**
     * Remove piece animal pieces.
     *
     * @param animalType the animal type
     * @return the animal pieces
     */
    public AnimalPieces removePiece(String animalType)
    {
        AnimalPieces deletePiece = this.playerPiece.remove(animalType);
        return deletePiece;
    }


    /**
     * Gets piece.
     *
     * @param animalType the animal type
     * @return the piece
     */
    public AnimalPieces getPiece(String animalType)
    {
        return this.playerPiece.get(animalType);
    }

}
