import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Test suite for Infection class.
 */
public class InfectionTest {
    private Node a = new Node("a");
    private Node b = new Node("b");
    private Node c = new Node("c");
    private Node d = new Node("d");
    private Node e = new Node("e");
    private Node f = new Node("f");
    private Node g = new Node("g");
    private Node h = new Node("h");
    private Node i = new Node("i");
    private Node j = new Node("j");
    private Node k = new Node("k");
    private Node l = new Node("l");
    private Node m = new Node("m");
    private Node n = new Node("n");
    private Node o = new Node("o");
    private Node p = new Node("p");
    private Node q = new Node("q");
    private Node r = new Node("r");
    private Node s = new Node("s");
    private Node t = new Node("t");
    private Node u = new Node("u");
    private Node v = new Node("v");
    private Node w = new Node("w");
    private Node x = new Node("x");

    private HashMap<Node, ArrayList<Node>> graph1 = graph1();
    private HashMap<Node, ArrayList<Node>> graph2 = graph2();
    private HashMap<Node, ArrayList<Node>> graph3 = graph3();
    private HashMap<Node, ArrayList<Node>> graph4 = graph4();
    private HashMap<Node, ArrayList<Node>> graph5 = graph5();
    private HashMap<Node, ArrayList<Node>> graph6 = graph6();

    private int count;

    /**
     * Check that all nodes are infected. Clean the graph between each test.
     * @throws Exception
     */
    @Test
    public void testTotalInfection() throws Exception {
        // Graph 1.
        Infection.totalInfection(a, graph1);
        ArrayList<Node> isInfected1 = new ArrayList<Node>();
        isInfected1.add(a);

        for (Node n: graph1.keySet()) {
            if (isInfected1.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph1);

        // Graph 2.
        Infection.totalInfection(b, graph2);
        ArrayList<Node> isInfected2 = new ArrayList<Node>();
        isInfected2.add(a);
        isInfected2.add(b);
        isInfected2.add(c);

        for (Node n: graph2.keySet()) {
            if (isInfected2.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph2);

        // Graph 3 - one connected component.
        Infection.totalInfection(e, graph3);
        ArrayList<Node> isInfected3 = new ArrayList<Node>();
        isInfected3.add(a);
        isInfected3.add(b);
        isInfected3.add(c);
        isInfected3.add(d);
        isInfected3.add(e);
        isInfected3.add(f);

        for (Node n: graph3.keySet()) {
            if (isInfected3.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph3);

        // Graph 3 - the other connected component.
        Infection.totalInfection(h, graph3);
        isInfected3 = new ArrayList<Node>();
        isInfected3.add(g);
        isInfected3.add(h);

        for (Node n: graph3.keySet()) {
            if (isInfected3.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph3);

        // Graph 4.
        Infection.totalInfection(f, graph4);
        ArrayList<Node> isInfected4 = new ArrayList<Node>();
        isInfected4.add(a);
        isInfected4.add(b);
        isInfected4.add(c);
        isInfected4.add(d);
        isInfected4.add(e);
        isInfected4.add(f);
        isInfected4.add(g);
        isInfected4.add(h);
        isInfected4.add(i);

        for (Node n: graph4.keySet()) {
            if (isInfected4.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph4);

        // Graph 5.
        Infection.totalInfection(i, graph5);
        ArrayList<Node> isInfected5 = new ArrayList<Node>();
        isInfected5.add(a);
        isInfected5.add(b);
        isInfected5.add(c);
        isInfected5.add(d);
        isInfected5.add(e);
        isInfected5.add(f);
        isInfected5.add(g);
        isInfected5.add(h);
        isInfected5.add(i);

        for (Node n: graph5.keySet()) {
            if (isInfected5.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph5);
    }

    /**
     * Check that the number of users infected is kept to a minimum after reaching the limit. There may be multiple
     * optimal solutions, so cannot always check which nodes are infected. Need to clean the graph between each test.
     * @throws Exception
     */
    @Test
    public void testLimitedInfection() throws Exception {
        // Graph 1.
        Infection.limitedInfection(a, 10, graph1);
        ArrayList<Node> isInfected1 = new ArrayList<Node>();
        isInfected1.add(a);

        for (Node n: graph1.keySet()) {
            if (isInfected1.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph1);

        // Graph 2.
        Infection.limitedInfection(c, 1, graph2);
        ArrayList<Node> isInfected2 = new ArrayList<Node>();
        isInfected2.add(c);

        for (Node n: graph2.keySet()) {
            if (isInfected2.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph2);

        // Graph 3 - one connected component.
        Infection.limitedInfection(a, 3, graph3);
        ArrayList<Node> isInfected3 = new ArrayList<Node>();
        isInfected3.add(a);
        isInfected3.add(b);
        isInfected3.add(c);
        isInfected3.add(d);
        isInfected3.add(e);
        isInfected3.add(f);

        for (Node n: graph3.keySet()) {
            if (isInfected3.contains(n)) {
                assert(n.getInfected());
            } else {
                assert(!n.getInfected());
            }
        }
        Infection.cleanGraph(graph3);

        // Graph 4.
        Infection.limitedInfection(i, 5, graph4);

        // Changing to checking count rather than infected nodes because we there are multiple optimal infected sets.
        count = 0;
        for (Node n: graph4.keySet()) {
            if (n.getInfected()) {
                count += 1;
            }
        }
        assert(count == 5);
        Infection.cleanGraph(graph4);

        // Graph 5.
        Infection.limitedInfection(f, 5, graph5);

        // Changing to checking count rather than infected nodes because we there are multiple optimal infected sets.
        count = 0;
        for (Node n: graph5.keySet()) {
            if (n.getInfected()) {
                count += 1;
            }
        }
        assert(count == 5);
        Infection.cleanGraph(graph5);

        // Graph 6.
        Infection.limitedInfection(a, 9, graph6);

        // Changing to checking count rather than infected nodes because we there are multiple optimal infected sets.
        count = 0;
        for (Node n: graph6.keySet()) {
            if (n.getInfected()) {
                count += 1;
            }
        }
        // Can't get exactly 9 infected users.
        assert(count == 11);
        Infection.cleanGraph(graph6);

        // Graph 6.
        Infection.limitedInfection(u, 7, graph6);

        // Changing to checking count rather than infected nodes because we there are multiple optimal infected sets.
        count = 0;
        for (Node n: graph6.keySet()) {
            if (n.getInfected()) {
                count += 1;
            }
        }
        // Can't get exactly 7 infected users.
        assert(count == 9);
        Infection.cleanGraph(graph6);

        // Graph 6.
        Infection.limitedInfection(n, 5, graph6);

        // Changing to checking count rather than infected nodes because we there are multiple optimal infected sets.
        count = 0;
        for (Node n: graph6.keySet()) {
            if (n.getInfected()) {
                count += 1;
            }
        }
        // Can't get exactly 5 infected users.
        assert(count == 7);
        Infection.cleanGraph(graph6);
    }

    /**
     * Test finding separate connected components in the graph. Need to compare the given ArrayLists with HashSets
     * because order of connected components and order of nodes in each is not guaranteed.
     * @throws Exception
     */
    @Test
    public void testFindConnectedComponents() throws Exception {
        // Graph 1 - four separate connected components.
        HashSet<Node> aConnectedComponent = new HashSet<Node>();
        aConnectedComponent.add(a);
        HashSet<Node> bConnectedComponent = new HashSet<Node>();
        bConnectedComponent.add(b);
        HashSet<Node> cConnectedComponent = new HashSet<Node>();
        cConnectedComponent.add(c);
        HashSet<Node> dConnectedComponent = new HashSet<Node>();
        dConnectedComponent.add(d);
        HashSet<HashSet<Node>> connectedComponents1 = new HashSet<HashSet<Node>>();
        connectedComponents1.add(aConnectedComponent);
        connectedComponents1.add(bConnectedComponent);
        connectedComponents1.add(cConnectedComponent);
        connectedComponents1.add(dConnectedComponent);

        for (ArrayList<Node> cc: Infection.findConnectedComponents(graph1)) {
            for (HashSet<Node> hs: connectedComponents1) {
                if (hs.contains(cc.get(0))) {
                    for (Node n: cc) {
                        hs.remove(n);
                    }
                    break;
                }
            }
        }
        for (HashSet<Node> hs: connectedComponents1) {
            assert(hs.isEmpty());
        }

        // Graph 2 - one connected component.
        HashSet<Node> abcConnectedComponent = new HashSet<Node>();
        abcConnectedComponent.add(a);
        abcConnectedComponent.add(b);
        abcConnectedComponent.add(c);
        HashSet<HashSet<Node>> connectedComponents2 = new HashSet<HashSet<Node>>();
        connectedComponents2.add(abcConnectedComponent);

        for (ArrayList<Node> cc: Infection.findConnectedComponents(graph2)) {
            for (HashSet<Node> hs: connectedComponents1) {
                if (hs.contains(cc.get(0))) {
                    for (Node n: cc) {
                        hs.remove(n);
                    }
                    break;
                }
            }
        }
        for (HashSet<Node> hs: connectedComponents1) {
            assert(hs.isEmpty());
        }

        // Graph 3 - one large connected component and a smaller isolated connected component.
        HashSet<Node> ghConnectedComponent = new HashSet<Node>();
        ghConnectedComponent.add(g);
        ghConnectedComponent.add(h);
        HashSet<Node> otherConnectedComponent = new HashSet<Node>();
        otherConnectedComponent.add(a);
        otherConnectedComponent.add(b);
        otherConnectedComponent.add(c);
        otherConnectedComponent.add(d);
        otherConnectedComponent.add(e);
        otherConnectedComponent.add(f);
        HashSet<HashSet<Node>> connectedComponents3 = new HashSet<HashSet<Node>>();
        connectedComponents3.add(ghConnectedComponent);
        connectedComponents3.add(otherConnectedComponent);

        for (ArrayList<Node> cc: Infection.findConnectedComponents(graph3)) {
            for (HashSet<Node> hs: connectedComponents1) {
                if (hs.contains(cc.get(0))) {
                    for (Node n: cc) {
                        hs.remove(n);
                    }
                    break;
                }
            }
        }
        for (HashSet<Node> hs: connectedComponents1) {
            assert(hs.isEmpty());
        }
    }

    /**
     * Test that the status effects are reset properly.
     * @throws Exception
     */
    @Test
    public void testCleanGraph() throws Exception {
        // Resets infected status.
        a.setInfected(true);
        assert(a.getInfected());
        Infection.cleanGraph(graph1);
        assert(!a.getInfected());

        // Resets discovered status.
        i.setDiscovered(true);
        assert(i.getDiscovered());
        Infection.cleanGraph(graph5);
        assert(!i.getDiscovered());

        // Resets infected and discovered status.
        g.setInfected(true);
        g.setDiscovered(true);
        assert(g.getInfected());
        assert(g.getDiscovered());
        Infection.cleanGraph(graph4);
        assert(!g.getInfected());
        assert(!g.getDiscovered());
    }


    public HashMap<Node, ArrayList<Node>> graph1() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);

        ArrayList<Edge> edges = new ArrayList<Edge>();

        return Infection.createGraph(nodes, edges);
    }

    public HashMap<Node, ArrayList<Node>> graph2() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(b, c));

        return Infection.createGraph(nodes, edges);
    }

    public HashMap<Node, ArrayList<Node>> graph3() {
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

        return Infection.createGraph(nodes, edges);
    }

    public HashMap<Node, ArrayList<Node>> graph4() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);
        nodes.add(i);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(b, c));
        edges.add(new Edge(c, d));
        edges.add(new Edge(d, e));
        edges.add(new Edge(b, f));
        edges.add(new Edge(f, g));
        edges.add(new Edge(f, h));
        edges.add(new Edge(i, f));

        return Infection.createGraph(nodes, edges);
    }

    public HashMap<Node, ArrayList<Node>> graph5() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);
        nodes.add(i);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(b, d));
        edges.add(new Edge(d, e));
        edges.add(new Edge(c, f));
        edges.add(new Edge(c, d));
        edges.add(new Edge(g, c));
        edges.add(new Edge(g, h));
        edges.add(new Edge(i, h));

        return Infection.createGraph(nodes, edges);
    }

    public HashMap<Node, ArrayList<Node>> graph6() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);
        nodes.add(g);
        nodes.add(h);
        nodes.add(i);
        nodes.add(j);
        nodes.add(k);
        nodes.add(l);
        nodes.add(m);
        nodes.add(n);
        nodes.add(o);
        nodes.add(p);
        nodes.add(q);
        nodes.add(r);
        nodes.add(s);
        nodes.add(t);
        nodes.add(u);
        nodes.add(v);
        nodes.add(w);
        nodes.add(x);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(a, b));
        edges.add(new Edge(a, c));
        edges.add(new Edge(c, d));
        edges.add(new Edge(c, e));
        edges.add(new Edge(c, f));
        edges.add(new Edge(d, k));
        edges.add(new Edge(d, l));
        edges.add(new Edge(e, o));
        edges.add(new Edge(e, n));
        edges.add(new Edge(f, q));
        edges.add(new Edge(f, p));
        edges.add(new Edge(b, g));
        edges.add(new Edge(b, h));
        edges.add(new Edge(b, i));
        edges.add(new Edge(b, j));
        edges.add(new Edge(j, v));
        edges.add(new Edge(j, w));
        edges.add(new Edge(j, x));
        edges.add(new Edge(h, s));
        edges.add(new Edge(h, t));
        edges.add(new Edge(h, u));
        edges.add(new Edge(g, r));

        return Infection.createGraph(nodes, edges);
    }
}