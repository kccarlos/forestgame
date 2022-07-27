package hk.edu.polyu.comp.comp2021.jungle.model.square;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.piece.AnimalPieces;

/**
 * The type Trap square.
 */
public class TrapSquare extends Square {

    /**
     * Instantiates a new Trap square.
     *
     * @param animal         the animal
     * @param rowPosition    the row position
     * @param columnPosition the column position
     */
    public TrapSquare(AnimalPieces animal, int rowPosition, char columnPosition)
    {
        super(animal, rowPosition , columnPosition , '#');

    }

}
