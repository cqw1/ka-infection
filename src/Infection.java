import java.util.*;

public class Infection {

    public static void main (String [] args) {


//        totalInfection(a, graph);
//        System.out.println(graph.keySet());
//        cleanGraph(graph);
//        System.out.println(graph.keySet());
//        limitedInfection(a, 8, graph);
//        System.out.println(graph.keySet());
//        cleanGraph(graph);

//        for (Node n: graph.keySet()) {
//            System.out.println(n + ": " + graph.get(n));
//        }
    }

    /**
     * Infects all users connected to the start user if there exists some path between the two users.
     * @param startNode User to start the infection from.
     * @param graph Graph of user relationships.
     */
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

    /**
     * Attempts to infect a limited number of users starting from a given user. If a "coach" is infected, and we choose
     * to infect its "coachees", then all "coachees" will be infected even if this causes us to go over the limit.
     * @param startNode User to start the infection from.
     * @param limit The limit on number of infections.
     * @param graph The graph of the relationships between users.
     */
    public static void limitedInfection(Node startNode, int limit, HashMap<Node, ArrayList<Node>> graph) {
        HashMap<Node, ArrayList<Node>> graphCopy = deepClone(graph);
        // Map from length of the list of neighboring nodes to a list of all possible neighbors of that length.
        // e.g. {2 = [[a, b], [c, d]]} means that the two lists of neighbors of [a, b] and [c, d] have a length of 2.
        TreeMap<Integer, ArrayList<ArrayList<Node>>> possibleNodes = new TreeMap<Integer, ArrayList<ArrayList<Node>>>();

        ArrayList<ArrayList<Node>> temp = new ArrayList<ArrayList<Node>>();
        temp.add(graphCopy.get(startNode));
        possibleNodes.put(graphCopy.get(startNode).size(), temp);
        startNode.setInfected(true);

        int currentInfected = 1;

        // Keep looking until we've run out of nodes on our connected component, or until we've reached our limit.
        while (currentInfected < limit && !possibleNodes.isEmpty()) {
            // Try to find the maximum number of neighbors in the next step, that still stays under our limit. If not
            // possible, then take the least number of neighbors so we minimize how far we go over our limit.
            Map.Entry<Integer, ArrayList<ArrayList<Node>>> nextEntry = possibleNodes.floorEntry(limit - currentInfected);
            if (nextEntry == null) {
                nextEntry = possibleNodes.ceilingEntry(limit - currentInfected);
            }

            // Since all lists of neighbors have the same length, arbitrarily take the last ArrayList of neighbors off
            // the list.
            ArrayList<Node> nextNodes = nextEntry.getValue().remove(nextEntry.getValue().size() - 1);
            if (nextEntry.getValue().size() == 0) {
                // There are no more lists of neighbors of this size, so remove it from the map.
                possibleNodes.remove(nextEntry.getKey());
            }

            // For each neighbor node, infect them and then add its own neighbors to our map of possibleNodes so we can
            // continue expanding the infection if needed.
            for (Node node: nextNodes) {
                if (!node.getInfected()) {
                    node.setInfected(true);
                    currentInfected += 1;

                    ArrayList<Node> neighbors = graphCopy.get(node);

                    // Remove all nodes that we've already infected so we can keep an accurate count of how many nodes
                    // can still get infected.
                    for (int k = neighbors.size() - 1; k >= 0; k--) {
                        if (neighbors.get(k).getInfected()) {
                            neighbors.remove(k);
                        }
                    }

                    // Add the neighbors that can be infected.
                    int neighborsSize = neighbors.size();
                    if (neighborsSize > 0) {
                        if (possibleNodes.containsKey(neighborsSize)) {
                            possibleNodes.get(neighborsSize).add(neighbors);
                        } else {
                            ArrayList<ArrayList<Node>> tempWrapper = new ArrayList<ArrayList<Node>>();
                            tempWrapper.add(neighbors);
                            possibleNodes.put(neighborsSize, tempWrapper);
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds all connected components in the graph.
     * @param graph The graph of the relationships between users.
     * @return ArrayList of connected components, where each connected component is represented by its own ArrayList of
     *          nodes. There is no ordering between connected components or between nodes in the same connected component.
     */
    public static ArrayList<ArrayList<Node>> findConnectedComponents(HashMap<Node, ArrayList<Node>> graph) {
        ArrayList<ArrayList<Node>> allConnectedComponents = new ArrayList<ArrayList<Node>>();
        Set<Node> nodes = graph.keySet();

        // Continues looking through all nodes in graph in case there are isolated sections.
        while (!nodes.isEmpty()) {
            ArrayList<Node> newConnectedComponent = new ArrayList<Node>();
            Stack<Node> stack = new Stack<Node>();

            // Trying to get an item from the set.
            for (Node n: nodes) {
                stack.push(n);
                break;
            }

            // DFS search to find all nodes that are connected with a given start node, and stores them in one
            // connected component.
            while (!stack.empty()) {
                Node node = stack.pop();
                node.setDiscovered(true);
                newConnectedComponent.add(node);

                ArrayList<Node> neighbors = graph.get(node);
                nodes.remove(node);

                // Null-check needed in case we have already discovered this node through a different path, and have
                // removed it from the graph.
                if (neighbors != null) {
                    for (Node neighbor: neighbors) {
                        if (!neighbor.getDiscovered()) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
            allConnectedComponents.add(newConnectedComponent);
        }
        return allConnectedComponents;
    }


    /**
     * Given a list of nodes and edges, construct a graph representation of the relationships.
     * @param nodes List of nodes - where nodes represent users.
     * @param edges List of edges - where edges represent the existence of an relationship between the users.
     * @return HashMap of nodes to their list of neighbors.
     */
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

    /**
     * Deep clones the graph so that when we want to remove or modify the key set, we don't have to delete the original
     * graph's entries. However, deep copies of the nodes are not copied into the clone so changing a node's information
     * (e.g. infected status or discovered status) in the copy will change the node's status in the original. The
     * benefit of this is that we can find the node in the original graph with the same reference.
     * @param original Original graph of user relationships.
     * @return Copy of the graph.
     */
    public static HashMap<Node, ArrayList<Node>> deepClone(HashMap<Node, ArrayList<Node>> original) {
        HashMap<Node, ArrayList<Node>> copy = new HashMap<Node, ArrayList<Node>>();

        for (Map.Entry<Node, ArrayList<Node>> entry: original.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }

        return copy;
    }

    /**
     * Reset the infected and discovered status of each node.
     * @param graph Graph of user relationships.
     */
    public static void cleanGraph(HashMap<Node, ArrayList<Node>> graph) {
        for (Node n: graph.keySet()) {
            n.setInfected(false);
            n.setDiscovered(false);
        }
    }
}
