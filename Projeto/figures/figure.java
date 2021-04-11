package figures;

import java.awt.*;
import java.awt.event.*;

public abstract class figure {
    public int x, y;
    public int w, h;
    public float thickness;
    String type;
    public Color border, inner;
    
    public abstract void paint(Graphics g);
    public abstract void drag(int x, int y);
    public abstract boolean contains(MouseEvent evt);
    public abstract void resize();

    public void print () {
        System.out.format("%s de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.type, this.w, this.h, this.x, this.y);
    }
}