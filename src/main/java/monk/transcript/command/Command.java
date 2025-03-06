package monk.transcript.command;

import net.minecraft.command.CommandBase;

import java.util.List;

public abstract class Command extends CommandBase {
  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  @Override
  public List<String> getCommandAliases() {
    return super.getCommandAliases();
  }
}
