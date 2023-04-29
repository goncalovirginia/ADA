import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] rowsColsTests = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		char[][] map = new char[rowsColsTests[0]][rowsColsTests[1]];
		
		for (int i = 0; i < rowsColsTests[0]; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		MapSolver mapSolver = new MapSolver(map);
		
		for (int i = 0; i < rowsColsTests[2]; i++) {
			int[] coords = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int solution = mapSolver.solveBfs(coords);
			
			if (solution == -1) {
				System.out.println("Stuck");
				continue;
			}
			
			System.out.println(solution);
		}
	}
	
}