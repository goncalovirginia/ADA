import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MagicRescue {
	
	private static final int BIG_VALUE = 1000000;
	
	private static final int[][] MONSTER_ITEM_COST = {
			{4, 5, 6, BIG_VALUE},
			{0, 5, 6, BIG_VALUE},
			{0, 0, 6, BIG_VALUE}
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
		char lastPlot = route.charAt(route.length() - 1);
		int[] prev = isMonster(lastPlot) ? MONSTER_ITEM_COST[index(lastPlot)] : new int[]{2, 2, 2, 1}, curr = prev;
		
		for (int plotIndex = route.length() - 2; plotIndex >= 0; plotIndex--) {
			curr = new int[4];
			char plot = route.charAt(plotIndex);
			
			for (int itemIndex = 0; itemIndex < 4; itemIndex++) {
				if (isMonster(plot)) {
					int monsterCost;
					curr[itemIndex] = itemIndex == 3 || (monsterCost = MONSTER_ITEM_COST[index(plot)][itemIndex]) == 0 ?
							BIG_VALUE : monsterCost + prev[itemIndex];
					continue;
				}
				
				int min = prev[3];
				if (itemIndex != 3) min = Math.min(min, 1 + prev[itemIndex]);
				if (plot != 'e') min = Math.min(min, 1 + prev[index(plot)]);
				curr[itemIndex] = (itemIndex == 3 ? 1 : 2) + min;
			}
			
			prev = curr;
		}
		
		return curr[3];
	}
	
	private static int index(char plot) {
		return switch (plot) {
			case '3', 'h' -> 0;
			case 't', 'p' -> 1;
			case 'd', 'c' -> 2;
			default -> 3;
		};
	}
	
	private static boolean isMonster(char plot) {
		return plot == '3' || plot== 't' || plot == 'd';
	}
	
}
