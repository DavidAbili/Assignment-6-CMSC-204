import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road> {


	private Set<Town> t;
	private Set<Road> r;
	private Map <String, Town> pVert;
	private final int MAX = Integer.MAX_VALUE;

	Graph(){
		t = new HashSet<>();
		r = new HashSet<>();
		pVert = new HashMap<>();
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {

		for ( Road ro: r)
		{
			if ( ro.getSource().equals(sourceVertex) )
				if (ro.getDesination().equals(destinationVertex))
					return ro;
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {


		Road theR = new Road(sourceVertex, destinationVertex, weight, description);
		r.add(theR);

		if (!(containsEdge(sourceVertex, destinationVertex)))
			return null;
		else
			return theR;
	}

	@Override
	public boolean addVertex(Town v) {

		if (containsVertex(v)) {
			return false;
		}
		else 
			t.add(v);
		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {

		for ( Road ro: r)
		{
			if ((ro.getSource().equals(sourceVertex) ))
				if(ro.getDesination().equals(destinationVertex)) 
					return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {

		boolean val = false;
		for (Town thisVertex : t) {
			if (thisVertex.equals(v))

				val = true;
		}
		return val;
	}

	@Override
	public Set<Road> edgeSet() {
		return r;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {

		Set<Road> e = new HashSet<>();

		for (Road tE : r) {
			if (tE.getSource().equals(vertex) || tE.getDesination().equals(vertex) ) {
				e.add(tE);
			}
		}
		return e;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

		Road re = new Road(sourceVertex,destinationVertex,weight,description);

		if(containsVertex(destinationVertex))
			if (containsVertex(sourceVertex))
				for (Road ro : r) {
					if (ro.equals(re)) {
						r.remove(ro);
						return re;
					}
				}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {

		
			for (Town to: t)
			{
				if (to.equals(v))
				{
					for (Road ro: edgesOf(to))
						r.remove(ro);
					t.remove(to);
					return true;
		
			}
		}
		return false;
	}

	@Override
	public Set<Town> vertexSet() {
		return t;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> val = new ArrayList<String>();

		dijkstraShortestPath(sourceVertex);
		Town nex = destinationVertex;

		while (!nex.equals(sourceVertex)) {
			if (!pVert.containsKey(nex.getName())) {
				val.clear();
			}

			Town prev = pVert.get(nex.getName());

			if (prev == null) 
				return val;
				
			Road ed = getEdge(prev, nex);
			val.add(0, prev.getName() + " via " + ed.getName() + " to " + nex.getName() + " " + ed.getWeight() + " mi");
			nex = prev;
		}
		return val;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {

		ArrayList<Town> u = new ArrayList<Town>();
		Set<Town> v = new HashSet<Town>(); 
		HashMap<String, Integer> d = new HashMap<String, Integer>();
		pVert.clear(); 

		for (Town to : t) {

			d.put(to.getName(), MAX);
			pVert.put(to.getName(), null);
			u.add(to);
		}
		d.put(sourceVertex.getName(), 1);
		while (!u.isEmpty()) {
			int s = 0; 

			for (int i = 0; i < u.size(); i++) {

				Town vert = u.get(i);

				if (!( d.get(u.get(s).getName()) < d.get(vert.getName()))) {
					s = i;
				}		
			}

			Town c = u.remove(s);
			v.add(c);
			for (Road ec : edgesOf(c)) {

				Town ad = ec.getDesination();

				if (!(ad.equals(c))) {

				} else {
					ad = ec.getSource();
				}

				int a = ec.getWeight() + d.get(c.getName()) ;

				if (!(a > d.get(ad.getName()))) {
					pVert.put(ad.getName(), c);
					d.put(ad.getName(), a);
				}
			}
		}
	}
}
