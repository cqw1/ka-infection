/**
 * Created by cwang on 11/9/2015.
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
