package monk.transcript.alert;

public class AlertCheckResult {
  public String message;
  public boolean overrideMessage;

  public AlertCheckResult(String message, boolean overrideMessage) {
    this.message = message;
    this.overrideMessage = overrideMessage;
  }
}
