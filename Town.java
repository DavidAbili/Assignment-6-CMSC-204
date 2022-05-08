
public class Town implements Comparable<Town>{

	private String name;

	public Town(String n) {
		name = n;
	}

	public Town(Town temp) {
		name = temp.name;
	}

	public String getName() {
		return name;
	}

	public int compareTo(Town o) {
		int n = name.compareTo(o.name);
		return n;
	}

	public String toString() {
		return name;
	}
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object o) {
		Town t = (Town) o;
		return name.equals(t.name);
	}

}

