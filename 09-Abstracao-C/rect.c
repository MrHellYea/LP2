#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

typedef struct Rect {
    int x, y, w, h;

    void (*print) (struct Rect*);
    void (*drag) (struct Rect*, int, int);
} Rect;

Rect* Rect_new(int x, int y, int w, int h) {
    Rect* this = malloc(sizeof(Rect));

    this->print = Rect_print;
    this->drag = Rect_drag;
    this->x = x;
    this->y = y;
    this->w = w;
    this->h = h;

    return this;
}

void Rect_print(Rect* this) {
    printf("Retangulo de tamanho (%d, %d) na posicao (%d, %d).\n",
        this->w, this->h, this->x, this->y);
}

void Rect_drag(Rect* this, int dx, int dy) {
    this->x += dx;
    this->y += dy;
}
