package painttool;

import java.awt.BasicStroke;

public class DashStroke extends BasicStroke {
  public DashStroke(float lineWidth, DashPattern pattern) {
    super(lineWidth, CAP_BUTT, JOIN_BEVEL, 1.0f, pattern.getPattern(), 0.0f);
  }
}