package painttool.ui.option;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import painttool.PaintStateManager;
import painttool.drawing.Drawing;

public class LineCountSpinner extends JSpinner {
  public LineCountSpinner(PaintStateManager stateManager) {
    super(new SpinnerNumberModel(Drawing.DEFAULT_LINE_COUNT, 1, 20, 1));
    addChangeListener(e -> {
      stateManager.setLineCount((int) getValue());
    });
  }

}
