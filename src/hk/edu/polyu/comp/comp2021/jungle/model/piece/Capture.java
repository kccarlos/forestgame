package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.square.LandSquare;
import hk.edu.polyu.comp.comp2021.jungle.model.square.Square;
import hk.edu.polyu.comp.comp2021.jungle.model.square.TrapSquare;

/**
 * The interface Capture.
 */
public interface Capture {

    /**
     * Capture animal boolean.
     *
     * @param sourceSquare the source square
     * @param destSquare   the dest square
     * @return the boolean
     */
    default boolean captureAnimal(Square sourceSquare, Square destSquare)
    {
        boolean captureSuccess = false;
        if((sourceSquare.getAnimal().getRank() >= destSquare.getAnimal().getRank() &&
                (LandSquare.class.isInstance(sourceSquare) && LandSquare.class.isInstance(destSquare))) ||
                TrapSquare.class.isInstance(destSquare) )
        {

            destSquare.getAnimal().getPlayer().removePiece(destSquare.getAnimal().getPiecesName());
            destSquare.setAnimal(sourceSquare.getAnimal());
            sourceSquare.setAnimal(null);
            captureSuccess = true;
        }


        return captureSuccess;

    }


}
