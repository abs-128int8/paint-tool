package painttool.ui.option;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import painttool.PaintStateManager;
import painttool.drawing.Drawing;

public class LineWidthSpinner extends JSpinner {
  public LineWidthSpinner(PaintStateManager stateManager) {
    super(new SpinnerNumberModel(Drawing.DEFAULT_LINE_WIDTH, 0, 20, 1));
    addChangeListener(e -> {
      stateManager.setLineWidth((int) getValue());
    });
  }
}
