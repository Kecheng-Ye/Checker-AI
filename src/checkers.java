import java.util.ArrayList;
import java.util.Iterator;

public class checkers{
	int x;
	int y;
	boolean type; //true is w, false is b
	boolean isking;
	char content;
	board board;
	
	public checkers(board board,boolean type, int x, int y) {
		this.board = board;
		this.type =type;
		this.x = x;
		this.y = y;
		isking = false;
		
		if(type) {
			content = 'w';
		}else {
			content = 'b';
		}
	}
	
	public checkers(board board, boolean type, int x, int y , boolean Isking) {
		this.board = board;
		this.type =type;
		this.x = x;
		this.y = y;
		if(type) {
			content = 'w';
		}else {
			content = 'b';
		}
		if(Isking) {
			this.become_King();
		}
	}
	
	public checkers clone() {
		return new checkers(this.board,this.type,this.x,this.y,this.isking);
	}
	
	public void coord_change(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void become_King() {		
		this.isking = true;
		if(type) {
			content = 'W';
		} else {
			content = 'B';
		}
		isking = true;
		
	}
	
	public String toString() {
		return Character.toString(content);
	}
	
	
	public ArrayList<action> validmove(checkers[][] board) { 
		ArrayList<action> list =new ArrayList<action>();
		if (type != this.board.turn) return null;
		if(! isking && type ) {
			if(x-1>=0 && y-1>=0) { //white go left
				if (board[x-1][y-1] == null) {
					list.add(new action(x,y,x-1,y-1));
				}else {
					if (board[x-1][y-1].type == false) {
						if (x-2>=0 && y-2>=0 && board[x-2][y-2] == null) {
							list.add(new action(x,y,x-2,y-2));
						}
					}
				}
			}
			if (x+1 < board.length && y-1>=0) { //white go right 
				if (board[x+1][y-1] == null) {
					list.add(new action(x,y,x+1,y-1));
				}else {
					if (board[x+1][y-1].type == false) {
						if (x+2<board.length && y-2>=0 && board[x+2][y-2] == null) {
							list.add(new action(x,y,x+2,y-2));
						}
					}
				}
			}
		}
		else if (! isking && ! type) {
			if(x-1>=0 && y+1<board.length) { //black go left
				if (board[x-1][y+1] == null) {
					list.add(new action(x,y,x-1,y+1));
				}else {
					if (board[x-1][y+1].type == true) {
						if (x-2>=0 && y+2<board.length && board[x-2][y+2] == null) {
							list.add(new action(x,y,x-2,y+2));
						}
					}
				}
			}
			if (x+1 < board.length && y+1<board.length) { //black go right
				if (board[x+1][y+1] == null) {
					list.add(new action(x,y,x+1,y+1));
				}else {
					if (board[x+1][y+1].type == true) {
						if (x+2<board.length && y+2<board.length && board[x+2][y+2] == null) {
							list.add(new action(x,y,x+2,y+2));
						}
					}
				}
			}
		}
		else if (isking && type) {
			if (x-1>=0 && y-1>=0) {  //white go up left 
				if (board[x-1][y-1] == null) {
					list.add(new action(x,y,x-1,y-1));
				}else {
					if (board[x-1][y-1].type == false) {
						if (x-2>=0 && y-2>=0 && board[x-2][y-2] == null) {
							list.add(new action(x,y,x-2,y-2));
						}
					}
				}
			}
			if (x+1 < board.length && y-1>=0) { //white go up right 
					if (board[x+1][y-1] == null) {
						list.add(new action(x,y,x+1,y-1));
					}else {
						if (board[x+1][y-1].type == false) {
							if (x+2<board.length && y-2>=0 && board[x+2][y-2] == null) {
								list.add(new action(x,y,x+2,y-2));
							}
						}
					}
			}
			if(x-1>=0 && y+1<board.length) { //white go down left
					if (board[x-1][y+1] == null) {
						list.add(new action(x,y,x-1,y+1));
					}else {
						if (board[x-1][y+1].type == false) {
							if (x-2>=0 && y+2<board.length && board[x-2][y+2] == null) {
								list.add(new action(x,y,x-2,y+2));
							}
						}
					}
			}
			if (x+1 < board.length && y+1 < board.length) { //white go down right
					if (board[x+1][y+1] == null) {
						list.add(new action(x,y,x+1,y+1));
					}else {
						if (board[x+1][y+1].type == false) {
							if (x+2<board.length && y+2<board.length && board[x+2][y+2] == null) {
								list.add(new action(x,y,x+2,y+2));
							}
						}
					}
			}
			
		}
		
		else if (isking && !type) {
			if (x-1>=0 && y-1>=0) {  //black go up left 
				if (board[x-1][y-1] == null) {
					list.add(new action(x,y,x-1,y-1));
				}else {
					if (board[x-1][y-1].type == true) {
						if (x-2>=0 && y-2>=0 && board[x-2][y-2] == null) {
							list.add(new action(x,y,x-2,y-2));
						}
					}
				}
			}
			if (x+1 < board.length && y-1>=0) { //black go up right 
					if (board[x+1][y-1] == null) {
						list.add(new action(x,y,x+1,y-1));
					}else {
						if (board[x+1][y-1].type == true) {
							if (x+2<board.length && y-2>=0 && board[x+2][y-2] == null) {
								list.add(new action(x,y,x+2,y-2));
							}
						}
					}
			}
			if(x-1>=0 && y+1<board.length) { //black go down left
					if (board[x-1][y+1] == null) {
						list.add(new action(x,y,x-1,y+1));
					}else {
						if (board[x-1][y+1].type == true) {
							if (x-2>=0 && y+2<board.length && board[x-2][y+2] == null) {
								list.add(new action(x,y,x-2,y+2));
							}
						}
					}
			}
			if (x+1 < board.length && y+1 < board.length) { //black go down right
					if (board[x+1][y+1] == null) {
						list.add(new action(x,y,x+1,y+1));
					}else {
						if (board[x+1][y+1].type == true) {
							if (x+2<board.length && y+2<board.length && board[x+2][y+2] == null) {
								list.add(new action(x,y,x+2,y+2));
							}
						}
					}
			}
		}
			
		return list;
		
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
		
//		if (!temp[a.x2][a.y2].isking && ((temp[a.x2][a.y2].type && a.y2 == 0)
//				||(!temp[a.x2][a.y2].type && a.y2 == temp.length-1))) {
//			temp[a.x2][a.y2].become_King();
//		}
		
		temp[a.x2][a.y2].coord_change(a.x2 , a.y2);
		return temp;

	}
	
	public checkers[][] fake_move(ArrayList<action> list, checkers[][] new_board) {
		for(action a : list) {	
			new_board = move (a, new_board);
		}
		
//		action a = list.get(list.size()-1); // last action
//		if (!new_board[a.x2][a.y2].isking && ((new_board[a.x2][a.y2].type && a.y2 == 0)
//				||(!new_board[a.x2][a.y2].type && a.y2 == new_board.length-1))) {
//			new_board[a.x2][a.y2].become_King();
//		}
		return new_board;
			
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
	
	public ArrayList<ArrayList<action>> multi_validmove (checkers[][] new_board) { 
		checkers[][]  board = copy(new_board);
		
//		printGame(board);
		ArrayList<ArrayList<action>> actionlist = new ArrayList<ArrayList<action>>();
		ArrayList<action> list = validmove(board);
		if(list == null) {
			return null;
		}
		for(action each_move : list) {
			ArrayList<action> one_Ans = new ArrayList<action>();
			if(Math.abs(each_move.x2 - each_move.x1) == 1) { //one step no kill
				one_Ans.add(each_move);
				ArrayList<action> temp = new ArrayList<action>(one_Ans);
				actionlist.add(temp);
			}else if(Math.abs(each_move.x2 - each_move.x1) > 1) { //two step jump
				one_Ans.add(each_move);
				checkers[][] temp = move(each_move, board);
				ArrayList<action> sublist = temp[each_move.x2][each_move.y2].validmove(temp);
				sub_routine(temp, sublist, one_Ans,actionlist);
			}
		}
		boolean if_big_jump = false;
		for (ArrayList<action> a:actionlist) {
			if(a.get(0).x2 - a.get(0).x1 > 1) {
				if_big_jump=true;
				break;
			} 
		}
		if (if_big_jump = true) {
			for (ArrayList<action> b:actionlist) {
				if (b.get(0).x2 - b.get(0).x1 == 1) {
					actionlist.remove((ArrayList<action>) b);
				}
			}
		}
		return actionlist;
		
	}
	
	
	public ArrayList<action> validmove(checkers[][] board , boolean whoseturn) { 
		ArrayList<action> list =new ArrayList<action>();
		if (type != whoseturn) return null;
		if(! isking && type ) {
			if(x-1>=0 && y-1>=0) { //white go left
				if (board[x-1][y-1] == null) {
					list.add(new action(x,y,x-1,y-1));
				}else {
					if (board[x-1][y-1].type == false) {
						if (x-2>=0 && y-2>=0 && board[x-2][y-2] == null) {
							list.add(new action(x,y,x-2,y-2));
						}
					}
				}
			}
			if (x+1 < board.length && y-1>=0) { //white go right 
				if (board[x+1][y-1] == null) {
					list.add(new action(x,y,x+1,y-1));
				}else {
					if (board[x+1][y-1].type == false) {
						if (x+2<board.length && y-2>=0 && board[x+2][y-2] == null) {
							list.add(new action(x,y,x+2,y-2));
						}
					}
				}
			}
		}
		else if (! isking && ! type) {
			if(x-1>=0 && y+1<board.length) { //black go left
				if (board[x-1][y+1] == null) {
					list.add(new action(x,y,x-1,y+1));
				}else {
					if (board[x-1][y+1].type == true) {
						if (x-2>=0 && y+2<board.length && board[x-2][y+2] == null) {
							list.add(new action(x,y,x-2,y+2));
						}
					}
				}
			}
			if (x+1 < board.length && y+1<board.length) { //black go right
				if (board[x+1][y+1] == null) {
					list.add(new action(x,y,x+1,y+1));
				}else {
					if (board[x+1][y+1].type == true) {
						if (x+2<board.length && y+2<board.length && board[x+2][y+2] == null) {
							list.add(new action(x,y,x+2,y+2));
						}
					}
				}
			}
		}
		else if (isking && type) {
			if (x-1>=0 && y-1>=0) {  //white go up left 
				if (board[x-1][y-1] == null) {
					list.add(new action(x,y,x-1,y-1));
				}else {
					if (board[x-1][y-1].type == false) {
						if (x-2>=0 && y-2>=0 && board[x-2][y-2] == null) {
							list.add(new action(x,y,x-2,y-2));
						}
					}
				}
			}
			if (x+1 < board.length && y-1>=0) { //white go up right 
					if (board[x+1][y-1] == null) {
						list.add(new action(x,y,x+1,y-1));
					}else {
						if (board[x+1][y-1].type == false) {
							if (x+2<board.length && y-2>=0 && board[x+2][y-2] == null) {
								list.add(new action(x,y,x+2,y-2));
							}
						}
					}
			}
			if(x-1>=0 && y+1<board.length) { //white go down left
					if (board[x-1][y+1] == null) {
						list.add(new action(x,y,x-1,y+1));
					}else {
						if (board[x-1][y+1].type == false) {
							if (x-2>=0 && y+2<board.length && board[x-2][y+2] == null) {
								list.add(new action(x,y,x-2,y+2));
							}
						}
					}
			}
			if (x+1 < board.length && y+1 < board.length) { //white go down right
					if (board[x+1][y+1] == null) {
						list.add(new action(x,y,x+1,y+1));
					}else {
						if (board[x+1][y+1].type == false) {
							if (x+2<board.length && y+2<board.length && board[x+2][y+2] == null) {
								list.add(new action(x,y,x+2,y+2));
							}
						}
					}
			}
			
		}
		
		else if (isking && !type) {
			if (x-1>=0 && y-1>=0) {  //black go up left 
				if (board[x-1][y-1] == null) {
					list.add(new action(x,y,x-1,y-1));
				}else {
					if (board[x-1][y-1].type == true) {
						if (x-2>=0 && y-2>=0 && board[x-2][y-2] == null) {
							list.add(new action(x,y,x-2,y-2));
						}
					}
				}
			}
			if (x+1 < board.length && y-1>=0) { //black go up right 
				if (board[x+1][y-1] == null) {
						list.add(new action(x,y,x+1,y-1));
				}
				else {
					if (board[x+1][y-1].type == true) {
						if (x+2<board.length && y-2>=0 && board[x+2][y-2] == null) {
							list.add(new action(x,y,x+2,y-2));		
						}
					}
				}
			}
			if(x-1>=0 && y+1<board.length) { //black go down left
					if (board[x-1][y+1] == null) {
						list.add(new action(x,y,x-1,y+1));
					}else {
						if (board[x-1][y+1].type == true) {
							if (x-2>=0 && y+2<board.length && board[x-2][y+2] == null) {
								list.add(new action(x,y,x-2,y+2));
							}
						}
					}
			}
			if (x+1 < board.length && y+1 < board.length) { //black go down right
					if (board[x+1][y+1] == null) {
						list.add(new action(x,y,x+1,y+1));
					}else {
						if (board[x+1][y+1].type == true) {
							if (x+2<board.length && y+2<board.length && board[x+2][y+2] == null) {
								list.add(new action(x,y,x+2,y+2));
							}
						}
					}
			}
		}
			
		return list;
		
	}
	
	public ArrayList<ArrayList<action>> multi_validmove (checkers[][] new_board, boolean whoesturn) { 
		checkers[][]  board = copy(new_board);
		
//		printGame(board);
		ArrayList<ArrayList<action>> actionlist = new ArrayList<ArrayList<action>>();
		ArrayList<action> list = validmove(board, whoesturn);
		if(list == null) {
			return null;
		}
		for(action each_move : list) {
			ArrayList<action> one_Ans = new ArrayList<action>();
			if(Math.abs(each_move.x2 - each_move.x1) == 1) { //one step no kill
				one_Ans.add(each_move);
				ArrayList<action> temp = new ArrayList<action>(one_Ans);
				actionlist.add(temp);
			}else if(Math.abs(each_move.x2 - each_move.x1) > 1) { //two step jump
				one_Ans.add(each_move);
				checkers[][] temp = move(each_move, board);
				ArrayList<action> sublist = temp[each_move.x2][each_move.y2].validmove(temp);
				sub_routine(temp, sublist, one_Ans,actionlist);
				

			}
		}
		
		boolean if_big_jump = false;
		for (ArrayList<action> a:actionlist) {
			if(Math.abs(a.get(0).x2 - a.get(0).x1 ) > 1) {
//				System.out.println(a);
				if_big_jump=true;
				break;
			} 
		}
		if (if_big_jump ) {
//			for (ArrayList<action> b:actionlist) {
//				if (Math.abs(b.get(0).x2 - b.get(0).x1 ) == 1) {
//					actionlist.remove((ArrayList<action>) b);
//				}
//			}
			Iterator iteator = actionlist.iterator();
			while(iteator.hasNext()) {
				ArrayList<action> next = (ArrayList<action>)iteator.next();
				if(Math.abs(next.get(0).x2 - next.get(0).x1 ) == 1) {
					iteator.remove();
				}
			}
		}
		
		return actionlist;
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
		}
	
	public void sub_routine(checkers[][] board, ArrayList<action> list, ArrayList<action> one_ans, ArrayList<ArrayList<action>> final_ans){
		if(list == null || only_one_step(list)) {
			ArrayList<action> one_final_ans = new ArrayList<action>(one_ans);
			final_ans.add(one_final_ans);
			return;
		}else {
			checkers[][] temp = copy(board);
			for(action each_move: list) {
				if(Math.abs(each_move.x2 - each_move.x1) == 1) {
					continue;
				}else {	
					one_ans.add(each_move);
					checkers[][] temp2 = move(each_move, temp);
					ArrayList<action> next_move = temp2[each_move.x2][each_move.y2].validmove(temp2);
					sub_routine(temp2 , next_move, one_ans , final_ans);
					one_ans.remove(each_move);
				}
			}
		}
	}
	
	public boolean only_one_step(ArrayList<action> list) {
		boolean ans = true;
		for(action each_move: list) {
			if(Math.abs(each_move.x2 - each_move.x1) == 1) {
				continue;
			}else {
				return false;
			}
		}
		
		return ans;
	}
	
	public String toString1() {
		return x + " " + y + " " + type + " " + isking;
	}
	
	
	
}