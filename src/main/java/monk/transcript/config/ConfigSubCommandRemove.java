package monk.transcript.config;

import com.mojang.realmsclient.gui.ChatFormatting;
import monk.transcript.command.SubCommand;
import monk.transcript.util.Messaging;

public class ConfigSubCommandRemove extends SubCommand {
  @Override
  public String getName() {
    return "remove";
  }

  @Override
  public boolean process(String[] args) {
    // Check args length
    if (args.length < 1) return false;

    // Find phrase
    StringBuilder target = new StringBuilder();
    for (int i = 1; i < args.length; i++) target.append(args[i]).append(i < args.length - 1 ? " " : "");

    // Add alert to config
    ConfigElement newConfig = ConfigHandler.getInstance().configGet();
    Messaging.sendMessage(newConfig.removePhrase(target.toString()) ?
        "Successfully added phrase '" + ChatFormatting.GREEN + target + ChatFormatting.RESET + "'." :
        "Could not add phrase, as it already exists.");
    ConfigHandler.getInstance().configSet(newConfig);
    return false;
  }
}
