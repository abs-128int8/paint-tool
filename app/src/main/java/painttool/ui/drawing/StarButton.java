package painttool.ui.drawing;

import javax.swing.JButton;
import painttool.PaintStateManager;
import painttool.state.StarState;

public class StarButton extends JButton {
  PaintStateManager stateManager;

  public StarButton(PaintStateManager stateManager) {
    super("Star");
    this.stateManager = stateManager;
    addActionListener(e -> {
      stateManager.setState(new StarState(stateManager));
    });
  }
}