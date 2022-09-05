package twenty48;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MCTSALGO implements Agent{

    private static int one  =1;
    private static int zero  =0;
    Random random=new Random();

    int roundnumber=zero;
    int depth=200;//depth it goes to
    int simulation=25; //number of simulations it goes through

    static ExecutorService pool=Executors.newFixedThreadPool(4); //creates 4 threads to make 4 moves

    Future<Integer> createTask(final Boardtwenty board, final int move){ //if the computation is complete
        return pool.submit(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                return evalMove(board, move);
            }
        });
    }

    @Override
    public synchronized int makeMove(final Boardtwenty board) {
        var id=zero;
        List<Integer> m =board.possiblemoves();
        var best= zero;
        var bscore= -zero;
        ArrayList<ArrayList<Future<Integer>>> f=new ArrayList<>();
        {
            int i1 = 0;
            while (i1 < m.size()) {
                Integer i = m.get(i1);
                ArrayList<Future<Integer>> list = new ArrayList<>();
                list.add(createTask(board, i)); //makes the move up
                list.add(createTask(board, i));//or down
                list.add(createTask(board, i));//or right
                list.add(createTask(board, i));//or left
                f.add(list);

                i1++;
            }
        }
        for (Integer i : m) {
            var score = zero;
            ArrayList<Future<Integer>> ar = f.get(id++);
            for (int i1 = ar.size() - 1; i1 >= 0; i1--) {
                Future<Integer> ft = ar.get(i1);
                try {
                    score += ft.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if (score > bscore) {  //updates score
                bscore = score;
                best = i;
            }
        }
        roundnumber++;
        depth=roundnumber/10+100;
        simulation=roundnumber/16+2048;
        return best;
    }

    private int evalMove(Boardtwenty board, int move) {
        var count=zero;
        var srchspc=zero;
        Boardtwenty nb=new Boardtwenty(board); //board copy
        nb.mmove(move);
        nb.gentile();
        Boardtwenty updatedboard=new Boardtwenty();
        List<Integer> listofpossibleMoves;
        var i=zero;
        while (i<simulation) {
            updatedboard.copyFrom(nb);
            if (!(srchspc >= depth || (listofpossibleMoves = updatedboard.possiblemoves()).isEmpty()))
                do { //makes move if listofpossiblemoves is not empty
                    updatedboard.mmove(listofpossibleMoves.get(random.nextInt(listofpossibleMoves.size())));
                    updatedboard.gentile();
                    srchspc += one;
                } while (srchspc < depth && !(listofpossibleMoves = updatedboard.possiblemoves()).isEmpty());
            if (srchspc == depth) count = (int) (count + depth * 1.5);
            else count = count + srchspc;
            i++;
        }
        return count;
    }
}

