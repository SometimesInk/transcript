package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.List;

public class Alert {
  public String target;

  public class Element {
    public Callback type;
    public ChatFormatting highlighting;

    public Element(Callback type, ChatFormatting highlighting) throws Exception {
      this.type = type;
      if (type != Callback.HIGHLIGHT && highlighting != null) throw new Exception("highlight must be " +
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
}


