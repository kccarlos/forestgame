package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.square.LandSquare;
import hk.edu.polyu.comp.comp2021.jungle.model.square.Square;
import hk.edu.polyu.comp.comp2021.jungle.model.square.TrapSquare;

/**
 * The type Elephant piece.
 */
public class ElephantPiece extends AnimalPieces {

    /**
     * Instantiates a new Elephant piece.
     *
     * @param player the player
     */
    public ElephantPiece(Player player)
    {
        super(8, "ELEPHANT" , player, "E");
    }


    @Override
    public boolean captureAnimal(Square sourceSquare, Square destSquare)
    {
        boolean captureSuccess = false;
        if((sourceSquare.getAnimal().getRank() >= destSquare.getAnimal().getRank() && !(RatPiece.class.isInstance(destSquare.getAnimal())) &&
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
