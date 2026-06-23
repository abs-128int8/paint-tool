package painttool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import painttool.ui.drawing.HendecagonalButton;
import painttool.ui.drawing.OvalButton;
import painttool.ui.drawing.RectButton;
import painttool.ui.drawing.StarButton;
import painttool.ui.option.DashCheckBox;
import painttool.ui.option.DashPatternCombo;
import painttool.ui.option.DropShadowCheckBox;
import painttool.ui.option.LineCountSpinner;
import painttool.ui.option.LineWidthSpinner;
import painttool.ui.tool.SelectButton;

public class CanvasFrame extends JFrame {
  PaintStateManager stateManager;
  PaintCanvas canvas;

  public CanvasFrame(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    canvas = new PaintCanvas();
    canvas.setBackground(Color.WHITE);

    var jp = new JPanel(new FlowLayout());

    stateManager = new PaintStateManager(canvas);

    var rectButton = new RectButton(stateManager);
    jp.add(rectButton);
    var ovalButton = new OvalButton(stateManager);
    jp.add(ovalButton);
    var hendecagonalButton = new HendecagonalButton(stateManager);
    jp.add(hendecagonalButton);
    var starButton = new StarButton(stateManager);
    jp.add(starButton);
    var selectButton = new SelectButton(stateManager);
    jp.add(selectButton);
    var dropShadowCheck = new DropShadowCheckBox(stateManager);
    jp.add(dropShadowCheck);
    var dashCheck = new DashCheckBox(stateManager);
    jp.add(dashCheck);
    var dashPatternCombo = new DashPatternCombo(stateManager);
    jp.add(dashPatternCombo);
    jp.add(new JLabel("Line Width:"));
    var lineWidthSpinner = new LineWidthSpinner(stateManager);
    jp.add(lineWidthSpinner);
    jp.add(new JLabel("Line Count:"));
    var lineCountSpinner = new LineCountSpinner(stateManager);
    jp.add(lineCountSpinner);

    setLayout(new BorderLayout());
    add(jp, BorderLayout.NORTH);
    add(canvas, BorderLayout.CENTER);

    canvas.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        stateManager.mouseDown(e.getX(), e.getY());
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        stateManager.mouseUp(e.getX(), e.getY());
      }
    });

    canvas.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        stateManager.mouseDrag(e.getX(), e.getY());
      }
    });
  }
}