public class Node {
    private String name;
    private String infectedName;
    private boolean infected;
    private boolean discovered;

    public Node(String name) {
        this.name = name;
        this.infectedName = name + "*";
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

    public String toString() {
       return getName();
    }
}
