package monk.transcript.config;

import monk.transcript.command.SubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.Collections;
import java.util.List;

public class ConfigSubCommandPingVolume extends SubCommand {
  @Override
  public String getName() {
    return "pingVolume";
  }

  @Override
  public boolean process(String[] args) {
    if (args.length < 1) return false;

    // Set ping volume
    ConfigElement newConfig = ConfigHandler.getInstance().configGet();
    try {
      newConfig.pingVolume = Float.parseFloat(args[1]);
    } catch (Exception e) {
      return false;
    }
    ConfigHandler.getInstance().configSet(newConfig);
    return true;
  }

  @Override
  public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos pos) {
    return Collections.emptyList();
  }
}
