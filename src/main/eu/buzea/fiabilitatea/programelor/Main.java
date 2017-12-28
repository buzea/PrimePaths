package eu.buzea.fiabilitatea.programelor;

import eu.buzea.fiabilitatea.programelor.graph.Graph;

public class Main {

	public static void main(String[] args) {
		GraphReader reader = new GraphReader("sample_graph.txt");
		Graph graph = reader.readFromFile();
		GraphVerfier graphVerfier = new GraphVerfier(graph);
		graphVerfier.buildSimplePaths();
		graphVerfier.printSimplePaths();
		graphVerfier.buildPrimePaths();
		System.out.println("\nPrime paths:");
		for (SimplePath primePath : graphVerfier.getPrimePaths()) {
			System.out.println(primePath);
		}

	}

}
