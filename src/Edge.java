/**
 * Edge class to represent the relationship between two users. Represented by an undirected edge since infections can be
 * transferred by both the "coaches" and "is coached" relationship.
 */
public class Edge {
    private Node from;
    private Node to;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public String toString() {
        return "(" + this.from.getName() + ", " + this.to.getName() + ")";
    }
}
