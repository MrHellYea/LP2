import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main(String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    Rect2 r1, r2, r3, r4;

    PaintFrame() {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect2(50, 50, 100, 30, Color.BLACK, Color.RED);
        this.r2 = new Rect2(200, 50, 100, 30, Color.BLUE, Color.YELLOW);
        this.r3 = new Rect2(50, 100, 100, 30, Color.GREEN, Color.PINK);
        this.r4 = new Rect2(200, 100, 100, 30, Color.ORANGE, Color.CYAN);
    }

    public void paint(Graphics g) {
        super.paint(g);

        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.r4.paint(g);
    }
}

class Rect2 {
    int x, y;
    int w, h;
    Color border, inner;

    Rect2(int x, int y, int w, int h, Color border, Color inner) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.border = border;
        this.inner = inner;
    }

    void print() {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(this.inner);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        g2d.setPaint(this.border);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}