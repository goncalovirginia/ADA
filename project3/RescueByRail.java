import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class RescueByRail {

	LinkedList<int[]>[] nodeOutEdges;

	public RescueByRail(int[][] regions, int[][] links) {
		nodeOutEdges = new LinkedList[regions.length * 2 + 1];
		nodeOutEdges[0] = new LinkedList<>();

		int currNode = 1;
		for (int[] region : regions) {
			nodeOutEdges[currNode] = new LinkedList<>();
			nodeOutEdges[currNode + 1] = new LinkedList<>();

			nodeOutEdges[0].add(new int[]{currNode, region[0]});
			nodeOutEdges[currNode].add(new int[]{0, 0});

			nodeOutEdges[currNode].add(new int[]{currNode + 1, region[1]});
			nodeOutEdges[currNode + 1].add(new int[]{currNode, 0});

			currNode += 2;
		}

		for (int[] link : links) {
			nodeOutEdges[link[0] * 2].add(new int[]{link[1] * 2 - 1, Integer.MAX_VALUE});
			nodeOutEdges[link[1] * 2 - 1].add(new int[]{link[0] * 2, 0});
			nodeOutEdges[link[1] * 2].add(new int[]{link[0] * 2 - 1, Integer.MAX_VALUE});
			nodeOutEdges[link[0] * 2 - 1].add(new int[]{link[1] * 2, 0});
		}
	}

	public int solve(int sink) {
		sink = sink * 2 - 1;
		int[][] flow = new int[nodeOutEdges.length][nodeOutEdges.length];
		int[] parent = new int[nodeOutEdges.length];
		int maxFlow = 0, increment;

		while ((increment = findPath(flow, sink, parent)) != 0) {
			maxFlow += increment;
			int currNode = sink, currParent;

			while (currNode != 0) {
				currParent = parent[currNode];
				flow[currParent][currNode] += increment;
				flow[currNode][currParent] -= increment;
				currNode = currParent;
			}
		}

		return maxFlow;
	}

	private int findPath(int[][] flow, int sink, int[] parent) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] explored = new boolean[nodeOutEdges.length];
		int[] pathIncrement = new int[nodeOutEdges.length];
		queue.add(0);
		explored[0] = true;
		pathIncrement[0] = Integer.MAX_VALUE;

		while (!queue.isEmpty()) {
			int currNode = queue.poll();

			for (int[] edge : nodeOutEdges[currNode]) {
				int nextNode = edge[0];
				int residue = edge[1] - flow[currNode][nextNode];

				if (!explored[nextNode] && residue > 0) {
					parent[nextNode] = currNode;
					pathIncrement[nextNode] = Math.min(pathIncrement[currNode], residue);

					if (nextNode == sink) {
						return pathIncrement[nextNode];
					}

					explored[nextNode] = true;
					queue.add(nextNode);
				}
			}
		}

		return 0;
	}

}
