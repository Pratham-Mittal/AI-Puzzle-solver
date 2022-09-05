package Tetris;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Hashtable;


public class Tetris implements KeyListener
{
    private int zero = 0;
    private int one  =1;
    private final int yaxistext;
    private final int mstartx;
    private final int mstarty = 50;
    private final int px;
    private final int py;
    private final int bw;
    private final int bh;
    private final int mw;
    private final int mh;
    private final int bsize;
    private static Font font1 = new Font("Arial", Font.BOLD, 13);
    private static Font font2 = new Font("Arial", Font.BOLD, 23);
    private static Font  font3 = new Font("Arial", Font.BOLD, 32);
    private Board board;
    public Player pr;
    private int score;
    private static double u = 1;
    private boolean isgameover;
    private JFrame gameframe;
    private BufferStrategy buffer;
    private Canvas canvas;
    private Graphics2D g;

    public Tetris() {
        mstartx = 10;
        yaxistext = 28;
        px = 500;
        py = 50;
        bw = 800;
        bh = 800;
        mw = 15;
        mh = 23;
        bsize = 30;
    }
    public Board getBoard()
    {
        return board;
    }
    public Canvas getCanvas()
    {
        return canvas;
    }
    public static void main(String[] args)
    {
        Menu.Button q = new Menu.Button("quit");
        Menu.Button Reset = new Menu.Button("Reset");
        Hashtable<Integer, JLabel> sm;
        Hashtable<Integer, JLabel> marks;
        JLabel l3 = new JLabel("rules", SwingConstants.CENTER);
        JLabel l4 = new JLabel("AI", SwingConstants.CENTER);
        // JLabel l6 = new JLabel("Select", SwingConstants.CENTER);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        Hashtable<Integer, JLabel> smarks;
        smarks = new Hashtable<Integer, JLabel>();
        JFrame frame = new JFrame("Player Select");
        frame.setSize(400, 500);
        JLabel l1 = new JLabel("Select Player:", SwingConstants.CENTER);
        JLabel title = new JLabel("TETRIS", SwingConstants.CENTER);
        JPanel panel1 = new JPanel();
        JLabel l5 = new JLabel("Mechanics", SwingConstants.CENTER);
        l1.setForeground(Color.WHITE);
        title.setForeground(Color.WHITE);
        title.setFont(font3);
        title.setBorder(new EmptyBorder(15,0,0,0));
        l1.setBorder(new EmptyBorder(0,0,15,0));
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.BLACK);
        panel1.add(title, BorderLayout.NORTH);
        panel1.add(l1, BorderLayout.SOUTH);
        frame.add(panel1);
        JLabel l6 = new JLabel("Select", SwingConstants.CENTER);
        l5.setForeground(Color.WHITE);
        l6.setForeground(Color.WHITE);
        Button b1 = new Button("Human Player");
        Button b2 = new Button("AI");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        smarks.put(1, new JLabel("1"));
        smarks.put(100, new JLabel("100") );
        JPanel buttonpanel = new JPanel();
        GridLayout layout = new GridLayout(0,1);
        frame.setLayout(layout);
        frame.add(b1);
        frame.add(b2);
        frame.setVisible(true);
        JButton lleave = new JButton("leave");
        lleave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //setVisible (false);
                System.exit(0);
            }
        });
        q.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }

        });
        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        buttonpanel.add(lleave);
        b1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Thread th = new Thread(new Runnable()
                {
                    Tetris t = new Tetris();
                    @Override
                    public void run()
                    {
                        t.pr = new Human();
                        t.frameinitial(false);
                        t.games();
                    }
                });
                th.start();
                frame.dispose();
            }
        });

        b2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Thread th = new Thread(new Runnable()
                {
                    Tetris t = new Tetris();
                    @Override
                    public void run()
                    {

                        t.pr = new AI();
                        t.frameinitial(false);
                        t.games();
                    }
                });
                th.start();
                frame.dispose();
            }
        });
    }

    private void frameinitial(boolean reset)
    {
        if (reset) {
        } else {
            // Initial frame
            gameframe = new JFrame("Tetris");
            canvas = new Canvas();
            gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameframe.setSize(bw, bh);
            gameframe.setResizable(false);
            gameframe.setLocationRelativeTo(null); // Centers the window
            gameframe.addNotify();
            gameframe.add(canvas);
            canvas.setBackground(Color.BLACK);
            canvas.createBufferStrategy(2);
            canvas.setFocusable(true);
            canvas.addKeyListener(this);
            buffer = canvas.getBufferStrategy();
            g = (Graphics2D) buffer.getDrawGraphics();
            Tetromino.initialize();
            gameframe.setVisible(true);
        }
        score = zero;
        isgameover = false;
        board = new Board(mw, mh);
        pr.initial(this);
        pr.cntr(board);
    }

    private void draw()
    {
        if (!isgameover) {
        } else {
            g.setColor(Color.WHITE);
            g.setFont(font2);
            g.drawString("Well Played, Game Over", 465, 525);
            g.setFont(font2);
            g.drawString("Press Enter to Continue", 510, 555);
        }
        //title
        g.setColor(Color.WHITE);
        g.setFont(font1);
        int xaxistext = 10;
        g.drawString("Tetris", xaxistext, yaxistext);
        g.setColor(Color.WHITE);
        g.drawRect(
                mstartx - 2, mstarty - 2,
                mw * bsize + 5, mh * bsize + 5);
        board.drawboard(mstartx, mstarty, bsize, g);
        pr.ctetromino.drawblock(
                mstartx + pr.x * bsize,
                mstarty + pr.y * bsize,
                bsize, g);
        g.setColor(Color.WHITE);
        g.drawRect(px, py, 250, 250);
        g.setFont(font2);
        g.drawString("Number of Lines Completed", 480, 400); //score
        g.setColor(Color.WHITE);
        g.setFont(font2);
        FontMetrics fm = g.getFontMetrics(font2);
        int sx;
        sx = 640 - (int)(fm.stringWidth(String.valueOf(score)) / 2.0);
        g.drawString(String.valueOf(score), sx, 450);
        g.drawString("Next Tetromino", px + 40, py + 36); //next piece rev
        int smallx;
        smallx = (int) (px + 135 - (pr.next.GetWidth() / 2.0) * bsize);
        int smally;
        smally = (int) (py + 165 - (pr.next.GetHeight() / 2.0) * bsize);
        pr.next.drawblock(smallx, smally, bsize, g);
        g.setColor(Color.WHITE);
        g.setFont(font2);
    }
    public void flashing()
    {
        //this helps in solving the flashing of the blocks at each tick
        buffer = canvas.getBufferStrategy();
        g = (Graphics2D) buffer.getDrawGraphics();

        g.clearRect(0, 0, bw, bh); //clear bord
        draw();
        g.dispose();
        buffer.show();
    }
    private void update()
    {
        if (board.takar(pr.x, pr.y + 1, pr.ctetromino)) {
            boolean fail = !board.place(pr.x, pr.y, pr.ctetromino);
            if (!fail) {
                pr.upcoming();
                pr.cntr(board);
                pr.upcomingp(this);

                score += board.tetriseveluatetion(false, mstartx, mstarty, bsize, g);
                System.out.println("SCORE: " + score);
            } else {
                System.out.println("FINAL SCORE: " + score);
                isgameover = true;
                return;
            }

        } else {
            pr.y++;
            pr.upd(this);
        }
        if(isgameover)
            return;
    }
    private void games()
    {
        var ct = System.nanoTime();
        var lt = ct;
        int ut = zero;
        //ititial updates
        g.clearRect(0, 0, bw, bh);
        pr.upcomingp(this);
        update();
        do
        { //calc time
            ct = System.nanoTime();
            ut += ct - lt;
            lt = ct;
            if(ut >= (0.1/ u) * 1000000000L) //checks for time to be passed to put on next block
            {
                update();
                ut = zero;
            }
            flashing();
        }
        while(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) //if pressed enter go to init frame
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> frameinitial(true);
        }
    }

}
