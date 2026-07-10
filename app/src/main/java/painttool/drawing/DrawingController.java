package painttool.drawing;

import java.util.Vector;
import java.awt.Color;
import java.util.Enumeration;

import painttool.DashPattern;
import painttool.PaintCanvas;

public class DrawingController {
  private Vector<Drawing> drawings;
  private PaintCanvas canvas;
  private Vector<Drawing> selectedDrawings;
  private Vector<Drawing> copiedDrawings;
  private Rectangle selectionRectangle;
  private boolean rangeSelecting = false;

  public DrawingController(PaintCanvas canvas) {
    this.canvas = canvas;
    drawings = new Vector<Drawing>();
    selectedDrawings = new Vector<Drawing>();
    copiedDrawings = new Vector<Drawing>();
    selectionRectangle = new Rectangle(0, 0, 0, 0, Color.gray, new Color(0.5f, 0.5f, 0.5f, 0.6f), 2);
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

  public void moveSelectedDrawings(int dx, int dy) {
    for (var d : selectedDrawings) {
      d.move(dx, dy);
    }
  }

  public void repaint() {
    canvas.repaint();
  }

  public void clearSelecting() {
    for (var d : selectedDrawings) {
      d.setSelected(false);
    }
    selectedDrawings.clear();
  }

  public Vector<Drawing> getSelectedDrawings() {
    return selectedDrawings;
  }

  public Drawing findDrawingAt(int x, int y) {
    for (int i = drawings.size() - 1; i >= 0; i--) {
      Drawing d = drawings.get(i);
      if (d.contains(x, y)) {
        return d;
      }
    }
    return null;
  }

  public void addSelectedDrawing(Drawing drawing) {
    if (!selectedDrawings.contains(drawing)) {
      selectedDrawings.add(drawing);
      drawing.setSelected(true);
    }
  }

  public void selectDrawings() {
    clearSelecting();
    for (var d : drawings) {
      if (d == selectionRectangle) {
        continue;
      }
      if (selectionRectangle.getRegion().contains(d.getRegion().getBounds2D())) {
        addSelectedDrawing(d);
      }
    }
    repaint();
  }

  public void removeSelectedDrawing(Drawing drawing) {
    if (selectedDrawings.contains(drawing)) {
      selectedDrawings.remove(drawing);
      drawing.setSelected(false);
    }
  }

  public void removeSelectedDrawings() {
    drawings.removeAll(selectedDrawings);
    selectedDrawings.clear();
    repaint();
  }

  public void setSelectionBounds(int x, int y, int w, int h) {
    selectionRectangle.setBounds(x, y, w, h);
    repaint();
  }

  public void setRangeSelecting(boolean rangeSelecting) {
    if (rangeSelecting) {
      addDrawing(selectionRectangle);
    } else {
      removeDrawing(selectionRectangle);
    }
    this.rangeSelecting = rangeSelecting;
  }

  public boolean isRangeSelecting() {
    return rangeSelecting;
  }

  public void setSelectedFillColor(Color color) {
    for (var d : selectedDrawings) {
      d.setFillColor(color);
    }
    repaint();
  }

  public Color getSelectedFillColor() {
    if (!selectedDrawings.isEmpty()) {
      return selectedDrawings.get(0).getFillColor();
    }
    return null;
  }

  public void setSelectedLineColor(Color color) {
    for (var d : selectedDrawings) {
      d.setLineColor(color);
    }
    repaint();
  }

  public Color getSelectedLineColor() {
    if (!selectedDrawings.isEmpty()) {
      return selectedDrawings.get(0).getLineColor();
    }
    return null;
  }

  public void setSelectedLineWidth(int lineWidth) {
    for (var d : selectedDrawings) {
      d.setLineWidth(lineWidth);
    }
    repaint();
  }

  public void setSelectedDropShadow(boolean dropShadow) {
    for (var d : selectedDrawings) {
      d.setDropShadow(dropShadow);
    }
    repaint();
  }

  public void copySelectedDrawings() {
    if (!selectedDrawings.isEmpty()) {
      copiedDrawings.clear();
      for (var d : selectedDrawings) {
        copiedDrawings.add(d.clone());
      }
    }
  }

  public void cutSelectedDrawings() {
    copySelectedDrawings();
    removeSelectedDrawings();
  }

  public void pasteCopiedDrawings(int x, int y) {
    if (!copiedDrawings.isEmpty()) {
      for (var d : copiedDrawings) {
        Drawing newDrawing = d.clone();
        newDrawing.setLocation(x, y);
        addDrawing(newDrawing);
      }
      repaint();
    }
  }
}
