package monk.transcript.alert;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.List;

public class Alert {
  public String target;

  public enum Type {
    HIGHLIGHT_FORMAT_OBFUSCATED,
    HIGHLIGHT_FORMAT_STRIKETHROUGH,
    HIGHLIGHT_FORMAT_BOLD,
    HIGHLIGHT_FORMAT_ITALIC,
    HIGHLIGHT_FORMAT_UNDERLINE,

    HIGHLIGHT_COLOR_BLACK,
    HIGHLIGHT_COLOR_DARK_BLUE,
    HIGHLIGHT_COLOR_DARK_GREEN,
    HIGHLIGHT_COLOR_DARK_AQUA,
    HIGHLIGHT_COLOR_DARK_RED,
    HIGHLIGHT_COLOR_DARK_PURPLE,
    HIGHLIGHT_COLOR_GOLD,
    HIGHLIGHT_COLOR_GRAY,
    HIGHLIGHT_COLOR_DARK_GRAY,
    HIGHLIGHT_COLOR_BLUE,
    HIGHLIGHT_COLOR_GREEN,
    HIGHLIGHT_COLOR_AQUA,
    HIGHLIGHT_COLOR_RED,
    HIGHLIGHT_COLOR_LIGHT_PURPLE,
    HIGHLIGHT_COLOR_YELLOW,
    HIGHLIGHT_COLOR_WHITE,

    PING,
    TITLE
  }

  public List<Type> types;

  public Alert(String target, List<Type> types) {
    this.target = target;
    this.types = types;
  }

  public static boolean isHighlight(Type type) {
    return type.toString().startsWith("HIGHLIGHT_");
  }

  /**
   * <p>
   * Check whether the given list has any highlighting types.
   * </p>
   *
   * @param types List of check for highlighting.
   */
  public static boolean hasHighlighting(List<Alert> types) {
    for (Alert alert : types) for (Type type : alert.types) if (isHighlight(type)) return true;
    return false;
  }

  public static String getHighlightingChatFormatting(List<Type> types) {
    StringBuilder highlight = new StringBuilder();
    for (Type type : types) {
      if (!isHighlight(type)) continue;

      // Parse highlighting type
      switch (type) {
        case HIGHLIGHT_FORMAT_OBFUSCATED:
          highlight.append(ChatFormatting.OBFUSCATED);
          break;
        case HIGHLIGHT_FORMAT_STRIKETHROUGH:
          highlight.append(ChatFormatting.STRIKETHROUGH);
          break;
        case HIGHLIGHT_FORMAT_BOLD:
          highlight.append(ChatFormatting.BOLD);
          break;
        case HIGHLIGHT_FORMAT_ITALIC:
          highlight.append(ChatFormatting.ITALIC);
          break;
        case HIGHLIGHT_FORMAT_UNDERLINE:
          highlight.append(ChatFormatting.UNDERLINE);
          break;
        case HIGHLIGHT_COLOR_BLACK:
          highlight.append(ChatFormatting.BLACK);
          break;
        case HIGHLIGHT_COLOR_DARK_BLUE:
          highlight.append(ChatFormatting.DARK_BLUE);
          break;
        case HIGHLIGHT_COLOR_DARK_GREEN:
          highlight.append(ChatFormatting.DARK_GREEN);
          break;
        case HIGHLIGHT_COLOR_DARK_AQUA:
          highlight.append(ChatFormatting.DARK_AQUA);
          break;
        case HIGHLIGHT_COLOR_DARK_RED:
          highlight.append(ChatFormatting.DARK_RED);
          break;
        case HIGHLIGHT_COLOR_DARK_PURPLE:
          highlight.append(ChatFormatting.DARK_PURPLE);
          break;
        case HIGHLIGHT_COLOR_GOLD:
          highlight.append(ChatFormatting.GOLD);
          break;
        case HIGHLIGHT_COLOR_GRAY:
          highlight.append(ChatFormatting.GRAY);
          break;
        case HIGHLIGHT_COLOR_DARK_GRAY:
          highlight.append(ChatFormatting.DARK_GRAY);
          break;
        case HIGHLIGHT_COLOR_BLUE:
          highlight.append(ChatFormatting.BLUE);
          break;
        case HIGHLIGHT_COLOR_GREEN:
          highlight.append(ChatFormatting.GREEN);
          break;
        case HIGHLIGHT_COLOR_AQUA:
          highlight.append(ChatFormatting.AQUA);
          break;
        case HIGHLIGHT_COLOR_RED:
          highlight.append(ChatFormatting.RED);
          break;
        case HIGHLIGHT_COLOR_LIGHT_PURPLE:
          highlight.append(ChatFormatting.LIGHT_PURPLE);
          break;
        case HIGHLIGHT_COLOR_YELLOW:
          highlight.append(ChatFormatting.WHITE);
          break;
        case HIGHLIGHT_COLOR_WHITE:
          highlight.append(ChatFormatting.WHITE);
          break;
      }
    }

    return highlight.toString().isEmpty() ? null : highlight.toString();
  }
}


