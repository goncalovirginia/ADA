import java.util.LinkedList;
import java.util.Queue;

public class MapSolver {
	
	private static final char OBJECT = 'O', HOLE = 'H';
	private static final int[] directions = {-1, 1};
	
	private final char[][] map;
	private final int[] dimensions;
	
	public MapSolver(char[][] map) {
		this.map = map;
		this.dimensions = new int[]{map.length, map[0].length};
	}
	
	public int solveBfs(int[] coords) {
		int[][] numMoves = new int[dimensions[0]][dimensions[1]];
		numMoves[--coords[0]][--coords[1]] = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(coords);
		queue.add(coords);
		
		while (!queue.isEmpty()) {
			int[] currCoords = queue.poll();
			int axis = numMoves[currCoords[0]][currCoords[1]] > 0 ? 0 : 1;
			
			for (int direction : directions) {
				int[] nextCoords = {currCoords[0], currCoords[1]};
				
				while (nextCoords[axis] >= 0 && nextCoords[axis] < dimensions[axis]) {
					if (map[nextCoords[0]][nextCoords[1]] == OBJECT) {
						nextCoords[axis] -= direction;
						
						if (numMoves[nextCoords[0]][nextCoords[1]] == 0) {
							queue.add(nextCoords);
							numMoves[nextCoords[0]][nextCoords[1]] = -numMoves[currCoords[0]][currCoords[1]] + (axis == 0 ? -1 : 1);
						}
						
						break;
					}
					
					if (map[nextCoords[0]][nextCoords[1]] == HOLE) {
						return Math.abs(numMoves[currCoords[0]][currCoords[1]]);
					}
					
					nextCoords[axis] += direction;
				}
			}
			
			numMoves[currCoords[0]][currCoords[1]] = -numMoves[currCoords[0]][currCoords[1]];
		}
		
		return -1;
	}
	
}
