import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MapSolver {
	
	private static final char OBJECT = 'O', HOLE = 'H';
	private static final int[] DIRECTIONS = {-1, 1};
	
	private final char[][] map;
	private final int[] dimensions, movesToHole, parent;
	
	public MapSolver(char[][] map) {
		this.map = map;
		this.dimensions = new int[]{map.length, map[0].length};
		this.movesToHole = new int[dimensions[0] * dimensions[1]];
		this.parent = new int[dimensions[0] * dimensions[1]];
		Arrays.fill(this.parent, -1);
	}
	
	public int solve(int[] coords) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{--coords[0], --coords[1], 0});
		queue.add(new int[]{coords[0], coords[1], 1});
		
		boolean[] explored = new boolean[dimensions[0] * dimensions[1]];
		int startCoordsIndex = coords[0] * dimensions[1] + coords[1];
		explored[startCoordsIndex] = true;
		
		movesToHole[startCoordsIndex] = -1;
		int minMoves = Integer.MAX_VALUE;
		
		while (!queue.isEmpty()) {
			int[] currCoords = queue.poll();
			int currCoordsIndex = currCoords[0] * dimensions[1] + currCoords[1];
			
			for (int direction : DIRECTIONS) {
				int[] nextCoords = {currCoords[0], currCoords[1], currCoords[2] == 0 ? 1 : 0};
				int axis = nextCoords[2];
				
				while (nextCoords[axis] >= 0 && nextCoords[axis] < dimensions[axis]) {
					char character = map[nextCoords[0]][nextCoords[1]];
					
					if (character == OBJECT) {
						nextCoords[axis] -= direction;
						int nextCoordsIndex = nextCoords[0] * dimensions[1] + nextCoords[1];
						
						if (!explored[nextCoordsIndex]) {
							explored[nextCoordsIndex] = true;
							
							if (movesToHole[nextCoordsIndex] > 0) {
								minMoves = Math.min(minMoves, -movesToHole[currCoordsIndex] + movesToHole[nextCoordsIndex]);
							}
							else if (-movesToHole[currCoordsIndex] < minMoves) {
								queue.add(nextCoords);
								parent[nextCoordsIndex] = currCoordsIndex;
								movesToHole[nextCoordsIndex] = movesToHole[currCoordsIndex] - 1;
							}
						}
						
						break;
					}
					if (character == HOLE) {
						int moves = 1, curr = nextCoords[0] * dimensions[1] + nextCoords[1];
						parent[curr] = currCoordsIndex;
						
						while (curr != startCoordsIndex) {
							movesToHole[curr = parent[curr]] = moves++;
						}
						
						return movesToHole[curr];
					}
					
					nextCoords[axis] += direction;
				}
			}
		}
		
		return minMoves;
	}
	
}
