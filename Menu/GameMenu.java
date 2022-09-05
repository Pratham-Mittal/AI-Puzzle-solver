package Menu;

import Tetris.Tetris;
import twenty48.Main;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameMenu extends JFrame {

    public static void main(String args[]) {
        new GameMenu();
    }
    public GameMenu() {
        setSize(600, 400);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        ImageIcon logo = new ImageIcon();
//        try {
//
//            logo = new ImageIcon(ImageIO.read(this.getClass().getResource("/Resources/download.jpg")));
//            Image image = logo.getImage();
//            Image newimg = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//            logo = new ImageIcon(newimg);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        setLayout(new BorderLayout());
        //getContentPane().add(new JLabel(logo), BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.black);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        Button tetrisButton = new Button("Tetris");
        Button twentyButton = new Button("2048");
        tetrisButton.setFont(new Font("Arial", Font.BOLD, 30));
        twentyButton.setFont(new Font("Arial", Font.BOLD, 30));
        Button q = new Button("quit");
        Button Reset = new Button("Reset");

        tetrisButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        twentyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        tetrisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = new String[]{};
                Tetris.main(args);
            }
        });

        twentyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = new String[]{};
                Main.main(args);
            }
        });
        buttons.add(tetrisButton);
        buttons.add(twentyButton);
        getContentPane().add(buttons);
        setVisible(true);
    }
}




