import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

public class ListApp {
    public static void main(String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    ListFrame() {
        this.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit((0));
                }
            }
        );

        this.addKeyListener(
            new KeyAdapter() {
                public void keyPressed(KeyEvent evt) {
                    int x = rand.nextInt(300);
                    int y = rand.nextInt(300);
                    int w = 10 + rand.nextInt(40);
                    int h = 10 + rand.nextInt(40);
                    if (evt.getKeyChar() == 'r')
                        figs.add(new Rect4(x, y, w, h, Color.BLACK, Color.BLUE));
                    else if (evt.getKeyChar() == 'e')
                        figs.add(new Ellipse3(x, y, w, h, Color.BLACK, Color.GREEN));
                    else if (evt.getKeyChar() == 'h')
                        figs.add(new Hexagon2(x, y, w, h, Color.BLACK, Color.RED));
                    repaint(); 
                }
            }
        );

        this.setTitle("Listas");
        this.setSize(350, 350);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (Figure fig: this.figs) {
            fig.paint(g);
            fig.print();
        }
    }
}
