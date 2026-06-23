package painttool.ui.option;

import javax.swing.JCheckBox;

import painttool.PaintStateManager;

public class DropShadowCheckBox extends JCheckBox {
  PaintStateManager stateManager;

  public DropShadowCheckBox(PaintStateManager stateManager) {
    super("drop shadow");
    this.stateManager = stateManager;
    addActionListener(e -> {
      if (isSelected()) {
        stateManager.setDropShadow(true);
      } else {
        stateManager.setDropShadow(false);
      }
    });
  }
}