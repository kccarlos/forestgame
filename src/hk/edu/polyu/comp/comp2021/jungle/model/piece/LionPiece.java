package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.square.Square;

/**
 * The type Lion piece.
 */
public class LionPiece extends AnimalPieces {

    /**
     * Instantiates a new Lion piece.
     *
     * @param player the player
     */
    public LionPiece(Player player)
    {
        super(7, "LION" , player, "Li");
    }


    @Override
    public boolean isValidMovement(Player currPlayer, Square sourceSquare , Square destSquare)
    {
        boolean isValid = false;
        isValid = super.isValidMovement(currPlayer, sourceSquare , destSquare);
        if(!isValid)
        {
            if((sourceSquare.getRowPosition() == destSquare.getRowPosition() && sourceSquare.getColumnPosition() + 3 == destSquare.getColumnPosition() )||
                    (sourceSquare.getRowPosition() == destSquare.getRowPosition() && sourceSquare.getColumnPosition() - 3 == destSquare.getColumnPosition()) ||
                    (sourceSquare.getRowPosition() + 4 == destSquare.getRowPosition() && sourceSquare.getColumnPosition() == destSquare.getColumnPosition()) ||
                    (sourceSquare.getRowPosition() - 4 == destSquare.getRowPosition() && sourceSquare.getColumnPosition() == destSquare.getColumnPosition()))
            {
                if(isAroundRiver(sourceSquare.getRowPosition(), sourceSquare.getColumnPosition()))
                {
                    isValid = true;
                }
            }
        }
        return isValid;
    }


}
