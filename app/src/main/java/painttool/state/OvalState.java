package painttool.state;

import painttool.PaintStateManager;
import painttool.drawing.Oval;

public class OvalState implements State {
  PaintStateManager stateManager;
  Oval oval;

  public OvalState(PaintStateManager stateManager) {
    this.stateManager = stateManager;
  }

  @Override
  public void mouseDown(int x, int y) {
    oval = new Oval(x, y, 0, 0);
    stateManager.addDrawing(oval);
  }

  @Override
  public void mouseUp(int x, int y) {
  }

  @Override
  public void mouseDrag(int x, int y) {
    int ovalX = oval.getX();
    int ovalY = oval.getY();
    oval.setBounds(ovalX, ovalY, x - ovalX, y - ovalY);
  }

}
