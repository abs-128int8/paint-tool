package painttool.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.Objects;

import painttool.DashPattern;

public abstract class Drawing {
  private int x;
  private int y;
  private int w;
  private int h;
  private Color lineColor;
  private Color fillColor;
  private int lineWidth;
  private boolean dashedLine;
  private DashPattern dashPattern;
  private int lineCount;
  private int dropShadowOffset;
  private boolean dropShadow;

  private boolean selected;
  private Shape region;

  private static final int SELECTED_BOX_SIZE = 6;

  public static final int DEFAULT_X = 0;
  public static final int DEFAULT_Y = 0;
  public static final int DEFAULT_W = 100;
  public static final int DEFAULT_H = 100;
  public static final Color DEFAULT_LINE_COLOR = Color.black;
  public static final Color DEFAULT_FILL_COLOR = Color.white;
  public static final int DEFAULT_LINE_WIDTH = 2;
  public static final boolean DEFAULT_DASHED_LINE = false;
  public static final DashPattern DEFAULT_DASH_PATTERN = DashPattern.DASHED;
  public static final int DEFAULT_LINE_COUNT = 1;
  public static final int DEFAULT_DROP_SHADOW_OFFSET = 5;
  public static final boolean DEFAULT_DROP_SHADOW = false;

  public Drawing() {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_W, DEFAULT_H, DEFAULT_LINE_COLOR, DEFAULT_FILL_COLOR, DEFAULT_LINE_WIDTH,
        DEFAULT_DASHED_LINE, DEFAULT_DASH_PATTERN, DEFAULT_LINE_COUNT, DEFAULT_DROP_SHADOW_OFFSET, DEFAULT_DROP_SHADOW);
  }

  public Drawing(int x, int y) {
    this(x, y, DEFAULT_W, DEFAULT_H, DEFAULT_LINE_COLOR, DEFAULT_FILL_COLOR, DEFAULT_LINE_WIDTH, DEFAULT_DASHED_LINE,
        DEFAULT_DASH_PATTERN, DEFAULT_LINE_COUNT, DEFAULT_DROP_SHADOW_OFFSET, DEFAULT_DROP_SHADOW);
  }

  public Drawing(int x, int y, int w, int h) {
    this(x, y, w, h, DEFAULT_LINE_COLOR, DEFAULT_FILL_COLOR, DEFAULT_LINE_WIDTH, DEFAULT_DASHED_LINE,
        DEFAULT_DASH_PATTERN, DEFAULT_LINE_COUNT, DEFAULT_DROP_SHADOW_OFFSET, DEFAULT_DROP_SHADOW);
  }

  public Drawing(Color lineColor, Color fillColor) {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_W, DEFAULT_H, lineColor, fillColor, DEFAULT_LINE_WIDTH, DEFAULT_DASHED_LINE,
        DEFAULT_DASH_PATTERN, DEFAULT_LINE_COUNT, DEFAULT_DROP_SHADOW_OFFSET, DEFAULT_DROP_SHADOW);
  }

  public Drawing(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
    this(x, y, w, h, lineColor, fillColor, lineWidth, DEFAULT_DASHED_LINE, DEFAULT_DASH_PATTERN, DEFAULT_LINE_COUNT,
        DEFAULT_DROP_SHADOW_OFFSET, DEFAULT_DROP_SHADOW);
  }

  public Drawing(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth, boolean dashedLine,
      DashPattern dashPattern, int lineCount, int dropShadowOffset, boolean dropShadow) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.lineColor = Objects.requireNonNull(lineColor);
    this.fillColor = Objects.requireNonNull(fillColor);
    this.lineWidth = lineWidth;
    this.dashedLine = dashedLine;
    this.dashPattern = dashPattern;
    this.lineCount = lineCount;
    this.dropShadowOffset = dropShadowOffset;
    this.dropShadow = dropShadow;
  }

  public void draw(Graphics g) {
    if (selected) {
      Objects.requireNonNull(g);

      g.setColor(Color.black);
      g.fillRect(x - SELECTED_BOX_SIZE / 2, y - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x + w / 2 - SELECTED_BOX_SIZE / 2, y - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x + w - SELECTED_BOX_SIZE / 2, y - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x - SELECTED_BOX_SIZE / 2, y + h / 2 - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x + w - SELECTED_BOX_SIZE / 2, y + h / 2 - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x - SELECTED_BOX_SIZE / 2, y + h - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x + w / 2 - SELECTED_BOX_SIZE / 2, y + h - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
      g.fillRect(x + w - SELECTED_BOX_SIZE / 2, y + h - SELECTED_BOX_SIZE / 2, SELECTED_BOX_SIZE,
          SELECTED_BOX_SIZE);
    }
  }

  public void move(int dx, int dy) {
    x += dx;
    y += dy;
    updateRegion();
  }

  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
    updateRegion();
  }

  public void setSize(int w, int h) {
    this.w = w;
    this.h = h;
    updateRegion();
  }

  public void setBounds(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    updateRegion();
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getW() {
    return w;
  }

  public int getH() {
    return h;
  }

  public void setLineColor(Color lineColor) {
    this.lineColor = Objects.requireNonNull(lineColor);
  }

  public Color getLineColor() {
    return lineColor;
  }

  public void setFillColor(Color fillColor) {
    this.fillColor = Objects.requireNonNull(fillColor);
  }

  public Color getFillColor() {
    return fillColor;
  }

  public void setLineWidth(int lineWidth) {
    this.lineWidth = lineWidth;
  }

  public int getLineWidth() {
    return lineWidth;
  }

  public void setDashedLine(boolean dashedLine) {
    this.dashedLine = dashedLine;
  }

  public boolean isDashedLine() {
    return dashedLine;
  }

  public void setDashPattern(DashPattern dashPattern) {
    this.dashPattern = dashPattern;
  }

  public DashPattern getDashPattern() {
    return dashPattern;
  }

  public void setLineCount(int lineCount) {
    this.lineCount = lineCount;
  }

  public int getLineCount() {
    return lineCount;
  }

  public void setDropShadowOffset(int dropShadowOffset) {
    this.dropShadowOffset = dropShadowOffset;
  }

  public int getDropShadowOffset() {
    return dropShadowOffset;
  }

  public void setDropShadow(boolean dropShadow) {
    this.dropShadow = dropShadow;
  }

  public boolean isDropShadow() {
    return dropShadow;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public boolean isSelected() {
    return selected;
  }

  public boolean contains(int px, int py) {
    return region != null && region.contains(px, py);
  }

  public void setRegion(Shape region) {
    this.region = region;
  }

  public abstract void updateRegion();
}
