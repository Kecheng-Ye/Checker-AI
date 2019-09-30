
public class play {
	board board;
	SmartestAI AI;
	
	public play(board board, SmartestAI AI) {
		this.board = board;
		this.AI = AI;
	}

	public void startgame() {
//		while(!board.check_success(board.board)) {
//			board.usermove();
//			board.printGame(board.board);
//			board.move(AI.MINIMAX_DECISION(),board.board);
//			board.printGame(board.board);
//			board.turn = !board.turn;
//		}
//		if (board.turn) {
//			System.out.print( + "")
//		}
		
		boolean temp = board.turn;
		while(!board.check_success(board.board)) {
			if(temp == true) {
				board.usermove();
//				board.printGame(board.board);
			}else {
				long time = System.currentTimeMillis();
				if (SmartestAI.chooselevel == 1) {
					System.out.println("I'm thinking...");
					board.move(AI.randomplay(),board.board);
				} else if (SmartestAI.chooselevel == 2) {
					System.out.println("I'm thinking...");
					board.move(AI.MINIMAX_DECISION(),board.board);
				} else if (SmartestAI.chooselevel == 3) {
					System.out.println("I'm thinking...");
					board.move(AI.ALPHA_BETA_DECISION(),board.board);
				} else if (SmartestAI.chooselevel == 4) {
					System.out.println("I'm thinking...");
					board.move(AI.H_MINMAX(),board.board);
				}
				double used = (System.currentTimeMillis() - time) / 1000.0;
				System.out.println("Time used to compute: " + used + " seconds");
				board.printGame(board.board);

			}
			temp = !temp;
		}
		
	}
}
