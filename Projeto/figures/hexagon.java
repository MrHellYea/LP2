package figures;

import java.awt.*;
import java.awt.event.*;


public class hexagon extends figure {
    private int x1, y1, x2, y2, x3, y3;
    private int x4, y4, x5, y5, x6, y6;
    private int x_array[], y_array[];
    Polygon polygon;

    public hexagon(int x, int y, int w, int h, Color border, Color inner) {
        this.type = "Hexagono";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.x1 = x;
        this.y1 = y + h / 2;

        this.x2 = x + w / 3;
        this.y2 = y;

        this.x3 = x + 2 * w / 3;
        this.y3 = y;

        this.x4 = x + w;
        this.y4 = y + h / 2;

        this.x5 = x + 2 * w / 3;
        this.y5 = y + h;

        this.x6 = x + w / 3;
        this.y6 = y + h;

        int x_arr[] = {this.x1, this.x2, this.x3, this.x4, this.x5, this.x6};
        int y_arr[] = {this.y1, this.y2, this.y3, this.y4, this.y5, this.y6};
        this.x_array = x_arr;
        this.y_array = y_arr;

        this.border = border;
        this.inner = inner;

        this.thickness = 2;
        this.polygon = new Polygon(this.x_array, this.y_array, 6);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.setColor(this.border);
        g2d.drawPolygon(this.polygon);

        g2d.setColor(this.inner);
        g2d.fillPolygon(this.polygon);
    }

    public void drag(int x, int y, Point mouse_pos) {
        this.x += x;
        this.y += y;

        for (int i = 0; i < this.x_array.length; i++) {
            this.x_array[i] += x;
            this.y_array[i] += y;
        }
        this.polygon = new Polygon(this.x_array, this.y_array, 6);
    }

    public boolean contains(MouseEvent evt) {
        if (this.polygon.contains(evt.getPoint()))
            return true;
        return false;
    }

    public void resize () {
        this.x1 = x;
        this.y1 = y + h / 2;

        this.x2 = x + w / 3;
        this.y2 = y;

        this.x3 = x + 2 * w / 3;
        this.y3 = y;

        this.x4 = x + w;
        this.y4 = y + h / 2;

        this.x5 = x + 2 * w / 3;
        this.y5 = y + h;

        this.x6 = x + w / 3;
        this.y6 = y + h;

        int x_arr[] = {this.x1, this.x2, this.x3, this.x4, this.x5, this.x6};
        int y_arr[] = {this.y1, this.y2, this.y3, this.y4, this.y5, this.y6};
        this.x_array = x_arr;
        this.y_array = y_arr;

        this.polygon = new Polygon(this.x_array, this.y_array, 6);
    }
}
