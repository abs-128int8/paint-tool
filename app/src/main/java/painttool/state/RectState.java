package painttool.state;

import painttool.PaintStateManager;
import painttool.drawing.Rectangle;

public class RectState implements State {
  PaintStateManager stateManager;
  Rectangle rect;

  public RectState(PaintStateManager stateManager) {
    this.stateManager = stateManager;
  }

  @Override
  public void mouseDown(int x, int y, boolean isControlDown, boolean isShiftDown) {
    rect = new Rectangle(x, y, 0, 0);
    stateManager.addDrawing(rect);
  }

  @Override
  public void mouseUp(int x, int y) {
  }

  @Override
  public void mouseDrag(int x, int y) {
    int rectX = rect.getX();
    int rectY = rect.getY();
    rect.setBounds(rectX, rectY, x - rectX, y - rectY);
  }

}
