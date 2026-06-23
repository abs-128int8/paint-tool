package painttool;

import painttool.state.State;
import painttool.state.RectState;

import java.awt.Color;

import painttool.drawing.Drawing;
import painttool.drawing.DrawingController;

public class PaintStateManager {
  private PaintCanvas canvas;

  private State state = new RectState(this);
  private boolean dashedLine = Drawing.DEFAULT_DASHED_LINE;
  private DashPattern dashPattern = Drawing.DEFAULT_DASH_PATTERN;
  private int lineWidth = Drawing.DEFAULT_LINE_WIDTH;
  private int lineCount = Drawing.DEFAULT_LINE_COUNT;
  private boolean dropShadow = Drawing.DEFAULT_DROP_SHADOW;
  private Color fillColor = Drawing.DEFAULT_FILL_COLOR;
  private Color lineColor = Drawing.DEFAULT_LINE_COLOR;

  public PaintStateManager(PaintCanvas canvas) {
    this.canvas = canvas;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void mouseDown(int x, int y) {
    state.mouseDown(x, y);
    canvas.repaint();
  }

  public void mouseUp(int x, int y) {
    state.mouseUp(x, y);
    canvas.repaint();
  }

  public void mouseDrag(int x, int y) {
    state.mouseDrag(x, y);
    canvas.repaint();
  }

  public void addDrawing(Drawing d) {
    d.setDashedLine(dashedLine);
    d.setDropShadow(dropShadow);
    d.setDashPattern(dashPattern);
    d.setLineWidth(lineWidth);
    d.setLineCount(lineCount);
    d.setFillColor(fillColor);
    d.setLineColor(lineColor);
    canvas.getController().addDrawing(d);
  }

  public void setDashedLine(boolean dashedLine) {
    this.dashedLine = dashedLine;
  }

  public boolean isDashedLine() {
    return dashedLine;
  }

  public void setDropShadow(boolean dropShadow) {
    this.dropShadow = dropShadow;
    getController().setSelectedDropShadow(dropShadow);
  }

  public boolean isDropShadow() {
    return dropShadow;
  }

  public void setDashPattern(DashPattern dashPattern) {
    this.dashPattern = dashPattern;
  }

  public DashPattern getDashPattern() {
    return dashPattern;
  }

  public void setLineWidth(int lineWidth) {
    this.lineWidth = lineWidth;
    getController().setSelectedLineWidth(lineWidth);
  }

  public int getLineWidth() {
    return lineWidth;
  }

  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
  }

  public int getLineCount() {
    return lineCount;
  }

  public DrawingController getController() {
    return canvas.getController();
  }

  public void setFillColor(Color color) {
    this.fillColor = color;
    getController().setSelectedFillColor(color);
  }

  public Color getFillColor() {
    var color = getController().getSelectedFillColor();
    if (color != null) {
      return color;
    }
    return fillColor;
  }

  public void setLineColor(Color color) {
    this.lineColor = color;
    getController().setSelectedLineColor(color);
  }

  public Color getLineColor() {
    var color = getController().getSelectedLineColor();
    if (color != null) {
      return color;
    }
    return lineColor;
  }
}
