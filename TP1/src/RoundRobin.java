import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RoundRobin {
	
	private final byte Q;		// Quantum
//	private final byte Ti;	// Tiempo cambio de contexto - DESPRECIABLE PARA EL TP
	private int R = 0;				// Reloj
	private ArrayList<Process> procesos = new ArrayList<Process>();
	private ArrayList<Log> logs = new ArrayList<Log>();
	

	// Metodos Clase RoundRobin
	
	public RoundRobin(byte Q) {
		this.Q = Q;
	}
	
	
	// Getters
	public ArrayList<Process> getProcesos(){
		return this.procesos;
	}
	
	public int getQuantum(){
		return this.Q;
	}
	
	// Procesos
	
	public boolean hayProcesos() {
		for (Process proceso: procesos) {
			if (proceso.getProcessTS() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public void ingresarProcesos() {
		InputStream stream = System.in;
		Scanner scanner = new Scanner(stream);
		System.out.println("[*] Para agregar un proceso, indique el Tiempo de Servicio (TS) del mismo (ingrese 0 para terminar): ");

		int input;
		while (true) {
			System.out.print("Proceso " + Process.getNextPID() + ": ");		
			input = scanner.nextInt();
			
			if (input > 0) {
				procesos.add(new Process(input));
			} else if (input == 0) {
				break;
			}
		}
		
		scanner.close();
	}
	
	private int getCantidadProcesos() {
		return this.procesos.size();
	}
	
	public void execute(Process proceso) {
		updateClock();
		System.out.println("Ejecutando proceso " + proceso.getProcessPID() + "...");
		proceso.execute(Q);
		System.out.println("Valor del Clock: " + R);
	}
	
	public void finishExecution(Process proceso) {
		updateClock(proceso);
		System.out.println("Matando proceso " + proceso.getProcessPID() + "...");
		proceso.execute(Q);
		System.out.println("Valor del Clock: " + R);
		logger(proceso);
	}
	
	// Clock
	
	private void updateClock() {
		this.R += Q;
	}

	private void updateClock(Process proceso) {
		this.R += proceso.getProcessTS();
	}
	
	// Logs
	public void logger(Process proceso) {
		this.logs.add(new Log(proceso, this.R));
	}
	
	public void getReturnTime() {
		System.out.println("\n[*] Tiempo de retorno");
		int tiempoRetorno;
		int tiempoRetornoTotal = 0;
		
		for (Log log: logs) {
			tiempoRetorno = log.getTiempoFinal();
			tiempoRetornoTotal += tiempoRetorno;
			System.out.println("Proceso " + log.getPID() + ": " + tiempoRetorno);		
		}
		System.out.println("[+] Tiempo de retorno total: " + tiempoRetornoTotal / getCantidadProcesos());
	}
	
	public void getWaitTime() {
		System.out.println("\n[*] Tiempo de espera");
		int tiempoEspera;
		int tiempoEsperaTotal = 0;
		
		for (Log log: logs) {
			tiempoEspera = log.getTiempoFinal()-log.getTS();
			tiempoEsperaTotal += tiempoEspera;
			System.out.println("Proceso " + log.getPID() + ": " + tiempoEspera);		
		}	
		System.out.println("[+] Tiempo de espera total: " + tiempoEsperaTotal / getCantidadProcesos());
	}
	
	
}
