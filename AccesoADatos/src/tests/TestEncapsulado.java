package tests;

public abstract class TestEncapsulado {

	private String textoDetalle;

	public TestEncapsulado(String textoDetalle) {
		this.textoDetalle = textoDetalle;
	}

	public void run() {
		long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long start = System.currentTimeMillis();
		String result = executeMethod();
		long end = System.currentTimeMillis();
		long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long elapsed = end - start;
		long mem = endMem - startMem;

		StringBuilder sb = new StringBuilder();
		sb.append(getDetailText());
		sb.append(result);
		sb.append(" (");
		sb.append(elapsed);
		sb.append("ms)");
		sb.append(" (");
		sb.append(mem / 1024 / 1024);
		sb.append(" Mb)");

		System.out.println(sb.toString());
	}

	public abstract String executeMethod();

	public String getDetailText() {
		return textoDetalle == null ? "" : textoDetalle;
	}
}