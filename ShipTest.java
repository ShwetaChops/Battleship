package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that unit tests the methods in the class Ship
 * @author shwetachopra
 *
 */
class ShipTest {
	
	//Declare variables
	Ship ship1;
	Ship ship2b;
	Ship ship3c;
	Ship ship4d;
	Ship ship5s;
	Ship ship6e;
	Ocean ocean;
	boolean[] array;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
		//Create Ocean
		this.ocean = new Ocean();
		
		//Create ships
		this.ship1 = new Ship();
		this.ship2b = new Battleship();
		this.ship3c = new Cruiser();
		this.ship4d = new Destroyer();
		this.ship5s = new Submarine();
		this.ship6e = new EmptySea();
		
		//Create boolean array - false
		this.array = new boolean [4];
		
		
	}

	@Test
	void testShip() {
		
		//Construct new ship
		
		assertEquals(this.array[0], this.ship1.getHit()[0]);
		assertEquals(this.array[1], this.ship1.getHit()[1]);
		assertEquals(this.array[2], this.ship1.getHit()[2]);
		assertEquals(this.array[3], this.ship1.getHit()[3]);
	}

	@Test
	void testGetLength() {
		//Test length for each ship type - Test sub-classes as well
		assertEquals(4, this.ship2b.getLength());
		assertEquals(3, this.ship3c.getLength());
		assertEquals(2, this.ship4d.getLength());
		assertEquals(1, this.ship5s.getLength());
		assertEquals(1, this.ship6e.getLength());
		
	}

	@Test
	void testGetBowRow() {
		this.ship1.setBowRow(4);
		assertEquals(4,this.ship1.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		this.ship1.setBowColumn(4);
		assertEquals(4,this.ship1.getBowColumn());
	}

	@Test
	void testGetHit() {
		assertEquals(this.array[0], this.ship1.getHit()[0]);
		assertEquals(this.array[1], this.ship1.getHit()[1]);
		assertEquals(this.array[2], this.ship1.getHit()[2]);
		assertEquals(this.array[3], this.ship1.getHit()[3]);
	}

	@Test
	void testIsHorizontal() {
		this.ship1.setHorizontal(true);
		assertEquals(true, this.ship1.isHorizontal());
	}

	@Test
	void testGetShipType() {
		//Test sub-classes as well
		assertEquals("empty", this.ship6e.getShipType());
		assertEquals("submarine", this.ship5s.getShipType());
		assertEquals("destroyer", this.ship4d.getShipType());
		assertEquals("cruiser", this.ship3c.getShipType());
		assertEquals("battleship", this.ship2b.getShipType());
		assertEquals("", this.ship1.getShipType());
	}

	@Test
	void testSetBowRow() {
		this.ship1.setBowRow(8);
		assertEquals(8,this.ship1.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		this.ship1.setBowColumn(2);
		assertEquals(2,this.ship1.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		this.ship1.setHorizontal(false);
		assertEquals(false, this.ship1.isHorizontal());
	}

	@Test
	void testSetLength() {
		this.ship1.setLength(10);
		assertEquals(10, this.ship1.getLength());
	}

	@Test
	void testOkToPlaceShipAt() {
		//Place one ship
		this.ship2b.placeShipAt(2, 2, true, ocean);
		
		//Check if ok to place on already placed ship returns false
		assertFalse(this.ship3c.okToPlaceShipAt(2, 3, false, ocean));
		
		//Check if ok to place in valid area returns true
		assertTrue(this.ship5s.okToPlaceShipAt(0, 9, true, ocean)); //Corner case
		
		//Check if ok to place with ship extending out of ocean returns false
		assertFalse(this.ship4d.okToPlaceShipAt(9, 9, false, ocean));
		
	}

	@Test
	void testRowStart() {
		assertEquals(5, ship5s.rowStart(6,1,true));
		assertEquals(0, ship4d.rowStart(1,1,true));
		assertEquals(8, ship2b.rowStart(9,1,false));
		assertEquals(4, ship3c.rowStart(5,1,false));
	}

	@Test
	void testRowEnd() {
		assertEquals(7, ship5s.rowEnd(6,1,true));
		assertEquals(2, ship4d.rowEnd(1,1,true));
		assertEquals(9, ship2b.rowEnd(6,1,false));
		assertEquals(9, ship3c.rowEnd(7,1,false));
	}

	@Test
	void testColStart() {
		assertEquals(5, ship5s.colStart(6,6,true));
		assertEquals(0, ship4d.colStart(1,1,true));
		assertEquals(8, ship2b.colStart(9,9,false));
		assertEquals(4, ship3c.colStart(5,5,false));
	}

	@Test
	void testColEnd() {
		assertEquals(9, ship5s.colEnd(6,9,true));
		assertEquals(4, ship4d.colEnd(1,2,true));
		assertEquals(5, ship2b.colEnd(6,4,false));
		assertEquals(9, ship3c.colEnd(7,8,false));
	}

	@Test
	void testPlaceShipAt() {
		//Place ships
		this.ship2b.placeShipAt(2, 2, true, ocean);
		this.ship3c.placeShipAt(6, 6, false, ocean);
		
		//Check if ship has been correctly placed
		assertEquals(this.ship2b, ocean.getShipArray()[2][2]);
		assertEquals(this.ship2b, ocean.getShipArray()[2][3]);
		assertEquals(this.ship2b, ocean.getShipArray()[2][4]);
		assertEquals(this.ship2b, ocean.getShipArray()[2][5]);
		assertEquals(this.ship3c, ocean.getShipArray()[6][6]);
		assertEquals(this.ship3c, ocean.getShipArray()[7][6]);
		assertEquals(this.ship3c, ocean.getShipArray()[8][6]);
		
		//Check if incorrect locations return false
		assertFalse(this.ship2b.equals(ocean.getShipArray()[3][2]));
		assertFalse(this.ship3c.equals(ocean.getShipArray()[9][6]));
		
	}

	@Test
	void testShootAt() {
		//Place ships
		this.ship2b.placeShipAt(2, 2, true, ocean);
		this.ship3c.placeShipAt(6, 6, false, ocean);
		this.ship6e.placeShipAt(0, 0, true, ocean);
		
		//Check for return true if shoot at ship
		assertTrue(this.ship2b.shootAt(2, 3));
		//Check hit array updated
		assertEquals(true,this.ship2b.getHit()[1]);
		
		
		//Check for return false if shoot at empty sea
		assertFalse(this.ship2b.shootAt(2, 6));
		
		//Check if sunken ship returns false
		this.ship3c.shootAt(6, 6);
		this.ship3c.shootAt(7, 6);
		this.ship3c.shootAt(8, 6);
		assertFalse(this.ship3c.shootAt(6, 6));
		
		//Check emptySea - shoot at returns false
		assertFalse(this.ship6e.shootAt(0, 0));
		
	}

	@Test
	void testShipPosition() {
		//Place ships
		this.ship2b.placeShipAt(2, 2, true, ocean);
		this.ship3c.placeShipAt(6, 6, false, ocean);
		
		//Test
		assertEquals(3,this.ship2b.shipPosition(2, 5));
		assertEquals(0,this.ship3c.shipPosition(6, 6));
		assertEquals(-1,this.ship3c.shipPosition(0, 0));
	}

	@Test
	void testIsSunk() {
		//Place ship
		this.ship3c.placeShipAt(6, 6, false, ocean);
		this.ship2b.placeShipAt(0, 0, true, ocean);
		this.ship5s.placeShipAt(9, 9, true, ocean);
		
		//Shoot at ship - incomplete
		this.ship3c.shootAt(6, 6);
		this.ship3c.shootAt(7, 6);
		
		//Check isSunk returns false
		assertFalse(this.ship3c.isSunk());
		assertFalse(this.ship2b.isSunk());
		
		//Complete shooting down ship
		this.ship3c.shootAt(8, 6);
		this.ship5s.shootAt(9, 9);
		
		//Check isSunk returns true
		assertTrue(this.ship3c.isSunk());
		assertTrue(this.ship5s.isSunk());
		
		//Check emptySea isSunk returns False
		assertFalse(this.ship6e.isSunk());
	}

	@Test
	void testToString() {
		//Place ship
		this.ship3c.placeShipAt(6, 6, false, ocean);
		
		//Shoot at ship - incomplete
		this.ship3c.shootAt(6, 6);
		this.ship3c.shootAt(7, 6);
		
		//Check toString returns S
		assertEquals("S", this.ship3c.toString());
		
		//Complete shooting down ship - sunk
		this.ship3c.shootAt(8, 6);
		
		//Check toString returns x
		assertEquals("x", this.ship3c.toString());
		
		//Empty sea to string
		assertEquals("-", this.ship6e.toString());
	}

}
