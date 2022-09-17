
public class Log {
	private Process proceso;
	private int tiempoFinal;
	
	public Log(Process proceso, int tiempoFinal) {
		this.proceso = proceso;
		this.tiempoFinal = tiempoFinal;
	}
	
	public int getTS() {
		return this.proceso.getInitialProcessTS();
	}
	
	public int getPID() {
		return this.proceso.getProcessPID();
	}
	
	public int getTiempoFinal() {
		return this.tiempoFinal;
	}
}
