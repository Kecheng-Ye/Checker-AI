import java.util.ArrayList;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		System.out.print("choose your game: \n"
				+ "1. small 4*4 checkers \n"
				+ "2. standard 8*8 checkers\n"
				+ "Your choice is? Please enter1 or 2 \n");
		int input =scanner.nextInt();
		while(!(input == 1||input == 2)) {
			System.out.println("Please enter again ");
			input =scanner.nextInt();
		}
		System.out.println("choose your opponent:\n"
				+ "1. an agent that plays randomly\n"
				+ "2. an agent that uses MINIMAX\n"
				+ "3. an agent that uses MINIMAX with alpha-beta pruning\n"
				+ "4. an agent that uses H-MINIMAX with a fixed depth cutoff\n"
				+ "Your choice is? Please enter 1/2/3/4");
		int chooseopponent =scanner.nextInt();
		while(!(chooseopponent == 1||chooseopponent == 2||chooseopponent ==3||chooseopponent ==4)){
			System.out.println("Please enter again ");
			chooseopponent =scanner.nextInt();
		}
		
		System.out.println("Depth limit?");
		int choosedepth =scanner.nextInt();
		
		System.out.println("Do you want to play 1.WHITE(w) or 2.BLACK(b)? enter 1 or 2");
		int choosecolor =scanner.nextInt();
		while(!(choosecolor == 1||choosecolor == 2)) {
			System.out.println("Please enter again ");
			choosecolor =scanner.nextInt();
		}
	
		boolean color = (choosecolor == 1) ? true : false;
		
		
		board board = null;
		if(input==1) {
			board = new board(4 ,color);
		}else {
			board = new board(8 ,color);
		}
		
		//SmartestAI AI = new SmartestAI (board, !color);
		SmartestAI AI = new SmartestAI (board, !color, choosedepth, chooseopponent);

		play play_game = new play(board,AI);
		play_game.startgame();
	}
	
		
	}

