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

        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(a, d));
        edges.add(new Edge(a, e));
        edges.add(new Edge(a, f));
        edges.add(new Edge(g, h));

        HashMap<Node, ArrayList<Node>> graph = createGraph(nodes, edges);


        totalInfection(a, graph);
        System.out.println(graph.keySet());
        cleanGraph(graph);
        System.out.println(graph.keySet());
        limitedInfection(a, 3, graph);
        System.out.println(graph.keySet());
        cleanGraph(graph);

//        for (Node n: graph.keySet()) {
//            System.out.println(n + ": " + graph.get(n));
//        }
    }

    public static void totalInfection(Node startNode, HashMap<Node, ArrayList<Node>> graph) {
        ArrayList<ArrayList<Node>> allConnectedComponents = findConnectedComponents(deepClone(graph));

        for (ArrayList<Node> connectedComponent: allConnectedComponents) {
            if (connectedComponent.contains(startNode)) {
                for (Node n: connectedComponent) {
                    n.setInfected(true);
                }
                break;
            }
        }
    }

    public static void limitedInfection(Node startNode, int limit, HashMap<Node, ArrayList<Node>> graph) {
        HashMap<Node, ArrayList<Node>> graphCopy = deepClone(graph);
        TreeSet<ArrayList<Node>> possibleNodes = new TreeSet<ArrayList<Node>>(limitedInfectionComparator);

        possibleNodes.add(graphCopy.get(startNode));
        startNode.setInfected(true);

        int currentInfected = 1;

        // LimitArrayList is used to help find the appropriate size of neighbors we want to infect next. Unfortunately,
        // in order for our comparator to work properly with the ArrayLists, we need an ArrayList of a certain size for
        // the floor and ceiling methods to work.
        ArrayList<Node> limitArrayList = new ArrayList<Node>(limit);
        Node buffer = new Node("buffer");
        for (int i = 0; i < limit - 1; i++) {
            limitArrayList.add(buffer);
        }

        // Keep looking until we've run out of nodes on our connected component, or until we've reached our limit.
        while (currentInfected < limit && !possibleNodes.isEmpty()) {
            // Cut down the size of our limitArrayList to reflect the newly infected nodes. The number of neighbors we
            // want to infect should now be smaller, which is reflected in our limitArrayList size.
            for (int j = limitArrayList.size() - 1; j >= limit - currentInfected; j--) {
                limitArrayList.remove(j);
            }

            // Try to find the maximum number of users in the next step, that still stays under our limit. If not
            // possible, then take the least number of users so we minimize how far we go over our limit.
            ArrayList<Node> nextNodes = possibleNodes.floor(limitArrayList);
            if (nextNodes == null) {
                nextNodes = possibleNodes.ceiling(limitArrayList);
            }

            for (Node node: nextNodes) {
                if (!node.getInfected()) {
                    node.setInfected(true);
                    currentInfected += 1;

                    // Remove all nodes that we've all infected so we can keep an accurate count of how many people can still get infected.
                    ArrayList<Node> neighbors = graphCopy.get(node);
                    System.out.println("before neighbors: " + neighbors);

                    for (int k = neighbors.size() - 1; k >= 0; k--) {
                        neighbors.remove(k);
                    }
                    System.out.println("after neighbors: " + neighbors);

                    // Add the possible neighbors that can be infected.
                    if (neighbors.size() > 0) {
                        possibleNodes.add(neighbors);
                    }
                }
            }
            possibleNodes.remove(nextNodes);
            System.out.println(possibleNodes);
        }
    }

    public static ArrayList<ArrayList<Node>> findConnectedComponents(HashMap<Node, ArrayList<Node>> graph) {
        ArrayList<ArrayList<Node>> allConnectedComponents = new ArrayList<ArrayList<Node>>();

        Set<Node> nodes = graph.keySet();
        while (!nodes.isEmpty()) {
            ArrayList<Node> newConnectedComponent = new ArrayList<Node>();

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

                ArrayList<Node> neighbors = graph.get(node);
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


    public static HashMap<Node, ArrayList<Node>> createGraph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
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

    // Needed to deepClone the graph so when we remove/modify the keyset, we don't delete the original graph's entries.
    public static HashMap<Node, ArrayList<Node>> deepClone(HashMap<Node, ArrayList<Node>> original) {
        HashMap<Node, ArrayList<Node>> copy = new HashMap<Node, ArrayList<Node>>();

        for (Map.Entry<Node, ArrayList<Node>> entry: original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }

        return copy;
    }

    public static void cleanGraph(HashMap<Node, ArrayList<Node>> graph) {
        for (Node n: graph.keySet()) {
            n.setInfected(false);
            n.setDiscovered(false);
        }
    }

    public static HashMap<Node, ArrayList<Node>> graph1() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");

        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(a, d));
        edges.add(new Edge(a, e));
        edges.add(new Edge(a, f));
        edges.add(new Edge(g, h));

        return createGraph(nodes, edges);
    }

    public static Comparator<ArrayList<Node>> limitedInfectionComparator = new Comparator<ArrayList<Node>>() {

        @Override
        public int compare(ArrayList<Node> o1, ArrayList<Node> o2) {
            return o1.size() - o2.size();
        }
    };

}
