package monk.transcript.config;

import monk.transcript.command.SubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ConfigSubCommandReload extends SubCommand {
  @Override
  public String getName() {
    return "reload";
  }

  @Override
  public boolean process(String[] args) {
    try {
      ConfigHandler.getInstance().reload();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  @Override
  public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos pos) {
    return Collections.emptyList();
  }
}
