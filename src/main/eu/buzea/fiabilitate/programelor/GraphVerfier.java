package eu.buzea.fiabilitate.programelor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.buzea.fiabilitate.programelor.graph.Edge;
import eu.buzea.fiabilitate.programelor.graph.Graph;
import eu.buzea.fiabilitate.programelor.graph.Node;

public class GraphVerfier {

	private final Graph								graph;

	private Node									entryNode;

	private Node									exitNode;

	private final Map<Integer, List<SimplePath>>	simplePathsByLength	= new HashMap<>();

	public GraphVerfier(Graph graph) {
		this.graph = graph;
		for (Node node : graph.getNodesById().values()) {
			if (node.getIndegree() == 0) {
				if (entryNode != null) {
					throw new IllegalArgumentException("Graph has multiple entry points");
				}
				entryNode = node;
			} else if (node.getOutdegree() == 0) {
				if (exitNode != null) {
					throw new IllegalArgumentException("Graph has multiple exit points");

				}
				exitNode = node;
			}
		}
	}

	public void buildSimplePaths() {
		int maxPathLength = graph.getNodesById().size() + 1;
		for (int pathLenght = 0; pathLenght < maxPathLength; pathLenght++) {
			buildSimplePaths(pathLenght);
			if (!simplePathsByLength.containsKey(pathLenght)) {
				break;
			}
			List<SimplePath> simplePaths = simplePathsByLength.get(pathLenght);
			if (simplePaths.isEmpty()) {
				break;
			}
			System.out.println("\n" + pathLenght + "=");
			for (SimplePath simplePath : simplePaths) {
				System.out.println(simplePath);
			}
		}

	}

	public void buildSimplePaths(int length) {
		Collection<Node> nodes = graph.getNodesById().values();
		for (Node node : nodes) {
			SimplePath rootPath = new SimplePath(node);
			List<SimplePath> resultPaths = buildSimplePaths(rootPath, 0, length);
			for (SimplePath simplePath : resultPaths) {
				if (simplePath.getLength() == length) {
					List<SimplePath> simplePaths = simplePathsByLength.computeIfAbsent(length, ArrayList::new);
					simplePaths.add(simplePath);
				}
			}
		}
	}

	private List<SimplePath> buildSimplePaths(SimplePath rootPath, int index, int length) {
		Node lastNode = rootPath.getLastNode();
		ArrayList<SimplePath> result = new ArrayList<>();
		if (exitNode.equals(lastNode) || index == length) {
			result.add(rootPath);
		} else {
			for (Edge edge : lastNode.getOutgoingEdges()) {
				SimplePath forkingPath = new SimplePath(rootPath, edge.getTarget());
				if (forkingPath.isValid()) {
					List<SimplePath> resultPaths = buildSimplePaths(forkingPath, index + 1, length);
					result.addAll(resultPaths);
				}
			}
		}
		return result;
	}

}
