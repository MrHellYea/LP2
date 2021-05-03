package figures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class ellipse extends figure {
    Ellipse2D polygon;

    public ellipse (int x, int y, int w, int h, Color border, Color inner) {
        this.type = "Elipse";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.border = border;
        this.inner = inner;

        this.thickness = 2;
        this.polygon = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setColor(this.border);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));

        g2d.setColor(this.inner);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }

    public void drag (int x, int y, Point mouse_pos) {
        if (Math.sqrt(Math.pow((this.x + this.w) - mouse_pos.x, 2) + Math.pow((this.y + this.h)- mouse_pos.y, 2)) <= 10) {
            this.w += x;
            this.h += y;

            if (this.w < 20)
                this.w = 20;
            if (this.h < 20)
                this.h = 20;
        } else { 
            this.x += x;
            this.y += y;
        }
        this.polygon = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
    }

    public boolean contains(MouseEvent evt) {
        if (this.polygon.contains(evt.getPoint()))
            return true;
        return false;
    }

    public void resize () {
        this.polygon = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
    }
}
