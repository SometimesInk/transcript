package monk.transcript.config;

import com.mojang.realmsclient.gui.ChatFormatting;
import monk.transcript.alert.Alert;
import monk.transcript.util.Messaging;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommand extends CommandBase {
  @Override
  public String getCommandName() {
    return "transcript";
  }

  @Override
  public String getCommandUsage(ICommandSender sender) {
    return "/transcript <add> [HIGHLIGHT, PING, TITLE] <phrase>\n" +
        "/transcript <remove> <phrase>\n" +
        "/transcript <alert> <enable|disable>\n" +
        "/transcript <list>";
  }

  @Override
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    if (args.length < 1) throw new CommandException("Invalid arguments. Usage: \n" + getCommandUsage(sender));

    String cmd = args[0];

    if (cmd.equalsIgnoreCase("help")) onHelp(args);
    else if (cmd.equalsIgnoreCase("add")) onAdd(args);
    else if (cmd.equalsIgnoreCase("remove")) onRemove(args);
    else if (cmd.equalsIgnoreCase("alert")) onAlert();
    else if (cmd.equalsIgnoreCase("list")) onList();
    else throw new CommandException("Invalid arguments. Usage: \n" + getCommandUsage(sender));
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  private void onHelp(String[] args) {
    // Thing
  }

  private void onAdd(String[] args) throws CommandException {
    if (args.length < 2) throw new CommandException("Invalid number of arguments");

    String unparsedType = args[1];

    List<Alert.Element> types = new ArrayList<Alert.Element>();

    // Parse types 
    for (String type : unparsedType.split(",")) {
      // Get type and its second parameter if it exists
      String[] values = type.split("_");
      System.out.println(values.length);
      System.out.println(values[0] + " " + (values.length > 1 ? values[1] : "")); // Logging
      types.add(new Alert.Element(monk.transcript.alert.Alert.Callback.valueOf(values[0]),
          values.length > 1 ? ChatFormatting.valueOf(values[1]) : null));
    }

    // Find phrase
    StringBuilder target = new StringBuilder();
    for (int i = 2; i < args.length; i++) target.append(args[i]).append(i < args.length - 1 ? " " : "");

    Alert alert = new Alert(target.toString(), types);

    // Add alert to config
    ConfigElement newConfig = ConfigHandler.getInstance().configGet();
    Messaging.sendMessage(newConfig.addOverride(alert) ?
        "Successfully added phrase '" + ChatFormatting.GREEN + target + ChatFormatting.RESET + "'." :
        "Could not add phrase, as it already exists.");
  }

  private void onRemove(String[] args) {
    Messaging.sendMessage(new ChatComponentText(ChatFormatting.RED + "Removed phrase"));
  }

  private void onAlert() {
    //subscribe/unsubscribe to the alert player
  }

  private void onList() {
    Messaging.sendMessage("Words to react to:");
    for (Alert alert : ConfigHandler.getInstance().configGet().targets) Messaging.sendMessage(alert.toString());
  }
}
