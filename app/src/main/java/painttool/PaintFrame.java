package painttool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class PaintFrame extends JFrame {
  PaintStateManager stateManager;
  PaintCanvas canvas;

  public PaintFrame(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    canvas = new PaintCanvas();
    stateManager = new PaintStateManager(canvas);

    canvas.setBackground(Color.WHITE);

    var jp = new JPanel(new FlowLayout());
    registerComponents(jp);
    setLayout(new BorderLayout());
    add(jp, BorderLayout.NORTH);
    add(canvas, BorderLayout.CENTER);

    registerListeners(canvas);
  }

  private void registerComponents(JPanel jp) {
    var menuBar = new JMenuBar();
    var colorMenu = new JMenu("Color");
    var colorMenuFill = new JMenuItem("Fill Color");
    var colorMenuLine = new JMenuItem("Line Color");

    setJMenuBar(menuBar);
    colorMenu.add(colorMenuFill);
    colorMenu.add(colorMenuLine);
    colorMenuFill.addActionListener(e -> {
      Color color = JColorChooser.showDialog(this, "Fill Color", stateManager.getFillColor());
      if (color != null) {
        stateManager.setFillColor(color);
      }
    });
    colorMenuLine.addActionListener(e -> {
      Color color = JColorChooser.showDialog(this, "Line Color", stateManager.getLineColor());
      if (color != null) {
        stateManager.setLineColor(color);
      }
    });
    menuBar.add(colorMenu);

    var rectButton = new RectButton(stateManager);
    var ovalButton = new OvalButton(stateManager);
    var hendecagonalButton = new HendecagonalButton(stateManager);
    var starButton = new StarButton(stateManager);
    var selectButton = new SelectButton(stateManager);
    var dropShadowCheck = new DropShadowCheckBox(stateManager);
    var dashCheck = new DashCheckBox(stateManager);
    var dashPatternCombo = new DashPatternCombo(stateManager);
    var lineWidthSpinner = new LineWidthSpinner(stateManager);
    var lineCountSpinner = new LineCountSpinner(stateManager);

    jp.add(rectButton);
    jp.add(ovalButton);
    jp.add(hendecagonalButton);
    jp.add(starButton);
    jp.add(selectButton);
    jp.add(dropShadowCheck);
    jp.add(dashCheck);
    jp.add(dashPatternCombo);
    jp.add(new JLabel("Line Width:"));
    jp.add(lineWidthSpinner);
    jp.add(new JLabel("Line Count:"));
    jp.add(lineCountSpinner);
  }

  private void registerListeners(PaintCanvas canvas) {
    canvas.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        canvas.requestFocusInWindow();
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

    canvas.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
          stateManager.getController().deleteSelectedDrawing();
        }
      }
    });
  }
}