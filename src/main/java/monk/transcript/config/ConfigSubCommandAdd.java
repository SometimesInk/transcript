package monk.transcript.config;

import com.mojang.realmsclient.gui.ChatFormatting;
import monk.transcript.alert.Alert;
import monk.transcript.alert.AlertCallback;
import monk.transcript.alert.AlertElement;
import monk.transcript.command.SubCommand;
import monk.transcript.util.Messaging;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class ConfigSubCommandAdd extends SubCommand {
  @Override
  public String getName() {
    return "add";
  }

  @Override
  public boolean process(String[] args) {
    if (args.length < 2) return false;

    // Get types
    List<AlertElement> types = AlertElement.parse(args[1]);

    // Find phrase
    StringBuilder target = new StringBuilder();
    for (int i = 2; i < args.length; i++) target.append(args[i]).append(i < args.length - 1 ? " " : "");

    Alert alert = new Alert(target.toString(), types);

    // Add alert to config
    ConfigElement newConfig = ConfigHandler.getInstance().configGet();
    Messaging.sendMessage(newConfig.addOverride(alert) ?
        "Successfully added phrase '" + ChatFormatting.GREEN + target + ChatFormatting.RESET + "'." :
        "Could not add phrase, as it already exists.");
    ConfigHandler.getInstance().configSet(newConfig);
    return true;
  }

  @Override
  public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos pos) {
    if (args.length < 1 || args.length > 2) return null;

    return args[args.length - 1].startsWith("HIGHLIGHT") ? AlertCallback.getExtendedHighlightValues() :
        AlertCallback.getValues();
  }
}
