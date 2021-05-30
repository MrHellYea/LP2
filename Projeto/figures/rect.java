package figures;

import java.awt.*;
import java.awt.event.*;

public class rect extends figure {
    private Rectangle polygon;

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

        if (this.inner != null) {
            g2d.setPaint(this.inner);
            g2d.fillRect(this.x, this.y, this.w, this.h);
        }

        g2d.setPaint(this.border);
        g2d.drawRect(this.x, this.y, this.w, this.h);
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
        this.polygon = new Rectangle(this.x, this.y, this.w, this.h);
    }
}
