import java.util.ArrayList;

public class action {
	int x1,y1;
	int x2,y2;
	
	public action (int x1,int y1,int x2,int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
	}
	
	public action(String s) {
		ArrayList<Character> list = new ArrayList<>();
		list.add('A');
		list.add('B');
		list.add('C');
		list.add('D');
		list.add('E');
		list.add('F');
		list.add('G');
		list.add('H');
		this.x1 = (int)(s.charAt(1) - '0') - 1;
		this.x2 = (int)(s.charAt(4) - '0') - 1;
		this.y1 = list.indexOf(s.charAt(0));
		this.y2 = list.indexOf(s.charAt(3));
		//System.out.println(x1+" "+y1+" "+x2+" "+y2);
	}
	
	public String toString() {
		String[] magic = {"A","B","C","D","E","F","G","H"};
		return magic[y1] + (x1+1) + "-" +magic[y2] + (x2+1);
	}
	
	public boolean equals(action o) { 
		  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */

          
        // Compare the data members and return accordingly  
        return Integer.compare(this.x1, o.x1) == 0
                && Integer.compare(this.x2, o.x2) == 0
                && Integer.compare(this.y1, o.y1) == 0
                && Integer.compare(this.y2, o.y2) == 0;
    }  
}
