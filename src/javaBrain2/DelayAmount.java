package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class DelayAmount {
	/*
	 * file keisiki ha
	 * desttimeamo+No+.txt
	 * 23/300,0.300;34(0)/400,0.400;34(1)/200,0.500
	 *
	 */
	public static int No;
	public HashMap<NoandOrder,Unit> unitNo;//key is toNo
	public static class Unit{
		public int time;//delaytime
		public double amount;
	}
	public static class NoandOrder{
		public int No;
		public int order;
		public NoandOrder(int No,int order) {
			this.No=No;
			this.order=order;
		}
	}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
	}
	public DelayAmount(int No) {
		String delaystr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(CommonFunc.getPath("desttimeamo", No, ".txt")));
			String str =br.readLine();
			while(str!=null) {
				delaystr=delaystr+str;
				str=br.readLine();
			}///null_ni_nattara_yomikomi_owaru_saigono_str_ha_kanarazu_null

			br.close();

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}
		String[] delaystrlist=delaystr.split(";");
		if(this.unitNo==null) {
			this.unitNo=new HashMap<NoandOrder,Unit>();
		}
		for(int i=0;i<delaystrlist.length;i++) {
			String[] list=delaystrlist[i].split("/");
			int orderint;int no;
			if(Pattern.matches("(", list[0])) {
				Pattern dd=Pattern.compile("(\\d)");
				String order=dd.matcher(list[0]).group(1);
				order.replace("(","");
				order.replace(")","");
				orderint=Integer.parseInt(order);
				no=Integer.parseInt(Pattern.compile("(\\d)").matcher(list[0]).replaceAll(""));
			}else {
				no=Integer.parseInt(list[0]);
				orderint=0;
			}

			NoandOrder toNo=new NoandOrder(no,orderint);
			if(this.unitNo.containsKey(toNo)==false) {
				this.unitNo.put(toNo,new Unit());
			}
			String[] datalist=list[1].split(",");
			this.unitNo.get(toNo).time=Integer.parseInt(datalist[0]);
			this.unitNo.get(toNo).amount=Double.parseDouble(datalist[1]);
		}

	}
	public void write() {
		StringBuffer sb=new StringBuffer();
		for(NoandOrder no:unitNo.keySet()){
			sb.append(no.No);
			if(unitNo.containsKey(new NoandOrder(no.No,1))) {
				sb.append("(");
				sb.append(no.order);
				sb.append(")");
			}
			sb.append("/");
			sb.append(unitNo.get(no).time);
			sb.append(",");
			sb.append(unitNo.get(no).amount);
			sb.append(";");
		}
		try {
			File file=new File("desttimeamo"+No+".txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(sb.toString());
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public DAReturn conversion(int starttime) {
		DAReturn da=new DAReturn();
		da.No=new Integer[this.unitNo.size()];
		da.amount=new Double[this.unitNo.size()];
		da.time=new Integer[this.unitNo.size()];
		int i=0;
		for(NoandOrder key :this.unitNo.keySet()) {
			da.No[i]=key.No;
			da.order[i]=key.order;
			da.time[i]=this.unitNo.get(key).time+starttime;
			da.amount[i]=this.unitNo.get(key).amount;
			i++;
		}
		return da;
	}
	public class DAReturn {
		Integer[] No;
		Integer[] order;
		Integer[] time;
		Double[] amount;
	}
}
