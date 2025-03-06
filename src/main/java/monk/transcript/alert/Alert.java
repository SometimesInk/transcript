package monk.transcript.alert;

import java.util.List;

public class Alert {
  public String target;

  public List<AlertElement> types;

  public Alert(String target, List<AlertElement> types) {
    this.target = target;
    this.types = types;
  }

  @Override
  public String toString() {
    StringBuilder formattedTypes = new StringBuilder();
    for (int i = 0; i < types.size(); i++)
      formattedTypes.append(types.get(i).type.name()).append(i < types.size() - 1 ? " " : "");

    return target + " - " + formattedTypes;
  }
}


