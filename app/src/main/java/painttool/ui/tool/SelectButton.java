package painttool.ui.tool;

import javax.swing.JButton;

import painttool.PaintStateManager;
import painttool.state.SelectState;

public class SelectButton extends JButton {
  PaintStateManager stateManager;

  public SelectButton(PaintStateManager stateManager) {
    super("Select");
    this.stateManager = stateManager;
    addActionListener(e -> {
      stateManager.setState(new SelectState(stateManager));
    });
  }
}
