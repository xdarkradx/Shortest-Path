import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

//Node class a.k.a Vertex
class Vertex implements Comparable<Vertex> {
	public String name; // Name of the node
	public Edge[] adjacencies; // List of adjacencies
	public double minDistance = Double.POSITIVE_INFINITY; // Initiate the
															// minDistance,
															// infinity if not
															// visited yet
	public Vertex previous; // Reference to the last node

	public Vertex(String Name) {
		name = Name;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

}

// Edge class for each edge of the node
class Edge {
	public Vertex target;
	public int weight;

	public Edge(Vertex Target, int Weight) {
		target = Target;
		weight = Weight;
	}
}

public class ShortestPath_MkII {
	// Implementation of Dijkstra's algorithm
	public static void calculateDistance(Vertex startVertex) {
		// Initiate the minDistance for start Vertex to add to the queue
		startVertex.minDistance = 0.;
		// minDistance as the priority
		// The queue only have the vertex that has distance, not infinity
		PriorityQueue<Vertex> frontier = new PriorityQueue<Vertex>();
		frontier.add(startVertex);
		// Visit each vertex which has the smallest minDistance first
		while (!frontier.isEmpty()) {
			// Remove the vertex from queue
			Vertex u = frontier.poll();
			// Visit each of its edge and adjust minDistance
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					frontier.remove(u);
					// If this is the shortest path to v, then set the
					// minDistance accordingly
					v.minDistance = distanceThroughU;
					v.previous = u;
					frontier.add(v);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		// Initiate a list to store all vertices
		List<Vertex> list = new ArrayList<Vertex>();

		// Initiate all the vertices
		Vertex A = new Vertex("A");
		list.add(A);
		Vertex B = new Vertex("B");
		list.add(B);
		Vertex C = new Vertex("C");
		list.add(C);
		Vertex D = new Vertex("D");
		list.add(D);
		Vertex E = new Vertex("E");
		list.add(E);
		Vertex F = new Vertex("F");
		list.add(F);
		Vertex G = new Vertex("G");
		list.add(G);
		Vertex H = new Vertex("H");
		list.add(H);
		Vertex I = new Vertex("I");
		list.add(I);
		Vertex J = new Vertex("J");
		list.add(J);
		Vertex K = new Vertex("K");
		list.add(K);
		Vertex L = new Vertex("L");
		list.add(L);
		Vertex M = new Vertex("M");
		list.add(M);
		Vertex N = new Vertex("N");
		list.add(N);
		Vertex O = new Vertex("O");
		list.add(O);
		Vertex P = new Vertex("P");
		list.add(P);
		Vertex Q = new Vertex("Q");
		list.add(Q);
		Vertex R = new Vertex("R");
		list.add(R);
		Vertex S = new Vertex("S");
		list.add(S);
		Vertex T = new Vertex("T");
		list.add(T);
		Vertex U = new Vertex("U");
		list.add(U);
		Vertex V = new Vertex("V");
		list.add(V);
		Vertex W = new Vertex("W");
		list.add(W);
		Vertex X = new Vertex("X");
		list.add(X);
		Vertex Y = new Vertex("Y");
		list.add(Y);
		Vertex Z = new Vertex("Z");
		list.add(Z);

		//Read input file
		File f = new File("Data.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {

			// Add adjacencies for each vertex

			String line;
			while ((line = br.readLine()) != null) {

				// Read the file line by line
				// Remove white space between the letter
				String[] details = line.split("\\s");
				String target = details[0];
				String source = details[1];
				int weight = Integer.parseInt(details[2]);

				// Testing for reading the right input
				// System.out.println(source + " " + target + " " + weight);

				// For each vertex in array
				// Check if the vertex need to have another edge by reading the
				// file
				for (Vertex vStart : list) {
					if (vStart.name.equals(source)) {
						// If the vertex needs new edge
						// Loop through the list again to find the target
						for (Vertex vEnd : list) {
							if (vEnd.name.equals(target)) {
								// Connect the target and source with new
								// weighted edge
								vEnd.adjacencies = new Edge[] { new Edge(vStart, weight) };
							}
						}
					}
				}

			} // end of each line

		} // end file reading

		// Read user input
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter start: ");
		String startInput = reader.next();
		System.out.print("Enter end: ");
		String endInput = reader.next();

		// Initiate the distance and use for error checking later
		double distance = 0.0;

		// Loop through the list to find the start vertex
		// Then compute all the route from start to every node
		for (Vertex VStartInput : list) {
			if (VStartInput.name.equals(startInput)) {
				
				//Test parsing the input
				//System.out.println(VStartInput.name + " " + VStartInput.adjacencies);
				
				//Check whether the start vertex have any edge
				if (VStartInput.adjacencies == null) {
					System.out.println("The start vertex does not have any edge.");
					System.exit(1);
				}
				else {
					calculateDistance(VStartInput);
				}
			}
		}

		// Loop through the list to find the end vertex
		// Then set the distance from start to end
		for (Vertex VEndInput : list) {
			if (VEndInput.name.equals(endInput)) {
				//Test parsing the input
				//System.out.println(VEndInput.name + " " + VEndInput.adjacencies);
				distance = VEndInput.minDistance;
			}
		}

		// If the result is infiniy, this means there is no path between 2
		// existing node
		if (distance == Double.POSITIVE_INFINITY) {
			System.out.println("There is no path between these vertices");
		}
		// If the result is 0.0, then the input is invalid
		else if (distance == 0.0) {
			System.out.println("Invalid input");
		} else {
			System.out.println("Distance from " + startInput + " to " + endInput + ": " + distance);
		}

	}
}
