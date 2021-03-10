#include <stdio.h>

typedef struct {
    int x, y;
    float radius;
} Circle;

void print(Circle*);

int main() {
    Circle c1 = {1820, 980, 100.0};
    print(&c1);

    return 0;
}

void print(Circle* circle) {
    printf("Circulo de raio %.2f na posicao (%i, %i).\n", circle->radius, circle->x, circle->y);
}
