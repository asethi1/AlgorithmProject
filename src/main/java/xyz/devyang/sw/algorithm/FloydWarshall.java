package xyz.devyang.sw.algorithm;

import xyz.devyang.sw.core.Edge;
import xyz.devyang.sw.core.Graph;

/**
 * Created by YangYu on 11/22/15.
 */
public class FloydWarshall {

    private int distancematrix[][];
    private int numberofvertices;
    private int adjacencymatrix[][];
    public static final int INFINITY = Integer.MAX_VALUE;

    public FloydWarshall(Graph graph) {
        distancematrix = new int[graph.getNodes().size() + 1][graph.getNodes().size() + 1];
        this.numberofvertices = graph.getNodes().size();
        adjacencymatrix = new int[numberofvertices + 1][numberofvertices + 1];
        for (Edge edge : graph.getEdges()) {
            adjacencymatrix[edge.getSource().getId()][edge.getDestination().getId()] = 1;
            adjacencymatrix[edge.getDestination().getId()][edge.getSource().getId()] = 1;
        }
        for (int source = 1; source <= numberofvertices; source++) {
            for (int destination = 1; destination <= numberofvertices; destination++) {
                if (adjacencymatrix[source][destination] == 0) {
                    if (source == destination) {
                        adjacencymatrix[source][destination] = 0;
                    } else {
                        adjacencymatrix[source][destination] = INFINITY;
                    }
                }
            }
        }
    }

    public void execute() {
        for (int source = 1; source <= numberofvertices; source++) {
            for (int destination = 1; destination <= numberofvertices; destination++) {
                distancematrix[source][destination] = adjacencymatrix[source][destination];
            }
        }

        for (int intermediate = 1; intermediate <= numberofvertices; intermediate++) {
            for (int source = 1; source <= numberofvertices; source++) {
                for (int destination = 1; destination <= numberofvertices; destination++) {
                    if (distancematrix[source][intermediate] + distancematrix[intermediate][destination]
                            < distancematrix[source][destination])
                        distancematrix[source][destination] = distancematrix[source][intermediate]
                                + distancematrix[intermediate][destination];
                }
            }
        }

        for (int source = 1; source <= numberofvertices; source++)
            System.out.print("\t" + source);

        System.out.println();
        for (int source = 1; source <= numberofvertices; source++) {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= numberofvertices; destination++) {
                System.out.print(distancematrix[source][destination] + "\t");
            }
            System.out.println();
        }

    }

    public static void main(String... arg) {
//        int adjacency_matrix[][];
//        int numberofvertices;
//
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter the number of vertices");
//        numberofvertices = scan.nextInt();
//
//        adjacency_matrix = new int[numberofvertices + 1][numberofvertices + 1];
//        System.out.println("Enter the Weighted Matrix for the graph");
//        for (int source = 1; source <= numberofvertices; source++)
//        {
//            for (int destination = 1; destination <= numberofvertices; destination++)
//            {
//                adjacency_matrix[source][destination] = scan.nextInt();
//                if (source == destination)
//                {
//                    adjacency_matrix[source][destination] = 0;
//                    continue;
//                }
//                if (adjacency_matrix[source][destination] == 0)
//                {
//                    adjacency_matrix[source][destination] = INFINITY;
//                }
//            }
//        }
//
//        System.out.println("The Transitive Closure of the Graph");
//        FloydWarshall floydwarshall = new FloydWarshall(numberofvertices);
//        floydwarshall.floydwarshall(adjacency_matrix);
//        scan.close();
    }
}
