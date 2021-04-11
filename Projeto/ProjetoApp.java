import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

public class ProjetoApp {
    public static void main(String[] args) {
        List_frame frame = new List_frame();
        frame.setVisible(true);
    }
}

class List_frame extends JFrame {
    private static final long serialVersionUID = 1L;

    Point start_pos;
    boolean change_inner, change_border;
    int inner_color_index = 0, border_color_index = 1;
    ArrayList<figure> figs = new ArrayList<figure>();
    figure focus = null;
    Color aux = null, colors[] = {
        Color.WHITE, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
        Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.GRAY,
        Color.RED, Color.YELLOW, Color.PINK
    };
    Random rand = new Random();

    List_frame() {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addMouseListener (
            new MouseAdapter() {
                public void mousePressed (MouseEvent evt) {
                    start_pos = getMousePosition();
                    if (focus != null) {
                        focus.border = aux;
                    }

                    focus = null;

                    for (figure fig: figs) {
                        if (fig.contains(evt)) {
                            focus = fig;
                            aux = focus.border;
                        }
                    }
                    
                    if (focus != null) {
                        focus.border = Color.RED;
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
                        if (start_pos != null)
                            focus.drag(evt.getX() - start_pos.x, evt.getY() - start_pos.y);
                        start_pos = getMousePosition();
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
                    else if (evt.getKeyCode() == 39 & focus != null) { // right
                        if (focus.w < 200) {
                            focus.w += 5;
                            focus.resize();
                        }
                    } else if (evt.getKeyCode() == 37 & focus != null) { // left
                        if (focus.w > 20) {
                            focus.w -= 5;
                            focus.resize();
                        }
                    } else if (evt.getKeyCode() == 38 & focus != null) { // up
                        if (focus.h > 20) {
                            focus.h -= 5;
                            focus.resize();
                        }
                    } else if (evt.getKeyCode() == 40 & focus != null) { // down
                        if (focus.h < 200) {
                            focus.h  += 5;
                            focus.resize();
                        }
                    } else if (evt.getKeyCode() == 61 & focus != null) { // =
                        if (focus.thickness < 5)
                            focus.thickness += 0.2;
                    } else if (evt.getKeyCode() == 45 & focus != null) { // -
                        if (focus.thickness > 1)
                            focus.thickness -= 0.2;
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
    }
}
