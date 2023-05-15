import java.util.Arrays;
import java.util.LinkedList;

public class RescueByRail {
	
	LinkedList<int[]>[] outEdges;
	
	public RescueByRail(int[][] regions, int[][] links, int safeRegion) {
		outEdges = new LinkedList[regions.length * 2];
		outEdges[0] = new LinkedList<>();
		
		int i = 1;
		for (int[] region : regions) {
			outEdges[0].add(new int[]{i, Integer.MAX_VALUE});
			outEdges[i] = new LinkedList<>();
			outEdges[i].add(new int[]{i + 1, Math.min(0, region[1] - region[0])});
			outEdges[i+1] = new LinkedList<>();
			
			i += 2;
		}
		
		for (int[] link : links) {
			outEdges[link[0] + 1].add(new int[]{link[1], Integer.MAX_VALUE});
		}

		outEdges[safeRegion].add(new int[]{i, Integer.MAX_VALUE});

		System.out.println(Arrays.toString(outEdges));
	}
	
	public int solve() {
		return 0;
	}
	
}
