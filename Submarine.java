package battleship;

/**
 * Sub-class of Ship, of length 1
 * @author shwetachopra
 *
 */
public class Submarine extends Ship {

	//Set up instance variables
	
	/**
	 * Static variable - Ship type
	 */
	private static final String SHIP_TYPE = "submarine";
	
	/**
	 * Static variable - Ship length
	 */
	private static final int SHIP_LENGTH = 1;

	//Set up constructor
	/** 
	 * Constructs ship of length 1
	 */
	public Submarine() {
		this.setLength(Submarine.SHIP_LENGTH);
	}

	/**
	 * Returns ship type
	 */
	@Override
	public String getShipType(){
		return Submarine.SHIP_TYPE;
	}


}
