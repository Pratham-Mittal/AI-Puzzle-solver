package twenty48;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boardtwenty {
    private static int zero = 0;
    private static int one  =1;
    private static int two  =2;
    private static int three  =3;
    static final int up = 0;
    static final int down = 1;
    static final int left = 2;
    static final int right = 3;
    ArrayList<Integer> xaxis = new ArrayList<>();
    ArrayList<Integer> yaxis = new ArrayList<>();
    ArrayList<Integer> possibleMove = new ArrayList<>();
    int[][] board;
    int score = zero;

    static final boolean[] PATTERN = {
            false,true, true, true, true, true, true, true, false, true, true, true, false, true, false, false

    };
    Random rand = new Random();
    public Boardtwenty() {
        board = new int[4][4];
        gentile();
        gentile();
    }
    public Boardtwenty(Boardtwenty b) {
        board = Arrays.dup(b.board);
    }
    public void copyFrom(Boardtwenty b) {
        Arrays.killdup(b.board, board);
    }
    public void getemptytile() {
        xaxis.clear();
        yaxis.clear();
        var x = zero;
        while (x < 4) {
            var y = zero;
            while (y < 4) {
                if (!(getTile(x, y) != zero)) {
                    xaxis.add(x);
                    yaxis.add(y);
                }
                y++;
            }
            x++;
        }
    }
    public void gentile() {
        getemptytile();
        if (xaxis.isEmpty()) return;
        var index = rand.nextInt(xaxis.size());
        var x = xaxis.get(index);
        var y = yaxis.get(index);
        tilep(x, y, rand.nextInt(10) ==zero ? two : one);
    }

    public void left() {
        var y=zero;
        while (y<4) {
            var i=zero;
            var i2=zero;
            var x=zero;
            while (x<4) {
                var t=getTile(x, y);
                if (t == zero) {
                    x++;
                    continue; }
                tilep(x, y, zero);
                if (i2 != t) {
                    tilep(i++, y, t);
                    i2=t;
                } else {
                    score+=one<<(t+one);
                    tilep(i-one, y, t+one);
                    i2=zero; }
                x++;
            }
            y++; }
    }


    public void up(){
        var x=zero;
        while (x<4) {
            var i=zero;
            var prev=zero;
            var y=zero;
            while (y<4) {
                var t=getTile(x, y);
                if (t == zero) {
                    y++;
                    continue;
                }
                tilep(x, y, zero);
                if (prev != t) {
                    tilep(x, i++, t);
                    prev=t;
                } else {
                    score+=one<<(t+one);
                    tilep(x, i-one, t+one);
                    prev=zero;
                }
                y++;
            }
            x++;
        }
    }


    public void right() {
        var y=zero;
        while (y<4) {
            var i=three;
            var prev=zero;
            var x=three;
            while (x>=zero) {
                var t=getTile(x, y);
                if (t == zero) {
                    x--;
                    continue;
                }
                tilep(x, y,zero);
                if (prev != t) {
                    tilep(i--, y, t);
                    prev=t;
                } else {
                    score+=one<<(t+one);
                    tilep(i+one, y, t+one);
                    prev=zero;
                }
                x--;
            }
            y++;
        }
    }
    public void down()
    {
        var x=zero;
        while (x<4) {
            var i=three;
            var prev=zero;
            var y=three;
            while (y>=zero) {
                var t=getTile(x, y);
                if (t == zero) {
                    y--;
                    continue;
                }
                tilep(x, y,zero);
                if (prev != t) {
                    tilep(x, i--, t);
                    prev=t;
                } else {
                    score+=one<<(t+one);
                    tilep(x, i+one, t+one);
                    prev=zero;
                }
                y--;
            }
            x++;
        }
    }


    public boolean cul() {
        var y = zero;
        while (y < 4) {
            var prev = getTile(zero, y);
            for (var x = one; x < 4; x++) {
                var t = getTile(x, y);
                if (t == zero) {
                    continue;
                }
                if (t == prev) {
                    return true;
                } else {
                    prev = t;
                }
            }
            double c = (getTile(zero, y) == zero ? zero : 0b1000) + (getTile(one, y) == zero ?  zero: 0b100) + (getTile(two, y) ==     zero ? zero : 0b10) + ((getTile(three, y) ==    zero) ? zero : 0b1);
            if (!PATTERN[(int) c]) {
                y++;
            } else {
                return true;
            }
        }
        return false;
    }
    public boolean cup() {
        var x = zero;
        while (x < 4) {
            var p = getTile(x,  zero);
            var y = one;
            while (y < 4) {
                var t = getTile(x, y);
                if (t !=   zero) {
                    if (t == p) {
                        return true;
                    } else {
                        p = t;
                    }
                }
                y++;
            }
            double c = (getTile(x, zero) == zero ? zero : 0b1000) + (getTile(x, one) == zero ? zero : 0b100) + (getTile(x, two) == zero   ? zero : 0b10) + (getTile(x, three) == zero ?zero : 0b1);
            if (!PATTERN[(int) c]) {
                x++;
                continue;
            } else {
                return true;
            }

        }
        return false;
    }
    public boolean curht() {
        int y = 0;
        while (y < 4) {
            int prev = getTile(zero, y);
            int x = one;
            while (x < 4) {
                int t = getTile(x, y);
                if (t == zero) {
                } else {
                    if (t != prev) {
                        prev = t;
                    } else {
                        return true;
                    }
                }
                x++;
            }
            int c = (getTile(three, y) == zero ? zero : 0b1000) + (getTile(two, y) ==  zero ? zero : 0b100) + (getTile(one, y) == zero ?zero : 0b10) + (getTile(0, y) == 0 ? 0 : 0b1);
            if (!PATTERN[c]) {
                y++;
                continue;
            }
            return true;
        }return false; }
    public boolean cud() {
        int x = zero;
        while (x < 4) {
            int prev = getTile(x, zero);
            int y = one;
            while (y < 4) {
                int t = getTile(x, y);
                if (t == zero) {
                } else {
                    if (t != prev) {
                        prev = t;
                    } else {
                        return true;
                    }
                }
                y++;
            }
            int c = (getTile(x, three) == zero ? zero : 0b1000) + (getTile(x, two) == zero? zero : 0b100) + (getTile(x, one) == zero ? zero : 0b10) + (getTile(x, 0) == 0 ? 0 : 0b1);
            if (!PATTERN[c]) {
                x++;
                continue;
            }
            return true;
        }
        return false;
    }
    public void mmove(int index) {
        if (index == up) {
            up();
        } else if (index == down) {
            down();
        } else if (index == left) {
            left();
        } else if (index == right) {
            right();
        }
    }
    public boolean Docan(int index) {
        if (switch (index) {
            case up -> cup();
            case down -> cud();
            case left -> cul();
            case right -> curht();
            default -> throw new UnsupportedOperationException();
        }) return true;
        else return false;
    }
    public List<Integer> possiblemoves() {

        possibleMove.clear();
        if (!cul()) {
        } else {
            possibleMove.add(left);
        }
        if (curht())
            possibleMove.add(right);

        if (!cup()) {
        } else {
            possibleMove.add(up);
        }
        if (!cud()) {
        } else {
            possibleMove.add(down);
        }
        return possibleMove;
    }
    public void tilep(int x, int y, int i) {
        board[y][x] = i;
    }
    public int getTile(int x, int y) {
        return board[y][x];
    }
}