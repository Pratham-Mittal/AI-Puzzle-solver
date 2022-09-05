package Tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Human extends Player implements KeyListener
{
    int zero = 0;
    int one = 1;
    private Board b;
    private boolean nr = false;
    Human() {}
    @Override
    public void initial(Tetris tetris)
    {
        super.initial(tetris);
        this.b = tetris.getBoard();
        if (nr) {
            return;
        }
        tetris.getCanvas().addKeyListener(this);
        nr = true;
    }
    @Override
    public void upd(Tetris tet) { }
    @Override
    public void upcomingp(Tetris tet) { }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e)
    {
        // Left
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!b.takar(x - 1, y, ctetromino)) {
                    x--;
                }
                break;
        }
        // Down
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if (!b.takar(x, y + 1, ctetromino)) {
                    y++;
                }
                break;
        }
        // Right
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R -> {
                ctetromino.seedharotate();
                if (b.takar(x, y, ctetromino)) {
                    ctetromino.ultarotate();
                }
            }
        }
        // Right
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (!b.takar(x + 1, y, ctetromino)) {
                    x++;
                }
                break;
        }
    }
}
abstract class Player
{
    public int zero = 0;
    public int one  =1;
    public int x,a,b,c;
    public int y = zero;
    public Tetromino ctetromino;
    public Tetromino next;

    protected Player() {
        x = zero;
    }

    public abstract void upcomingp(Tetris tetris);
    public abstract void upd(Tetris tetris);
    public void initial(Tetris tetris)
    {
        x = zero;
        y = zero;
        this.ctetromino = Tetromino.random();
        this.next = Tetromino.random();
    }
    public final void upcoming()
    {
        this.ctetromino = next;
        this.next = Tetromino.random();
    }
    public final void cntr(Board board)
    {
        x = board.getW() / 2 - ctetromino.GetWidth() / 2;
        y = zero;
    }
}
