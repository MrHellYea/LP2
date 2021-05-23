import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

import figures.*;
import buttons.*;

public class ProjetoApp {
    public static void main(String[] args) {
        List_frame frame = new List_frame();
        frame.setVisible(true);
    }
}

class List_frame extends JFrame {
    private static final long serialVersionUID = 1L;

    boolean change_inner, change_border, resize;
    int inner_color_index = 0, border_color_index = 1, dist_x, dist_y;
    ArrayList<figure> figs = new ArrayList<figure>();

    ArrayList<button> buttons = new ArrayList<button>() {{
        add(new button(0, new rect(15, 40, 20, 20, Color.BLACK, Color.BLACK)));
        add(new button(1, new ellipse(15, 70, 20, 20, Color.BLACK, Color.BLACK)));
        add(new button(2, new triangle(15, 100, 20, 20, Color.BLACK, Color.BLACK)));
        add(new button(3, new hexagon(15, 130, 20, 20, Color.BLACK, Color.BLACK)));
        add(new button(4, new line(15, 160, 20, 20, Color.BLACK)));
    }};

    figure focus = null, focus_rect = new rect(0, 0, 0, 0, Color.RED, null), focus_ellipse = new ellipse(0, 0, 10, 10, Color.BLACK, Color.WHITE);
    button selected = null;

    Color aux = null, colors[] = {
        Color.WHITE, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
        Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.GRAY,
        Color.RED, Color.YELLOW, Color.PINK
    };

    List_frame() {
        this.setFocusTraversalKeysEnabled(false);

        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<figure>) o.readObject();
            o.close();
        } catch (Exception err) {
            System.out.format("Erro ao ler arquivo: %s\n", err.getMessage());
        }

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception err) {
                        System.out.format("Erro ao salvar arquivo: %s\n", err.getMessage());
                    }
 
                    System.exit(0);
                }
            }
        );

        this.addMouseListener (
            new MouseAdapter() {
                public void mousePressed (MouseEvent evt) {
                    if (selected != null && !(evt.getX() >= 10 && evt.getX() <= 40 && evt.getY() >= 35 && evt.getY() <= 185)) {
                        if (selected.fig.type == "Retangulo")
                            figs.add(new rect(evt.getX(), evt.getY(), 60, 60, Color.BLACK, Color.WHITE));
                        else if (selected.fig.type == "Elipse")
                            figs.add(new ellipse(evt.getX(), evt.getY(), 60, 60, Color.BLACK, Color.WHITE));
                        else if (selected.fig.type == "Triangulo")
                            figs.add(new triangle(evt.getX(), evt.getY(), 60, 60, Color.BLACK, Color.WHITE));
                        else if (selected.fig.type == "Hexagono")
                            figs.add(new hexagon(evt.getX(), evt.getY(), 60, 60, Color.BLACK, Color.WHITE));
                        else
                            figs.add(new line(evt.getX(), evt.getY(), 60, 60, Color.BLACK));

                        selected.focused = false;
                        selected = null;
                        repaint();
                        return;
                    }

                    selected = null;

                    for (button but: buttons) {
                        but.focused = false;

                        if (but.clicked(evt)) {
                            but.focused = true;
                            selected = but;
                        }
                    }

                    if (selected != null) {
                        focus = null;
                        repaint();
                        return;
                    }

                    if (focus_rect.clicked(evt) && focus != null) {
                        resize = false;
                        if (Math.sqrt(Math.pow((focus.x + focus.w) - evt.getX(), 2) + Math.pow((focus.y + focus.h) - evt.getY(), 2)) <= 10)
                            resize = true;
                        dist_x = evt.getX() - focus.x;
                        dist_y = evt.getY() - focus.y;
                        return;
                    }

                    focus = null;

                    for (figure fig: figs) {
                        if (fig.clicked(evt))
                            focus = fig;
                    }
                    
                    if (focus != null) {
                        resize = false;
                        if (Math.sqrt(Math.pow((focus.x + focus.w) - evt.getX(), 2) + Math.pow((focus.y + focus.h) - evt.getY(), 2)) <= 10)
                            resize = true;
                        dist_x = evt.getX() - focus.x;
                        dist_y = evt.getY() - focus.y;
                        focus_rect.x = focus.x - 1;
                        focus_rect.y = focus.y - 1;
                        focus_rect.w = focus.w + 2;
                        focus_rect.h = focus.h + 2;
                        focus_ellipse.x = focus.x + focus.w - 8;
                        focus_ellipse.y = focus.y + focus.h - 8;
                        focus_ellipse.update();
                        focus_rect.update();
                        figs.remove(focus);
                        figs.add(focus);
                    }

                    repaint();
                }
            }
        );

        this.addMouseMotionListener (
            new MouseMotionAdapter() {
                public void mouseDragged (MouseEvent evt) {
                    if (focus != null) {
                        Point point = evt.getPoint();

                        if (resize == true) {
                            focus.resize(point);
                            focus_rect.resize(point);
                        } else {
                            focus.drag(point, dist_x, dist_y);
                            focus_rect.drag(point, dist_x + 1, dist_y + 1);
                        }

                        focus_ellipse.x = focus.x + focus.w - 8;
                        focus_ellipse.y = focus.y + focus.h - 8;
                        focus.update();
                        focus_rect.update();
                        focus_ellipse.update();
                        repaint();
                    }
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed(KeyEvent evt) {
                    Point mouse_pos = getMousePosition();

                    if (mouse_pos == null) {
                        return;
                    }
                    if (evt.getKeyChar() == 'r')
                        figs.add(new rect(mouse_pos.x, mouse_pos.y, 60, 60, Color.BLACK, Color.WHITE));
                    else if (evt.getKeyChar() == 'e')
                        figs.add(new ellipse(mouse_pos.x, mouse_pos.y, 60, 60, Color.BLACK, Color.WHITE));
                    else if (evt.getKeyChar() == 'h')
                        figs.add(new hexagon(mouse_pos.x, mouse_pos.y, 60, 60, Color.BLACK, Color.WHITE));
                    else if (evt.getKeyChar() == 't')
                        figs.add(new triangle(mouse_pos.x, mouse_pos.y, 60, 60, Color.BLACK, Color.WHITE));
                    else if (evt.getKeyChar() == 'l')
                        figs.add(new line(mouse_pos.x, mouse_pos.y, 60, 60, Color.BLACK));
                    else if (evt.getKeyCode() == 9) { // tab                  
                        if (figs.size() > 0) {
                            focus = figs.get(0);
                            focus_rect.x = focus.x - 1;
                            focus_rect.y = focus.y - 1;
                            focus_rect.w = focus.w + 2;
                            focus_rect.h = focus.h + 2;
                            focus_ellipse.x = focus.x + focus.w - 8;
                            focus_ellipse.y = focus.y + focus.h - 8;
                            focus_rect.update();

                            figs.remove(focus);
                            figs.add(focus);
                        }
                    } else if (evt.getKeyCode() == 61 & focus != null) { // =
                        if (focus.thickness < 7.5)
                            focus.thickness += 0.5;
                    } else if (evt.getKeyCode() == 45 & focus != null) { // -
                        if (focus.thickness > 1)
                            focus.thickness -= 0.5;
                    } else if (evt.getKeyCode() == 35 & focus != null) { // end
                        inner_color_index--;
                        change_inner = true;
                    } else if (evt.getKeyCode() == 36 & focus != null) { // home
                        inner_color_index++;
                        change_inner = true;
                    } else if (evt.getKeyCode() == 33 & focus != null) { // pgup
                        border_color_index++;
                        change_border = true;
                    } else if (evt.getKeyCode() == 34 & focus != null) { // pgdn
                        border_color_index--;
                        change_border = true;
                    } else if (evt.getKeyCode() == 127 & focus != null) { // delete
                        figs.remove(focus);
                        focus = null;
                    }

                    if (border_color_index >= colors.length)
                        border_color_index = 0;
                    else if (border_color_index < 0)
                        border_color_index = colors.length - 1;
                    else if (inner_color_index >= colors.length)
                        inner_color_index = 0;
                    else if (inner_color_index < 0)
                        inner_color_index = colors.length - 1;

                    if (focus != null) {
                        if (change_border == true) {
                            focus.border = colors[border_color_index];
                            change_border = false;
                            aux = focus.border;
                        } else if (change_inner == true) {
                            focus.inner = colors[inner_color_index];
                            change_inner = false;
                        }
                    }

                    repaint(); 
                }
            }
        );

        this.setTitle("Projeto");
        this.setSize(600, 600);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (figure fig: this.figs) {
            fig.paint(g);
        }
        
        if (focus != null) {
            focus_rect.paint(g);
            focus_ellipse.paint(g);
        }

        for (button but: this.buttons) {
            but.paint(g);
        }
    }
}
