package monk.transcript.alert;

import java.util.ArrayList;
import java.util.List;

public class Alert {
  public String targetPhrase;

  public enum Type {
    PING,
    HIGHLIGHT,
    TITLE
  }

  public List<Type> alerts = new ArrayList<Type>();

  public Alert(String targetPhrase, List<Type> alerts) {
    this.targetPhrase = targetPhrase;
    this.alerts = alerts;
  }
}


