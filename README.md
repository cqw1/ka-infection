# ka-infection

## Overview

I chose to use a graph to represent the infection problem. Users are represented by a Node and a relationship between two users are represented by an Edge. Since infections can be transferred from a "coaches" and a "is coached" relationship, it is an undirected graph. Infections must also be transferred across an immediate edge between user A and B, and cannot be transferred through intermediate users without infecting the intermediate users.

For total and limited infections, I assumed that we would be given a user from which the infection spreads from.

To model a total infection, the separate connected components in the graph are identified first. Then, we find the connected component that contains the starting user, and we use depth first search to infect all other users. This allows us to find and infect connected users, but we do not see users that are in other connected components.

To model a limited infection, I assumed a few more rules:
- Additionally for a limited infection, I assumed that we would be given an integer limit that we must satisfy, and if we went over the limit, we must try to minimize the overflow.
- Given that we want to infect user B from user A (and there exists an edge between A and B), we must also infect all other neighboring users connected to A. e.g. if A is connected to B, C, and D, then we cannot only infect B. C and D must also be infected.

- While trying to reach the limit of infections and given that we have multiple sets of neighbors to infect, we will try to infect the set of maximum neighbors that keeps us under the limit. If every possible set of neighbors will push us over the limit, then take the set of least neighbors. This was determined by trying to consider which approach would realistically be taken. I assumed that the limit was chosen by saying we need a minimum set of data (which is why we must meet or exceed the limit). Next, it would be more useful if we could observe one class with a large number of students experiencing the same new feature, than if we could observe multiple classes of smaller sample sizes. One example scenario is that we are given a limit of 20 infected users. I assumed that infecting 1 teacher with 19 students would provide more useful data than 10 separate sets of teacher-student pairs. With smaller sample sizes, there could be too many uncontrolled variables that may affect the study's results. With the 19 students, we could more confidently conclude the effects of the new feature.

## Instructions

To use the program, create an ArrayList of Nodes for the users and an ArrayList of Edges for any existing relationships. Although there are no rules enforcing that the nodes must be distinct, it is highly recommended to keep them so. Each node is given a name to identify it and inherently has a infected property. Again, there are no restrictions on the names, but I chose to represent an infected node by appending a * to the end of the name, so it would be best to avoid naming a node with just a * (e.g. if node A were to be infected, it would appear as A*).
Pass the two lists of nodes and edges into Infection.createGraph, and this will create the graph representation that the totalInfection and limitedInfection methods use.

The method headers for each type of infection is:
- void totalInfection(Node startNode, HashMap<Node, ArrayList<Node>> graph)
- void limitedInfection(Node startNode, int limit, HashMap<Node, ArrayList<Node>> graph)

I chose to make the infection methods modify the input graph because I believed that creating a new graph could cost too much memory if the graphs grew to be extremely large, which is possible given a realistic set of users. However, the infection methods do not modify the structure of the graphs; they only modify the status of each node to be infected or not. To obtain the original graph, there exists a cleanGraph method that will reset the infected status and other internal information so that it is reverted back to its original state. If graphs will be reused, it is recommended to "clean" them before reuse.

## Running Tests
To run existing tests, run the InfectionTest suite. There already exist a few pre-made graphs that tests can be run on, but making a new graph can easily be done with the createGraph method. Since the order of discovering nodes and choosing between two equal paths (equal in the terms of the goals of our infection algorithm), is not guaranteed, the tests often need to compare the infected nodes to a set of expected nodes or the optimal limited infected count must be maintained. 

