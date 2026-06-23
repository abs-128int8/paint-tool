package painttool.ui.option;

import javax.swing.JComboBox;

import painttool.PaintStateManager;
import painttool.DashPattern;

public class DashPatternCombo extends JComboBox<DashPattern> {
  public DashPatternCombo(PaintStateManager stateManager) {
    super(DashPattern.values());
    addActionListener(e -> {
      DashPattern selected = (DashPattern) getSelectedItem();
      if (selected != null) {
        stateManager.setDashPattern(selected);
      }
    });
  }
}
