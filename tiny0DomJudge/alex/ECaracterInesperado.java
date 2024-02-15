package alex;

public class ECaracterInesperado extends RuntimeException {
	String error;
	public ECaracterInesperado(String msg) {
		System.out.println(msg);
	}
	
	
}
