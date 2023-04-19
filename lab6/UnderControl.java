import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UnderControl {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] nt = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		nt[0]++;
		
		List<List<Edge>> nodeOutEdges = new ArrayList<>(nt[0]), nodeInEdges = new ArrayList<>(nt[0]);
		
		for (int i = 0; i < nt[0]; i++) {
			nodeOutEdges.add(new LinkedList<>());
			nodeInEdges.add(new LinkedList<>());
		}
		
		for (int i = 0; i < nt[1]; i++) {
			int[] edgeData = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Edge edge = new Edge(edgeData[0], edgeData[1], edgeData[2]);
			nodeOutEdges.get(edgeData[0]).add(edge);
			nodeInEdges.get(edgeData[1]).add(edge);
		}
		
		Edge[] solution = solve(nodeOutEdges, nodeInEdges);
		System.out.println(solution.length);
		
		for (Edge edge : solution) {
			System.out.println(edge.firstNode() + " " + edge.secondNode());
		}
	}
	
	private static Edge[] solve(List<List<Edge>> nodeOutEdges, List<List<Edge>> nodeInEdges) {
		int[] topologicalSort = topologicalSort(nodeOutEdges, nodeInEdges), minTimeToCompleteNode = new int[nodeOutEdges.size()];
		Set<Edge> postponableEdges = new TreeSet<>(new SolutionComparator());
		List<Integer> updatedNodes = new LinkedList<>();
		
		for (int node : topologicalSort) {
			for (Edge edge : nodeOutEdges.get(node)) {
				int currMinTime = minTimeToCompleteNode[node] + edge.weight;
				
				if (currMinTime > minTimeToCompleteNode[edge.secondNode]) {
					minTimeToCompleteNode[edge.secondNode] = currMinTime;
					updatedNodes.add(edge.secondNode);
				}
				if (currMinTime < minTimeToCompleteNode[edge.secondNode]) {
					postponableEdges.add(edge);
				}
			}
		}
		
		for (int updatedNode : updatedNodes) {
			for (Edge inEdge : nodeInEdges.get(updatedNode)) {
				if (minTimeToCompleteNode[inEdge.firstNode] + inEdge.weight < minTimeToCompleteNode[updatedNode]) {
					postponableEdges.add(inEdge);
				}
			}
		}
		
		return postponableEdges.toArray(new Edge[0]);
	}
	
	private static int[] topologicalSort(List<List<Edge>> nodeOutEdges, List<List<Edge>> nodeInEdges) {
		int[] inDegree = new int[nodeInEdges.size()], topologicalSort = new int[nodeInEdges.size()];
		int currPosition = 0;
		LinkedList<Integer> queue = new LinkedList<>();
		
		for (int node = 0; node < nodeInEdges.size(); node++) {
			if ((inDegree[node] = nodeInEdges.get(node).size()) == 0) {
				queue.add(node);
			}
		}
		
		while (!queue.isEmpty()) {
			for (Edge edge : nodeOutEdges.get(topologicalSort[currPosition++] = queue.remove())) {
				if (--inDegree[edge.secondNode] == 0) {
					queue.add(edge.secondNode);
				}
			}
		}
		
		return topologicalSort;
	}
	
	private record Edge(int firstNode, int secondNode, int weight) {
	
	}
	
	private static class SolutionComparator implements Comparator<Edge> {
		
		@Override
		public int compare(Edge e1, Edge e2) {
			return e1.firstNode != e2.firstNode ? Integer.compare(e1.firstNode, e2.firstNode) : Integer.compare(e1.secondNode, e2.secondNode);
		}
		
	}
	
}
