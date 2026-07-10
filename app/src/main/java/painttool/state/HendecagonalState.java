package painttool.state;

import painttool.PaintStateManager;
import painttool.drawing.Hendecagonal;

public class HendecagonalState implements State {
  PaintStateManager stateManager;
  Hendecagonal hendecagonal;

  public HendecagonalState(PaintStateManager stateManager) {
    this.stateManager = stateManager;
  }

  @Override
  public void mouseDown(int x, int y, boolean isControlDown, boolean isShiftDown) {
    hendecagonal = new Hendecagonal(x, y, 0, 0);
    stateManager.addDrawing(hendecagonal);
  }

  @Override
  public void mouseUp(int x, int y) {
  }

  @Override
  public void mouseDrag(int x, int y) {
    int hendecaX = hendecagonal.getX();
    int hendecaY = hendecagonal.getY();
    hendecagonal.setBounds(hendecaX, hendecaY, x - hendecaX, y - hendecaY);
  }

}
