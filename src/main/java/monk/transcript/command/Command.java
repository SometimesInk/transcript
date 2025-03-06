package monk.transcript.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Command extends CommandBase {
  public List<SubCommand> subCommands = new ArrayList<SubCommand>();

  @Override
  public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    if (args.length == 0) throw commandFailure(sender);

    // Find the used subcommand
    String arg = args[0];
    SubCommand subCommand = SubCommand.contains(subCommands, arg);
    if (subCommand == null) throw commandFailure(sender);

    // Process subcommand
    if (subCommand.process(args)) return;
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

  protected List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos pos) {
    // Output subcommands for first arg
    if (args.length < 2) return SubCommand.getNames(subCommands);

    // Get subcommand completions
    SubCommand sub = SubCommand.contains(subCommands, args[0]);
    if (sub != null) return sub.getTabCompletions(sender, args, pos);

    // Return base
    return super.addTabCompletionOptions(sender, args, pos);
  }

  @Override
  public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
    String currentArg = args.length != 0 ? args[args.length - 1] : null;

    // Get completions sorted alphabetically
    List<String> completions = getTabCompletions(sender, args, pos);
    if (completions == null) return null;
    Collections.sort(completions);

    // Only keep completions that fit with the current argument
    if (currentArg == null || currentArg.isEmpty()) return completions;

    // Only keep completions that complete the current arg
    for (int i = completions.size() - 1; i >= 0; i--)
      if (!completions.get(i).startsWith(currentArg)) completions.remove(i);

    return completions;
  }
}
