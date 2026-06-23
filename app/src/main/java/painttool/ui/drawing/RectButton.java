package painttool.ui.drawing;

import javax.swing.JButton;
import painttool.PaintStateManager;
import painttool.state.RectState;

public class RectButton extends JButton {
  PaintStateManager stateManager;

  public RectButton(PaintStateManager stateManager) {
    super("Rectangle");
    this.stateManager = stateManager;
    addActionListener(e -> {
      stateManager.setState(new RectState(stateManager));
    });
  }
}
