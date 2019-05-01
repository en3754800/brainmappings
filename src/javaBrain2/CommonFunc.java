package javaBrain2;

import java.text.DecimalFormat;

public class CommonFunc {

	public static DecimalFormat df=new DecimalFormat("#0.###");
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public static String getPath(String name,int No,String extention) {
		int secdirectory=No / 500;
		int firdirectory=No/250000;
		String sep=CommonVal.separator;
		return "."+sep+firdirectory+sep+secdirectory+sep+name+No+extention;
	}
	public static double round3(double value) {
		return Double.parseDouble(df.format(value));
	}

}
