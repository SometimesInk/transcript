package monk.transcript.highlight;

import com.mojang.realmsclient.gui.ChatFormatting;
import monk.transcript.alert.Alert;
import monk.transcript.alert.AlertCallback;
import monk.transcript.alert.AlertElement;

import java.util.List;

public class Highlighting {
  /**
   * <p>
   * This method adds the given chat formatting right before the target word is
   * found.
   * </p>
   * <pre>
   * words=is ink here?
   * target=ink
   * format=&e
   *
   * output=is &eink&r here?
   * </pre>
   * <p>
   * As shown in the example above, the reset formatting is automatically added.
   * </p>
   */
  @SuppressWarnings("SpellCheckingInspection")
  private static String highlightStringWord(String[] words, String target, String format) {
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

  public static String highlight(String message, List<Alert> matches) {
    if (message == null) throw new NullPointerException();

    // For each given match, highlight its target
    for (Alert match : matches)
      message = highlightStringWord(message.split(" "), match.target, getHighlightingSequence(match.types));
    return message;
  }

  private static String getHighlightingSequence(List<AlertElement> types) {
    StringBuilder seq = new StringBuilder();

    // Add chat formatting of all highlight types to sequence
    for (AlertElement e : types) if (e.type == AlertCallback.HIGHLIGHT) seq.append(e);

    return seq.toString();
  }
}
