import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MagicRescue {
	
	private static final int BIG_VALUE = 1000000;
	
	private static final int[][] PLOT_ITEM_COST = {
			{4, 5, 6},
			{0, 5, 6},
			{0, 0, 6}
	};
	
	private static String route;
	private static int[][] minCost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numTests = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < numTests; i++) {
			route = in.readLine();
			minCost = new int[route.length()][4];
			System.out.println(minCostMemo(0, 'n'));
		}
		
		in.close();
	}
	
	private static int index(char c) {
		return switch (c) {
			case '3', 'h' -> 0;
			case 't', 'p' -> 1;
			case 'd', 'c' -> 2;
			default -> 3;
		};
	}
	
	private static int cost(char plot, char item) {
		return PLOT_ITEM_COST[index(plot)][index(item)];
	}
	
	private static int minCost(int plotIndex, char item) {
		char plot = route.charAt(plotIndex);
		int traversalCost = item == 'n' ? 1 : 2;
		
		if (plotIndex == route.length() - 1) {
			return traversalCost;
		}
		
		if (plot == '3' || plot== 't' || plot == 'd') {
			if (item == 'n') return BIG_VALUE;
			traversalCost = cost(plot, item);
			if (traversalCost == 0) return BIG_VALUE;
			return traversalCost + minCost(plotIndex + 1, item);
		}
		
		int min = minCost(plotIndex + 1, 'n');
		if (item != 'n') min = Math.min(min, 1 + minCost(plotIndex + 1, item));
		if (plot != 'e') min = Math.min(min, 1 + minCost(plotIndex + 1, plot));
		return traversalCost + min;
	}
	
	private static int minCostMemo(int plotIndex, char item) {
		int itemIndex = index(item);
		int calculatedCost = minCost[plotIndex][itemIndex];
		
		if (calculatedCost > 0) {
			minCost[plotIndex][itemIndex] = calculatedCost;
		}
		
		char plot = route.charAt(plotIndex);
		int traversalCost = item == 'n' ? 1 : 2;
		
		if (plotIndex == route.length() - 1) {
			minCost[plotIndex][itemIndex] = traversalCost;
		}
		else if (plot == '3' || plot== 't' || plot == 'd') {
			if (item == 'n') return minCost[plotIndex][itemIndex] = BIG_VALUE;
			traversalCost = cost(plot, item);
			if (traversalCost == 0) minCost[plotIndex][itemIndex] = BIG_VALUE;
			minCost[plotIndex][itemIndex] = traversalCost + minCostMemo(plotIndex + 1, item);
		}
		else {
			int min = minCostMemo(plotIndex + 1, 'n');
			if (item != 'n') min = Math.min(min, 1 + minCostMemo(plotIndex + 1, item));
			if (plot != 'e') min = Math.min(min, 1 + minCostMemo(plotIndex + 1, plot));
			minCost[plotIndex][itemIndex] = traversalCost + min;
		}
		
		return minCost[plotIndex][itemIndex];
	}
	
}
