package figures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class line extends figure{
    private int x1, x2;
    private int y1, y2;
    Line2D polygon;

    public line (int x, int y, int w, int h, Color border) {
        this.type = "Linha";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.x1 = x;
        this.y1 = y;

        this.x2 = x + w;
        this.y2 = y + h;

        this.border = border;

        this.thickness = 5;
        this.polygon = new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setColor(this.border);
        g2d.draw(this.polygon);
    }

    public void drag (int x, int y) {
        this.x += x;
        this.y += y;

        this.x1 += x;
        this.y1 += y;

        this.x2 += x;
        this.y2 += y;
        this.polygon = new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }

    public boolean contains(MouseEvent evt) {
        if (this.polygon.ptSegDist(evt.getPoint()) <= 5)
            return true;
        return false;
    }

    public void resize () {
        this.x1 = x;
        this.y1 = y;

        this.x2 = x + w;
        this.y2 = y + h;

        this.polygon = new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }
}
