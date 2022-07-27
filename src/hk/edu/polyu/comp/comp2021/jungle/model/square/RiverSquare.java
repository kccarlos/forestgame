package hk.edu.polyu.comp.comp2021.jungle.model.square;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.AnimalPieces;

/**
 * The type River square.
 */
public class RiverSquare extends Square {

    /**
     * Instantiates a new River square.
     *
     * @param animal         the animal
     * @param rowPosition    the row position
     * @param columnPosition the column position
     */
    public RiverSquare(AnimalPieces animal, int rowPosition, char columnPosition)
    {
        super(animal, rowPosition , columnPosition , '^');
    }



}
