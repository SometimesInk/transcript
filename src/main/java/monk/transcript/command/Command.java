package monk.transcript.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.List;

public abstract class Command extends CommandBase {
  public abstract List<SubCommand> getSubCommands();

  @Override
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    if (args.length == 0) throw commandFailure(sender);

    for (SubCommand s : getSubCommands()) {
      if (args[0].equals(s.getName())) if (!s.process(args)) throw commandFailure(sender);
      return;
    }

    throw commandFailure(sender);
  }

  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  @Override
  public List<String> getCommandAliases() {
    return super.getCommandAliases();
  }

  protected CommandException commandFailure(ICommandSender sender) {
    return new CommandException("Usage: " + getCommandUsage(sender));
  }
}
