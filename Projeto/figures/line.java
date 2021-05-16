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

        this.thickness = 2;
        this.polygon = new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setColor(this.border);
        g2d.draw(this.polygon);
    }

    public void drag (Point mouse_pos, int dist_x, int dist_y) {
        this.x = mouse_pos.x - dist_x;
        this.y = mouse_pos.y - dist_y;
    }

    public boolean clicked(MouseEvent evt) {
        if (this.polygon.ptSegDist(evt.getPoint()) <= 5)
            return true;
        return false;
    }

    public void resize (Point mouse_pos) {
        this.w += mouse_pos.x - (this.x + this.w);
        this.h += mouse_pos.y - (this.y + this.h);

        if (this.w < 20) {
            this.w = 20;
            this.x2 = this.x + this.w;
        } if (this.h < 20) {
            this.h = 20;
            this.y2 = this.y + this.h;
        }
    }

    public void update() {
        this.x1 = x;
        this.y1 = y;

        this.x2 = x + w;
        this.y2 = y + h;

        this.polygon = new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }
}
