package monk.transcript.config;

import monk.transcript.command.Command;
import net.minecraft.command.ICommandSender;

public class ConfigCommand extends Command {
  public ConfigCommand() {
    subCommands.add(new ConfigSubCommandAdd());
    subCommands.add(new ConfigSubCommandList());
    subCommands.add(new ConfigSubCommandPingVolume());
    subCommands.add(new ConfigSubCommandReload());
    subCommands.add(new ConfigSubCommandRemove());
  }

  @Override
  public String getCommandName() {
    return "transcript";
  }

  @Override
  public String getCommandUsage(ICommandSender sender) {
    return "/transcript <add|list|pingVolume|reload|remove> [type/phrase] [phrase]";
  }
}
