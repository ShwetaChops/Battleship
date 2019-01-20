package battleship;

/**
 * Sub-class of Ship, of length 4
 * @author shwetachopra
 *
 */
public class Battleship extends Ship {

	//Set up instance variables
	/**
	 * Static variable - Ship type
	 */
	private static final String SHIP_TYPE = "battleship";
	
	/**
	 * Static variable - Ship length
	 */
	private static final int SHIP_LENGTH = 4;

	//Set up constructor
	/**
	 * Constructs ship of length 4
	 */
	public Battleship() {
		this.setLength(Battleship.SHIP_LENGTH);
	}

	/**
	 * Returns ship type
	 */
	@Override
	public String getShipType(){
		return Battleship.SHIP_TYPE;
	}

}
