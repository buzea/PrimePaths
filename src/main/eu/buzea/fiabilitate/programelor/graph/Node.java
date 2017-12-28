package eu.buzea.fiabilitate.programelor.graph;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private final String	number;

	private final Set<Edge>	outgoingEdges	= new HashSet<>();

	public Set<Edge> getOutgoingEdges() {
		return this.outgoingEdges;
	}

	public Set<Edge> getIncomingEdges() {
		return this.incomingEdges;
	}

	private final Set<Edge> incomingEdges = new HashSet<>();

	public Node(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}

	public int getIndegree() {
		return this.incomingEdges.size();
	}

	public int getOutdegree() {
		return this.outgoingEdges.size();
	}

	@Override
	public String toString() {
		return "" + this.number + "";
	}

}
