#include <stdio.h>
#include <stdlib.h>

typedef struct Color {
    int r, g, b;
} Color;

typedef struct figure {
    int x, y, w, h;
    float thickness;
    Color border, inner;

    void (*print) (struct figure*);
} figure;

typedef void (*figure_print) (struct figure*);

////////////////////////////////////////

typedef struct hexagon {
    figure super;
    int x1, y1, x2, y2, x3, y3;
    int x4, y4, x5, y5, x6, y6;
    int x_array[6];
    int y_array[6];
} hexagon;

void hexagon_print(hexagon* this) {
    figure* sup = (figure*) this;
    printf("Hexagono de tamanho (%d, %d) na posicao (%d, %d).\n",
        sup->w, sup->h, sup->x, sup->y);
}

hexagon* hexagon_new(int x, int y, int w, int h) {
    hexagon* this = malloc(sizeof(hexagon));
    figure* sup = (figure*) this;
    sup->print = (figure_print) hexagon_print;
    sup->x = x;
    sup->y = y;
    sup->w = w;
    sup->h = h;

    this->x1 = x;
    this->y1 = y + h / 2;

    this->x2 = x + w / 3;
    this->y2 = y;

    this->x3 = x + 2 * w / 3;
    this->y3 = y;

    this->x4 = x + w;
    this->y4 = y + h / 2;

    this->x5 = x + 2 * w / 3;
    this->y5 = y + h;

    this->x6 = x + w / 3;
    this->y6 = y + h;

    this->x_array[0] = this->x1;
    this->x_array[1] = this->x2;
    this->x_array[2] = this->x3;
    this->x_array[3] = this->x4;
    this->x_array[4] = this->x5;
    this->x_array[5] = this->x6;
    
    this->y_array[0] = this->y1;
    this->y_array[1] = this->y2;
    this->y_array[2] = this->y3;
    this->y_array[3] = this->y4;
    this->y_array[4] = this->y5;
    this->y_array[5] = this->y6;

    sup->thickness = 1;

    return this;
}

////////////////////////////////////////

typedef struct triangle {
    figure super;
    int x1, y1, x2, y2, x3, y3;
    int x_array[3];
    int y_array[3];
} triangle;

void triangle_print(triangle* this) {
    figure* sup = (figure*) this;
    printf("Triangulo de tamanho (%d, %d) na posicao (%d, %d).\n",
        sup->w, sup->h, sup->x, sup->y);
}

triangle* triangle_new(int x, int y, int w, int h) {
    triangle* this = malloc(sizeof(triangle));
    figure* sup = (figure*) this;
    sup->print = (figure_print) triangle_print;
    sup->x = x;
    sup->y = y;
    sup->w = w;
    sup->h = h;

    this->x1 = x;
    this->y1 = y + h;

    this->x2 = x + w / 2;
    this->y2 = y;

    this->x3 = x + w;
    this->y3 = y + h;

    this->x_array[0] = this->x1;
    this->x_array[1] = this->x2;
    this->x_array[2] = this->x3;
    
    this->y_array[0] = this->y1;
    this->y_array[1] = this->y2;
    this->y_array[2] = this->y3;

    sup->thickness = 1;

    return this;
}

////////////////////////////////////////

int main () {
    figure* figs[5] = {
        (figure*) hexagon_new(10, 10, 23, 20),
        (figure*) triangle_new(12, 10, 40, 20),
        (figure*) hexagon_new(11, 10, 22, 21),
        (figure*) hexagon_new(16, 10, 28, 22),
        (figure*) triangle_new(14, 10, 21, 39)
    };

    for (int i = 0; i < 5; i++) 
        figs[i]->print(figs[i]);

    for (int i = 0; i < 5; i++) 
        free(figs[i]);

    return 0;
}