package figures;

import java.awt.*;
import java.awt.event.*;

public class triangle extends figure {
    private int x1, x2, x3;
    private int y1, y2, y3;
    private int x_array[], y_array[];
    Polygon polygon;

    public triangle (int x, int y, int w, int h, Color border, Color inner) {
        this.type = "Triangulo";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.x1 = x;
        this.y1 = y + h;

        this.x2 = x + w / 2;
        this.y2 = y;

        this.x3 = x + w;
        this.y3 = y + h;

        int x_arr[] = {this.x1, this.x2, this.x3};
        int y_arr[] = {this.y1, this.y2, this.y3};
        this.x_array = x_arr;
        this.y_array = y_arr;

        this.border = border;
        this.inner = inner;

        this.thickness = 2;
        this.polygon = new Polygon(this.x_array, this.y_array, 3);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setColor(this.border);
        g2d.drawPolygon(this.polygon);

        g2d.setColor(this.inner);
        g2d.fillPolygon(this.polygon);
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
        this.x1 = x;
        this.y1 = y + h;

        this.x2 = x + w / 2;
        this.y2 = y;

        this.x3 = x + w;
        this.y3 = y + h;

        int x_arr[] = {this.x1, this.x2, this.x3};
        int y_arr[] = {this.y1, this.y2, this.y3};
        this.x_array = x_arr;
        this.y_array = y_arr;

        this.polygon = new Polygon(this.x_array, this.y_array, 3);
    }
}