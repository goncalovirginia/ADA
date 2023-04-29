
import java.util.LinkedList;
import java.util.Queue;

public class MapSolver {
	
	private final char[][] map;
	
	public MapSolver(char[][] map) {
		this.map = map;
	}
	
	public int solveBfs(int[] coords) {
		coords[0]--;
		coords[1]--;
		
		Queue<int[]> queue = new LinkedList<>();
		int[][] numMoves = new int[map.length][map[0].length];
		queue.add(coords);
		numMoves[coords[0]][coords[1]] = 1;
		
		while (!queue.isEmpty()) {
			int[] currCoords = queue.poll();
			int[][] nextCoords = new int[4][2];
			nextCoords[0] = exploreDirection(currCoords[0], currCoords[1], -1, 0);
			nextCoords[1] = exploreDirection(currCoords[0], currCoords[1], 1, 0);
			nextCoords[2] = exploreDirection(currCoords[0], currCoords[1], 0, -1);
			nextCoords[3] = exploreDirection(currCoords[0], currCoords[1], 0, 1);
			
			for (int i = 0; i < 4; i++) {
				int[] iCoords = nextCoords[i];
				
				if (iCoords[0] == -1) {
					continue;
				}
				if (map[iCoords[0]][iCoords[1]] == 'H') {
					return numMoves[currCoords[0]][currCoords[1]];
				}
				if (numMoves[iCoords[0]][iCoords[1]] == 0) {
					queue.add(iCoords);
					numMoves[iCoords[0]][iCoords[1]] = numMoves[currCoords[0]][currCoords[1]] + 1;
				}
			}
		}
		
		return -1;
	}
	
	private int[] exploreDirection(int x, int y, int dirX, int dirY) {
		while (x + dirX >= 0 && x + dirX < map.length && y + dirY >= 0 && y + dirY < map[0].length) {
			if (map[x + dirX][y + dirY] == 'O') {
				return new int[]{x, y};
			}
			
			x += dirX;
			y += dirY;
			
			if (map[x][y] == 'H') {
				return new int[]{x, y};
			}
		}
		
		return new int[]{-1, -1};
	}
	
}
