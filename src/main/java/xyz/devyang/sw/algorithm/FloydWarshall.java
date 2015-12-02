package xyz.devyang.sw.algorithm;

import xyz.devyang.sw.core.Edge;
import xyz.devyang.sw.core.Graph;

/**
 * Floyd-Warshall algorithm implementation
 *
 * Created by YangYu on 11/22/15.
 */
public class FloydWarshall {

    private short distancematrix[][];
    private int numberofvertices;
    private byte adjacencymatrix[][];
    public static final int INFINITY = Byte.MAX_VALUE;

    public FloydWarshall(Graph graph) {
        distancematrix = new short[graph.getNodes().size()+1][graph.getNodes().size()+1];
        this.numberofvertices = graph.getNodes().size();
        adjacencymatrix = new byte[graph.getNodes().size()+1][graph.getNodes().size()+1];
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
                        distancematrix[source][destination] = (short)(distancematrix[source][intermediate]
                                + distancematrix[intermediate][destination]);
                }
            }
        }

//        for (int source = 1; source <= numberofvertices; source++)
//            System.out.print("\t" + source);
//
//        System.out.println();
//        for (int source = 1; source <= numberofvertices; source++) {
//            System.out.print(source + "\t");
//            for (int destination = 1; destination <= numberofvertices; destination++) {
//                System.out.print(distancematrix[source][destination] + "\t");
//            }
//            System.out.println();
//        }

    }
}
