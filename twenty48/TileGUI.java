package twenty48;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class TileGUI extends JComponent{

    private static final double serialVersionUID = -8804446439773037674L;

    Tile tile;
    static final Color BACKGROUND=new Color(0xBBADA0);

    static final Color[] COLOR={
            new Color(255, 255, 255, 148),
            new Color(0xEC8787),	//2
            new Color(0xDC976D),	//4
            new Color(0xFF9B00),	//8
            new Color(0x799C54),	//16
            new Color(0x0D8700),	//32
            new Color(0x259E99),	//64
            new Color(0x208CFF),	//128
            new Color(0x4559D0),	//256
            new Color(0xB26ABF),	//512
            new Color(0x884990),	//1024
            new Color(0x743468),	//2048
            new Color(0x743468),	//SUPER
    };

    static final Color font1=new Color(0x78716D);
    static final Color font2=new Color(0xFFFFFF);

    static final Color[] fc={
            null,
            font1,	//2
            font1,	//4
            font2,	//8
            font2,	//16
            font2, //32
            font2,
            font2,
            font2,
            font2,
            font2,
            font2,
            font2,
    };

    final Font s=new Font("Arial", Font.BOLD , 30);
    final Font[] FONT={ null,s,s,s,s,s,s,s,s,s,s,s,s};

    public TileGUI(Tile t){
        tile=t;
        setSize(100, 100);
    }

    public void paint(Graphics gp){
        Graphics2D g=(Graphics2D)gp;
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, 100, 100);
        if (tile.getLog() == 0) {
            g.setColor(COLOR[0]);
            g.fillRoundRect(5, 5, 93, 93, 5, 5);
        } else {
            int tColorScheme=tile.getLog();
            if(tColorScheme>=COLOR.length)tColorScheme=COLOR.length-1;
            g.setColor(COLOR[tColorScheme]);
            g.fillRoundRect(5, 5, 93, 93, 5, 5);

            g.setFont(FONT[tColorScheme]);
            String str=tile.getVal()+"";
            g.setColor(fc[tColorScheme]);
            FontMetrics font=g.getFontMetrics();
            g.drawString(str, 50-g.getFontMetrics().stringWidth(str)/2, 50+(font.getAscent()-font.getDescent() + font.getLeading())/2);
        }
    }
    public void tilePos(int x, int y){
        setLocation(100*x, 100*y);
    }

}
class Tile {
    int p;

    public Tile(int i) {
        p =i;
    }

    public int getLog(){
        return p;
    }

    public int getVal(){
        return 1<< p;
    }

}

