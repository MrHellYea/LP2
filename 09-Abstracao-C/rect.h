typedef struct Rect Rect;
Rect* Rect_new(int, int, int, int);
void Rect_drag(Rect*, int, int);
void Rect_print(Rect*);