package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.List;

public class Alert {
  public String target;

  public static class Element {
    public Callback type;
    public ChatFormatting highlighting;

    public Element(Callback type, ChatFormatting highlighting) throws NullPointerException {
      this.type = type;
      if (type != Callback.HIGHLIGHT && highlighting != null) throw new NullPointerException("highlight must be " +
          "null for non-highlighting types.");
      this.highlighting = highlighting;
    }

    public Element(Callback type) throws Exception {
      if (type == Callback.HIGHLIGHT) throw new Exception("Must specify highlighting.");
      this.type = type;
    }

    @Override
    public String toString() {
      return highlighting != null ? highlighting.toString() : "null";
    }
  }

  public enum Callback {
    HIGHLIGHT,
    PING,
    TITLE
  }

  public List<Element> types;

  public Alert(String target, List<Element> types) {
    this.target = target;
    this.types = types;
  }

  public static String getHighlightingSequence(List<Element> types) {
    StringBuilder seq = new StringBuilder();

    // Add chat formatting of all highlight types to sequence
    for (Element e : types) if (e.type == Callback.HIGHLIGHT) seq.append(e);

    return seq.toString();
  }

  @Override
  public String toString() {
    StringBuilder formattedTypes = new StringBuilder();
    for (int i = 0; i < types.size(); i++)
      formattedTypes.append(types.get(i).type.name()).append(i < types.size() - 1 ? " " : "");

    return target + " - " + formattedTypes;
  }
}


