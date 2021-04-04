package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse3 extends Figure {
    private int x, y;
    private int w, h;

    private Color border, inner;

    public Ellipse3 (int x, int y, int w, int h, Color border, Color inner) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.border = border;
        this.inner = inner;
    }

    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.border);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));

        g2d.setColor(this.inner);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}
