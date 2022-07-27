package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.square.RiverSquare;
import hk.edu.polyu.comp.comp2021.jungle.model.square.Square;
import hk.edu.polyu.comp.comp2021.jungle.model.square.TrapSquare;

/**
 * The interface Movement.
 */
public interface Movement {

    /**
     * Is valid movement boolean.
     *
     * @param currPlayer   the curr player
     * @param sourceSquare the source square
     * @param destSquare   the dest square
     * @return the boolean
     */
    default boolean isValidMovement(Player currPlayer, Square sourceSquare , Square destSquare)
    {
        boolean isValid = false;
        if((sourceSquare.getRowPosition()+ 1 == destSquare.getRowPosition() &&  sourceSquare.getColumnPosition() == destSquare.getColumnPosition() )||
                (sourceSquare.getRowPosition() - 1 == destSquare.getRowPosition() &&  sourceSquare.getColumnPosition() == destSquare.getColumnPosition() )||
                (sourceSquare.getRowPosition() == destSquare.getRowPosition() &&  sourceSquare.getColumnPosition() + 1 == destSquare.getColumnPosition() )||
                (sourceSquare.getRowPosition() == destSquare.getRowPosition() &&  sourceSquare.getColumnPosition() - 1 == destSquare.getColumnPosition() ))
        {
            if(!RiverSquare.class.isInstance(destSquare))
            {
                isValid =true;
            }
        }
        return isValid;
    }

    /**
     * Is around river boolean.
     *
     * @param sourceRow the source row
     * @param sourceCol the source col
     * @return the boolean
     */
    default Boolean isAroundRiver(int sourceRow , char sourceCol)
    {
        boolean result = false;
        switch (sourceCol)
        {
            case 'A':
            case 'D':
            case 'G':
                result = sourceRow >= 4 && sourceRow <=6;
                break;
            case 'B':
            case 'C':
            case 'E':
            case 'F':
                result = sourceRow == 3 || sourceRow == 7;
                break;
        }
        return result;
    }



    /**
     * Move to board destination boolean.
     *
     * @param currPlayer   the curr player
     * @param sourceSquare the source square
     * @param destSquare   the dest square
     * @return the boolean
     */
    boolean moveToBoardDestination(Player currPlayer, Square sourceSquare , Square destSquare);



}
