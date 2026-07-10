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
  public void mouseDown(int x, int y, boolean isControlDown, boolean isShiftDown) {
    previousX = x;
    previousY = y;
    var controller = stateManager.getController();
    var d = controller.findDrawingAt(x, y);
    if (d != null) {
      if (!isControlDown && !isShiftDown) {
        controller.clearSelecting();
        controller.addSelectedDrawing(d);
      } else if (isShiftDown) {
        if (controller.getSelectedDrawings().contains(d)) {
          controller.removeSelectedDrawing(d);
        } else {
          controller.addSelectedDrawing(d);
        }
      } else if (isControlDown) {
        controller.addSelectedDrawing(d);
      }
    }

  }

  @Override
  public void mouseUp(int x, int y) {
  }

  @Override
  public void mouseDrag(int x, int y) {
    int dx = x - previousX;
    int dy = y - previousY;
    stateManager.getController().moveSelectedDrawings(dx, dy);
    previousX = x;
    previousY = y;
  }

}
