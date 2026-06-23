package painttool;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import painttool.drawing.Drawing;
import painttool.drawing.DrawingController;

public class PaintCanvas extends JPanel {
  private DrawingController controller;
  private int mouseX;
  private int mouseY;

  public PaintCanvas() {
    controller = new DrawingController(this);

    setBackground(Color.white);
    setFocusable(true);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    var e = controller.getDrawingElements();
    while (e.hasMoreElements()) {
      Drawing d = e.nextElement();
      d.draw(g);
    }
  }

  public DrawingController getController() {
    return controller;
  }

  public void setMousePosition(int x, int y) {
    mouseX = x;
    mouseY = y;
  }

  public int getMouseX() {
    return mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }
}
