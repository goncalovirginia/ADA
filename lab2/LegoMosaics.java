import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LegoMosaics {
	
	private static final int[] LEGO_LENGTHS = {1, 2, 3, 4, 6, 8, 10, 12, 16};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] dimensions = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		String[] mosaic = new String[dimensions[0]];
		
		for (int i = 0; i < dimensions[0]; i++) {
			mosaic[i] = in.readLine();
		}
		
	}
	
	private static int ways(int length) {
		if (length == 0) {
			return 0;
		}
		
		int ways = 0;
		
		for (int i = 0; i < LEGO_LENGTHS.length; i++) {
			if (LEGO_LENGTHS[i] > length) {
				continue;
			}
			ways += ;
		}
		
		return ways(length - 1) + 
		
		return 0;
	}
	
	private static int solveRecursive() {
	
	}
	
}
