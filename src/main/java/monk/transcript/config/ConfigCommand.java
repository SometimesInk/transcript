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

    String subCommand1 = args[0];

    if (subCommand1.equalsIgnoreCase("add")) {
      onAdd(args);
    } else if (subCommand1.equalsIgnoreCase("remove")) {
      onRemove(args);
    } else if (subCommand1.equalsIgnoreCase("alert")) {
      onAlert();
    } else if (subCommand1.equalsIgnoreCase("list")) {
      onList();
    } else {
      throw new CommandException("Invalid arguments. Usage: \n" + getCommandUsage(sender));
    }
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  private void onAdd(String[] args) {

    ConfigElement newConfig = ConfigHandler.getInstance().configGet();

    List<Alert.Type> notifications = new ArrayList<Alert.Type>();
    ChatComponentText notificationsOrdered = new ChatComponentText("");
    boolean isPhrase = false;
    String phrase = "";

    // Loops through all arguments given by the executed command
    for (int i = 1; i < args.length; i++) {
      if (!isPhrase) {
        // Tries to convert the argument to an Alert Type
        try {
          Alert.Type type = Alert.Type.valueOf(args[i].toUpperCase());
          notifications.add(type);
          notificationsOrdered.appendSibling(new ChatComponentText(ChatFormatting.ITALIC + "- " + type.name() + "\n"));
          // If it doesn't correspond to an Alert Type assume it's the phrase
        } catch (Exception e) {
          isPhrase = true;
        }
      }
      if (isPhrase) phrase += args[i] + " ";
    }

    ChatComponentText message = new ChatComponentText(
        ChatFormatting.GREEN + "Added Chat-Alert: \n" +
            ChatFormatting.GREEN + "Phrase: \n" + ChatFormatting.ITALIC + phrase + "\n" +
            ChatFormatting.GREEN + "Alerts: \n");

    newConfig.targets.add(new Alert(phrase, notifications));
    ConfigHandler.getInstance().configSet(newConfig);

    message.appendSibling(notificationsOrdered);
    Messaging.sendMessage(message);
  }

  private void onRemove(String[] subCommands) {
    Messaging.sendMessage(new ChatComponentText(ChatFormatting.RED + "Removed phrase"));
  }

  private void onAlert() {
    //subscribe/unsubscribe to the alert player
  }

  private void onList() {
    // Loop through entries
  }
}
