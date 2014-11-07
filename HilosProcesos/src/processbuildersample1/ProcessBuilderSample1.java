package processbuildersample1;
import java.io.File;
import java.io.IOException;


public class ProcessBuilderSample1 {
	public static void main(String args[]) throws IOException {
		ProcessBuilder dirProcess = new ProcessBuilder("cmd");
		File comandos = new File("C:/procesos/comandos.bat");
		File dirOut = new File("C:/procesos/out.txt");
		File dirErr = new File("C:/procesos/err.txt");
		dirProcess.redirectInput(comandos);
		dirProcess.redirectOutput(dirOut);
		dirProcess.redirectError(dirErr);
		dirProcess.start();
	}
}