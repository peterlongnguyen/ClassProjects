Peter Nguyen
3004098818
LPNGUYEN@USC.EDU

Program description:  
-This program utilizes ordered arraylists of type node to keep the shortest-distance-node at the top.  All data is stored within each node (past, present, future costs (euclidean, manhattan, chessboard), current coordinates, and history of moves to get to that node).  At every iteration it will expand the top node and pop it, continuing the process until it finds a solution or no solution. Sorry the file writing section at the end is messy, I didn't realize we would be taking in three sets of coordinates until it was late.
-History: it will be a string in the form of digits, with each digit representing a past move (1 = up, 2 = down, 3 = left, 4 = right) chronologically from left to right (e.g. hist = 3412 means the first move was from the default position to LEFT).

Directions:
I emailed you asking about the spacing issue with the input.txt file, so I decided to take out all spaces.
Ex: the accepted input format is 
4
1 2
3 4
6
2 3
4 3
9
7 6
8 6

