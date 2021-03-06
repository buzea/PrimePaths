package eu.buzea.fiabilitate.programelor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import eu.buzea.fiabilitatea.programelor.GraphReader;
import eu.buzea.fiabilitatea.programelor.GraphVerfier;
import eu.buzea.fiabilitatea.programelor.SimplePath;
import eu.buzea.fiabilitatea.programelor.graph.Graph;

public class TestGraphVerifier {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNonSESE() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Graph has multiple entry points");
		GraphReader reader = new GraphReader("non_sese_graph.txt");
		Graph graph = reader.readFromFile();
		new GraphVerfier(graph);
	}

	@Test
	public void testSESE() {
		GraphReader reader = new GraphReader("sese_graph.txt");
		Graph graph = reader.readFromFile();
		new GraphVerfier(graph);
	}

	@Test
	public void testSimplePaths() {
		GraphReader reader = new GraphReader("sample_graph.txt");
		Graph graph = reader.readFromFile();
		GraphVerfier graphVerfier = new GraphVerfier(graph);
		graphVerfier.buildSimplePaths();
		graphVerfier.printSimplePaths();
	}

	@Test
	public void testPrimePaths() {
		GraphReader reader = new GraphReader("sample_graph.txt");
		Graph graph = reader.readFromFile();
		GraphVerfier graphVerfier = new GraphVerfier(graph);
		graphVerfier.buildSimplePaths();
		// graphVerfier.printSimplePaths();
		graphVerfier.buildPrimePaths();
		for (SimplePath primePath : graphVerfier.getPrimePaths()) {
			System.out.println(primePath);
		}
	}
}
