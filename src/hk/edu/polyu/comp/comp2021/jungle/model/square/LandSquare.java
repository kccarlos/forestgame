package hk.edu.polyu.comp.comp2021.jungle.model.square;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.AnimalPieces;

/**
 * The type Land square.
 */
public class LandSquare extends Square {

    /**
     * Instantiates a new Land square.
     *
     * @param animal         the animal
     * @param rowPosition    the row position
     * @param columnPosition the column position
     */
    public LandSquare(AnimalPieces animal, int rowPosition, char columnPosition)
    {
        super(animal, rowPosition , columnPosition, ' ');
    }

}
