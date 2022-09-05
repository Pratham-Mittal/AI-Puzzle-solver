package twenty48;

import java.util.ArrayList;
import java.util.List;

// Minimax was not used as this algoirthm was not as efficient as monte carlo tree search afteer the GUI implementation of the code.
// this did worked fine with the cmd board.
public class MinimaxAlgo {

//    static class GameState {
//        boolean player;
//        Boardtwenty board;
//        GameState[] Cnodes;
//
//        public GameState(Boardtwenty b, boolean p) {
//            board = b;
//            player = p;
//        }
//        public void gnodes() {
//            ArrayList<GameState> child = new ArrayList<>();
//            if (player) {
//                List<Integer> moves = board.possiblemoves();
//                for (int m : moves) {
//                    Boardtwenty b = new Boardtwenty(board);
//                    b.mmove(m);
//                    child.add(new GameState(b, false));
//                }
//            } else {
//                board.getemptytile();
//                for (int i = 0; i < board.xaxis.size(); i++) {
//                    int x = board.xaxis.get(i);
//                    int y = board.yaxis.get(i);
//                    Boardtwenty b = new Boardtwenty(board);
//                    b.tilep(x, y, 2);
//                    child.add(new GameState(b, true));
//                }
//            }
//            Cnodes = child.toArray(new GameState[child.size()]);
//        }
//
//        public int evaluation() {
//            if (Cnodes == null) {
//                board.getemptytile();
//                int freeSpace = board.xaxis.size();
//                int max = max(board.board);  //the maximizer move
//                return freeSpace + max;
//            } else if (Cnodes.length == 0) {
//                return Integer.MIN_VALUE;
//            } else { //maximizer player
//                if (player) {
//                    int max = Integer.MIN_VALUE;
//                    for (GameState s : Cnodes) {
//                        int v = s.evaluation();
//                        if (v > max) {
//                            max = v;
//                        }
//                    }
//                    return max;
//                } else {  //minimizer player
//                    int min = Integer.MAX_VALUE;
//                    for (GameState s : Cnodes) {
//                        int v = s.evaluation();
//                        if (v < min) {
//                            min = v;
//                        }
//                    }
//                    return min;
//                }
//            }
//        }
//
////        public void deep(int depth) {
////            if (depth == 0) return;
////            gnodes();
////            for (GameState s : Cnodes) {
////                s.deep(depth - 1);
////            }
////        }
//    }
//
//    public static int max(int[][] arr) {
//        int max=Integer.MIN_VALUE;
//        for(int i=0;i<arr.length;i++){
//            for(int j=0;j<arr[0].length;j++){
//                if(arr[i][j]>max){
//                    max=arr[i][j];
//                }
//            }
//        }
//        return max;
//    }

    /* Add a make move function and evaluate the move function
    which will just make the move for the class to be ready to implement in the game.
    Makemove func will use getpossiblemove functionin gameboard class and compare moves to get the best score.
     Number of simulations and search depth must be  updated after every move made
     Once the function have been written the class can be implemented in the game
    */
}


