package cmsc256;

public class MyCustomGraph<V> extends UnweightedGraph<V> {
    public MyCustomGraph<V> readFile(String fileName) {
        return null;
    }

    public boolean isComplete() {
        return false;
    }

    public boolean areAdjacent(V v1, V v2) {
        return false;
    }

    public boolean isConnected() {
        return false;
    }

    public List<V> getShortestPath(V begin, V end) {
        return null;
    }

    public boolean hasCycle() {
        return false;
    }
}
