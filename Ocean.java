package battleship;

import java.util.Random;

/**
 * Ocean class 
 * Tracks overall progress of the game 
 * @author shwetachopra
 *
 */
public class Ocean {
	//Declare static variables
	/**
	 * Static variable - stores length of the ocean
	 */
	private static final int OCEAN_LENGTH = 10;

	/**
	 * Static variable - Count of Battleships in the game
	 */
	private static final int BATTLESHIP_COUNT = 1;

	/**
	 * Static variable - Count of Cruisers in the game
	 */
	private static final int CRUISER_COUNT = 2;

	/**
	 * Static variable - Count of Destroyers in the game
	 */
	private static final int DESTROYER_COUNT = 3;

	/**
	 * Static variable - Count of Submarines in the game
	 */
	private static final int SUBMARINE_COUNT = 4;

	/**
	 * Static variable - Count of Total Ships in the game
	 */
	private static final int SHIP_COUNT = BATTLESHIP_COUNT + CRUISER_COUNT + DESTROYER_COUNT 
			+ SUBMARINE_COUNT;

	//Declare instance variables
	/**
	 * Array that stores the locations of all the ships on the ocean map
	 */
	private Ship[][] ships = new Ship [Ocean.OCEAN_LENGTH][Ocean.OCEAN_LENGTH];

	/**
	 * Count of total shots fired by the user
	 */
	private int shotsFired;

	/**
	 * Count of total shots by user that hit a ship
	 */
	private int hitCount;

	/**
	 * Count of total ships sunk in the game, at any point
	 */
	private int shipsSunk;

	/**
	 * Duplicate array that stores the locations of all the ships on the ocean map
	 */
	protected boolean[][] oceanHits = new boolean [Ocean.OCEAN_LENGTH][Ocean.OCEAN_LENGTH];

	//Set up constructor
	/**
	 * Constructor for the ocean class
	 * Initializes instance variables
	 */
	public Ocean() {
		//TO DO
		this.ships = this.createOcean(ships);
		this.hitCount = 0;
		this.shotsFired = 0;
		this.shipsSunk = 0;
	}



	//Set up methods
	/**
	 * Method to populate the ships array with empty sea
	 * @param array of size 10 x 10
	 * @return array with empty sea in each position
	 */
	private Ship[][] createOcean(Ship [][] array) {
		for (int i = 0; i < Ocean.OCEAN_LENGTH; i++) {
			for (int j = 0; j < Ocean.OCEAN_LENGTH; j++) {
				array[i][j] = new EmptySea();
			}
		}
		return array;
	}

	/**
	 * Method that creates all 10 ships for the game
	 * and places the ships in random positions on the ocean
	 */
	void placeAllShipsRandomly() {

		//Create all ships to be placed
		Battleship battleship = new Battleship();
		Cruiser cruiser1 = new Cruiser();
		Cruiser cruiser2 = new Cruiser();
		Destroyer destroyer1 = new Destroyer();
		Destroyer destroyer2 = new Destroyer();
		Destroyer destroyer3 = new Destroyer();
		Submarine submarine1 = new Submarine();
		Submarine submarine2 = new Submarine();
		Submarine submarine3 = new Submarine();
		Submarine submarine4 = new Submarine();

		//Place all ships
		this.placeShip(battleship);
		this.placeShip(cruiser1);
		this.placeShip(cruiser2);
		this.placeShip(destroyer1);
		this.placeShip(destroyer2);
		this.placeShip(destroyer3);
		this.placeShip(submarine1);
		this.placeShip(submarine2);
		this.placeShip(submarine3);
		this.placeShip(submarine4);

	}

	/**
	 * Helper method that places a single ship at a random position on the ocean
	 * @param ship
	 */
	private void placeShip(Ship ship) {

		//Generate random parameters to place ship
		Random rand = new Random();
		int row = rand.nextInt(10);
		int col = rand.nextInt(10);
		boolean orient; //boolean that stores whether horizontal-0 or vertical-1
		int orientation = rand.nextInt(2);

		if (orientation == 0) {
			orient = true;
		}

		else {
			orient = false;
		}

		//While the random position offered is invalid, keep generating possible positions
		while (!ship.okToPlaceShipAt(row, col, orient, this)) {

			//Generate new random possible positions for placement
			row = rand.nextInt(10);
			col = rand.nextInt(10);
			orientation = rand.nextInt(2);

			if (orientation == 0) {
				orient = true;
			}

			else {
				orient = false;
			}

		}

		//Once a valid position is found, place the ship using the chosen parameters
		ship.placeShipAt(row, col, orient, this);
	}

	/**
	 * Returns whether position in Ships Array is occupied by a ship
	 * @param row number
	 * @param column number
	 * @return true/false
	 */
	boolean isOccupied(int row, int column) {
		//if location is not an empty sea
		if (ships[row][column].getShipType() != "empty") {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Method that lets the user shoot at a particular row/column combination on the ocean
	 * Returns true if shot hits a ship
	 * @param row number
	 * @param column number
	 * @return true/false
	 */
	boolean shootAt(int row, int column) {
		//Increase count of shots fired
		this.shotsFired++;
		//Record shot at a particular location
		this.recordHit(row, column);

		//If ship hit, increase hit count and return true, else return false
		if (ships[row][column].getShipType() != "empty" &&
				!ships[row][column].isSunk()) {

			this.hitCount++;

			//Record shot for a particular ship, and if newly sunk, then increase Sunk Ship count
			if (this.getShipArray()[row][column].shootAt(row, column)) {
				if (this.getShipArray()[row][column].isSunk()) {
					this.shipsSunk++;;
				}
			}

			return true;
		}

		else {

			return false;
		}


	}

	/**
	 * Getter for shotsFired
	 * @return shotsFired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * Getter for hitCount
	 * @return hitCount
	 */
	int getHitCount() {
		return this.hitCount;
	}

	/**
	 * Getter for shipsSunk
	 * @return shipsSunk
	 */
	int getShipsSunk() {
		return shipsSunk;
	}

	/**
	 * Checks and returns whether the game is over
	 * @return true/false
	 */
	boolean isGameOver() {
		if (this.shipsSunk == Ocean.SHIP_COUNT) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Provides access to the ships array
	 * @return ships
	 */
	Ship[][] getShipArray() {
		return ships;
	}

	/**
	 * Method that prints ocean for the game
	 * Provides the user with insight into their progress
	 */
	void print() {

		System.out.println("\n");

		//Print three blank spaces
		System.out.print("   ");

		//Print column header
		for (int i = 0; i < OCEAN_LENGTH; i++) {
			System.out.print(i + "  ");
		}

		//Move to next line
		System.out.println("\n");

		//Print row headers and cells
		for (int i = 0; i < OCEAN_LENGTH; i++) {

			System.out.print(i + "  ");

			for (int j = 0; j < OCEAN_LENGTH; j++) {


				//If the location has been shot at
				if (this.locationHit(i,j)) {
					System.out.print(ships[i][j].toString() + "  ");
				}

				else {
					System.out.print("." + "  ");
				}

			}
			System.out.println("\n");
		}	
	}

	/**
	 * Method that helps with debugging by revealing ship positions on the ocean
	 * Prints ocean while revealing ship positions
	 */
	void printShipLocations() {


		System.out.println("\n");

		//Print three blank spaces
		System.out.print("   ");

		//Print column header
		for (int i = 0; i < OCEAN_LENGTH; i++) {
			System.out.print(i + "  ");
		}

		//Move to next line
		System.out.println("\n");

		//Print row headers and cells
		for (int i = 0; i < OCEAN_LENGTH; i++) {

			System.out.print(i + "  ");

			for (int j = 0; j < OCEAN_LENGTH; j++) {


				//If the location has a ship
				if (ships[i][j].getShipType() == "battleship") {
					System.out.print("b" + "  ");
				}

				else if (ships[i][j].getShipType() == "cruiser") {
					System.out.print("c" + "  ");
				}			

				else if (ships[i][j].getShipType() == "destroyer") {
					System.out.print("d" + "  ");
				}

				else if (ships[i][j].getShipType() == "submarine") {
					System.out.print("s" + "  ");
				}

				else {
					System.out.print("." + "  ");
				}

			}

			System.out.println("\n");
		}	

	}

	/**
	 * Helper method that explains whether a location has been shot at
	 * @param row number
	 * @param column number
	 * @return true/false
	 */
	private boolean locationHit(int row, int column) {
		//check if location has been shot at
		if (oceanHits[row][column]) {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * Method that records that a location has been shot at
	 * @param row number
	 * @param column number
	 */
	private void recordHit(int row, int column) {
		//set true at location that has been hit
		oceanHits[row][column] = true;
	}

}
