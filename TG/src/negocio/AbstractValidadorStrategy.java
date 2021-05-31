package negocio;

public abstract class AbstractValidadorStrategy implements IStrategy {
	protected StringBuilder sb = new StringBuilder();
	
	protected String verificaMsg() {
		if(sb.length()>0) {
			return sb.toString();
		}else {
			return null;
		}
	}

}
