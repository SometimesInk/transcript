package monk.transcript.alert;

import java.util.List;

public class Alert {
  public String target;

  public List<AlertElement> types;

  public Alert(String target, List<AlertElement> types) {
    this.target = target;
    this.types = types;
  }

  public static String getHighlightingSequence(List<AlertElement> types) {
    StringBuilder seq = new StringBuilder();

    // Add chat formatting of all highlight types to sequence
    for (AlertElement e : types) if (e.type == AlertCallback.HIGHLIGHT) seq.append(e);

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


