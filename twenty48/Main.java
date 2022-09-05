package twenty48;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

interface Agent {
    int makeMove(Boardtwenty b);
}

public class Main extends JFrame{
    private static final Color bbgcolor = new Color(0xbbada0);
    private static final String font = "Arial";
    private static final int tsize = 64;
    private static final int tmarg = 16;
    static final String[] movenames = {"up", "down", "left", "right"};

    Boardtwenty board=new Boardtwenty();
    boolean stop=true;

    public Main() {
        //change agent to change the algorithm used
        final Agent agent=new MCTSALGO();

        JPanel Panel=new JPanel();
        JLabel score = new JLabel();
        JPanel Panel1 = new JPanel();

        final JButton help=new JButton("Hint");
        help.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                var action=agent.makeMove(board);
                help.setText("Hint: "+movenames[action]);
                requestFocus();
            }
        });
        Panel.add(help);
        Panel.add(score);


        final JButton algo=new JButton("AI");
        algo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stop){
                    stop=false;
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            while(!stop){
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                int i=agent.makeMove(board);  //makes move
                                board.mmove(i);
                                board.gentile();
                                repaint();
                            }
                        }
                    }).start();
                    help.setEnabled(false);
                }else{
                    stop=true;
                    requestFocus();
                    help.setEnabled(true);
                }
            }
        });
        Panel.add(help);
        Panel.add(algo);


        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int action=-1;
                switch(e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        action= Boardtwenty.left;
                        break;
                    case KeyEvent.VK_RIGHT:
                        action= Boardtwenty.right;
                        break;
                    case KeyEvent.VK_UP:
                        action= Boardtwenty.up;
                        break;
                    case KeyEvent.VK_DOWN:
                        action= Boardtwenty.down;
                        break;
                    default:
                        return;
                }
                if(board.Docan(action)){
                    board.mmove(action);
                    board.gentile();
                    help.setText("hint");
                }
                repaint();
            }
        });
        add(Panel, BorderLayout.NORTH);
        add(new GUI(board));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
    public void paint(Graphics g){
        setTitle("2048 - Score "+board.score);
        super.paint(g);

    }

    public static void main(String[] args){
        new Main();
    }
}
