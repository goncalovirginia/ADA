import dataStructures.DiGraph;
import dataStructures.LinkedDiGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Promotions {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] abep = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		DiGraph diGraph = new LinkedDiGraph(abep[2]);
		
		for (int i = 0; i < abep[3]; i++) {
			int[] precedenceRule = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			diGraph.addEdge(precedenceRule[0], precedenceRule[1]);
		}
		
		System.out.println(Arrays.toString(numNodesPerDepth(diGraph)));
		
		int[] solution = solve(abep[0], abep[1], diGraph);
		System.out.println(solution[0]);
		System.out.println(solution[1]);
		System.out.println(abep[2] - solution[1]);
	}
	
	private static int[] solve(int a, int b, DiGraph diGraph) {
		int[] numNodesPerDepth = numNodesPerDepth(diGraph), ab = new int[2];
		int depth = 0, sum = 0;
		
		while (sum + numNodesPerDepth[depth] <= a) {
			sum += numNodesPerDepth[depth];
			depth++;
		}
		
		ab[0] = ab[1] = sum;
		
		while (sum + numNodesPerDepth[depth] <= b) {
			sum += numNodesPerDepth[depth];
			depth++;
		}
		
		ab[1] = sum;
		
		return ab;
	}
	
	private static int[] numNodesPerDepth(DiGraph diGraph) {
		int[] numNodesPerDepth = new int[diGraph.numNodes()], inDegree = new int[diGraph.numNodes()];
		LinkedList<Integer> bag = new LinkedList<>();
		int currDepth = 0;
		
		for (int node = 0; node < diGraph.numNodes(); node++) {
			if ((inDegree[node] = diGraph.inDegree(node)) == 0) {
				bag.add(node);
				numNodesPerDepth[currDepth]++;
			}
		}
		
		currDepth++;
		
		while (!bag.isEmpty()) {
			for (int i = 0, j = bag.size(); i < j; i++) {
				for (int successor : diGraph.outAdjacentNodes(bag.remove())) {
					if (--inDegree[successor] == 0) {
						bag.add(successor);
						numNodesPerDepth[currDepth]++;
					}
				}
			}
			currDepth++;
		}
		
		return numNodesPerDepth;
	}
	
}
