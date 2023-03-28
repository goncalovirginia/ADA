import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static final int BIG_VALUE = 1000000, NUM_ITEMS = 4, NO_ITEM = 3, NO_MONSTER = 3;
	
	private static final int[][] COST = {
			{4, 5, 6, BIG_VALUE},
			{BIG_VALUE, 5, 6, BIG_VALUE},
			{BIG_VALUE, BIG_VALUE, 6, BIG_VALUE},
			{2, 2, 2, 1}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numTests = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < numTests; i++) {
			System.out.println(solve(in.readLine()));
		}
		
		in.close();
	}
	
	private static int solve(String route) {
		int[] temp = new int[NUM_ITEMS], prev = new int[NUM_ITEMS], curr = temp;
		
		for (int plotIndex = route.length() - 1; plotIndex >= 0; plotIndex--) {
			temp = prev;
			prev = curr;
			curr = temp;
			char plot = route.charAt(plotIndex);
			int plotInt = plotToInt(plot);
			boolean plotHasMonster = plot == '3' || plot == 't' || plot == 'd';
			
			for (int itemInt = 0; itemInt < NUM_ITEMS; itemInt++) {
				curr[itemInt] = plotHasMonster ? Math.min(COST[plotInt][itemInt] + prev[itemInt], BIG_VALUE) :
						COST[NO_MONSTER][itemInt] + Math.min(prev[NO_ITEM], 1 + Math.min(prev[itemInt], prev[plotInt]));
			}
		}
		
		return curr[NO_ITEM];
	}
	
	private static int plotToInt(char plot) {
		return switch (plot) {
			case '3', 'h' -> 0;
			case 't', 'p' -> 1;
			case 'd', 'c' -> 2;
			default -> 3;
		};
	}
	
}
