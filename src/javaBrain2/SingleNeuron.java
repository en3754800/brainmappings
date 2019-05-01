package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import javaBrain2.DelayAmount.DAReturn;

public class SingleNeuron {


	public static void testrun() {

	}
	public static void main() {
		singleneuron(12,0,3000);

		//testrun();
	}
	public static NoTimeAmount singleneuron(int No,int time,int limittime) {
		DelayAmount da=new DelayAmount(No);
		DAReturn dr=da.conversion(time);
		NoTimeAmount limit=new NoTimeAmount();
		if ((dr==null)||(time>limittime)) {

		}else {
			for(int i=0;i<dr.No.length;i++) {
				CommonVal.output.add(dr.No[i],dr.time[i],dr.amount[i]);
				double border=readborder(dr.No[i]);
				DAReturn dr2=CommonVal.memAnalize.add(dr.No[i], dr.amount[i], dr.time[i], border,No,time);
				if(dr2.No.length>=1) {
					limit.No=(ArrayList<Integer>)Arrays.asList(dr2.No);
					limit.time=(ArrayList<Integer>)Arrays.asList(dr2.time);
					limit.amount=(ArrayList<Double>)Arrays.asList(dr2.amount);
				}
			}
		}
		return limit;
	}
	/**
	 * singou nyuuryoku no NoTimeAmount wo memanalize to output ni kiroku site
	 * sonogo ha time gotoni true wo sagasite shuturyoku suru
	 * !firsttime ha first no dono time yorimo hayaku siteokukoto
	 * firsttime kara limittime madewo kennsakusuru!
	 * @param first
	 * @param firsttime
	 * @param limittime
	 */
	public static void timetorun(NoTimeAmount first,int firsttime,int limittime){
		for(int i=0;i<first.No.size();i++) {
			singleneuron(first.No.get(i),first.time.get(i),limittime);
		}
		for(int i=0;i<(limittime-firsttime)/100;i++) {
			int pointtime=firsttime+i*100;
			if(CommonVal.memAnalize.list.time.containsKey(pointtime)) {
				for(int No :CommonVal.memAnalize.list.time.get(pointtime).unit.keySet()) {
					if(CommonVal.memAnalize.list.time.get(pointtime).unit.get(No).trued==true) {
						singleneuron(No,pointtime,limittime);
					}
				}
			}
		}
		output();
	}
	public static class NoTimeAmount{
		ArrayList<Integer> No;
		ArrayList<Integer> time;
		ArrayList<Double> amount;
		public NoTimeAmount() {
			this.No=new ArrayList<Integer>();
			this.time=new ArrayList<Integer>();
			this.amount=new ArrayList<Double>();
		}
		public void add(int No,int time,double amount) {
			this.No.add(No);
			this.time.add(time);
			this.amount.add(amount);
		}
	}
	public class singleReturn{
		int No;
		double amount;
		int time;
		int[] switchlist=new int[2];
	}
	/**
	 * border No .txt no border jouhou wo yomikomi hensuuni ireru
	 * @param No
	 * @return
	 */
	public static double readborder(int No) {
		File file=new File("border"+No+".txt");
		String bordstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String str =br.readLine();
			while(str!=null) {
				bordstr=bordstr+str;
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
		double border=Double.parseDouble(bordstr);
		return border;
	}
	public static void writeborder(int No,double border) {
		BigDecimal bd=new BigDecimal(border);
		File wf=new File("border"+No+".txt");
		try {
			FileWriter filewrite=new FileWriter(wf);
			filewrite.write(bd.setScale(3, RoundingMode.HALF_UP).toString());
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
/*
 * shuturyoku wo matomeru method outputneuron no hannou sita kaisuuni oujite
 * hannnouno tuyosawo suutika suru 0.7 madeni hannousuru neuron ga 7ko ->7 no tuyosa
 * mikinyuu
 */
	public static void output() {
		CommonVal.output.output();
		for(int time:CommonVal.output.formoutput.keySet()) {
			for(int No:CommonVal.output.formoutput.get(time).keySet()) {
				System.out.println("time:"+time+" No:"+No+
						" amount:"+CommonVal.output.formoutput.get(time).get(No));
			}
		}
	}

	//ika betukansuu
	//
	//

}

