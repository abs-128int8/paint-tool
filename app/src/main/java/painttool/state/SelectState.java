package painttool.state;

import painttool.PaintStateManager;

public class SelectState implements State {
  private int previousX;
  private int previousY;

  PaintStateManager stateManager;

  public SelectState(PaintStateManager stateManager) {
    this.stateManager = stateManager;
  }

  @Override
  public void mouseDown(int x, int y) {
    previousX = x;
    previousY = y;
    stateManager.getController().selectDrawing(x, y);
  }

  @Override
  public void mouseUp(int x, int y) {
  }

  @Override
  public void mouseDrag(int x, int y) {
    int dx = x - previousX;
    int dy = y - previousY;
    stateManager.getController().moveSelectedDrawing(dx, dy);
    previousX = x;
    previousY = y;
  }

}
