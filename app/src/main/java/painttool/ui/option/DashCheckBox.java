package painttool.ui.option;

import javax.swing.JCheckBox;

import painttool.PaintStateManager;

public class DashCheckBox extends JCheckBox {
  PaintStateManager stateManager;

  public DashCheckBox(PaintStateManager stateManager) {
    super("dash line");
    this.stateManager = stateManager;
    addActionListener(e -> {
      if (isSelected()) {
        stateManager.setDashedLine(true);
      } else {
        stateManager.setDashedLine(false);
      }
    });
  }
}
