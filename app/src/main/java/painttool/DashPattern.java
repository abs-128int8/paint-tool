package painttool;

public enum DashPattern {
  DASHED("Dashed", new float[] { 10f, 5f }),
  DASH_DOT("Dash-Dot", new float[] { 12f, 4f, 2f, 4f }),
  DASH_DOT_DOT("Dash-Dot-Dot", new float[] { 12f, 4f, 2f, 4f, 2f, 4f }),
  DOTS("Dots", new float[] { 2f, 4f });

  private final String displayName;
  private final float[] pattern;

  DashPattern(String displayName, float[] pattern) {
    this.displayName = displayName;
    this.pattern = pattern;
  }

  public float[] getPattern() {
    return pattern;
  }

  public String getDisplayName() {
    return displayName;
  }
}