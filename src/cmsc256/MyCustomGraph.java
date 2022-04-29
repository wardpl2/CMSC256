package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CMSC 256
 * Project 6
 * Ward, Preston
 *
 * Project 6 reads a txt file and creates a graph. Then methods are used to analyze the features of the graph
 */
public class MyCustomGraph<V> extends UnweightedGraph<V> {

    public MyCustomGraph(){ super(); }
//    public MyCustomGraph(int[][] edges, int numberOfVertices) { super(edges, numberOfVertices); }
    public MyCustomGraph(List<Edge> edges, int numberOfVertices) { super(edges, numberOfVertices); }
//    public MyCustomGraph(List<V> vertices, List<Edge> edges) { super(vertices, edges); }
//    public MyCustomGraph(V[] vertices, int[][] edges) { super(vertices, edges); }


    public MyCustomGraph<V> readFile(String fileName) throws FileNotFoundException, NumberFormatException {
        Scanner in = new Scanner(new File(fileName));
        int numVerts = Integer.parseInt(in.nextLine());
        String[] edgeStrings;
        List<Edge> edges = new ArrayList<>();

        while (in.hasNextLine()) {
            edgeStrings = in.nextLine().split(" ");
            for (int i = 1; i < edgeStrings.length; i++) {
                edges.add(new Edge(Integer.parseInt(edgeStrings[0]), Integer.parseInt(edgeStrings[i])));
            }
        }

        return new MyCustomGraph<>(edges,numVerts);
    }

    public boolean isComplete() {
//        Each vertice must be connected to exactly N-1 other vertices.
        for (int i = 0; i < getVertices().size(); i++) {
            if (getNeighbors(i).size() < (getVertices().size() - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean areAdjacent(V v1, V v2) { return getNeighbors(getIndex(v1)).contains(getIndex(v2)); }

    public boolean isConnected() {
        if (getVertices().size() == 1) {
            return false;
        }
        boolean isConnected = false;
        for (int i = 0; i < getVertices().size(); i++) {
            SearchTree tree = dfs(i);
            isConnected = tree.getNumberOfVerticesFound() == getVertices().size();
        }

        return isConnected;
    }

    public List<V> getShortestPath(V begin, V end) {
        List<V> order = bfs(getIndex(end)).getPath(getIndex(begin));
        if (begin == end) {
            List<V> list = new ArrayList<>();
            list.add(begin);
            return list;
        }
        if (order.isEmpty() || (order.size() == 1 && getIndex(order.get(0)) == 0)) {
            return null;
        }
        return order;
    }

    public boolean hasCycle() {
        int verts = getVertices().size();
        boolean[] visited = new boolean[verts];
        for (int i = 0; i < verts; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < verts; i++) {
            if (!visited[i]) {
                if (cycleHelper(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean cycleHelper(int vert, boolean[] visited, int parent) {
        visited[vert] = true;
        int count;

        for (int newVert : getNeighbors(vert)) {
            count = newVert;

            if (!visited[count]) {
                if (cycleHelper(count,visited,vert)) {
                    return true;
                }
            } else if (count != parent) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){
        MyCustomGraph<Integer> myGraph = new MyCustomGraph<>();
        try{
            MyCustomGraph<Integer> myGraph2 = myGraph.readFile("test2-1.txt");
            System.out.println("Graph of text2-1.txt");
            System.out.println("is complete => " + myGraph2.isComplete());
            System.out.println("is Connected => " + myGraph2.isConnected());
            System.out.println("A path from 0 to 4 is " + myGraph2.getShortestPath(0, 4));
            System.out.println("hasCycle() returns " + myGraph2.hasCycle());
            System.out.println("printEdges() displays: ");
            myGraph2.printEdges();
            System.out.println();

            MyCustomGraph<Integer> myGraph3 = myGraph.readFile("test3.txt");
            System.out.println("Graph of text3.txt");
            myGraph3.printEdges();
            System.out.println("is complete => " + myGraph3.isComplete());
            System.out.println("is connected => " + myGraph3.isConnected());
            System.out.println("The shortest path from 0 to 4 is " + myGraph3.getShortestPath(0, 4));
            System.out.println("The shortest path from 5 to 4 is " + myGraph3.getShortestPath(5, 4));
            System.out.println("hasCyclic() returns " + myGraph3.hasCycle());
        } catch (Exception e) {
            System.out.println("Oops, something went wrong.");
            e.printStackTrace();
        }
    }
}
