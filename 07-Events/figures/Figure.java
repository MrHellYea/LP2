package figures;

import java.awt.*;

public abstract class Figure {
    int x, y;
    int w, h;
    String type;
    Color border, inner;

    public abstract void paint(Graphics g);

    public void print () {
        System.out.format("%s de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.type, this.w, this.h, this.x, this.y);
    }
}