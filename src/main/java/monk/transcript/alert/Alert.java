package monk.transcript.alert;

import java.util.List;

public class Alert {

  String targetPhrase;
 
  public enum Type {
    PING,
    HIGHLIGHT,
    TITLE
  }

  List<Type> alerts;

  public Alert(String targetPhrase, List<Type> alerts) {
    this.targetPhrase = targetPhrase;
    this.alerts = alerts;
  }
}


