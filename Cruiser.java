package battleship;

/**
 * Sub-class of Ship, of length 3
 * @author shwetachopra
 *
 */
public class Cruiser extends Ship {

	//Set up instance variables
	/**
	 * Static variable - Ship type
	 */
	private static final String SHIP_TYPE = "cruiser";
	/**
	 * Static variable - Ship length
	 */
	private static final int SHIP_LENGTH = 3;

	//Set up constructor
	/** 
	 * Constructs ship of length 3
	 */
	public Cruiser() {
		this.setLength(Cruiser.SHIP_LENGTH);
	}

	/**
	 * Returns ship type
	 */
	@Override
	public String getShipType(){
		return Cruiser.SHIP_TYPE;
	}

}
