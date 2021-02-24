import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            } 
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        getContentPane().setBackground(Color.black);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.red);
        int w = getWidth();
        int h = getHeight();

        g2d.drawLine(w/2,0, w/2,h);
        g2d.drawLine(0,h/2, w,h/2);
        g2d.drawLine(0,0, w,h);
        g2d.drawLine(0,h, w,0);

        g2d.setPaint(Color.green);
        g2d.fillRect((int) Math.round(w*0.1),
            (int) Math.round(h*0.15),
            (int) Math.round(w*0.8),
            (int) Math.round(w*0.3)
        );

        g2d.fillRect(
            (int) Math.round(w*0.1),
            (int) Math.round(h*0.55),
            (int) Math.round(w*0.8),
            (int) Math.round(w*0.3)
        );

        g2d.setPaint(Color.blue);
        g.drawString("Play", (int) Math.round(w*0.47), (int) Math.round(h*0.32));
        g.drawString("Exit", (int) Math.round(w*0.47), (int) Math.round(h*0.72));
    }
}