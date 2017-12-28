package eu.buzea.fiabilitate.programelor;

import java.util.ArrayList;
import java.util.List;

import eu.buzea.fiabilitate.programelor.graph.Node;

/**
 * A path from node ni to nj is simple if
 * no node appears more than once,
 * except possibly the first and last nodes.
 * <ul>
 * <li>No internal loops
 * <li>Includes all other subpaths
 * <li>A loop is a simple path
 * </ul>
 *
 * @author Buzea Vlad-Calin (buzea.vlad@gmail.com)
 */
public class SimplePath {

	private final List<Node>	nodeList;
	private Boolean				isValid;

	public SimplePath(Node startNode) {
		nodeList = new ArrayList<>();
		nodeList.add(startNode);
	}

	public SimplePath(SimplePath rootPath, Node tailNode) {
		if (!rootPath.isValid()) {
			throw new IllegalArgumentException("Simple paths can be extended only from valid simple paths");
		}
		nodeList = new ArrayList<>();
		nodeList.addAll(rootPath.nodeList);
		nodeList.add(tailNode);
	}

	public int getLength() {
		return nodeList.size() - 1;
	}

	public Node getLastNode() {
		return nodeList.get(nodeList.size() - 1);
	}

	public boolean isValid() {
		if (isValid == null) {
			isValid = true;
			Node lastNode = getLastNode();
			for (int i = 1; i < nodeList.size() - 1; i++) {
				Node node = nodeList.get(i);
				if (node.equals(lastNode)) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	@Override
	public String toString() {
		return nodeList.toString();
	}

}
