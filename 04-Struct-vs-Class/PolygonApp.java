public class PolygonApp {
    public static void main(String[] args) {
        Polygon p1 = new Polygon(100, 100, 5, 100, 100);
        p1.print();
    }
}

class Polygon {
    int x, y, sides, w, h;

    Polygon(int x, int y, int sides, int w, int h) {
        this.x = x;
        this.y = y;
        this.sides = sides;
        this.w = w;
        this.h = h;
    }

    void print() {
        System.out.format("Poligono de tamanho (%d, %d) na posicao (%d, %d) com %d vertices.\n",
            this.w, this.h, this.x, this.y, this.sides);
    }
}
