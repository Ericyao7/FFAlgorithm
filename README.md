# FFAlgorithm
Three ways to implement the Ford–Fulkerson algorithm

The Ford–Fulkerson method or Ford–Fulkerson algorithm (FFA) is an algorithm that computes the maximum flow in a flow network. 
It is called a "method" instead of an "algorithm" as the approach to finding augmenting paths in a residual graph is not fully 
specified[1] or it is specified in several implementations with different running times.[2] It was published in 1956 by L. R. Ford, Jr. 
and D. R. Fulkerson.[3] The name "Ford–Fulkerson" is often also used for the Edmonds–Karp algorithm, which is a specialization of 
Ford–Fulkerson.

The idea behind the algorithm is as follows: as long as there is a path from the source (start node) to the sink (end node), 
with available capacity on all edges in the path, we send flow along one of the paths. Then we find another path, and so on. 
A path with available capacity is called an augmenting path.
