package Tetris;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Collections;

public class Tetromino
{

    private static int zero = 0;
    private static int one =1;

    private Tetromino() {};
    public static enum Rotation
    {
        NONE,
        sava,
        bytwo,
        threefourth
    }
    // private int zero = 0;
    private int o  =1;
    private Rotation rotation = Rotation.NONE;
    private Color color;
    private ArrayList<boolean[][]> shapes = new ArrayList<boolean[][]>();

    private static boolean[][] roatetet(boolean[][] shape)
    {
        boolean[][] res;
        res = new boolean[shape[zero].length][shape.length];
        int x = shape.length - one;
        while (x >= zero) {
            int y = shape[zero].length - one;
            while (y >= zero) {
                res[shape[zero].length - y - one][x] = shape[x][y];
                y--;
            }
            x--;
        }
        return res;
    }
    public Tetromino(boolean[][] bs, Color c)
    {
        this.shapes.add(bs);
        this.shapes.add(bs = roatetet(bs));
        this.shapes.add(bs = roatetet(bs));
        this.shapes.add(bs = roatetet(bs));
        this.rotation = Rotation.NONE;
        this.color = c;
    }
    public void drawblock(int ox, int oy, int bs, Graphics2D g)
    {
        boolean[][] s = getblockshape();
        int x = zero;
        while (true) {
            if (x >= s.length) break;
            int y = zero;
            while (true) {
                if (y >= s[zero].length) break;
                if(s[x][y])
                {
                    g.setColor(color);
                    g.fillRect(
                            ox + (y * bs),
                            oy + (x * bs),
                            bs, bs); }
                y++;
            }
            x++;
        }
    }

    public void ultarotate()
    { rotation = Rotation.values()[(rotation.ordinal() + 3) % 4]; //three byu 4 to rotate anti
    }
    public boolean[][] getblockshape()
    {
        final boolean[][] booleans = shapes.get(rotation.ordinal());
        return booleans;
    }
    public void seedharotate()
    {
        rotation = Rotation.values()[(rotation.ordinal() + one) % 4];
    }
    public int GetWidth()
    {
        final var length = getblockshape()[zero].length;
        return length;
    }
    public Rotation getroationvalue()
    {
        Rotation rotation = this.rotation;
        return rotation;
    }
    public int GetHeight()
    {
        int length = getblockshape().length;
        return length;
    }

    public Color getcolor()
    {
        Color color = this.color;
        return color;
    }
    public Tetromino tapena()
    {
        Tetromino res;
        res = new Tetromino();
        res.shapes = shapes;
        res.rotation = rotation;
        res.color = color;
        return res;
    }
    private static ArrayList<Tetromino> as;
    private static ArrayList<Tetromino> b;
    @SuppressWarnings("unchecked")
    public static Tetromino random()
    {
        if (b.size() >= 2) return b.remove(zero).tapena();
        b = (ArrayList<Tetromino>) as.clone();
        Collections.shuffle(b);
        return b.remove(0).tapena();
    }
    public static void initialize()
    {
        as = new ArrayList<Tetromino>();
        b = new ArrayList<Tetromino>();
        as.add(I);
        as.add(J);
        as.add(L);
        as.add(O);
        as.add(S);
        as.add(T);
        as.add(Z);
    }
    public static final Tetromino Z = new Tetromino
            (new boolean[][]
                    {{true ,true, false}, {false,true ,true }, {false,false,false}
                    }, Color.RED);
    public static  final Tetromino J = new Tetromino
            (new boolean[][]
                    {{true ,false,false}, {true ,true ,true }, {false,false,false}
                    }, Color.BLUE);

    public static final Tetromino I = new Tetromino
            (
                    new boolean[][]
                            {{false,false,false,false}, {true ,true ,true ,true }, {false,false,false,false}, {false,false,false,false}},
                    Color.WHITE
            );
    public static final Tetromino L = new Tetromino
            (
                    new boolean[][]
                            {{false,false,true }, {true ,true ,true }, {false,false,false}},
                    Color.ORANGE
            );

    public static final Tetromino O = new Tetromino
            (
                    new boolean[][]
                            {{true,true,}, {true,true,}},
                    Color.CYAN
            );

    public static final Tetromino S = new Tetromino
            (
                    new boolean[][]
                            {{false,true, true }, {true ,true ,false}, {false,false,false}
                            },
                    Color.GREEN
            );

    public static  final Tetromino T = new Tetromino
            (
                    new boolean[][]
                            {{false,true, false}, {true ,true ,true }, {false,false,false}
                            },
                    Color.MAGENTA
            );
}