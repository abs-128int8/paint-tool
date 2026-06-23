package painttool.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Objects;
import painttool.DashStroke;

public class Star extends Drawing {
  public Star() {
    super();
  }

  public Star(int x, int y) {
    super(x, y);
  }

  public Star(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  public Star(Color lineColor, Color fillColor) {
    super(lineColor, fillColor);
  }

  public Star(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
    super(x, y, w, h, lineColor, fillColor, lineWidth);
  }

  private Polygon calcStarPolygon(int x, int y, int w, int h, double angleOffset) {
    var polygon = new Polygon();
    for (int i = 0; i < 10; i++) {
      int px;
      int py;
      if (i % 2 == 0) {
        px = (int) (x + w / 2 * (1 - Math.sin(Math.PI * i / 5 + angleOffset)));
        py = (int) (y + h / 2 * (1 - Math.cos(Math.PI * i / 5 + angleOffset)));
      } else {
        var r = Math.cos(2 * Math.PI / 5) * Math.sqrt(2 / (1 + Math.cos(2 * Math.PI / 5)));
        px = (int) (x + w / 2 * (1 - r * Math.sin(Math.PI * i / 5 + angleOffset)));
        py = (int) (y + h / 2 * (1 - r * Math.cos(Math.PI * i / 5 + angleOffset)));
      }
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

    var polygon = calcStarPolygon(x, y, w, h, angleOffset);

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
      int gap = 6;
      var offsetPolygon = calcStarPolygon(x - gap * i * getLineWidth(), y - gap * i * getLineWidth(),
          w + 2 * gap * i * getLineWidth(), h + 2 * gap * i * getLineWidth(), angleOffset);
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

    var polygon = calcStarPolygon(x, y, w, h, angleOffset);
    setRegion(polygon.getBounds());
  }

  @Override
  public Drawing clone() {
    var clone = new Star(getX(), getY(), getW(), getH(), getLineColor(), getFillColor(),
        getLineWidth());
    clone.setDashedLine(isDashedLine());
    clone.setDashPattern(getDashPattern());
    clone.setLineCount(getLineCount());
    clone.setDropShadow(isDropShadow());
    clone.setDropShadowOffset(getDropShadowOffset());
    return clone;
  }
}
