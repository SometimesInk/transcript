package monk.transcript.config;

import monk.transcript.command.Command;
import monk.transcript.command.SubCommand;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

public class ConfigCommand extends Command {
  @Override
  public List<SubCommand> getSubCommands() {
    return Arrays.asList(new ConfigSubCommandAdd(), new ConfigSubCommandRemove(), new ConfigSubCommandList());
  }

  @Override
  public String getCommandName() {
    return "transcript";
  }

  @Override
  public String getCommandUsage(ICommandSender sender) {
    return "/transcript <add> [HIGHLIGHT,PING,TITLE] <phrase>\n" +
        "/transcript <remove> <phrase>\n" +
        "/transcript <list>";
  }
}
