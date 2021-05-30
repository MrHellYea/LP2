package buttons;

import java.awt.*;
import java.awt.event.*;
import interfaces.*;
import figures.*;

public class button implements iVisible {
    private int x, y;
    public figure fig;
    public boolean focused;

    public button(int index, figure fig) {
        this.fig = fig;
        this.focused = false;

        this.x = 10;
        this.y = 35 + index * 30;
    }

    public boolean clicked(MouseEvent evt) {
        if (evt.getX() >= this.x && evt.getX() <= this.x + 30 && evt.getY() >= this.y && evt.getY() <= this.y + 30)
            return true;

        return false;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(1));

        if (this.focused)
            g2d.setPaint(Color.DARK_GRAY);
        else
            g2d.setPaint(Color.GRAY);

        g2d.fillRect(this.x, this.y, 30, 30);

        g2d.setPaint(Color.BLACK);
        g2d.drawRect(this.x, this.y, 30, 30);

        this.fig.paint(g);
    }
}
