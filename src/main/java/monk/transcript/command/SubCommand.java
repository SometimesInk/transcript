package monk.transcript.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {
  public abstract String getName();

  public abstract boolean process(String[] args);

  public abstract List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos pos);

  public static SubCommand contains(List<SubCommand> subCommands, String name) {
    for (SubCommand sub : subCommands) if (sub.getName().equals(name)) return sub;
    return null;
  }

  public static List<String> getNames(List<SubCommand> subCommands) {
    List<String> names = new ArrayList<String>();

    // Get all names
    for (SubCommand s : subCommands) names.add(s.getName());

    return names;
  }
}
