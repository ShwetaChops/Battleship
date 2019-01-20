package battleship;

/**
 * Sub-class of Ship, which refers to no ship
 * @author shwetachopra
 *
 */
public class EmptySea extends Ship  {

	//Set up instance variables
	/**
	 * Static variable - Ship type
	 */
	private static final String SHIP_TYPE = "empty";
	
	/**
	 * Static variable - Ship length
	 */
	private static final int SHIP_LENGTH = 1;

	//Set up constructor
	/** 
	 * Constructs empty sea of length 1
	 */
	public EmptySea() {
		this.setLength(EmptySea.SHIP_LENGTH);
	}

	//Set up methods

	/**
	 * Overrides shootAt method to always return false
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	/**
	 * Overrides isSunk method to always return false
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * Overrides toString method and returns "-"
	 */
	@Override
	public String toString() {
		return "-";
	}

	/**
	 * Overrides getShipType
	 */
	@Override
	public String getShipType() {
		return EmptySea.SHIP_TYPE;
	}
}
