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

    public void drag (int x, int y, Point mouse_pos) {
        if (Math.sqrt(Math.pow((this.x + this.w) - mouse_pos.x, 2) + Math.pow((this.y + this.h)- mouse_pos.y, 2)) <= 10) {
            this.w += x;
            this.h += y;

            if (this.w < 20)
                this.w = 20;
            if (this.h < 20)
                this.h = 20;

            this.resize();
        } else { 
            this.x += x;
            this.y += y;

            for (int i = 0; i < this.x_array.length; i++) {
                this.x_array[i] += x;
                this.y_array[i] += y;
            }
            this.polygon = new Polygon(this.x_array, this.y_array, 3);
        }
    }

    public boolean contains(MouseEvent evt) {
        if (this.polygon.contains(evt.getPoint()))
            return true;
        return false;
    }

    public void resize () {
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