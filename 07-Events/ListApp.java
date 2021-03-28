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

    ArrayList<Ellipse3> es = new ArrayList<Ellipse3>();
    ArrayList<Rect4> rs = new ArrayList<Rect4>();
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
                    if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(300);
                        int y = rand.nextInt(300);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        rs.add(new Rect4(x, y, w, h, Color.BLACK, Color.BLUE));
                        repaint(); 
                    }

                    else if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(300);
                        int y = rand.nextInt(300);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        es.add(new Ellipse3(x, y, w, h, Color.BLACK, Color.GREEN));
                        repaint(); 
                    }
                }
            }
        );

        this.setTitle("Listas");
        this.setSize(350, 350);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (Rect4 r: this.rs)
            r.paint(g);
        for (Ellipse3 e: this.es)
            e.paint(g);
    }
}
