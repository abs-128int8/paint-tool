package painttool.ui.drawing;

import javax.swing.JButton;
import painttool.PaintStateManager;
import painttool.state.HendecagonalState;

public class HendecagonalButton extends JButton {
  PaintStateManager stateManager;

  public HendecagonalButton(PaintStateManager stateManager) {
    super("Hendecagonal");
    this.stateManager = stateManager;
    addActionListener(e -> {
      stateManager.setState(new HendecagonalState(stateManager));
    });
  }
}