import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] numRegionsNumLinks = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] regions = new int[numRegionsNumLinks[0]][], links = new int[numRegionsNumLinks[1]][];
		
		for (int i = 0; i < numRegionsNumLinks[0]; i++) {
			regions[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		for (int i = 0; i < numRegionsNumLinks[1]; i++) {
			links[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int safeRegion = Integer.parseInt(in.readLine());
		
		System.out.println(new RescueByRail(regions, links).solve(safeRegion));
	}
	
}
