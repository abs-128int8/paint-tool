package painttool.ui.drawing;

import javax.swing.JButton;
import painttool.PaintStateManager;
import painttool.state.OvalState;

public class OvalButton extends JButton {
  PaintStateManager stateManager;

  public OvalButton(PaintStateManager stateManager) {
    super("Oval");
    this.stateManager = stateManager;
    addActionListener(e -> {
      stateManager.setState(new OvalState(stateManager));
    });
  }
}