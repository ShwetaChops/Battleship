package battleship;

import java.util.Scanner;

/**
 * BattleshipGame class contains the main method for the game
 * @author shwetachopra
 *
 */
public class BattleshipGame {

	/**
	 * Main method for project
	 * @param args
	 */
	public static void main(String[] args) {

		boolean playAgain = true;

		//Create scanner for input collection
		Scanner input = new Scanner(System.in);

		while (playAgain) {
			//Set up game
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();

			//Welcome user to the game
			System.out.println("WELCOME TO BATTLESHIP!\nTry and sink the Computer's ships and become king of the ocean!");

			//Set quitting parameter to false
			boolean quit = false;


			//Declare row and column variables

			//While game is on, keep playing
			while (!quit) {

				//Print ocean
				ocean.print();

				//Display user stats
				System.out.println("\nYour Stats:\n");
				System.out.println("Shots Fired: " + ocean.getShotsFired());
				System.out.println("Targets Hit: " + ocean.getHitCount());
				System.out.println("Ships Sunk: " + ocean.getShipsSunk());
				System.out.println("");

				//Check if user wants to play
				System.out.print("Enter Q to quit, anything else to continue game: ");
				String playInput = input.nextLine().trim();

				//Quit game if user chooses
				if (playInput.toLowerCase().equals("q")) {
					quit = true;
					continue;
				}

				System.out.println("Which cell do you want to shoot at?");

				//Take row input
				boolean inputValid = false;
				int rowInput = 0;
				int colInput = 0;

				//Check if valid input entered
				while (!inputValid) {
					System.out.print("Row, Column: ");
					String Entry = input.nextLine().trim();

					if (!Entry.contains(",") || Entry.length() < 3) {
						System.out.println("Please enter in the format - Row, Column");
					}

					else {
						
						//Separate out row and column inputs
						String rowEntry = Entry.split(",")[0].trim();
						String colEntry = Entry.split(",")[1].trim();

						try {

							rowInput = Integer.parseInt(rowEntry);
							colInput = Integer.parseInt(colEntry);

							if (rowInput < 0 || rowInput > 9 || colInput < 0 || colInput > 9) {
								System.out.println("Only integers from 0 to 9 may be entered.");
							}

							else {

								inputValid = true;
							}


						}
						catch (NumberFormatException nfe) {

							System.out.println("Enter integers from 0 to 9 only.");
						}
					}

				}





				// IGNORE THIS COMMENTED OUT SECTION - FOR TESTING CODE
				//				for (int i = 0; i < 10; i++) {
				//					for (int j = 0; j < 10; j++) {
				//						int rowInput = i;
				//						int colInput = j;
				//						ocean.shootAt(rowInput, colInput);	
				//						//Check if game over
				//						if (ocean.isGameOver()) {
				//							ocean.print();
				//							System.out.println("CONGRATULATIONS! YOU WIN!");
				//							System.out.println("Your Score: " + ocean.getShotsFired() + " shots");
				//							quit = true;
				//							break;
				//						}
				//					}
				//				}



				//Record shot
				ocean.shootAt(rowInput, colInput);			


				//Check if game over
				if (ocean.isGameOver()) {
					ocean.print();
					System.out.println("CONGRATULATIONS! YOU WIN!");
					System.out.println("Your Score: " + ocean.getShotsFired() + " shots");
					quit = true;
				}

			}

			//Add Play Again option
			System.out.println("Enter Y to play again, anything else to exit game: ");
			String play = input.nextLine().trim();

			if (!play.toLowerCase().equals("y")) {
				playAgain = false;
			}

		}



		//Close scanner
		input.close();
		System.out.print("THE END");

	}

}
