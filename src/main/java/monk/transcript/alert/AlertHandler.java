package monk.transcript.alert;

import monk.transcript.highlight.Highlighting;
import monk.transcript.sound.Pinging;

import java.util.ArrayList;
import java.util.List;

public class AlertHandler {
  private static final AlertHandler INSTANCE = new AlertHandler();

  private AlertHandler() {
  }

  public static AlertHandler getInstance() {
    return INSTANCE;
  }

  public AlertCheckResult check(String message, List<Alert> matches) {
    List<Alert> highlightMatches = new ArrayList<Alert>();
    List<Alert> pingMatches = new ArrayList<Alert>();

    for (Alert alert : matches)
      for (AlertElement element : alert.types)
        switch (element.type) {
          case HIGHLIGHT:
            if (element.highlighting == null) throw new NullPointerException("An highlight alert element was defined" +
                "without any highlight property.");
            if (!highlightMatches.contains(alert)) highlightMatches.add(alert);
            break;
          case PING:
            if (!pingMatches.contains(alert)) pingMatches.add(alert);
            break;
        }

    String msg = null;
    if (!highlightMatches.isEmpty()) msg = Highlighting.highlight(message, highlightMatches);
    if (!pingMatches.isEmpty()) Pinging.ping(message, pingMatches);
    return new AlertCheckResult(msg, !highlightMatches.isEmpty());
  }
}
