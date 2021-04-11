package figures;

import java.awt.*;
import java.awt.event.*;

public class rect extends figure {
    Rectangle polygon;

    public rect (int x, int y, int w, int h, Color border, Color inner) {
        this.type = "Retangulo";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.border = border;
        this.inner = inner;
        this.thickness = 1;

        this.polygon = new Rectangle(this.x, this.y, this.w, this.h);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setPaint(this.inner);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        g2d.setPaint(this.border);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }

    public void drag (int x, int y, Point mouse_pos) {
        this.x += x;
        this.y += y;
        this.polygon = new Rectangle(this.x, this.y, this.w, this.h);
    }

    public boolean contains(MouseEvent evt) {
        if (this.polygon.contains(evt.getPoint()))
            return true;
        return false;
    }

    public void resize () {
        this.polygon = new Rectangle(this.x, this.y, this.w, this.h);
    }
}
