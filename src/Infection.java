import java.util.*;

/**
 * Created by cwang on 11/9/2015.
 */
public class Infection {

    public static void main (String [] args) {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");

        HashSet<Node> nodes = new HashSet<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);

        HashSet<Edge> edges = new HashSet<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(a, d));
        edges.add(new Edge(a, e));
        edges.add(new Edge(a, f));
        edges.add(new Edge(g, h));

        HashMap<Node, HashSet<Node>> graph = createGraph(nodes, edges);


        totalInfection(a, graph);
        for (Node n: graph.keySet()) {
            System.out.println(n + ": " + graph.get(n));
        }
        System.out.println(graph.keySet());
    }

    public static void totalInfection(Node startNode, HashMap<Node, HashSet<Node>> graph) {
        HashSet<HashSet<Node>> allConnectedComponents = findConnectedComponents(deepClone(graph));

        for (HashSet<Node> connectedComponent: allConnectedComponents) {
            if (connectedComponent.contains(startNode)) {
                for (Node n: connectedComponent) {
                    n.setInfected(true);
                }
                break;
            }
        }

        //return allConnectedComponents;
    }

    public static HashSet<HashSet<Node>> findConnectedComponents(HashMap<Node, HashSet<Node>> graph) {
        HashSet<HashSet<Node>> allConnectedComponents = new HashSet<HashSet<Node>>();

        Set<Node> nodes = graph.keySet();
        while (!nodes.isEmpty()) {
            HashSet<Node> newConnectedComponent = new HashSet<Node>();

            Stack<Node> stack = new Stack<Node>();

            // Trying to get an item from the set.
            for (Node n: nodes) {
                stack.push(n);
                break;
            }
            while (!stack.empty()) {
                Node node = stack.pop();
                node.setDiscovered(true);
                newConnectedComponent.add(node);

                HashSet<Node> neighbors = graph.get(node);
                nodes.remove(node);

                for (Node neighbor: neighbors) {
                    if (!neighbor.getDiscovered()) {
                        stack.push(neighbor);
                    }
                }
            }
            allConnectedComponents.add(newConnectedComponent);
        }

        return allConnectedComponents;
    }


    public static HashMap<Node, HashSet<Node>> createGraph(HashSet<Node> nodes, HashSet<Edge> edges) {
        HashMap<Node, HashSet<Node>> graph = new HashMap<Node, HashSet<Node>>();

        for (Node n: nodes) {
            graph.put(n, new HashSet<Node>());
        }

        for (Edge e: edges) {
            Node from = e.getFrom();
            Node to = e.getTo();

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        return graph;
    }

    // Needed to deepClone the graph so when we remove/modify the keyset, we don't delete the original graph's entries.
    public static HashMap<Node, HashSet<Node>> deepClone(HashMap<Node, HashSet<Node>> original) {
        HashMap<Node, HashSet<Node>> copy = new HashMap<Node, HashSet<Node>>();

        for (Map.Entry<Node, HashSet<Node>> entry: original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }

        return copy;
    }

    public static HashMap<Node, HashSet<Node>> graph1() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");

        HashSet<Node> nodes = new HashSet<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);

        HashSet<Edge> edges = new HashSet<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(a, d));
        edges.add(new Edge(a, e));
        edges.add(new Edge(a, f));
        edges.add(new Edge(g, h));

        return createGraph(nodes, edges);
    }

}
