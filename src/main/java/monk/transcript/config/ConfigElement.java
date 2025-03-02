package monk.transcript.config;

import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.HashMap;
import java.util.Map;

public class ConfigElement {
  /**
   * <p>
   * Keywords and phrases we're looking for.
   * </p>
   */
  public Map<String, ChatFormatting> targets = new HashMap<String, ChatFormatting>();

  public ConfigElement(Map<String, ChatFormatting> targets) {
    this.targets = targets;
  }

  public ConfigElement() {
  }
}
