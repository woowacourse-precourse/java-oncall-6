package oncall;

public class Console {

	public static String readLine() {
		System.out.print("> ");
		return System.console().readLine();
	}
}