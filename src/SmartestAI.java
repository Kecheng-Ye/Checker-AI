import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class SmartestAI {
	
	int my_num = 0 ;
	int opponent_num = 0;
	int my_kings = 0;
	int opponent_kings = 0 ;
	int my_bottom = 0;
	int opponent_bottom = 0;
	board board;
	boolean AIround; //true for w, false for black
	public static int choosedepth;
	public static int chooselevel;
	
	public void checkers_num(checkers[][] board, boolean whoseturn) {
		this.my_num = 0;
		this.opponent_num = 0;
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if (board[row][col] != null && board[row][col].type == whoseturn) {
					this.my_num ++;
				} else if (board[row][col] != null && board[row][col].type != whoseturn) {
					this.opponent_num ++;
				}
			}
		}
	}

//	public void checkers_at_bottom(checkers [][] board, boolean whoseturn) {
//		this.my_bottom = 0;
//		this.opponent_bottom = 0;
//		for(int row = 0; row < board.length; row ++) {
//			if (board[row][board.length-1] != null && board[row][0].type == whoseturn && AIround == true) {
//				this.my_bottom ++;
//			} else {
//				continue;
//			}
//		}
//		for(int row = 0; row < board.length; row ++) {
//			if (board[row][board.length-1] != null && board[row][0].type == whoseturn && AIround == true) {
//				this.opponent_bottom ++;
//			} else {
//				continue;
//			}
//		}
//	}
//	
	public void kings_num(checkers[][] board, boolean whoseturn) {
		int my_kings = 0;
		int opponent_kings = 0;
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if (board[row][col] != null && board[row][col].type == whoseturn && board[row][col].isking) {
					my_kings ++;
				} else if (board[row][col] != null && board[row][col].type != whoseturn && board[row][col].isking) {
					opponent_kings ++;
				}			
			}
		}
	}
		
	
	
	public int Heuristic(checkers[][] board) {
		int AiCheckerNum = 0;
		int AiKingNum = 0;
		int utility = 0;
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if(board[row][col] == null) {
					continue;
				}else if (board[row][col].type == AIround ) {
					AiCheckerNum++;
					if (board[row][col].isking  ) AiKingNum++;	
					continue;
				}else if (board[row][col].type == !AIround ) {
					AiCheckerNum--;
					if (board[row][col].isking  ) AiKingNum--;	
					continue;
				}
		
			}
		}
		
//		checkers_at_bottom(board,whoseturn);
		utility = 10*(AiCheckerNum) + 4*(AiKingNum);
		return utility;
	}


//	public void checkers_in_middle(checkers [][] board, boolean whoseturn) {
//		for(int row = 0; row < board.length; row ++) {
//			for (int col=0; col<board.length; col++) {
//				if (board[row][col] != null && board[row][col].type == whoseturn) {
//					
//				}
//			}
//		}
//	}
	
	
	public SmartestAI(board board, boolean AIround, int choosedepth, int chooselevel) {
		this.board = board;
		this.AIround = AIround;
		this.choosedepth = choosedepth;
		this.chooselevel = chooselevel;
	}
	
	public int rand(ArrayList<ArrayList<action>> ac){
		return (int) (Math.random() * (ac.size()-1));
	}
	
	
	public ArrayList <action> randomplay(){
		ArrayList<ArrayList<action>> all_action = ACTIONS(board.board, AIround);
		int choice = rand(all_action);
//		System.out.println(choice);
		ArrayList<action> temp = all_action.get(choice);
		System.out.println("Randomly chosen move is: "+ temp);
		return temp;
	}
	
	public ArrayList<action> H_MINMAX(){
		ArrayList<ArrayList<action>> all_action = ACTIONS(board.board, AIround);
		HashMap<ArrayList<action>,Integer> Answer=new HashMap<ArrayList<action>,Integer>();
		//System.out.println(action);
		int minvalue = Integer.MIN_VALUE;
		for(ArrayList<action> each_act: all_action) {
//			System.out.println("H_MINIMAX "+each_act);
			checkers[][] temp = copy(board.board);
			checkers[][] result = RESULT(each_act, temp);
//			board.printGame(result);
			Answer.put(each_act,H_MIN_VALUE(result, 0 ,!AIround));
		}
		int max = Integer.MIN_VALUE;
		ArrayList<action> ans = null;
		for(ArrayList<action> key : Answer.keySet()) {
			System.out.println(Answer.get(key) + " " + key);
			if(Answer.get(key) > max) {
				ans = key;
				max = Answer.get(key);
			}
		}		
		System.out.println("Best move is: "+ ans + ", it's utility value is: " + max);
		return ans;
	}
	
	public int H_MAX_VALUE(checkers[][] state, int depth , boolean whoseturn) {
//		System.out.println("MAX LEVEL");
		if(depth == choosedepth) {
//			System.out.println("Reach to the depth");
			return Heuristic(state);
		}
		if (Is_terminal(state,whoseturn)){
//			System.out.println("This is a terminal state utility " + Heuristic(state,whoseturn) +"\n");
//			board.printGame(state);
			return utility(state,whoseturn);
		}
		int H_MAX_VALUE=Integer.MIN_VALUE;
		ArrayList<ArrayList<action>> all_action = ACTIONS(state,whoseturn);
//		System.out.println("action list for MAX: "+ all_action);
		for(ArrayList<action> a:all_action) {
//			System.out.println("MAX performing"+a);
			checkers[][] result = RESULT(a, state);
//			board.printGame(result);
			H_MAX_VALUE = Math.max(H_MAX_VALUE, H_MIN_VALUE(result,depth+1,!whoseturn));
		}
		return H_MAX_VALUE;
	}
	
	public int H_MIN_VALUE(checkers[][] state, int depth , boolean whoseturn) {
//		System.out.println("MIN LEVEL");
		if(depth == choosedepth) {
//			System.out.println("Reach to the depth");
			
			return Heuristic(state);
		}
		
		if (Is_terminal(state,whoseturn)){
			System.out.println("This is a terminal state with utility " + Heuristic(state)  + "\n");
//			board.printGame(state);
			return utility(state,whoseturn);
		}
		int H_MIN_VALUE=Integer.MAX_VALUE;
		ArrayList<ArrayList<action>> all_action = ACTIONS(state,whoseturn);
//		System.out.println("action list for MIN: "+all_action);
		for(ArrayList<action> a:all_action) {
//			System.out.println("MIN performing"+a);
			checkers[][] result = RESULT(a, state);
//			board.printGame(result);
			H_MIN_VALUE = Math.min(H_MIN_VALUE, H_MAX_VALUE(result,depth+1,!whoseturn));
		}
		return H_MIN_VALUE;
	}
	
	
	public ArrayList<ArrayList<action>> ACTIONS(checkers[][] board , boolean whoseturn){ //W_OR_B TRUE FOR W, FALSE FOR B
		ArrayList<ArrayList<action>> all_action = new ArrayList<ArrayList<action>>();
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if(board[row][col] != null && board[row][col].type == whoseturn) {
//						System.out.println(row + " " + col + " checker is valid for " + whoseturn + " round");
						ArrayList<ArrayList<action>> one_check_act_list = board[row][col].multi_validmove(board,whoseturn);
//						System.out.println("One answer for " + row + " " + col +" is " +one_check_act_list + " and its cord is "+ board[row][col].x + 
//								" " +  board[row][col].y);
						all_action.addAll(one_check_act_list);
				}
			}
			
			boolean if_big_jump = false;
			for (ArrayList<action> a:all_action) {
				if(Math.abs(a.get(0).x2 - a.get(0).x1 ) > 1) {

					if_big_jump=true;
					break;
				} 
			}
			if (if_big_jump ) {

				Iterator iteator = all_action.iterator();
				while(iteator.hasNext()) {
					ArrayList<action> next = (ArrayList<action>)iteator.next();
					if(Math.abs(next.get(0).x2 - next.get(0).x1 ) == 1) {
						iteator.remove();
					}
				}
			}
		}
		
		return all_action;
	}
	
	public ArrayList<action> MINIMAX_DECISION(){
		ArrayList<ArrayList<action>> all_action = ACTIONS(board.board, AIround);
		HashMap<ArrayList<action>,Integer> Answer=new HashMap<ArrayList<action>,Integer>();
		//System.out.println(action);
		int minvalue = Integer.MIN_VALUE;
		for(ArrayList<action> each_act: all_action) {
//			System.out.println("MINIMAX "+each_act);
			checkers[][] temp = copy(board.board);
			checkers[][] result = RESULT(each_act, temp);
//			board.printGame(result);
			Answer.put(each_act,MIN_VALUE(result,0 ,!AIround));
		}
		
		int max = Integer.MIN_VALUE;
		ArrayList<action> ans = null;
		for(ArrayList<action> key : Answer.keySet()) {
//			System.out.println(Answer.get(key) + " " + key);
			if(Answer.get(key) > max) {
				ans = key;
				max = Answer.get(key);
			}
		}		
		System.out.println("Best move is: "+ ans + ", it's utility value is: " + max);
		return ans;
	}
	
	public int MAX_VALUE(checkers[][] state, int depth , boolean whoseturn) {
//		System.out.println("MAX LEVEL");
		
		if(depth == 10) {
//			System.out.println("Reach to the depth");
			return 0;
		}
		if (Is_terminal(state,whoseturn)){
//			System.out.println("This is a terminal state utility " + utility(state,whoseturn) +"\n");
//			board.printGame(state);
			return utility(state,whoseturn);
		}
		int MAX_VALUE=Integer.MIN_VALUE;
		ArrayList<ArrayList<action>> all_action = ACTIONS(state,whoseturn);
//		System.out.println("action list for MAX: "+ all_action);
		for(ArrayList<action> a:all_action) {
//			System.out.println("MAX performing"+a);
			checkers[][] result = RESULT(a, state);
//			board.printGame(result);
			MAX_VALUE = Math.max(MAX_VALUE, MIN_VALUE(result,depth+1,!whoseturn));
		}
		return MAX_VALUE;
	}
	
	public int MIN_VALUE(checkers[][] state, int depth , boolean whoseturn) {
//		System.out.println("MIN LEVEL");
		if(depth == 10) {
//			System.out.println("Reach to the depth");
			return 0;
		}
		
		if (Is_terminal(state,whoseturn)){
//			System.out.println("This is a terminal state with utility " + utility(state,whoseturn)  + "\n");
//			board.printGame(state);
			return utility(state,whoseturn);
		}
		int MIN_VALUE=Integer.MAX_VALUE;
		ArrayList<ArrayList<action>> all_action = ACTIONS(state,whoseturn);
//		System.out.println("action list for MIN: "+all_action);
		for(ArrayList<action> a:all_action) {
//			System.out.println("MIN performing"+a);
			checkers[][] result = RESULT(a, state);
//			board.printGame(result);
			MIN_VALUE = Math.min(MIN_VALUE, MAX_VALUE(result,depth+1,!whoseturn));
		}
		return MIN_VALUE;
	}
	
	public ArrayList<action> ALPHA_BETA_DECISION(){
		int moves = 0;
		ArrayList<ArrayList<action>> all_action = ACTIONS(board.board, AIround);
		HashMap<ArrayList<action>,Integer> Answer=new HashMap<ArrayList<action>,Integer>();
//		System.out.println(action);
		int minvalue = Integer.MIN_VALUE;
		for(ArrayList<action> each_act: all_action) {
//			System.out.println("MINIMAX "+each_act);
			checkers[][] temp = copy(board.board);
			checkers[][] result = RESULT(each_act, temp);
//			board.printGame(result);
			Answer.put(each_act,MIN_VALUE(result,0 ,!AIround,Integer.MIN_VALUE,Integer.MAX_VALUE));
			moves ++;
		}
		
		int max = Integer.MIN_VALUE;
		ArrayList<action> ans = null;
		for(ArrayList<action> key : Answer.keySet()) {
//			System.out.println(Answer.get(key) + " " + key);
			if(Answer.get(key) > max) {
				ans = key;
				max = Answer.get(key);
			}
		}
		System.out.println("Best move is: "+ ans + ", it's utility value is: " + max);
		return ans;
	}
	
	public int MAX_VALUE(checkers[][] state, int depth , boolean whoseturn, int alpha, int beta) {
//		System.out.println("MAX LEVEL");
		
//		if(depth == 10) {
//			System.out.println("Reach to the depth");
//			return 0;
//		}
		if (Is_terminal(state,whoseturn)){
//			System.out.println("This is a terminal state utility " + utility(state,whoseturn) +"\n");
//			board.printGame(state);
			return utility(state,whoseturn);
		}
		int MAX_VALUE=Integer.MIN_VALUE;
		ArrayList<ArrayList<action>> all_action = ACTIONS(state,whoseturn);
//		System.out.println("action list for MAX: "+ all_action);
		for(ArrayList<action> a:all_action) {
//			System.out.println("MAX performing"+a);
			checkers[][] result = RESULT(a, state);
//			board.printGame(result);
			MAX_VALUE = Math.max(MAX_VALUE, MIN_VALUE(result,depth+1,!whoseturn,alpha,beta));
			if (MAX_VALUE >= beta) {
				return MAX_VALUE;
			}
			alpha = Math.max(MAX_VALUE, alpha);
		}
		return MAX_VALUE;
	}
	
	public int MIN_VALUE(checkers[][] state, int depth , boolean whoseturn, int alpha, int beta) {
//		System.out.println("MIN LEVEL");
		if(depth == 10) {
//			System.out.println("Reach to the depth");
			return 0;
		}
		
		if (Is_terminal(state,whoseturn)){
//			System.out.println("This is a terminal state with utility " + utility(state,whoseturn)  + "\n");
//			board.printGame(state);
			return utility(state,whoseturn);
		}
		int MIN_VALUE=Integer.MAX_VALUE;
		ArrayList<ArrayList<action>> all_action = ACTIONS(state,whoseturn);
//		System.out.println("action list for MIN: "+all_action);
		for(ArrayList<action> a:all_action) {
//			System.out.println("MIN performing"+a);
			checkers[][] result = RESULT(a, state);
//			board.printGame(result);
			MIN_VALUE = Math.min(MIN_VALUE, MAX_VALUE(result,depth+1,!whoseturn,alpha,beta));
			if (MIN_VALUE <= alpha) {
				return MIN_VALUE;
			}
		}
		return MIN_VALUE;
	}
	
	
	public int utility(checkers[][] board, boolean whoseturn) {
		if(killed_all(board)) {
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board.length;j++){
					if (board[i][j] == null){
						continue;
					}
					if (board[i][j] != null && board[i][j].type == AIround) {
						return 1;
					}else {	
						return -1;
					}
				}	
			}
		}
		if (cant_move(board, whoseturn)) {
			if (whoseturn == AIround) {
				return -1;
			}else {
				return 1;
			}
		}
		return 100;
	}
	
	public boolean Is_terminal(checkers [][] board , boolean whoseturn) {
		if (killed_all(board) || cant_move(board, whoseturn)) {
//			System.out.println(killed_all(board) + " " + cant_move(board, whoseturn));
			
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
	
	public boolean cant_move(checkers [][] board, boolean whoseturn){
		boolean isend = false;
//		if(isend) {
//			return true;
//		}
//		// draw games in 4*4 game
		for(int row = 0; row < board.length; row ++) {
			for (int col=0; col<board.length; col++) {
				if ((board[row][col] != null) && board[row][col].type == whoseturn) {
				//	System.out.println(row + " " + col);
				//	System.out.println(board[row][col].multi_validmove(board));
					if (board[row][col].validmove(board,whoseturn).isEmpty()) {
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
	
	
	
	public checkers[][] copy(checkers[][] new_board){
		checkers[][] temp = new checkers[new_board.length][new_board.length];
		for(int i = 0; i < new_board.length; i++) {
			for(int j = 0; j < new_board.length; j++) {
				if(new_board[i][j] != null) {
					temp[i][j] = new_board[i][j].clone();
				}else {
					temp[i][j] = new_board[i][j];
				}
			}
		}
		return temp;
	}
	
	public checkers[][] move(action a, checkers[][] new_board) {
		checkers[][] temp = copy(new_board);
		
		if(Math.abs(a.x2 - a.x1) > 1) {
			temp[a.x2][a.y2] = temp[a.x1][a.y1];
			temp[a.x1][a.y1] = null;
			temp[(a.x1 + a.x2)/2][(a.y1 + a.y2)/2] = null;
			
		}else{ //one step no kill
			temp[a.x2][a.y2] = temp[a.x1][a.y1];
			temp[a.x1][a.y1] = null;
		}
		
		if (!temp[a.x2][a.y2].isking && ((temp[a.x2][a.y2].type && a.y2 == 0)
				||(!temp[a.x2][a.y2].type && a.y2 == temp.length-1))) {
			temp[a.x2][a.y2].become_King();
		}
		
//		System.out.println("Previously "+ temp[a.x2][a.y2].x + " " + temp[a.x2][a.y2].y);
		temp[a.x2][a.y2].coord_change(a.x2 , a.y2);
//		System.out.println("After Changed "+ temp[a.x2][a.y2].x + " " + temp[a.x2][a.y2].y);
		return temp;

	}
	
	public checkers[][] RESULT(ArrayList<action> list, checkers[][] board) {
		checkers[][] new_board = copy(board);
		for(action a : list) {	
			new_board = move (a, new_board);
		}
		
		action a = list.get(list.size()-1); // last action
		if (!new_board[a.x2][a.y2].isking && ((new_board[a.x2][a.y2].type && a.y2 == 0)
				||(!new_board[a.x2][a.y2].type && a.y2 == new_board.length-1))) {
			new_board[a.x2][a.y2].become_King();
		}
		return new_board;
			
	}
	
}
