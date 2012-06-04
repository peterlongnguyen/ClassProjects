Peter Nguyen
3004098818
lpnguyen@usc.edu

1. This program basically utilizes the minimax by basically searching depth first into a valid node, passing down, and then following the alpha beta pruning and minimax algorithm, sending highest values to max nodes and lowest values to min nodes.  It prunes any future nodes that do not maximize payoff.
2. javac *.java
	java *
	-classpath . hw3
3. Move ordering (best moves searched first) would improve the algorithm.  If max nodes generated successors of greatest value first and min nodes generated successors with least value first then it would save the time of exploring the rest of the nodes. 
	The improved version is faster by a factor of b^(m/6) than the standard alpha beta pruning.  If move ordering were somehow to become perfect then the branching factor of the game would not really affect the speed of the search, only the depth would.

