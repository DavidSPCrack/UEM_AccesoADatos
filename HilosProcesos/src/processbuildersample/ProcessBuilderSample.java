package processbuildersample;

import java.util.Map;

public class ProcessBuilderSample {
	public static void main(String args[]) {
		ProcessBuilder testProcesos = new ProcessBuilder();
		Map<String, String> environmentMap = testProcesos.environment();
		System.out.println(environmentMap);
		// Forma usual
		System.out.println(System.getenv());
	}
}