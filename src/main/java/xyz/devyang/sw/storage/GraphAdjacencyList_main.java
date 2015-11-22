package xyz.devyang.sw.storage;

import java.util.*;

public class GraphAdjacencyList_main extends GraphAdjacencyList{
	
	 public GraphAdjacencyList_main(int number_of_vertices) {
		super(number_of_vertices);
	 }
	 
	/*
     * Main Function reads the number of vertices and edges in a graph.
     * then creates a Adjacency List for the graph and prints it  
     */
     public static void main(String [] arg)
     {
         int source , destination;
         int number_of_edges,number_of_vertices;
         int count = 1;
         Scanner scan = new Scanner(System.in);
         try
         {
             /* Read the number of vertices and edges in graph */
             System.out.println("Enter the number of vertices and edges in graph");
             number_of_vertices = scan.nextInt();
             number_of_edges = scan.nextInt();
             GraphAdjacencyList adjacencyList = new GraphAdjacencyList(number_of_vertices);
 
             /* Reads the edges present in the graph */
             System.out.println("Enter the edges in graph Format : <source index> <destination index>");
             while (count <= number_of_edges)
             {
            	 source = scan.nextInt();
                 destination = scan.nextInt();
                 adjacencyList.setEdge(source, destination);
                 count++;
             }
 
             /* Prints the adjacency List representing the graph.*/
             MongoDBWriter writer = new MongoDBWriter();

             System.out.println ("the given Adjacency List for the graph \n");
             for (int i = 1 ; i <= number_of_vertices ; i++)
             {
            	 HashMap<Integer, ArrayList<Integer>> input=new HashMap<Integer, ArrayList<Integer>>();
            	  ArrayList<Integer> values = new ArrayList<Integer>();
                  
                 List<Integer> edgeList = adjacencyList.getEdge(i);
                 for (int j = 1 ; ; j++ )
                 {
                     if (j != edgeList.size())
                     {
                         values.add(edgeList.get(j - 1 ));
                     }else
                     {
                         values.add(edgeList.get(j - 1 ));
                         break;
                     }						 
                 }
                 input.put(i, values);
                 System.out.println("input hashmap is::"+input.toString());
                 Iterator it = input.entrySet().iterator();
                 while (it.hasNext()) {
                     Map.Entry entry = (Map.Entry)it.next();
                     writer.insertOne(entry);
                 }
              }

          } 
          catch(InputMismatchException inputMismatch)
          {
              System.out.println("Error in Input Format. \nFormat : <source index> <destination index>");
          }
          scan.close();
     }

}
