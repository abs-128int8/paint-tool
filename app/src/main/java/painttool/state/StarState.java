package painttool.state;

import painttool.PaintStateManager;
import painttool.drawing.Star;

public class StarState implements State {
  PaintStateManager stateManager;
  Star star;

  public StarState(PaintStateManager stateManager) {
    this.stateManager = stateManager;
  }

  @Override
  public void mouseDown(int x, int y, boolean isControlDown, boolean isShiftDown) {
    star = new Star(x, y, 0, 0);
    stateManager.addDrawing(star);
  }

  @Override
  public void mouseUp(int x, int y) {
  }

  @Override
  public void mouseDrag(int x, int y) {
    int starX = star.getX();
    int starY = star.getY();
    star.setBounds(starX, starY, x - starX, y - starY);
  }
}
