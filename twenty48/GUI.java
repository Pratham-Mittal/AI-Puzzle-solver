package twenty48;

import java.awt.*;
import javax.swing.JComponent;

public class GUI extends JComponent{
    private static final long serialVersionUID = -8804446439773037674L;
    private static int four  =4;
    private static int one  =1;
    private static int zero  =0;

    TileGUI[][] tiles=new TileGUI[four][four];
    Boardtwenty board;
    public Dimension getPreferredSize(){
        return new Dimension(400, 400);
    }
    public GUI(Boardtwenty bord)
    {
        board=bord ;
        for(var x=zero;x<four;x++)
        {
            for(var y=zero;y<four;y++)
            {
                tiles[x][y]=new TileGUI(new Tile(zero));
                tiles[x][y].tilePos(x, y);
                add(tiles[x][y]);
            }
        }
    }
    public void paintComponent(Graphics g){
        for(var x=zero;x<four;x++){
            for(var y=zero;y<four;y++){
                tiles[x][y].tile.p = board.getTile(x, y);
            }
        }
        g.setColor(new Color(0xBAB5AA));
        g.fillRect(zero, zero, 500, 500);
    }
}
