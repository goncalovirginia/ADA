import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BridgesOfKolsberg {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numSamples = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < numSamples; i++) {
			City[] citiesNorth = readCitySequence(in), citiesSouth = readCitySequence(in);
			int[] solution = solve(citiesNorth, citiesSouth);
			System.out.println(solution[0] + " " + solution[1]);
		}
		
		in.close();
	}
	
	private static City[] readCitySequence(BufferedReader in) throws IOException {
		int numCities = Integer.parseInt(in.readLine());
		City[] cities = new City[numCities + 1];
		
		for (int i = 1; i <= numCities; i++) {
			String[] cityInfo = in.readLine().split(" ");
			cities[i] = new City(cityInfo[1], Integer.parseInt(cityInfo[2]));
		}
		
		return cities;
	}
	
	private static int[] solve(City[] citiesNorth, City[] citiesSouth) {
		int[][] maxTradeValue = new int[citiesNorth.length][citiesSouth.length],
			minBridges = new int[citiesNorth.length][citiesSouth.length];
		
		for (int n = 1; n < citiesNorth.length; n++) {
			for (int s = 1; s < citiesSouth.length; s++) {
				if (citiesNorth[n].os.equals(citiesSouth[s].os)) {
					maxTradeValue[n][s] = maxTradeValue[n - 1][s - 1] + citiesNorth[n].tradeValue + citiesSouth[s].tradeValue;
					minBridges[n][s] = minBridges[n - 1][s - 1] + 1;
				}
				else {
					maxTradeValue[n][s] = Math.max(maxTradeValue[n - 1][s], maxTradeValue[n][s - 1]);
					minBridges[n][s] = maxTradeValue[n][s] == maxTradeValue[n - 1][s] ? minBridges[n - 1][s] : minBridges[n][s - 1];
				}
			}
		}
		
		return new int[] {maxTradeValue[citiesNorth.length - 1][citiesSouth.length - 1],
			minBridges[citiesNorth.length - 1][citiesSouth.length - 1]};
	}
	
	private record City(String os, int tradeValue) {
	
	}
	
}
