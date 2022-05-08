import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph g = new Graph();

	@Override
	public boolean addRoad(String t1, String t2, int weight, String roadName) {

		if (!(g.addEdge(getTown(t1), getTown(t2), weight, roadName) == null)) {
			return true;
		}
		return false;
	}

	@Override
	public String getRoad(String t1, String t2) {
		return g.getEdge(new Town(t1), new Town(t2)).getName();
	}

	@Override
	public boolean addTown(String t) {
		return g.addVertex(new Town(t));
	}

	@Override
	public boolean containsTown(String t) {
		return g.containsVertex(new Town(t));
	}

	@Override
	public boolean containsRoadConnection(String t1, String t2) {
		return g.containsEdge(getTown(t1), getTown(t2));
	}

	@Override
	public ArrayList<String> allRoads() {
		return g.edgeSet().stream().map(Road::getName).sorted().collect(null);
	}

	@Override
	public boolean deleteRoadConnection(String t1, String t2, String r) {
		if (!(g.removeEdge(new Town(t1), new Town(t2), 0, r) == null)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String t) {
		return g.removeVertex(getTown(t));
	}

	@Override
	public ArrayList<String> allTowns() {
		return g.vertexSet().stream().map(Town::getName).sorted().collect(null);
	}

	@Override
	public ArrayList<String> getPath(String t1, String t2) {
		return g.shortestPath(getTown(t1), getTown(t2));
	}

	@Override
	public Town getTown(String t) {

		Set<Town> ts = g.vertexSet();
		Town tr = null;
		for (Town te: ts) {
			if (te.getName().equals(t)) {
				tr = te;
			}
		}
		return tr;
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException {

		Scanner scanner = new Scanner(selectedFile);

		String[] line;
		String t1 = "";
		String t2 = "";
		String r = "";
		String m = "";

		while(scanner.hasNextLine()) {

			line = scanner.nextLine().split(";");
			t2 = line[2];
			t1 = line[1];
			addTown(t1);
			addTown(t2);
			addRoad(t1, t2, Integer.parseInt(m), r);
		}

	}
}
