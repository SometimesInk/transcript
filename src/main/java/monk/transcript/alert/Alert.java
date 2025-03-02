package monk.transcript.alert;

import java.util.ArrayList;
import java.util.List;

public class Alert {
  public String target;

  public enum Type {
    HIGHLIGHT_OBFUSCATED,
    HIGHLIGHT_STRIKETHROUGH,
    HIGHLIGHT_BOLD,
    HIGHLIGHT_ITALIC,
    HIGHLIGHT_UNDERLINE,
    HIGHLIGHT_COLOR_RED,
    PING,
    TITLE;
  }

  public List<Type> types = new ArrayList<Type>();

  public Alert(String target, List<Type> types) {
    this.target = target;
    this.types = types;
  }
}


