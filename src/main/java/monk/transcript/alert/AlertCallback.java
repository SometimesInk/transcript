package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public enum AlertCallback {
  HIGHLIGHT,
  PING,
  TITLE;

  public static List<String> getValues() {
    List<String> values = new ArrayList<String>();

    // Get values from enum
    for (AlertCallback callback : values()) values.add(callback.name());

    return values;
  }

  public static List<String> getExtendedHighlightValues() {
    List<String> values = new ArrayList<String>();

    // Get chat formatting values for highlight
    for (ChatFormatting format : ChatFormatting.values()) values.add("HIGHLIGHT_" + format.name());

    return values;
  }
}
