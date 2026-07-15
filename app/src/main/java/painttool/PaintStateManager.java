package painttool;

import painttool.state.State;
import painttool.state.RectState;

import java.awt.Color;

import painttool.drawing.Drawing;
import painttool.drawing.DrawingController;

public class PaintStateManager {
  private PaintCanvas canvas;
  private DrawingController controller;

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
    this.controller = canvas.getController();
  }

  public void setState(State state) {
    this.state = state;
  }

  public void mouseDown(int x, int y, boolean isControlDown, boolean isShiftDown) {
    state.mouseDown(x, y, isControlDown, isShiftDown);
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
    controller.addDrawing(d);
  }

  public DrawingController getController() {
    return controller;
  }

  public void setDashedLine(boolean dashedLine) {
    this.dashedLine = dashedLine;
    controller.setSelectedDashedLine(dashedLine);
  }

  public boolean isDashedLine() {
    var selectedDashedLine = controller.isSelectedDashedLine();
    return (selectedDashedLine) ? selectedDashedLine : dashedLine;
  }

  public void setDropShadow(boolean dropShadow) {
    this.dropShadow = dropShadow;
    controller.setSelectedDropShadow(dropShadow);
  }

  public boolean isDropShadow() {
    var selectedDropShadow = controller.isSelectedDropShadow();
    return (selectedDropShadow) ? selectedDropShadow : dropShadow;
  }

  public void setDashPattern(DashPattern dashPattern) {
    this.dashPattern = dashPattern;
    controller.setSelectedDashPattern(dashPattern);
  }

  public DashPattern getDashPattern() {
    var selectedDashPattern = controller.getSelectedDashPattern();
    return (selectedDashPattern != null) ? selectedDashPattern : dashPattern;
  }

  public void setLineWidth(int lineWidth) {
    this.lineWidth = lineWidth;
    controller.setSelectedLineWidth(lineWidth);
  }

  public int getLineWidth() {
    var selectedLineWidth = controller.getSelectedLineWidth();
    return (selectedLineWidth != -1) ? selectedLineWidth : lineWidth;
  }

  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
    controller.setSelectedLineCount(lineCount);
  }

  public int getLineCount() {
    var selectedLineCount = controller.getSelectedLineCount();
    return (selectedLineCount != -1) ? selectedLineCount : lineWidth;
  }

  public void setFillColor(Color color) {
    this.fillColor = color;
    controller.setSelectedFillColor(color);
  }

  public Color getFillColor() {
    var selectedFillColor = controller.getSelectedFillColor();
    return (selectedFillColor != null) ? selectedFillColor : fillColor;
  }

  public void setLineColor(Color color) {
    this.lineColor = color;
    controller.setSelectedLineColor(color);
  }

  public Color getLineColor() {
    var selectedLineColor = controller.getSelectedLineColor();
    return (selectedLineColor != null) ? selectedLineColor : lineColor;
  }

}
