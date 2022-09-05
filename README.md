In this project I develop AI agents to solve 2048 and Tetris.

# Technical Documentation
## 2048
In all I experimented with 4 different algorithms to develop the 2048 AI agent. The algorithms I used are expectimax algorithm, alpha-beta pruning algorithm, minimax algorithm and Monte Carlo Tree Search Algorithm. In the final product I have only implemented the Monte Carlo Tree search Algorithm and the code file for Minimax algorithm and not the rest since they were implemented in different versions of 2048 game that I developed. In this section we will discuss in detail how these algorithms have been implemented in our code.

### Monte-Carlo Tree Search
As Monte Carlo Tree Search algorithm is based on number of playouts or simulations or roll plays i.e. the game plays a simulation with equal amount of iterations after every move. In our particular algorithm we are iterating 100 times after every move made by the agent. Also every time the agents makes a move from an unexplored game state it explores a new move that was not previously explored. 
For playing 2048 game using monte carlo tree search agent we make a simulation of the game after every move to choose the best move, this simulation is played until the games has not ended, The game ends if all the tiles are filled). For each simulation we choose random moves until the game has achieved a result. For all the moves (up, down, Right and left) we make a copy of the board that runs through the depth of the game. In our case we have selected the depth of 200. If the move does not have an effect on the game state or it does not increase the score we cancel out that move. If the move helps in increasing the score we run the chosen number of simulations by making random moves.
Since 2048 has very large state space we decided that every single one of the tiles present on 0248 game boards (16 tile) can either take a value of zero (empty tile) or any power of two (2,4,8…2048). Usually while doing monte Carlo Tree search we take the action that has the best score or the move that maximized the UCB (upper confidence bound) value, which defined by the formula UCB=Vi+C .√(ln⁡〖N/ni〗 )     , where Vi is the estimate value of the node, N is number of times parent node has been visited, Ni is number of times node has been visited and C is exploration value that scales the value of the unexplored actions. For 2048, since we generate a new tile after every move and the value of the tiles increases as we make more moves as the tiles are merged together we are unlikely to visit any previously explored node. The fact that 2048 has a very large space state, approximately upper bound of 1816 [16], also helps in understanding that it is highly unlikely to revisit explored nodes. 
To evaluate our artificial intelligent agent we consider three features of the game. First on is the largest sum of tiles on the board when the game ends, second is the largest tile value at the end of the game and the score, third is the highest score. Most player just consider the score to be the main evaluation parameter, whereas I made an observation where I noticed that the higher the sum of board is at the end of the game the better are the chances to win if the game hasn’t been over. Also another parameter which is better than score is the value of the tile at the end of the game, the larger it is the higher are the chances of winning if game wouldn.t have ended. These observations were made after realizing that score increases just by merging the tiles, so it really doesn’t completely depend on the highest value of tile, but the number of tiles merged. For a good AI agent we need to consider all three parameters and the agent should achieve all of them. 

### Minimax
Minimax search algorithm is a decision making rule in artificial intelligence. The task of this algorithm is to reduce the possibility of a loosing or not achieving the goal in the worst case scenario. As discussed in above sections, this rule is generally used for two player games. But to apply this in 2048 we are assuming it has two players, one is computer and other is the player. Computer tries to minimize the chances of player winning hence acting as a minimizer and player tries to maximize his probability to win. Minimax algorithm uses a game tree to decide the best move for the player who is currently playing. But in many cases due the large depth of the tree the base node or the leaf node is never reached, this  doesn’t allow us to get the value of gain ( value of gain is the value that indicates the guaranteed payoff the opponent will have based on each players specific strategy). So in many programs depth limiter is set. We have set the depth limiter at 100.

### Alpha Beta Pruning
As discussed in the sections above Alpha Beta Pruning is optimized minimax searched. It save computational power and time. It does so by reducing the number of nodes visited in the search tree by not searching the next node if one possible move have been found which is a better choice. 
I experimented with this in other variants of the game and not the final product, so I wasn’t able to compare the results derived from this algorithm with others.
Pseudo code of the algorithm:

### Expectimax
Again this algorithm was also used in previous version of the game developed and was not used in the final game because I wasn’t able to make the algorithm solve the game, hence I was not able to compare the results of this algorithm with Monte Carlo or Minimax Search.

## Tetris
The game has been developed from scratch using java swing components. The idea of the algorithm completely goes to Yiyuan Lee. He mentions this in his blog Tetris AI – The (Near) Perfect Bot.
For Tetris the goal of the artificial intelligent agent is to maximize the score by picking out the best move for each of the possible tetriminoe shape at that particular board state. To do so the primary target of the agent is to clear as many lines as possible as to increase the score and the duration it plays for. To achieve this target the agent has been programmed to pick the best possible moves i.e. what place to place the tetriminoe and how much they should be rotated to get the most out of the move. To help the agent it is allowed to see one upcoming tetriminoe (the next piece), so that it can decide where to place the current shape to get the most out of second shape as well. The agent gives weights to each of the possible move, for calculating weights it considers both the current and the next tetriminoe and then selects the move which has the highest weight.
The weights are given on basis of heuristics, we consider four heuristics in our approach. We take into account the height of the board, number of completed lines, number of holes in the game board and the bumpiness of the board.
The weight of the board or the gris is calculated by combining our four heuristics. We get the values of the heuristics and define four constants a,b,c and d. We have taken the heuristic values from[7]. The function then becomes like:
A x height + b x completed lines + c x holes + d x bumpiness       
The values of a,b,c and d are as -0.55, 0.82, -0.41, -0.21 respectively. I tried experimenting with different heuristic values, in my observation these heuristics worked the best as they give more weight to completing lines.Our algorithm is like greedy genetic algorithm which focuses on making the optimally decision at every move.

# Run
To run the code you need to run the GameMenu.java

## References
1.	Louis Victor Allis, “Searching for Solutions in Games and Artificial Intellgence”.
2.	Tetris Wiki. 2022. SRS. [online] Available at: <https://tetris.fandom.com/wiki/SRS> [Accessed 29 April 2022].
3.	sandipanweb. 2022. Using Minimax with Alpha-Beta Pruning and Heuristic Evaluation Functions to Solve the 2048 game with a Computer: a python implementation. [online] Available at: <https://sandipanweb.wordpress.com/2017/03/06/using-minimax-with-alpha-beta-pruning-and-heuristic-evaluation-to-solve-2048-game-with-computer/> [Accessed 29 April 2022].
4.	M. Szubert and W. Jaśkowski, “Temporal difference learning of n-tuple networks for the game 2048,” in 2014 IEEE Conference on Computational Intelligence and Games (CIG), August 2014, pp. 1–8
5.	Kiarostami et al, “On using Monte-Carlo Tree Search to Solve Puzzles”
6.	Tsitsiklis & Van Roy (1996)
7.	Lee, Y., n.d. Tetris AI – The (Near) Perfect Bot. [online] Code My Road. Available at: <https://codemyroad.wordpress.com/2013/04/14/tetris-ai-the-near-perfect-player/> [Accessed 29 April 2022].
8.	Ramon & Driessens (2004)
9.	Ashwin Ram, Santiago Ontanon, Manish Mehta, “Artificial Intelligence for Adaptive Computer Games”
10.	Simon Algorta and Ozgur Simsek, “The game of Tetris in Machine Learning”
11.	Ahmad Zaky, “Minimax and Expectimax Algorithm to Solve 2048”
12.	2022. [online] Available at: <https://rockcontent.com /blog/artificial-intelligence algorithm/> [Accessed 29 April 2022].
13.	SciTechDaily. 2022. Gaming the Known and Unknown via Puzzle Solving With an Artificial Intelligence Agent. [online] Available at: <https://scitechdaily.com/gaming-the-known-and-unknown-via-puzzle-solving-with-an-artificial-intelligence-agent/> [Accessed 29 April 2022].
14.	M. Overlan. 2048 AI. [Online]. Available: http://ov3y.github.io/2048-AI/],
15.	GeeksforGeeks. 2022. ML | Monte Carlo Tree Search (MCTS) - GeeksforGeeks. [online] Available at: <https://www.geeksforgeeks.org/ml-monte-carlo-tree-search-mcts/> [Accessed 29 April 2022].
16.	2048?, H., 2022. How many possible board states in 2048?. [online] Mathematics Stack Exchange. Available at: <https://math.stackexchange.com/questions/920884/how-many-possible-board-states-in-2048> [Accessed 29 April 2022].
17.	Tetris Wiki. 2022. List of curiosities. [online] Available at: <https://tetris.fandom.com/wiki/List_of_curiosities> [Accessed 29 April 2022].
