package eu.buzea.fiabilitate.programelor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.buzea.fiabilitate.programelor.sese.Edge;
import eu.buzea.fiabilitate.programelor.sese.Graph;
import eu.buzea.fiabilitate.programelor.sese.Node;

public class GraphReader {
	public static final String FILENAME = "./sese.txt";
	public static final Pattern pattern = Pattern.compile("(\\d+) (\\d+)");

	public Graph readFromFile() {
		Graph result;
		try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
			result = new Graph();
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(currentLine);
				if (!matcher.matches()) {
					throw new UnsupportedOperationException();
				}
				Edge edge = new Edge(new Node(matcher.group(1)), new Node(matcher.group(2)));
				result.getEdges().add(edge);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
}
