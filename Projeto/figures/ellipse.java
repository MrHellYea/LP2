package figures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class ellipse extends figure {
    private Ellipse2D polygon;

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

    public void drag (Point mouse_pos, int dist_x, int dist_y) {
        this.x = mouse_pos.x - dist_x;
        this.y = mouse_pos.y - dist_y;
    }

    public boolean clicked(MouseEvent evt) {
        if (this.polygon.contains(evt.getPoint()))
            return true;
        return false;
    }

    public void resize (Point mouse_pos) {
        this.w += mouse_pos.x - (this.x + this.w);
        this.h += mouse_pos.y - (this.y + this.h);

        if (this.w < 20)
            this.w = 20;
        if (this.h < 20)
            this.h = 20;
    }

    public void update() {
        this.polygon = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
    }
}
