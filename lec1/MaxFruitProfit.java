import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaxFruitProfit {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] rowsCols = in.readLine().split(" ");
		int shops = Integer.parseInt(rowsCols[0]), cols = Integer.parseInt(rowsCols[1]);
		double[][] profit = new double[shops][cols];
		
		for (int i = 0; i < shops; i++) {
			double[] shopProfits = Arrays.stream(in.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
			for (int j = 0; j < cols; j++) {
				profit[i][j] = shopProfits[j];
			}
		}
		
		System.out.println(solve(profit));
	}
	
	private static double solve(double[][] profit) {
		int shops = profit.length, cols = profit[0].length;
		double[][] maxProfit = new double[shops][cols];
		
		for (int j = 0; j < cols; j++) {
			maxProfit[0][j] = profit[0][j];
		}
		
		for (int i = 1; i < shops; i++) {
			for (int j = 1; j < cols; j++) {
				maxProfit[i][j] = maxProfit[i - 1][j];
				for (int k = 1; k < j; k++) {
					double curr = profit[i][k] + maxProfit[i - 1][j - k];
					if (curr > maxProfit[i][j]) {
						maxProfit[i][j] = curr;
					}
				}
			}
		}
		
		return maxProfit[shops - 1][cols - 1];
	}
	
}
