package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.square.DenSquare;
import hk.edu.polyu.comp.comp2021.jungle.model.square.Square;

/**
 * The type Animal pieces.
 */
public abstract class AnimalPieces implements Movement, Capture {

    private int rank;
    private String piecesName;
    private Player player;

    private String abbreviation;

    /**
     * Instantiates a new Animal pieces.
     *
     * @param rank         the rank
     * @param piecesName   the pieces name
     * @param player       the player
     * @param abbreviation the abbreviation
     */
    AnimalPieces(int rank, String piecesName, Player player, String abbreviation)
    {
        this.rank = rank;
        this.piecesName = piecesName;
        this.player = player;
        this.abbreviation = abbreviation;
    }


    /**
     * Gets rank.
     *
     * @return the rank
     */
    public int getRank()
    {
        return this.rank;
    }

    /**
     * Get abbreviation string.
     *
     * @return the string
     */
    public String getAbbreviation(){
        return this.abbreviation;
    }


    /**
     * Gets pieces name.
     *
     * @return the pieces name
     */
    public String getPiecesName()
    {
        return this.piecesName;
    }


    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer()
    {
        return this.player;
    }


    @Override
    public boolean moveToBoardDestination(Player currPlayer, Square sourceSquare, Square destSquare)
    {
        boolean movementSuccess = false;
        // Check any animal is contained by the destination's square or not.
        if(!DenSquare.class.isInstance(destSquare))
        {
            if(destSquare.getAnimal() != null)
            {
                if(destSquare.getAnimal().getPlayer() != currPlayer)
                {
                    movementSuccess = captureAnimal(sourceSquare, destSquare);
                }

            }else
            {
                destSquare.setAnimal(sourceSquare.getAnimal());
                sourceSquare.setAnimal(null);
                movementSuccess = true;
            }
        }else
        {
            if(sourceSquare.getAnimal().getPlayer() != ((DenSquare) destSquare).getPlayerObj())
            {
                destSquare.setAnimal(sourceSquare.getAnimal());
                sourceSquare.setAnimal(null);
                currPlayer.setWinGame(true);
                movementSuccess = true;
            }
        }
        return movementSuccess;
    }
}
