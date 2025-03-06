package monk.transcript.config;

import monk.transcript.alert.Alert;
import monk.transcript.command.SubCommand;
import monk.transcript.util.Messaging;

public class ConfigSubCommandList extends SubCommand {
  @Override
  public String getName() {
    return "list";
  }

  @Override
  public boolean process(String[] args) {
    if (args.length > 1) return false;
    Messaging.sendMessage("Words to react to:");
    for (Alert alert : ConfigHandler.getInstance().configGet().targets) Messaging.sendMessage(alert.toString());
    return true;
  }
}
