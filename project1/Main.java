import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static final int BIG_VALUE = 1000000, NUM_ITEMS = 4, NO_ITEM = 3;
	
	private static final int[][] MONSTER_ITEM_COST = {
			{4, 5, 6, BIG_VALUE},
			{BIG_VALUE, 5, 6, BIG_VALUE},
			{BIG_VALUE, BIG_VALUE, 6, BIG_VALUE}
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
		int[] temp = new int[NUM_ITEMS], curr = temp, prev = new int[NUM_ITEMS];
		
		for (int plotIndex = route.length() - 1; plotIndex >= 0; plotIndex--) {
			curr = temp;
			char plot = route.charAt(plotIndex);
			int plotInt = plotToInt(plot);
			boolean plotHasMonster = plot == '3' || plot == 't' || plot == 'd';
			
			for (int itemIndex = 0; itemIndex < NUM_ITEMS; itemIndex++) {
				if (plotHasMonster) {
					curr[itemIndex] = Math.min(MONSTER_ITEM_COST[plotInt][itemIndex] + prev[itemIndex], BIG_VALUE);
					continue;
				}
				
				int min = prev[NO_ITEM];
				if (itemIndex != NO_ITEM) min = Math.min(min, 1 + prev[itemIndex]);
				if (plotInt != NO_ITEM) min = Math.min(min, 1 + prev[plotInt]);
				curr[itemIndex] = (itemIndex == NO_ITEM ? 1 : 2) + min;
			}
			
			temp = prev;
			prev = curr;
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
