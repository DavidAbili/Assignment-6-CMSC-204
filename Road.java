
public class Road implements Comparable<Road> {

	private Town s;
	private Town des;
	private String name;
	private int w;

	public Road(Town s, Town de, int d, String n) {
		this.s=s;
		des = de;
		w=d;
		name=n;
	}

	public Road(Town s, Town de, String n) {
		this.s=s;
		des = de;
		name=n;
		w=1;
	}

	public String toString() {
		return "The source is " + s+ "your desination is " + des + "the degree is " + w + "and the name is " + name;
	}

	public String getName() {
		return name;
	}

	public Town getDesination() {
		return des;
	}
	public Town getSource() {
		return s;
	}

	public int getWeight() {
		return w;
	}
	public int compareTo(Road o) {
		int n = name.compareTo(o.name);
		return n;

	}

	public boolean equals(Object o) {
		Road r = (Road) o;
		return name.equals(r.name) && des.equals(r.des) || name.equals(r.des) && des.equals(r.name) ;
	}
	public boolean contains(Town t) {
		boolean v = s.getName().equals(t.getName()) || des.getName().equals(t.getName());
		return v;
	}
}
