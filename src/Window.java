import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Window extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    int cellSize, prevSize;
    double convertRatio;
    char currentKey = (char)0;
    JFrame window;
    Cell start, end, selectedCell, temp;
    List<Cell> borders;
    HashMap<Cell, Boolean> exists;

    public Window() {
        cellSize = 25;

        window = new JFrame();
        window.setContentPane(this);
        window.getContentPane().setPreferredSize(new Dimension(700, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        setFocusable(true);

        borders = new LinkedList<>();
        exists = new HashMap<>();

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //drawing cells
        g.setColor(Color.LIGHT_GRAY);
        for(int ritr=0; ritr<this.getWidth(); ritr+=cellSize) {
            for(int citr=0; citr<this.getHeight(); citr+=cellSize) {
                g.drawRect(ritr, citr, cellSize, cellSize);
            }
        }

        //start node marking
        if(start != null) {
            g.setColor(Color.red);
            g.fillRect(start.getX(), start.getY(), cellSize, cellSize);
        }

        //end node marking
        if(end != null) {
            g.setColor(new Color(176,48,176));
            g.fillRect(end.getX(), end.getY(), cellSize, cellSize);
        }

        //drawing borders
        if(borders.size() > 0) {
            g.setColor(Color.black);
            for(int bitr=0; bitr<borders.size(); bitr++){
                g.fillRect(borders.get(bitr).getX()+1, borders.get(bitr).getY()+1, cellSize-1, cellSize-1);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        currentKey = keyEvent.getKeyChar();
        //System.out.println(currentKey);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        currentKey = (char)0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseAction(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseAction(e);
    }

    public void mouseAction(MouseEvent e) {
        int xAlter = e.getX() % cellSize;
        int yAlter = e.getY() % cellSize;

        //System.out.println( (e.getX() - xAlter) + " , " + (e.getY() - yAlter) );
        selectedCell = new Cell(e.getX() - xAlter ,  e.getY() - yAlter);

        if(SwingUtilities.isLeftMouseButton(e)) {
            if(currentKey == 's' && !exists.containsKey(selectedCell)) {
                if(start == null) {
                    start = selectedCell;
                    exists.put(start, true);
                }
                else {
                    exists.remove(start);
                    start.setXY(e.getX() - xAlter, e.getY() - yAlter);
                    exists.put(selectedCell, true);
                }
            }
            else if(currentKey == 'e' && !exists.containsKey(selectedCell)) {
                if(end == null) {
                    end = selectedCell;
                    exists.put(end, true);
                }
                else {
                    exists.remove(end);
                    end.setXY(e.getX() - xAlter, e.getY() - yAlter);
                    exists.put(selectedCell, true);
                }
            }
            else {
                if(!exists.containsKey(selectedCell)) {
                    borders.add(selectedCell);
                    exists.put(selectedCell, true);
                    //print(exists);
                }
            }
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            if(exists.containsKey(selectedCell)) {
                exists.remove(selectedCell);
                if(start!=null && start.equals(selectedCell)) {
                    start = null;
                }
                else if(end!=null && end.equals(selectedCell)) {
                    end = null;
                }
                else {
                    borders.remove(selectedCell);
                }
            }
        }
        repaint();
    }

    public static void print(HashMap<Cell, Boolean> map) {
        if (map.isEmpty()) {
            System.out.println("map is empty");
        }
        else {
            System.out.println(map);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotationDirection = e.getWheelRotation();
        //System.out.println(rotationDirection);

        prevSize = cellSize;
        //zoom out = -1
        //zoom in = 1

        if(rotationDirection == 1 && cellSize < 200)
            cellSize += 3;
        else if(rotationDirection == -1 && cellSize > 5)
            cellSize -= 3;

        convertRatio = (double)cellSize / prevSize;

        if(start != null) {
            exists.remove(start);
            start.setXY( (int)Math.round(start.getX()*convertRatio), (int)Math.round(start.getY()*convertRatio) );
            exists.put(start, true);
        }
        if(end != null) {
            exists.remove(end);
            end.setXY( (int)Math.round(end.getX()*convertRatio), (int)Math.round(end.getY()*convertRatio) );
            exists.put(end, true);
        }
        if(borders.size() > 0) {
            for(int bindex=0; bindex<borders.size(); bindex++) {
                selectedCell = borders.get(bindex);
                exists.remove(selectedCell);
                selectedCell.setXY( (int)Math.round(selectedCell.getX()*convertRatio), (int)Math.round(selectedCell.getY()*convertRatio) );
                exists.put(selectedCell, true);
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    public static void main(String[] args) {
        new Window();
    }
}
