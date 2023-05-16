import unionFind.EqualSetsException;
import unionFind.UnionFind;
import unionFind.UnionFindInArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PolygonPhobia {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numSegments = Integer.parseInt(in.readLine()), paintedSegments = 0, nodeCount = 0;
		UnionFind unionFind = new UnionFindInArray(numSegments * 2);
		Map<String, Integer> coordsToNode = new HashMap<>();

		for (int i = 0; i < numSegments; i++) {
			int[] segmentCoords = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			String key1 = segmentCoords[0] + " " + segmentCoords[1], key2 = segmentCoords[2] + " " + segmentCoords[3];
			coordsToNode.putIfAbsent(key1, nodeCount++);
			coordsToNode.putIfAbsent(key2, nodeCount++);

			try {
				unionFind.union(unionFind.find(coordsToNode.get(key1)), unionFind.find(coordsToNode.get(key2)));
				paintedSegments++;
			} catch (EqualSetsException ignored) {

			}
		}

		System.out.println(paintedSegments);
	}

}
