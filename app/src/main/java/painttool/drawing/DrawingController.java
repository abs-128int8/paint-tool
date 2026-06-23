package painttool.drawing;

import java.util.Vector;
import java.awt.Color;
import java.util.Enumeration;

import painttool.PaintCanvas;

public class DrawingController {
  private Vector<Drawing> drawings;
  private PaintCanvas canvas;
  private Drawing selectedDrawing = null;

  public DrawingController(PaintCanvas canvas) {
    this.canvas = canvas;
    drawings = new Vector<Drawing>();
  }

  public Enumeration<Drawing> getDrawingElements() {
    return drawings.elements();
  }

  public void addDrawing(Drawing drawing) {
    drawings.add(drawing);
  }

  public void removeDrawing(Drawing drawing) {
    drawings.remove(drawing);
  }

  // public Drawing getSelectedDrawing() {
  // return selectedDrawing;
  // }

  public void moveSelectedDrawing(int dx, int dy) {
    if (selectedDrawing != null) {
      selectedDrawing.move(dx, dy);
    }
  }

  public void repaint() {
    canvas.repaint();
  }

  public void selectDrawing(int x, int y) {
    for (var d : drawings) {
      d.setSelected(false);
      selectedDrawing = null;
    }

    for (int i = drawings.size() - 1; i >= 0; i--) {
      Drawing d = drawings.get(i);
      if (d.contains(x, y)) {
        selectedDrawing = d;
        selectedDrawing.setSelected(true);
        break;
      }
    }
  }

  public void setSelectedFillColor(Color color) {
    if (selectedDrawing != null) {
      selectedDrawing.setFillColor(color);
      repaint();
    }
  }

  public Color getSelectedFillColor() {
    if (selectedDrawing != null) {
      return selectedDrawing.getFillColor();
    }
    return null;
  }

  public void setSelectedLineColor(Color color) {
    if (selectedDrawing != null) {
      selectedDrawing.setLineColor(color);
      repaint();
    }
  }

  public Color getSelectedLineColor() {
    if (selectedDrawing != null) {
      return selectedDrawing.getLineColor();
    }
    return null;
  }

  public void setSelectedLineWidth(int lineWidth) {
    if (selectedDrawing != null) {
      selectedDrawing.setLineWidth(lineWidth);
      repaint();
    }
  }
}
