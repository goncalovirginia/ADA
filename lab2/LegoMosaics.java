import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LegoMosaics {

	private static final int[] LEGO_LENGTHS = {16, 12, 10, 8, 6, 4, 3, 2, 1};
	private static int[] ways;
	private static int highestLengthCalculated = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] dimensions = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ways = new int[dimensions[1] + 1];
		ways[0] = 1;
		ways[1] = 1;
		String[] mosaic = new String[dimensions[0]];

		for (int i = 0; i < dimensions[0]; i++) {
			mosaic[i] = in.readLine();
		}

		System.out.println(solve(mosaic));
	}

	private static int solve(String[] mosaic) {
		int ways = 1, segmentStart;

		for (String row : mosaic) {
			int j = 0;

			while (j < row.length()) {
				char currChar = row.charAt(j);

				if (currChar == '.') {
					j++;
					continue;
				}

				segmentStart = j;

				do {
					j++;
				} while (j < row.length() && row.charAt(j) == currChar);

				ways *= ways(j - segmentStart);
			}
		}

		return ways;
	}

	private static int ways(int length) {
		while (highestLengthCalculated < length) {
			highestLengthCalculated++;
			for (int legoLength : LEGO_LENGTHS) {
				if (legoLength <= highestLengthCalculated) {
					ways[highestLengthCalculated] += ways[highestLengthCalculated - legoLength];
				}
			}
		}
		return ways[length];
	}

}
