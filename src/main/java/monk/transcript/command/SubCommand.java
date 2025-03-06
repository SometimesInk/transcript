package monk.transcript.command;

public abstract class SubCommand {
  public abstract String getName();

  public abstract boolean process(String[] args);
}
