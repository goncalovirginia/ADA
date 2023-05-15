import java.util.LinkedList;

public class RescueByRail {
	
	LinkedList<int[]>[] outEdges, inEdges;
	
	public RescueByRail(int[][] regions, int[][] links, int safeRegion) {
		outEdges = new LinkedList[regions.length * 2];
		inEdges = new LinkedList[regions.length * 2];
		
		outEdges[0] = new LinkedList<>();
		
		int i = 1;
		for (int[] region : regions) {
			outEdges[0].add(new int[]{i, Integer.MAX_VALUE});
			
			inEdges[i] = new LinkedList<>();
			inEdges[i].add(new int[]{0, Integer.MAX_VALUE});
			outEdges[i] = new LinkedList<>();
			outEdges[i].add(new int[]{i + 1, region[0]});
			
			inEdges[i+1] = new LinkedList<>();
			inEdges[i+1].add(new int[]{i, region[0]});
			outEdges[i+1] = new LinkedList<>();
			
			i += 2;
		}
		
		for (int[] link : links) {
			inEdges[link[1]].add(new int[]{});
			outEdges[link[0]].add(new int[]{});
		}
	}
	
	public int solve() {
		return 0;
	}
	
}
