import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int numChildren = in.nextInt();
		int max = 0;
		
		for (int i = 0; i < numChildren; i++) {
			int numSticks = in.nextInt();
			
			for (int j = 0; j < numSticks; j++) {
				int stickNumber = in.nextInt();
				
				if (stickNumber > max) {
					max = stickNumber;
				}
			}
		}
		
		System.out.println(max);
	}
	
}
