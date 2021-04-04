package figures;

import java.awt.*;

public class Hexagon2 extends Figure {
    private int x, y;
    private int w, h;
    private int x1, y1, x2, y2, x3, y3;
    private int x4, y4, x5, y5, x6, y6;

    private Color border, inner;
    
    public Hexagon2(int x, int y, int w, int h, Color border, Color inner) {
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

        this.border = border;
        this.inner = inner;
    }

    public void print() {
        System.out.format("Hexagono de tamanho (%d, %d), na posicao ($d, %d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int x[] = {this.x1, this.x2, this.x3, this.x4, this.x5, this.x6};
        int y[] = {this.y1, this.y2, this.y3, this.y4, this.y5, this.y6};

        Polygon InnerHexagon = new Polygon(x, y, 6);

        g2d.setColor(this.inner);
        g2d.drawPolygon(InnerHexagon);

        g2d.setColor(this.border);
        g2d.fillPolygon(InnerHexagon);
    }
}
