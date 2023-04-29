
import java.util.LinkedList;
import java.util.Queue;

public class MapSolver {
	
	private static final int[][] directions = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1},
	};
	
	private final char[][] map;
	
	public MapSolver(char[][] map) {
		this.map = map;
	}
	
	public int solveBfs(int[] coords) {
		coords[0]--;
		coords[1]--;
		
		Queue<int[]> queue = new LinkedList<>();
		int[][] numMoves = new int[map.length][map[0].length];
		numMoves[coords[0]][coords[1]] = 1;
		queue.add(coords);
		
		while (!queue.isEmpty()) {
			int[] currCoords = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int[] nextCoords = exploreDirection(currCoords[0], currCoords[1], directions[i][0], directions[i][1]);
				
				if (nextCoords[0] == -1) {
					continue;
				}
				if (map[nextCoords[0]][nextCoords[1]] == 'H') {
					return numMoves[currCoords[0]][currCoords[1]];
				}
				if (numMoves[nextCoords[0]][nextCoords[1]] == 0) {
					queue.add(nextCoords);
					numMoves[nextCoords[0]][nextCoords[1]] = numMoves[currCoords[0]][currCoords[1]] + 1;
				}
			}
		}
		
		return -1;
	}
	
	private int[] exploreDirection(int x, int y, int dirX, int dirY) {
		while (x + dirX >= 0 && x + dirX < map.length && y + dirY >= 0 && y + dirY < map[0].length) {
			if (map[x][y] == 'H') {
				return new int[]{x, y};
			}
			if (map[x + dirX][y + dirY] == 'O') {
				return new int[]{x, y};
			}
			
			x += dirX;
			y += dirY;
		}
		
		return new int[]{-1, -1};
	}
	
}
