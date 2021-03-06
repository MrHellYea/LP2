import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    Rect3 r1, r2;
    Ellipse2 e1, e2;
    Hexagon1 h1, h2;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect3(50,50, 100,30, Color.BLACK, Color.BLUE);
        this.r2 = new Rect3(200,50, 100,30, Color.CYAN, Color.DARK_GRAY);
        this.e1 = new Ellipse2(50,100, 100,30, Color.GRAY, Color.GREEN);
        this.e2 = new Ellipse2(200,100, 100,30, Color.LIGHT_GRAY, Color.MAGENTA);
        this.h1 = new Hexagon1(50,150, 100,80, Color.ORANGE, Color.PINK);
        this.h2 = new Hexagon1(200,150, 100,80, Color.RED, Color.YELLOW);
    }

    public void paint (Graphics g) {
        super.paint(g);

        this.r1.paint(g);
        this.r2.paint(g);

        this.e1.paint(g);
        this.e2.paint(g);

        this.h1.paint(g);
        this.h2.paint(g);
    }
}