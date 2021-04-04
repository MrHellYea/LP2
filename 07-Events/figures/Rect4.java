package figures;

import java.awt.*;

public class Rect4 extends Figure {
    public Rect4 (int x, int y, int w, int h, Color border, Color inner) {
        this.type = "Retangulo";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.border = border;
        this.inner = inner;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(this.inner);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        g2d.setPaint(this.border);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
