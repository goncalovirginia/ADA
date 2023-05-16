import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class WasItADream {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] rowsColsTests = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		char[][] map = new char[rowsColsTests[0]][];

		for (int i = 0; i < rowsColsTests[0]; i++) {
			map[i] = in.readLine().toCharArray();
		}

		MapSolver mapSolver = new MapSolver(map);

		for (int i = 0; i < rowsColsTests[2]; i++) {
			int[] coords = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int solution = mapSolver.solve(coords);
			System.out.println(solution == Integer.MAX_VALUE ? "Stuck" : solution);
		}
	}

}
