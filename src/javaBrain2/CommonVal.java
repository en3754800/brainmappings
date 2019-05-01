package javaBrain2;

import java.nio.file.FileSystems;

public class CommonVal {
	static {
		separator=FileSystems.getDefault().getSeparator();//static block kyoutuu hennsuuno shokika ga dekiru
	}
	private CommonVal() {}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public static MemAnalize memAnalize=new MemAnalize(0);
	public static CountAnalize contAnalize=new CountAnalize();
	public static Output output=new Output();
	public static InputPlace input=new InputPlace();
	public static int absolutetime=0;
	public static String separator=FileSystems.getDefault().getSeparator();
	public static OutputPlace outputp=new OutputPlace();
}
