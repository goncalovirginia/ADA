import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numChildren = Integer.parseInt(in.readLine());
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < numChildren; i++) {
			String[] line = in.readLine().split(" ");
			int numSticks = Integer.parseInt(line[0]);
			
			for (int j = 1; j <= numSticks; j++) {
				int stickNumber = Integer.parseInt(line[j]);
				
				if (stickNumber > max) {
					max = stickNumber;
				}
			}
		}
		
		System.out.println(max);
	}
	
}
