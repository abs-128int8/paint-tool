package painttool.state;

import painttool.PaintStateManager;
import painttool.drawing.DrawingController;

public class SelectState implements State {
  private int previousX;
  private int previousY;
  private int startX;
  private int startY;

  // PaintStateManager stateManager;
  DrawingController controller;

  public SelectState(PaintStateManager stateManager) {
    // this.stateManager = stateManager;
    this.controller = stateManager.getController();
  }

  @Override
  public void mouseDown(int x, int y, boolean isControlDown, boolean isShiftDown) {
    previousX = x;
    previousY = y;
    var d = controller.findDrawingAt(x, y);
    if (d != null) {
      if (!isControlDown && !isShiftDown && controller.getSelectedDrawings().size() <= 1) {
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
    } else {
      controller.clearSelecting();
      controller.setRangeSelecting(true);
      controller.setSelectionBounds(x, y, 0, 0);
      startX = x;
      startY = y;
    }

  }

  @Override
  public void mouseUp(int x, int y) {
    if (controller.isRangeSelecting()) {
      controller.selectDrawings();
      controller.setRangeSelecting(false);
      controller.setSelectionBounds(0, 0, 0, 0);
    }
  }

  @Override
  public void mouseDrag(int x, int y) {
    int dx = x - previousX;
    int dy = y - previousY;

    if (controller.isRangeSelecting()) {
      controller.setSelectionBounds(startX, startY, x - startX, y - startY);
    } else {
      controller.moveSelectedDrawings(dx, dy);
    }

    previousX = x;
    previousY = y;
  }

}
