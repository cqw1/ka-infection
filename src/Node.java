/**
 * Created by cwang on 11/9/2015.
 */
public class Node {
    private String name;
    private String infectedName;
//    private ArrayList<Node> neighbors;
    private boolean infected;
    private boolean discovered;

    public Node(String name) {
    //public Node(String name, ArrayList<Node> neighbors) {
        this.name = name;
        this.infectedName = name + "*";
//        this.neighbors = neighbors;
        this.infected = false;
        this.discovered = false;
    }

    public String getName() {
        if (this.infected) {
            return this.infectedName;
        }
        return this.name;
    }

    public void setInfected(boolean status) {
        this.infected = status;
    }

    public void setDiscovered(boolean status) {
        this.discovered = status;
    }

    public boolean getDiscovered() {
        return this.discovered;
    }
//
//    public ArrayList<Node> getNeighbors() {
//        return this.neighbors;
//    }

    public String toString() {
        String result = getName();
//
//        result += ": [";
//        for (int i = 0; i < this.neighbors.size(); i++) {
//            if (i != 0) {
//                result += ", " + this.neighbors.get(i).getName();
//            } else {
//                result += this.neighbors.get(i).getName();
//            }
//        }
//        result += "]";

        return result;
    }
}
