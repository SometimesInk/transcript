package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.List;

public class AlertHandler {
  private static final AlertHandler INSTANCE = new AlertHandler();

  private AlertHandler() {
  }

  public static AlertHandler getInstance() {
    return INSTANCE;
  }

  // TODO: Change from character lookup to a  word-based one
  public String highlightString(String fullString, String target, String format) {
    int index = fullString.indexOf(target);

    // Insert format right before the target substring
    return fullString.substring(0, index) + format + fullString.substring(index, index + target.length()) +
        ChatFormatting.RESET + fullString.substring(index + target.length());
  }

  private String highlight(String message, List<Alert> matches) {
    for (Alert match : matches)
      message = highlightString(message, match.target, Alert.getHighlightingChatFormatting(match.types));
    return message;
  }

  public String check(String message, List<Alert> matches) {
    // Check if matches contain highlighting
    if (Alert.hasHighlighting(matches)) return highlight(message, matches);

    return null;
  }
}
