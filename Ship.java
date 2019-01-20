package battleship;

/**
 * Ship super class
 * Contains variables and methods that dictate the behaviour of all ships in the game
 * @author shwetachopra
 *
 */
public class Ship {

	//Creating instance variables
	
	/**
	 * Row in which ship's bow is positioned
	 */
	private int bowRow;
	/**
	 * Column in which ship's bow is positioned
	 */
	private int bowColumn;
	/**
	 * Length of the ship
	 */
	private int length;
	/**
	 * True/False value for whether ship is horizontal/vertical
	 */
	private boolean horizontal;
	/**
	 * Ship's hit array that records whether parts of the ship have been hit or not
	 */
	private boolean[] hit;

	//Define Default constructor
	/**
	 * Default constructor, initializes boolean hit array with false
	 */
	public Ship() {
		this.hit = new boolean [4];

	}

	//Defining methods

	//Getters

	/**
	 * Getter to access ship length
	 * @return length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Getter to access bow row
	 * @return bowRow
	 */
	public int getBowRow() {
		return this.bowRow;
	}

	/**
	 * Getter to access bow row
	 * @return bowColumn
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * Getter to access hit array
	 * @return hit
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * Getter to access whether ship is horizontal or not
	 * @return true/false
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}

	/**
	 * Getter to access ship type
	 * @return ""
	 */
	public String getShipType() {
		return "";
	}

	//Setters

	/**
	 * Set value of bowRow
	 * @param row number
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * Set value of bowColumn
	 * @param column number
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * Set value of horizontal
	 * @param horizontal orientation - True/False
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Set correct length value
	 * @param length - Length of ship in terms of cells
	 */
	public void setLength(int length) {
		this.length = length;
	}

	//Other methods

	/**
	 * Returns whether ship can legally be placed in this position
	 * @param row number
	 * @param column number
	 * @param horizontal - true/false
	 * @param ocean
	 * @return true/false
	 */
	boolean okToPlaceShipAt(int row, int column, boolean
			horizontal, Ocean ocean) {

		//Check for existing ships around
		for (int i = rowStart(row, column, horizontal); 
				i <= rowEnd(row, column, horizontal); 
				i++) {
			for (int j = colStart(row, column, horizontal); 
					j <= colEnd(row, column, horizontal); 
					j++) {
				if (ocean.isOccupied(i,j)) {
					return false;
				}
			}
		}

		//If ship would stretch out of ocean bounds, return false
		if (horizontal) {
			if (column + this.length > 10) {
				return false;
			}

			else {
				return true;
			}
		}

		else {
			if (row + this.length > 10) {
				return false;
			}

			else {
				return true;
			}
		}
	}

	/**
	 * Method to determine which row to start searching from
	 * for ship placement
	 * @param row number
	 * @param column number
	 * @param horizontal - true/false
	 * @return rowStart
	 */
	int rowStart(int row, int column, boolean horizontal) {
		return Math.max(row - 1, 0);
	}

	/**
	 * Method to determine which row to stop searching at
	 * for ship placement
	 * @param row number
	 * @param column number
	 * @param horizontal - true/false
	 * @return rowEnd
	 */
	int rowEnd(int row, int column, boolean horizontal) {

		if (horizontal) {
			return Math.min(row + 1, 9);
		}		

		else {
			return Math.min(row + this.length, 9);
		}
	}

	/**
	 * Method to determine which column to start searching from
	 * for ship placement
	 * @param row number
	 * @param column number
	 * @param horizontal - true/false
	 * @return colStart
	 */
	int colStart(int row, int column, boolean horizontal) {

		return Math.max(0, column - 1);		
	}

	/**
	 * Method to determine which column to stop searching at
	 * for ship placement
	 * @param row number
	 * @param column number
	 * @param horizontal - true/false
	 * @return colEnd
	 */
	int colEnd(int row, int column, boolean horizontal) {

		if (horizontal) {
			return Math.min(9, column + this.length);
		}		

		else {
			return Math.min(9, column + 1);
		}
	}



	/**
	 * Places a ship in the ocean
	 * @param row number
	 * @param column number
	 * @param horizontal - true/false
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean
			horizontal, Ocean ocean) {

		//Add position details to ship
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;
		//Add reference to ship in ocean
		if (horizontal) {
			for (int i = column; i < column + this.length; i++) {
				ocean.getShipArray()[row][i] = this;
			}
		}

		else {
			for (int i = row; i < row + this.length; i++) {
				ocean.getShipArray()[i][column] = this;
			}
		}
	}

	/**
	 * Method that records shot fired at a ship
	 * Records hit for the part of the ship fired at
	 * Returns true if an unsunken ship is hit, otherwise false
	 * @param row number
	 * @param column number
	 * @return true/false
	 */
	boolean shootAt(int row, int column) {
		if (this.shipPosition(row, column) != -1 && !this.isSunk()) {
			//record hit in hit array
			this.hit[this.shipPosition(row, column)] = true;
			return true;
		}

		else {

			return false;
		}
	}

	/**
	 * Helper method that determines which part of a ship occupies a position in the ocean
	 * @param row number
	 * @param column number
	 * @return integer from 0-3 or returns -1 if no ship in this position
	 */
	int shipPosition(int row, int column) {
		if (this.horizontal) {
			for (int i = this.bowColumn;
					i < this.bowColumn + this.length;
					i++) {
				if (this.bowRow == row && i == column) {
					//which ship cell belongs in a position
					return i - this.bowColumn;
				}
			}

			//if the ship does not occupy this cell at all
			return -1;
		}

		else {
			for (int i = this.bowRow;
					i < this.bowRow + this.length;
					i++) {
				if (i == row && this.bowColumn == column) {
					return i - this.bowRow;
				}
			}

			//if the ship does not occupy this cell at all
			return -1;
		}
	}

	/**
	 * Returns whether a ship has been sunk
	 * @return true/false
	 */
	boolean isSunk() {

		boolean[] sunk = new boolean [4];
		sunk[0] = true;
		sunk[1] = true;
		sunk[2] = true;
		sunk[3] = true;

		//for submarine
		if (this.length == 1) {
			if (this.hit[0] == sunk[0]) {
				return true;
			}

			else {

				return false;
			}
		}

		//for destroyer
		else if (this.length == 2) {
			if (this.hit[0] == sunk[0] && this.hit[1] == sunk[1]) {
				return true;
			}

			else {

				return false;
			}
		}

		//for cruiser
		else if (this.length == 3) {
			if (this.hit[0] == sunk[0] && this.hit[1] == sunk[1] && this.hit[2] == sunk[2]) {
				return true;
			}

			else {

				return false;
			}
		}

		//for battleship
		else {
			if (this.hit[0] == sunk[0] && this.hit[1] == sunk[1] && this.hit[2] == sunk[2] &&
					this.hit[3] == sunk[3]	) {
				return true;
			}

			else {

				return false;
			}

		}

	}

	/**
	 * Overrides the toString method
	 * Returns 'x' for a sunk ship
	 * Returns 'S' for an unsunk ship
	 * @return String
	 */
	@Override
	public String toString() {
		//for a sunk ship return x else return S
		if (this.isSunk() == true) {
			return "x";
		}

		else {
			return "S";
		}
	}
	
	
	/**
	 * Overrides equals method for Ship class
	 * @return true/false
	 */
	@Override
	public boolean equals(Object something) { 
		Ship p = (Ship)something;
		return this.bowRow == p.bowRow && this.bowColumn == p.bowColumn &&
				this.length == p.length && this.getShipType() == p.getShipType() &&
				this.getHit() == p.getHit(); 
	}

}
