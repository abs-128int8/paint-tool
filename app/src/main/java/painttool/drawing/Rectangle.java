package painttool.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Objects;

import painttool.DashStroke;

public class Rectangle extends Drawing {
  public Rectangle() {
    super();
  }

  public Rectangle(int x, int y) {
    super(x, y);
  }

  public Rectangle(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  public Rectangle(Color lineColor, Color fillColor) {
    super(lineColor, fillColor);
  }

  public Rectangle(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
    super(x, y, w, h, lineColor, fillColor, lineWidth);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    Objects.requireNonNull(g);

    int x = getX();
    int y = getY();
    int w = getW();
    int h = getH();

    if (w < 0) {
      x += w;
      w = -w;
    }
    if (h < 0) {
      y += h;
      h = -h;
    }

    var g2 = (Graphics2D) g;

    if (isDropShadow()) {
      int offset = getDropShadowOffset();
      g2.setColor(Color.BLACK);
      g2.fillRect(x + offset, y + offset, w, h);
    }

    g2.setColor(getFillColor());
    g2.fillRect(x, y, w, h);

    if (isDashedLine()) {
      g2.setStroke(new DashStroke(getLineWidth(), getDashPattern()));
    } else {
      g2.setStroke(new BasicStroke(getLineWidth()));
    }
    g2.setColor(getLineColor());
    for (int i = 0; i < getLineCount(); i++) {
      g2.drawRect(x - 2 * i * getLineWidth(), y - 2 * i * getLineWidth(), w + 4 * i * getLineWidth(),
          h + 4 * i * getLineWidth());
    }
  }

  @Override
  public void updateRegion() {
    setRegion(new java.awt.Rectangle(getX(), getY(), getW(), getH()));
  }

  @Override
  public Drawing clone() {
    var clone = new Rectangle(getX(), getY(), getW(), getH(), getLineColor(), getFillColor(), getLineWidth());
    clone.setDashedLine(isDashedLine());
    clone.setDashPattern(getDashPattern());
    clone.setLineCount(getLineCount());
    clone.setDropShadowOffset(getDropShadowOffset());
    clone.setDropShadow(isDropShadow());

    return clone;
  }
}