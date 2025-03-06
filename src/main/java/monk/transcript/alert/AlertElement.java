package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class AlertElement {
  public AlertCallback type;
  public ChatFormatting highlighting;

  public AlertElement(AlertCallback type, ChatFormatting highlighting) throws NullPointerException {
    this.type = type;
    if (type != AlertCallback.HIGHLIGHT && highlighting != null) throw new NullPointerException("highlight must be " +
        "null for non-highlighting types.");
    this.highlighting = highlighting;
  }

  public AlertElement(AlertCallback type) throws Exception {
    if (type == AlertCallback.HIGHLIGHT) throw new Exception("Must specify highlighting.");
    this.type = type;
  }

  public static List<AlertElement> parse(String formattedSequence) {
    List<AlertElement> types = new ArrayList<AlertElement>();

    for (String type : formattedSequence.split(",")) {
      // Get type and its second parameter if it exists
      String[] values = type.split("_");
      types.add(new AlertElement(monk.transcript.alert.AlertCallback.valueOf(values[0]),
          values.length > 1 ? ChatFormatting.valueOf(values[1]) : null));
    }

    return types;
  }

  @Override
  public String toString() {
    return highlighting != null ? highlighting.toString() : "null";
  }
}
