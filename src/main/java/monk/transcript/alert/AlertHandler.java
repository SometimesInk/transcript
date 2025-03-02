package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

public class AlertHandler {
  public String HighlightString(String fullString, String target, String format) {
    int targetLocation = fullString.indexOf(target);

    // Target is not in full string
    if (targetLocation == -1) return null;

    String prevSection = fullString.substring(0, targetLocation - 1);
    String lastSection = fullString.substring(targetLocation + target.length(), fullString.length() - 1);

    return prevSection + format + target + ChatFormatting.RESET + lastSection;
  }
}
