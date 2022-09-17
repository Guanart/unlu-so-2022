import java.io.InputStream;
import java.util.Scanner;

public class InterfazMain {

	public static void main(String[] args) {
		RoundRobin RR = new RoundRobin((byte) 4);
		RR.ingresarProcesos();
		System.out.println("\n[***] INICIALIZANDO PROCESOS");		

		int ronda = 1;
		while (RR.hayProcesos()) {
			System.out.println("\n[+] RONDA " + ronda);		
			for (Process proceso: RR.getProcesos()) {	
				if (proceso.getProcessTS() > RR.getQuantum()) {
					RR.execute(proceso);
				} else if (proceso.getProcessTS() > 0) {
					RR.finishExecution(proceso);
				}
			}
			ronda++;
		}
		
		System.out.println("\n[***] LOGS");
		RR.getReturnTime();
		RR.getWaitTime();
	}

}
