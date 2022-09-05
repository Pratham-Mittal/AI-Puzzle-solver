package Tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.stream.IntStream;

public class Board {
    private int zero =0;
    private int one = 1;
    private int w;
    private int h;
    private Color[][] board;
    public Board(int w, int h)
    {
        this.w = w;
        this.h = h;
        this.board = new Color[h][w];
    }

    public boolean place( int xaxispos, int yaxispos, Tetromino tetri)
    { switch (yaxispos) {
            case 0:
                return false;
        }
        boolean[][] value = tetri.getblockshape();
        int x = zero;
        while (x < value.length) {
            int y = zero;
            while (y < value[zero].length) {
                if (value[x][y]) {
                    board[x + yaxispos][y + xaxispos] = tetri.getcolor();
                }
                y++;
            }
            x++;
        }
        return true;
    }

    public void remove( int xaxispos, int yaxispos, Tetromino tetri)
    {
        boolean[][] value = tetri.getblockshape();
        int x = zero;
        while (x < value.length) {
            int y = zero;
            while (y < value[zero].length) {
                if (value[x][y]) {
                    board[x + yaxispos][y + xaxispos] = null; }
                y++;
            }
            x++;
        }
    }

    public boolean ch(int X, int Y)
    {
        return !(Y >= zero && Y < h && X >= zero && X < w) || board[Y][X] != null;
    }

    public boolean takar(int X, int Y, Tetromino tetri)
    {
        boolean[][] val = tetri.getblockshape();
        int x = zero;
        while (x < val.length) {
            int y = zero;
            while (y < val[zero].length) {
                if (val[x][y]) {

                    if (!((x + Y < h) && (y + X >= zero) && (y + X < w))) {

                        return true;
                    } else {

                        if (board[x + Y][y + X] != null) {

                            return true;
                        } } }
                y++;
            }
            x++;
        }
        return false;
    }

    public boolean checkforcompleteline(int i)
    {
        return IntStream.range(zero, w).noneMatch(x -> board[i][x] == null);
    }

    public double tetriseveluatetion(boolean b, int bordx, int bordY, int bs, Graphics2D g)
    {
        double score = zero;
        int x = h - one;
        while (x >= zero) {
            if (checkforcompleteline(x)) {
                if (b) {
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(bordx, x, bordx + w * bs, x + bs);

                    int y = x - one;
                    while (y > one) {
                        if (w >= zero) System.arraycopy(board[y], zero, board[y + one], zero, w);
                        y--;
                    }
                }
                score =score + one; }
            x--;
        }
        return score;
    }

    public void drawboard(int ox, int oy, int bs, Graphics2D g)
    {
        int x = zero;
        while (x < h) {
            int y = zero;
            while (y < w) {
                if (board[x][y] == null) {
                } else {
                    g.setColor(board[x][y]);
                    g.fillRect(ox + (y * bs), oy + (x * bs), bs, bs);
                }
                y++; }
            x++; }
    }
    public int getW()
    {
        int w = this.w;
        return w;
    }
    public int getHeight ()
    {
        int h = this.h;
        return h;
    }
}

