package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class AlertHandler {
  private static final AlertHandler INSTANCE = new AlertHandler();

  private AlertHandler() {
  }

  public static AlertHandler getInstance() {
    return INSTANCE;
  }

  // TODO: Change from character lookup to a  word-based one

  /**
   * <p>
   * This method adds the format before the target (which must be a singular word).
   * </p>
   */
  public String highlightStringWord(String[] words, String target, String format) {
    int targetI = -1;
    // Find where the target lies in the sentence
    for (int i = 0; i < words.length; i++)
      if (words[i].equals(target)) {
        targetI = i;
        break;
      }

    if (targetI == -1) return null;

    // Concatenate words to a single sentence separated with spaces
    StringBuilder sentence = new StringBuilder();
    for (int i = 0; i < words.length; i++) sentence.append(words[i]).append(i < words.length - 1 ? " " : "");

    return sentence.substring(0, targetI) + format + target + ChatFormatting.RESET +
        sentence.substring(targetI + target.length() + ChatFormatting.RESET.toString().length());
  }

  private String highlight(String message, List<Alert> matches) {
    for (Alert match : matches)
      message = highlightStringWord(message.split(" "), match.target,
          Alert.getHighlightingSequence(match.types));
    return message;
  }

  public String check(String message, List<Alert> matches) {
    // Check if matches contain highlighting
    List<Alert> highlightingMatches = new ArrayList<Alert>();

    for (Alert match : matches)
      for (Alert.Element type : match.types)
        if (type.type == Alert.Callback.HIGHLIGHT)
          highlightingMatches.add(match);

    if (!highlightingMatches.isEmpty()) return highlight(message, highlightingMatches);

    return null;
  }
}
