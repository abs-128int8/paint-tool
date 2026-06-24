package painttool.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Objects;

import painttool.DashStroke;

public class Hendecagonal extends Drawing {
  public Hendecagonal() {
    super();
  }

  public Hendecagonal(int x, int y) {
    super(x, y);
  }

  public Hendecagonal(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  public Hendecagonal(Color lineColor, Color fillColor) {
    super(lineColor, fillColor);
  }

  public Hendecagonal(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
    super(x, y, w, h, lineColor, fillColor, lineWidth);
  }

  private Polygon calcHendecagonalPolygon(int x, int y, int w, int h, double angleOffset) {
    var polygon = new Polygon();
    for (int i = 0; i < 11; i++) {
      int px = (int) (x + w / 2 * (1 - Math.sin(2 * Math.PI * i / 11 + angleOffset)));
      int py = (int) (y + h / 2 * (1 - Math.cos(2 * Math.PI * i / 11 + angleOffset)));
      polygon.addPoint(px, py);
    }
    return polygon;
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    Objects.requireNonNull(g);

    int x = getX();
    int y = getY();
    int w = getW();
    int h = getH();

    double angleOffset = 0;

    if (w < 0) {
      x += w;
      w = -w;
    }
    if (h < 0) {
      y += h;
      h = -h;
      angleOffset = Math.PI;
    }

    var polygon = calcHendecagonalPolygon(x, y, w, h, angleOffset);

    var g2 = (Graphics2D) g;

    if (isDropShadow()) {
      int offset = getDropShadowOffset();
      var shadowPolygon = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
      shadowPolygon.translate(offset, offset);
      g2.setColor(Color.BLACK);
      g2.fillPolygon(shadowPolygon);
    }

    g2.setColor(getFillColor());
    g2.fillPolygon(polygon);

    if (isDashedLine()) {
      g2.setStroke(new DashStroke(getLineWidth(), getDashPattern()));
    } else {
      g2.setStroke(new BasicStroke(getLineWidth()));
    }
    g2.setColor(getLineColor());
    g2.drawPolygon(polygon);
    for (int i = 1; i < getLineCount(); i++) {
      var offsetPolygon = calcHendecagonalPolygon(x - 2 * i * getLineWidth(), y - 2 * i * getLineWidth(),
          w + 4 * i * getLineWidth(), h + 4 * i * getLineWidth(), angleOffset);
      g2.drawPolygon(offsetPolygon);
    }
  }

  @Override
  public void updateRegion() {
    int x = getX();
    int y = getY();
    int w = getW();
    int h = getH();

    double angleOffset = 0;

    if (w < 0) {
      x += w;
      w = -w;
    }
    if (h < 0) {
      y += h;
      h = -h;
      angleOffset = Math.PI;
    }

    var polygon = calcHendecagonalPolygon(x, y, w, h, angleOffset);
    setRegion(polygon);
  }

  @Override
  public Drawing clone() {
    var clone = new Hendecagonal(getX(), getY(), getW(), getH(), getLineColor(), getFillColor(),
        getLineWidth());
    clone.setDashedLine(isDashedLine());
    clone.setDashPattern(getDashPattern());
    clone.setLineCount(getLineCount());
    clone.setDropShadow(isDropShadow());
    clone.setDropShadowOffset(getDropShadowOffset());
    return clone;
  }
}
