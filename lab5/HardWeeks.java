import graph.DiGraph;
import graph.LinkedDiGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class HardWeeks {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] tpl = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		DiGraph diGraph = new LinkedDiGraph(tpl[0]);

		for (int i = 0; i < tpl[1]; i++) {
			int[] precedenceRule = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			diGraph.addEdge(precedenceRule[0], precedenceRule[1]);
		}

		int[] solution = solve(tpl[2], diGraph);
		System.out.println(solution[0] + " " + solution[1]);

		in.close();
	}

	private static int[] solve(int limit, DiGraph diGraph) {
		int[] numNodesPerDepth = numNodesPerDepth(diGraph);
		int maxTasksInSingleWeek = 0, numHardWeeks = 0;

		for (int numNodes : numNodesPerDepth) {
			if (numNodes > maxTasksInSingleWeek) {
				maxTasksInSingleWeek = numNodes;
			}
			if (numNodes > limit) {
				numHardWeeks++;
			}
		}

		return new int[]{maxTasksInSingleWeek, numHardWeeks};
	}

	private static int[] numNodesPerDepth(DiGraph diGraph) {
		int[] numNodesPerDepth = new int[diGraph.numNodes()], inDegree = new int[diGraph.numNodes()];
		LinkedList<Integer> queue = new LinkedList<>();
		int currDepth = 0;

		for (int node = 0; node < diGraph.numNodes(); node++) {
			if ((inDegree[node] = diGraph.inDegree(node)) == 0) {
				queue.add(node);
				numNodesPerDepth[currDepth]++;
			}
		}

		currDepth++;

		while (!queue.isEmpty()) {
			for (int i = 0, j = queue.size(); i < j; i++) {
				for (int successor : diGraph.outAdjacentNodes(queue.remove())) {
					if (--inDegree[successor] == 0) {
						queue.add(successor);
						numNodesPerDepth[currDepth]++;
					}
				}
			}
			currDepth++;
		}

		return numNodesPerDepth;
	}

}
