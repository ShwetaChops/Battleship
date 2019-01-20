package battleship;

/**
 * Sub-class of Ship, of length 2
 * @author shwetachopra
 *
 */
public class Destroyer extends Ship  {

	//Set up instance variables
	/**
	 * Static variable - Ship type
	 */
	private static final String SHIP_TYPE = "destroyer";
	/**
	 * Static variable - Ship length
	 */
	private static final int SHIP_LENGTH = 2;

	//Set up constructor
	/** 
	 * Constructs ship of length 3
	 */
	public Destroyer() {
		this.setLength(Destroyer.SHIP_LENGTH);
	}

	/**
	 * Returns ship type
	 */
	@Override
	public String getShipType(){
		return Destroyer.SHIP_TYPE;
	}


}
