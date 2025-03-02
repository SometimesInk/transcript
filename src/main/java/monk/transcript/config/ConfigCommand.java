package monk.transcript.config;

import com.mojang.realmsclient.gui.ChatFormatting;
import monk.transcript.util.Messaging;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

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
        /* Sub commands:
            - add - adds a phrase/word  - /transcript add HIGHLIGHTS 5
            - remove - removes a phrase/word
            - alert - enables/disables alerts
            - list
         */
    String subCommand1 = args[0];

    if (subCommand1.equalsIgnoreCase("add")) {
      onAdd();
    } else if (subCommand1.equalsIgnoreCase("remove")) {
      onRemove();
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

  private void onAdd() {
    Messaging.sendMessage(new ChatComponentText(ChatFormatting.GREEN + "Added phrase"));
  }

  private void onRemove() {
    Messaging.sendMessage(new ChatComponentText(ChatFormatting.RED + "Removed phrase"));
  }

  private void onAlert() {
    //subscribe/unsubscribe to the alert player
  }

  private void onList() {
    // Loop through entries
  }
}
