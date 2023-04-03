import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MagicRescue {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numTests = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < numTests; i++) {
			System.out.println(new Route(in.readLine()).solve());
		}
		
		in.close();
	}
	
}
