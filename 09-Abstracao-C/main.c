#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

int main() {
    Rect* r1 = Rect_new(10, 15, 10, 10);
    Rect_print(r1);

    Rect* r2 = Rect_new(20, 25, 30, 20);
    Rect_drag(r2, 15, 15);
    Rect_print(r2);

    free(r1);
    free(r2);

    return 0;
}