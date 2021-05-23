package figures;

import java.awt.*;
import interfaces.*;
import java.io.Serializable;

public abstract class figure implements iVisible, Serializable {
    public int x, y;
    public int w, h;
    public float thickness;
    public String type;
    public Color border, inner;
    
    public abstract void drag(Point mouse_pos, int dist_x, int dist_y);
    public abstract void resize(Point mouse_pos);
    public abstract void update();

    public void print () {
        System.out.format("%s de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.type, this.w, this.h, this.x, this.y);
    }
}