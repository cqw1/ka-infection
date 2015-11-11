# ka-infection

## Overview

I chose to use a grpah to represent the infection problem. Users are represented by a Node and a relationship between two users are represented by an Edge. Since infections can be transferred from a "coaches" and a "is coached" relationship, it is an undirected graph. Infections must also be transferred across a direct edge between user A and B, and cannot be transferred through intermediate users without infecting the intermediate users.

For total and limited infections, I assumed that we would be given a user from which the infection spreads from. Additionally for a limited infection, I assumed that we would be given an integer limit that we must satisfy. It was also assumed that the limit must be satisfied, and if we went over the limit, we must try to minimize the overflow.

To model a total infection, the separate connected components in the graph are identified first. Then, we find the connected component that contains the starting user, and we use depth first search to infect all other users. This allows us to find and infect connected users, but we do not see users that are in other connected components.

To model a limited infection, I assumed a few more rules:
- Given that we want to infect user B from user A (and there exists an edge between A and B), we must also infect all other neighboring users connected to A. e.g. if A is connected to B, C, and D, then we cannot only infect B. C and D must also be infected.

- While trying to reach the limit of infections and given that we have multiple sets of neighbors to infect, we will try to infect the set of maximum neighbors that keeps us under the limit. If every possible set of neighbors will push us over the limit, then take the set of least neighbors. This was determined by trying to consider which approach would realistically be taken. I assumed that the limit was chosen by saying we need a minimum set of data (which is why we must meet or exceed the limit). Next, it would be more useful if we could observe one class with ten students experiencing the same new feature, than if we could observe multiple classes of smaller sample sizes. A worst case scenario is that we are given a limit of 20 infected users. I assumed that infecting 1 teacher with 19 students would provide more useful data than 10 separate sets of teacher-student pairs. With smaller sample sizes, there could be too many uncontrolled variables that may affect the study's results. With the 19 students, we could more confidently conclude the effects of the new feature.

## Instructions

## Running Tests

