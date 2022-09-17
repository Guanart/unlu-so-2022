
public class Process {
	
	private int PID;
	private int TSInicial;
	private int TSActual;
	private static int nextPID = 1000;
	
	public Process(int TS) {
		this.TSInicial = TS;
		this.TSActual = TS;
		this.PID = nextPID;
		nextPID++;
	}
	
	
	public void execute(int Q) {
		int time = this.TSActual - Q;
		this.TSActual = (time > 0) ? time : 0;
	}
	
	
	public int getProcessPID() {
		return this.PID;
	}
	
	public static int getNextPID() {
		return nextPID;
	}
	
	public int getProcessTS() {
		return this.TSActual;
	}
	
	public int getInitialProcessTS() {
		return this.TSInicial;
	}
}
