package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

public class AlertHandler {
  private static final AlertHandler INSTANCE = new AlertHandler();

  private AlertHandler() {
  }

  public static AlertHandler getInstance() {
    return INSTANCE;
  }

  public String HighlightString(String fullString, String target, String format) {
    int targetLocation = fullString.indexOf(target);

    // Target is not in full string
    if (targetLocation == -1) return null;

    String prevSection = fullString.substring(0, targetLocation);
    String lastSection = fullString.substring(targetLocation + target.length());

    return prevSection + format + target + ChatFormatting.RESET + lastSection;
  }
}
