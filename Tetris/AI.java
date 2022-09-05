package Tetris;
import java.util.ArrayList;
import java.util.stream.IntStream;

//idea taken from [Yiyuan Lee"Tetris AI- The (Near) Perfect Bot"](https://codemyroad.wordpress.com/2013/04/14/tetris-ai-the-near-perfect-player/)
public class AI extends Human
{
    private int zero = 0;
    private int one  =1;
    class tgt
    {
        public int x;
        public int y;
        public Tetromino.Rotation rot;
    }
    private ArrayList<Integer> h;
    private ArrayList<Integer> tempH;
    private tgt tgt;

    @Override
    public void initial(Tetris tetris)
    {
        super.initial(tetris);
        int size = tetris.getBoard().getW();
        h = new ArrayList<Integer>(size);
        tempH = new ArrayList<Integer>(size);
        IntStream.range(zero, size).forEach(i -> {
            h.add(zero);
            tempH.add(zero);
        });
    }
    @Override
    public void upcomingp(Tetris t)
    {
        calcH(h, t.getBoard());
        tgt = best(t.getBoard());
    }

    @Override
    public void upd(Tetris t)
    {
        if (ctetromino.getroationvalue() != tgt.rot) do {
            ctetromino.seedharotate();
        } while (ctetromino.getroationvalue() != tgt.rot);
        if (x < tgt.x) while (x != tgt.x) {
            x++;
        }
        else if (x > tgt.x) {
            while (x != tgt.x) {
                x--;
            }
        }
    }
    public int chhedcalc(Board b)  //calc holes
    {
        int ched = zero;
        {
            int x = zero;
            while (x < b.getW()) {
                {
                    int y = zero;
                    while (y < tempH.get(x)) {
                        if (b.ch(x, b.getHeight() - y)) {
                        } else {
                            ched++;
                        }y++;
                    } }
                x++;
            }
        }
        return ched;
    }

    private void calcH(ArrayList<Integer> h, Board b) //calc height
    {
        {
            int x = zero;
            while (x < b.getW()) {
                h.set(x, zero);
                {
                    int y = zero;
                    while (y < b.getHeight()) {
                        if (!b.ch(x, y)) {
                            y++;
                        } else {
                            h.set(x, b.getHeight() - y);
                            break;
                        }
                    } }
                x++;
            }
        }
    }

    //calc row
    public int calcrow(Board b)
    {
        int row = (int) IntStream.range(0, b.getHeight()).filter(b::checkforcompleteline).count();
        return row;
    }
    //returns heuristiocs
    public double getbscore(Board b, int X, int Y, Tetromino tetromino)
    {
        int agh = zero,bump = zero,ched,row;
        b.place(X, Y, tetromino);
        calcH(tempH, b);
        {
            int i = zero;
            while (i < tempH.size() - one) {
                bump =bump + Math.abs(tempH.get(i) - tempH.get(i + one));
                System.out.print(bump);
                i++;
            }
        }
        int i = zero;
        while (i < tempH.size()) {
            agh =agh + tempH.get(i);
            System.out.println(agh);
            i++;
        }
        ched = chhedcalc(b);
        row = calcrow(b);
        b.remove(X, Y, tetromino);

        return (-0.51 * agh) + (-0.18 * bump) + (-0.35 * ched) + (0.76 * row);
    }

    private AI.tgt best(Board b)
    {
        AI.tgt target = new AI.tgt();
        Tetromino shape = ctetromino.tapena();
        target.rot = Tetromino.Rotation.NONE;
        target.x = zero;
        target.y = zero;

        double bhs = -9999999.0;
        int r = zero;
        while (r < Tetromino.Rotation.values().length) {
            int checkHeight = 0;
            {
                int x = shape.GetWidth();
                while (x < b.getW()) { //if in the board
                    {
                        int y = shape.GetHeight();
                        while (y > zero) {
                            if(checkHeight < zero)
                            { r++;
                                x++;y--; continue; }
                            if (x > zero)
                                checkHeight = b.getHeight() - h.get(zero) - y;
                            else checkHeight = b.getHeight() - h.get(x) - y;

                            if(!b.takar(x, checkHeight, shape)) //collision test
                            { double score = getbscore(b, x, checkHeight, shape);
                                if (score > bhs) {
                                    target.x = x;
                                    target.y = checkHeight;
                                    target.rot = Tetromino.Rotation.values()[r];
                                    bhs = score;
                                } else {
                                    y--;
                                    continue;
                                }
                            }
                            y--;
                        }
                    }
                    x++;
                }
            }
            shape.seedharotate();
            r++;
        }
        return target;
    }
}

