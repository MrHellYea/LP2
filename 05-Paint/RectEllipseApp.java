import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

class RectEllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    Ellipse e1, e2, e3, e4;

    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Rect + Ellipse");
        this.setSize(350, 350);
        this.e1 = new Ellipse(50,100, 100,30, Color.BLACK, Color.BLUE);
        this.e2 = new Ellipse(200,100, 100,30, Color.ORANGE, Color.CYAN);
        this.e3 = new Ellipse(50,150, 100,30, Color.YELLOW, Color.MAGENTA);
        this.e4 = new Ellipse(200,150, 100,30, Color.GREEN, Color.LIGHT_GRAY);
    }

    public void paint (Graphics g) {
        super.paint(g);

        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.e4.paint(g);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    Color border, inner;

    Ellipse (int x, int y, int w, int h, Color border, Color inner) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.border = border;
        this.inner = inner;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.border);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));

        g2d.setColor(this.inner);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}