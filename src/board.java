import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class board {
	boolean turn = true;		// true for white, false for black
	int score = 0;
	checkers [][] board;
	int size;

//	public static void main(String[] args) {
//		board board = new board(4);
//		SmartestAI AI = new SmartestAI(board,!board.turn);
//		play game_play = new play(board , AI);
//		game_play.startgame();
////		System.out.print(AI.utility(board.board));
////		ArrayList<ArrayList<action>> ans = new ArrayList<ArrayList<action>>();
////		System.out.println(board.board[4][4].validmove(board.board));
//
//	//	ans = board.board[4][4].multi_validmove(board.board);
////		System.out.println(ans);
//		
////		board.move(ans.get(0),board.board);
////		board.printGame(board.board);
////		System.out.println(board.board[6][1].toString1());
////		System.out.println(board.board[6][1].validmove(board.board));
//	}
	
//	public board(int size) {
//		board = new checkers[size][size];
//		this.size = size;
//		for(int row=0;row<size;row++) {
//			for(int col=0;col<size;col++) {
//				board[row][col] = null;
//			}
//		}
//		Chekers();
//		printGame(board);
//	}
	
	public board(int size , boolean turn) {
		board = new checkers[size][size];
		this.size = size;
		this.turn = turn;
		for(int row=0;row<size;row++) {
			for(int col=0;col<size;col++) {
				board[row][col] = null;
			}
		}
		Chekers();
		printGame(board);
	}
	
	public static checkers[][] playAt(checkers [][] board,int xstart,int ystart,int xend,int yend){
		checkers[][] newboard = new checkers[board.length][board.length] ;
		for(int row=0;row<board.length;row++) {
			for(int col=0;col<board.length;col++) {
				newboard[row][col]=board[row][col];
			}
		}
		newboard[xend][yend]=board[xstart][ystart];
		newboard[xstart][ystart]=null;
		return newboard;
	}
	
	public void Chekers() {
		
		if (size == 4) {
//			int whitecheckers = 2;
//			int blackcheckers = 2;
			
			int i,j;
			for (i=0;i<size;i++)
			    for (j=0;j<size;j++)
				board [i][j] = null;
				board [0][3] = new checkers(this,true,0,3);
				board [2][3] = new checkers(this,true,2,3);
				board [1][0] = new checkers(this,false,1,0);
				board [3][0] = new checkers(this,false,3,0);
		}
		if (size == 8){
//			int whitecheckers = 12;
//			int blackcheckers = 12;
			int i,j;
			for (i=0;i<size;i++)
			    for (j=0;j<size;j++)
				board[i][j] = null;

			for (i=1;i<size;i+=2) {
			    board[i][0] = new checkers(this,false,i,0);
			    board[i][2] = new checkers(this,false,i,2);
			    board[i][6] = new checkers(this,true,i,6);
			}
			for (i=0;i<size;i+=2) {
			    board[i][1] = new checkers(this,false,i,1);
			    board[i][5] = new checkers(this,true,i,5);
			    board[i][7] = new checkers(this,true,i,7);
			}
			
//			checkers whiteking = new checkers(this,true,4,4);
//			board[4][4] = whiteking;
//			whiteking.become_King();
//			checkers blackking = new checkers(this,false,2,4);
//			board[2][4] = blackking;
//			blackking.become_King();
//			
//			checkers white = new checkers(this,true,2,7);
//			board[2][7] = white;
//			checkers black1 = new checkers(this,false,6,6);
//			board[6][6] = black1;
//			checkers black2 = new checkers(this,false,1,0);		
//			board[1][0] = black2;
			
			}
	}

	
	

	public static void printGame(checkers [][] board) {
		String[] magic = {"A","B","C","D","E","F","G","H"};
		if(board.length==4)	{	
		System.out.println("  1 2 3 4");
			}else {
		System.out.println("  1 2 3 4 5 6 7 8");
			}
		for(int i=0;i<board.length;i++) {

			if(board.length==4)	{	
				System.out.println(" +-+-+-+-+");
				}else {
				System.out.println(" +-+-+-+-+-+-+-+-+");
				}
			    System.out.print(magic[i]);
			    for (int j=0;j<board.length;j++) {
			    	System.out.print("|");
			    	if (board[j][i] == null) {
			    		System.out.print(" ");
			    		continue;
			    	}
			    	System.out.print(board[j][i].content);
			    }
			    System.out.print("|");
			    System.out.println();
				
			}
			
		if(board.length==4)	{	
			System.out.println(" +-+-+-+-+");
			}else {
			System.out.println(" +-+-+-+-+-+-+-+-+");
			}
		
//		System.out.println ()
	}

	public boolean check_success(checkers [][] board) {
		if (killed_all(board) || cant_move(board)) {
			if (this.turn == true) {
				System.out.println("White lose!");
			} else {
				System.out.println("Black lose!");
			}
			return true;
		} else {
			return false;
		}	
	}
	
		

	public boolean killed_all(checkers [][] board){
		boolean isend = true;
		boolean target = false;
		
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if(board[row][col] == null) {
					continue;
				} else {
					target = board[row][col].type;	
				}
			}
		}
		for(int i = 0; i < board.length; i ++) {
			for (int j=0; j<board.length; j++) {
				if(board[i][j] == null) {
					continue;
				} else {
					if (target != board[i][j].type) {
						isend = false;
					}
				}
			}
		}
		return isend;
	}
	
	public boolean cant_move(checkers [][] board){
		boolean isend = false;
//		if(isend) {
//			return true;
//		}
//		// draw games in 4*4 game
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if ((board[row][col] != null) && board[row][col].type == turn) {
				//	System.out.println(row + " " + col);
				//	System.out.println(board[row][col].multi_validmove(board));
					if (board[row][col].validmove(board).isEmpty()) {
			//			System.out.println ("I'm in");
						continue;
						
					}else {
						return false;
					}
				}
			}	
		}
		return !isend;
	}

	
	public checkers[][] move(ArrayList<action> list, checkers[][] new_board) {
		for(action a : list) {	
			if(Math.abs(a.x2 - a.x1) > 1) {
				new_board[a.x2][a.y2] = new_board[a.x1][a.y1];
				new_board[a.x1][a.y1] = null;
				new_board[(a.x1 + a.x2)/2][(a.y1 + a.y2)/2] = null;
				
			}else{ //one step no kill
				new_board[a.x2][a.y2] = new_board[a.x1][a.y1];
				new_board[a.x1][a.y1] = null;
			}
			
			new_board[a.x2][a.y2].coord_change(a.x2, a.y2);
		}
		
		action a = list.get(list.size()-1); // last action
		if (!new_board[a.x2][a.y2].isking && ((new_board[a.x2][a.y2].type && a.y2 == 0)
				||(!new_board[a.x2][a.y2].type && a.y2 == size-1))) {
			new_board[a.x2][a.y2].become_King();
		}
		return new_board;

	}
	
	public void usermove() {
		ArrayList<action> act_list = new ArrayList<action>();
		while(true) {
			System.out.print("Your move [src-det]: ");
			Scanner scanner= new Scanner(System.in);
			String actlist = scanner.next();
			String[] each_act = actlist.split(" ");
			act_list = new ArrayList<action>();
			
			for(String act : each_act) {
				act_list.add(new action(actlist));
			}
			
			
			int startX = act_list.get(0).x1;
			int startY = act_list.get(0).y1;
			
			if(board[startX][startY] == null) {
				System.out.println("No such checker! Input again");
				System.out.print("Your move [src-det]: ");
				continue;
			}
			
			ArrayList<ArrayList<action>> all_possible_act = board[startX][startY].multi_validmove(board,turn);
//			System.out.println("It is "+turn+ " round with all possible move " + all_possible_act);
			if(!exist_in_act_list(act_list,all_possible_act)) {
				System.out.print("Invalid input, please enter again, the correct range is: \n"
						+ "USE SPACE TO SPLIT ACTION PLZ \n");
				for(ArrayList<action> one_act : all_possible_act) {
					System.out.println(one_act);
				}
				continue;
			}
			
			break;
		}
		
		board = move(act_list, board);
		
	}
	
	public boolean exist_in_act_list(ArrayList<action> a,ArrayList<ArrayList<action>> all_possible_act) {
		for(ArrayList<action> each_act: all_possible_act) {
			if(compare(each_act,a )) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean compare(ArrayList<action> a, ArrayList<action> b) {
		if(a.size() != b.size()) {
			return false;
		}else {
			for(int i = 0; i < a.size(); i++) {
				if(!a.get(i).equals(b.get(i))) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
	
	public ArrayList<action> input(String actlist){
		String[] each_act = actlist.split(" ");
		ArrayList<action> act_list = new ArrayList<action>();
		
		for(String act : each_act) {
			act_list.add(new action(actlist));
		}
		
		return act_list;
	}
	
	
	
}
	



