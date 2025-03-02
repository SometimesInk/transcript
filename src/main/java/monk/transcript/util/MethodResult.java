package monk.transcript.util;

public class MethodResult<T,U> {
  public T stmout;
  public U stmerr;
  
  public MethodResult(T out, U err) {
    stmout = out;
    stmerr = err;
  }
}
