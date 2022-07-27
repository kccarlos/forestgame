package hk.edu.polyu.comp.comp2021.jungle.model.square;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.AnimalPieces;

/**
 * The type Square.
 */
public abstract  class Square {
    private AnimalPieces animal ;
    private int rowPosition;
    private char columnPosition;
    private char symbol;

    /**
     * Instantiates a new Square.
     *
     * @param animal         the animal
     * @param rowPosition    the row position
     * @param columnPosition the column position
     * @param symbol         the symbol
     */
    public Square(AnimalPieces animal, int rowPosition, char columnPosition, char symbol)
    {
        this.animal = animal;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.symbol = symbol;
    }

    /**
     * Gets animal.
     *
     * @return the animal
     */
    public AnimalPieces getAnimal()
    {
        return this.animal;
    }

    /**
     * Sets animal.
     *
     * @param newAnimal the new animal
     * @return the animal
     */
    public AnimalPieces setAnimal(AnimalPieces newAnimal)
    {
        return this.animal = newAnimal;
    }

    /**
     * Gets symbol.
     *
     * @return the symbol
     */
    public char getSymbol()
    {
        return this.symbol;
    }

    /**
     * Gets row position.
     *
     * @return the row position
     */
    public int getRowPosition()
    {
        return this.rowPosition;
    }

    /**
     * Gets column position.
     *
     * @return the column position
     */
    public char getColumnPosition()
    {
        return this.columnPosition;
    }

}
