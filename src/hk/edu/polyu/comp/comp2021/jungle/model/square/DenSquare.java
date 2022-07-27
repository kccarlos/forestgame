package hk.edu.polyu.comp.comp2021.jungle.model.square;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.piece.AnimalPieces;

/**
 * The type Den square.
 */
public class DenSquare extends Square {
    private Player playerObj;

    /**
     * Instantiates a new Den square.
     *
     * @param animal         the animal
     * @param playerObj      the player obj
     * @param rowPosition    the row position
     * @param columnPosition the column position
     */
    public DenSquare(AnimalPieces animal, Player playerObj, int rowPosition, char columnPosition)
    {
        super(animal, rowPosition , columnPosition, '@');
        this.playerObj = playerObj;
    }

    /**
     * Get player obj player.
     *
     * @return the player
     */
    public Player getPlayerObj (){return this.playerObj;}

    /**
     * Sets player obj.
     *
     * @param playerObj the player obj
     */
    public void setPlayerObj(Player playerObj)
    {
        this.playerObj = playerObj;
    }


}

