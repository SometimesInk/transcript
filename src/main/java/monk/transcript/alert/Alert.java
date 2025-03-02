package monk.transcript.alert;

import java.util.List;

public class Alert {

    String targetPhrase;
    List<Type> alerts;

    public Alert(String targetPhrase, List<Type> alerts) {
        this.targetPhrase = targetPhrase;
        this.alerts = alerts;
    }

    public enum Type {
        PING,
        HIGHLIGHT,
        TITLE
    }

}


