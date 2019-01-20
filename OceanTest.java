package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that unit tests the methods in the class Ocean
 * @author shwetachopra
 *
 */
class OceanTest {

	//Declare variables
	Ship ship;
	Ship battleship;
	Ship cruiser;
	Ship destroyer;
	Ship submarine;
	Ship emptySea;
	Ocean ocean2;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Create Ocean
		this.ocean2 = new Ocean();

		//Create ships
		this.ship = new Ship();
		this.battleship = new Battleship();
		this.cruiser = new Cruiser();
		this.destroyer = new Destroyer();
		this.submarine = new Submarine();
		this.emptySea = new EmptySea();


	}

	@Test
	void testOcean() {
		assertEquals(0, this.ocean2.getShotsFired());
		assertEquals(0, this.ocean2.getHitCount());
		assertEquals(0, this.ocean2.getShipsSunk());
		assertEquals("empty", this.ocean2.getShipArray()[0][0].getShipType());
		assertEquals("empty", this.ocean2.getShipArray()[9][9].getShipType());
		assertEquals("empty", this.ocean2.getShipArray()[5][5].getShipType());
		assertEquals("empty", this.ocean2.getShipArray()[2][2].getShipType());
	}
	
	
	@Test
	void testPlaceAllShipsRandomly() {
		ocean2.placeAllShipsRandomly();
		
		//Check for right number of ships of each type in ocean - by cell count
		int battleshipCells = 0;
		int cruiserCells = 0;
		int destroyerCells = 0;
		int submarineCells = 0;
		int emptySeaCells = 0;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ocean2.getShipArray()[i][j].getShipType().equals("battleship")) {
					battleshipCells++;
				}
				
				else if (ocean2.getShipArray()[i][j].getShipType().equals("cruiser")) {
					cruiserCells++;
				}

				else if (ocean2.getShipArray()[i][j].getShipType().equals("destroyer")) {
					destroyerCells++;
				}
				
				else if (ocean2.getShipArray()[i][j].getShipType().equals("submarine")) {
					submarineCells++;
				}
				
				else {
					emptySeaCells++;
				}
			}
		}
		
		//Check for correct values for each type
		assertTrue(battleshipCells == 4);
		assertTrue(cruiserCells == 6);
		assertTrue(destroyerCells == 6);
		assertTrue(submarineCells == 4);
		assertTrue(emptySeaCells == 80);
		
		
		//Check for correct ship count
		int battleshipCount = 0;
		int cruiserCount = 0;
		int destroyerCount = 0;
		int submarineCount = 0;
		
		ArrayList <Ship> test = new ArrayList<Ship>();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (!ocean2.getShipArray()[i][j].getShipType().equals("empty") &&
						!test.contains(ocean2.getShipArray()[i][j])) {
					test.add(ocean2.getShipArray()[i][j]);
				}
			}
		}
		
		
		for (int i = 0; i < 10; i++) {
			if (test.get(i).getShipType().equals("battleship")) {
				battleshipCount++;
			}
			
			else if (test.get(i).getShipType().equals("cruiser")) {
				cruiserCount++;
			}
			
			else if (test.get(i).getShipType().equals("destroyer")) {
				destroyerCount++;
			}
			
			else if (test.get(i).getShipType().equals("submarine")) {
				submarineCount++;
			}
		}
		
		assertEquals(10, test.size());
		assertEquals(1, battleshipCount);
		assertEquals(2, cruiserCount);
		assertEquals(3, destroyerCount);
		assertEquals(4, submarineCount);
		
		
	}
	
	
	
	
	

	@Test
	void testIsOccupied() {
		this.battleship.placeShipAt(2, 2, true, ocean2);
		this.submarine.placeShipAt(8, 8, false, ocean2);

		//Check is occupied
		assertFalse(this.ocean2.isOccupied(0, 0));
		assertFalse(this.ocean2.isOccupied(0, 0));
		assertTrue(this.ocean2.isOccupied(2, 3));
		assertTrue(this.ocean2.isOccupied(8, 8));
	}

	@Test
	void testShootAt() {
		//Place ships
		this.battleship.placeShipAt(2, 2, true, ocean2);
		this.submarine.placeShipAt(8, 8, false, ocean2);

		//Shoot at ocean - return true for hits and false for misses
		assertTrue(this.ocean2.shootAt(2, 4)); //hit
		assertTrue(this.ocean2.shootAt(8, 8)); //hit
		assertFalse(this.ocean2.shootAt(0, 0)); //miss
		assertFalse(this.ocean2.shootAt(9, 8)); //miss

		//Check for increment in shots fired and hit count

		assertEquals(2,this.ocean2.getHitCount());
		assertEquals(4,this.ocean2.getShotsFired());

		//If ship is newly sunk (submarine) - check increase sunk ship count
		assertEquals(1,this.ocean2.getShipsSunk());
		
		//Check for return false for shooting sunk ship and no increment in hit count
		// but increment shots fired
		assertFalse(this.ocean2.shootAt(8, 8));
		assertEquals(2,this.ocean2.getHitCount());
		assertEquals(5,this.ocean2.getShotsFired());

	}

	@Test
	void testGetShotsFired() {
		//Place ships
		this.battleship.placeShipAt(2, 2, true, ocean2);

		this.ocean2.shootAt(2, 4); //shoot hit

		assertEquals(1,this.ocean2.getShotsFired());



	}

	@Test
	void testGetHitCount() {
		//place ship
		this.battleship.placeShipAt(2, 2, true, ocean2);

		this.ocean2.shootAt(2, 4); //shoot hit
		this.ocean2.shootAt(0, 0); //shoot miss
		
		//test
		assertEquals(1,this.ocean2.getHitCount());
	}

	@Test
	void testGetShipsSunk() {
		//place ship
		this.submarine.placeShipAt(2, 2, true, ocean2);
		
		//test
		assertEquals(0,this.ocean2.getShipsSunk());
		
		//sink ship
		this.ocean2.shootAt(2, 2); //shoot hit
		
		//test
		assertEquals(1,this.ocean2.getShipsSunk());
	}

	@Test
	void testIsGameOver() {
		//Current Game ends when 10 ships have been sunk, test game over for false
		assertFalse(this.ocean2.isGameOver());
		
		//create 10 ships
		Ship s1 = new Submarine();
		Ship s2 = new Submarine();
		Ship s3 = new Submarine();
		Ship s4 = new Submarine();
		Ship s5 = new Submarine();
		Ship s6 = new Submarine();
		Ship s7 = new Submarine();
		Ship s8 = new Submarine();
		Ship s9 = new Submarine();
		Ship s10 = new Submarine();
		
		//place 10 ships
		s1.placeShipAt(0, 0, true, ocean2);
		s2.placeShipAt(2, 2, true, ocean2);
		s3.placeShipAt(4, 4, true, ocean2);
		s4.placeShipAt(6, 6, true, ocean2);
		s5.placeShipAt(8, 8, true, ocean2);
		s6.placeShipAt(0, 9, true, ocean2);
		s7.placeShipAt(9, 0, true, ocean2);
		s8.placeShipAt(2, 4, true, ocean2);
		s9.placeShipAt(6, 8, true, ocean2);
		s10.placeShipAt(7, 3, true, ocean2);
		
		
		//shoot and sink 10 ships
		this.ocean2.shootAt(0, 0);
		this.ocean2.shootAt(2, 2);
		this.ocean2.shootAt(4, 4);
		this.ocean2.shootAt(6, 6);
		this.ocean2.shootAt(8, 8);
		this.ocean2.shootAt(0, 9);
		this.ocean2.shootAt(9, 0);
		this.ocean2.shootAt(2, 4);
		this.ocean2.shootAt(6, 8);
		this.ocean2.shootAt(7, 3);
		
		//test game over for true
		assertTrue(this.ocean2.isGameOver());
	}

	@Test
	void testGetShipArray() {
		//Check before placing
		assertEquals("empty", this.ocean2.getShipArray()[0][0].getShipType());
		
		//Place ships
		this.battleship.placeShipAt(0, 0, true, ocean2);
		
		//Check if Ship Array is called correctly
		assertEquals(this.battleship, this.ocean2.getShipArray()[0][0]);
		assertEquals(this.battleship, this.ocean2.getShipArray()[0][1]);
		assertEquals(this.battleship, this.ocean2.getShipArray()[0][2]);
		assertEquals(this.battleship, this.ocean2.getShipArray()[0][3]);
		
	}

}
