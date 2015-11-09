import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by cwang on 11/9/2015.
 */
public class Infection {
    public static void main (String [] args) {

    }


    public static HashMap<Node, ArrayList<Node>> createGraph(Set<Node> nodes, Set<Edge> edges) {
        HashMap<Node, ArrayList<Node>> graph = new HashMap<Node, ArrayList<Node>>();

        for (Node n: nodes) {
            graph.put(n, new ArrayList<Node>());
        }

        for (Edge e: edges) {
            Node from = e.getFrom();
            Node to = e.getTo();

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        return graph;
    }

}
