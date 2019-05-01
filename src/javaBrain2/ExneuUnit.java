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
import java.util.HashMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javaBrain2.Brain0.Hosei;
import javaBrain2.Brain0.NeuReturn;
import javaBrain2.Brain0.Queue;
import javaBrain2.Brain0.Queue2;
import javaBrain2.DelayAmount.DAReturn;

public class ExneuUnit {

	private ExneuUnit(HashMap<Integer,String> map) {
		this.map=map;//tougou yotei kokono shusei ga hanei suruka check siyou
	}
	public static void testrun() {
		//quickspinmemory();
		Brain0 brain=new Brain0();
		Queue2 test=brain.new Queue2();
		int[] testloc= {3,5};
		int[]testloc2= {4,7};
		int[][] testlist= {{0,0,0,0,0,0,0,0,0,0,0},{1,1,1,1,1,1,1,1,1,1}};
		TmpConstruct tmp2=new TmpConstruct(2);
		tmp2.additional(quickspinneuron(test,2,testloc,testlist).order);
		tmp2.additional(quickspinneuron(test,2,testloc2,testlist).order);
		TmpConstruct tmp3=new TmpConstruct(3);
		tmp3.additional(quickspinneuron(test,3,testloc,testlist).order);
		PluralConstructs plu=new PluralConstructs();
		plu.additional(tmp2.aggregate(),2);
		plu.additional(tmp3.aggregate(),3);
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


	public static SpinNeuReturn quickspinneuron(Queue2 line,int No,int[] loc,int[][] list) {
		//int[] inputline={2,1,-10,6,0,-5,0,0,0,0};
		int[][] inputline=new int[loc.length][10];
		inputline=list;
		int[][] inlist =new int[10][10];

		//Road
		File filein=new File("./inlist.txt");
		String[] instrlist=new String[10];
				String instr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(filein));
			String str =br.readLine();
			while(str!=null) {
				instr=instr+str;
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
		instrlist= instr.split("/",0);
		for(int i=0;i<10;i++) {
			String[] tmplist=instrlist[i].split(",",0);
			for(int k=0;k<10;k++) {
				inlist[i][k]=Integer.parseInt(tmplist[k]);
			}
		}
		File fileans=new File("./quickspinanswerlist"+No+".txt");//";"->"/"->",",true1->12;false1->12
				String astr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(fileans));
			String str =br.readLine();
			while(str!=null) {
				astr=astr+str;
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
		String[] plabuflist=astr.split(";");
		int[][] tanswerlist=new int[loc.length][10];int[][] fanswerlist=new int[loc.length][10];
		int[][] tbuflist=new int[plabuflist.length][10];int[][] fbuflist=new int[plabuflist.length][10];
		int[] buflisted=new int[plabuflist.length];
		int count=0;
		for (int i=0;i<loc.length;i++) {
			if(buflisted[loc[i]]==1) {
				tanswerlist[loc[i]]=tbuflist[loc[i]];fanswerlist[loc[i]]=fbuflist[loc[i]];
			}else {
				String[] abufstr=new String[2];
				abufstr=plabuflist[loc[i]].split("/");
				String[] abuflist=new String[10];
				abuflist=abufstr[0].split(",");

				for(int j=0;j<9;j++) {
					tanswerlist[i][j]=Integer.valueOf(abuflist[j]);
					tbuflist[loc[i]][j]=Integer.valueOf(abuflist[j]);
				}
				abuflist=abufstr[1].split(",");
				for(int j=0;j<9;j++) {
					fanswerlist[i][j]=Integer.valueOf(abuflist[j]);
					fbuflist[loc[i]][j]=Integer.valueOf(abuflist[j]);
				}
				buflisted[loc[i]]=1;
			}
		}


		File filechk=new File("./quickspincheck"+No+".txt");
		int[] checklist=new int[2];
				String cstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(filechk));
			String str =br.readLine();
			while(str!=null) {
				cstr=cstr+str;
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
		String[] checkstr=cstr.split(";");
		int[][] checklist0=new int[checkstr.length][2];
		int[] checklisted=new int[checkstr.length];
		int[][] check0 =new int[loc.length][2];
		for (int i=0;i<loc.length-1;i++) {

			if(checklisted[loc[i]]==1) {
				check0[i]=checklist0[loc[i]];
			}else {
				String[] check0str=checkstr[loc[i]].split(",");
				check0[i][0]=Integer.parseInt(check0str[0]);check0[i][1]=Integer.parseInt(check0str[1]);
				checklisted[loc[i]]=1;
			}
			//Sum

		}


		File hfile=new File("./quickspinhosei"+No+".txt");
		String[] finhoslist=new String[270];
				String hosstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(hfile));
			String str =br.readLine();
			while(str!=null) {
				hosstr=hosstr+str;
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
		String[] hosstrlist=hosstr.split(";");
		int[][] hoseilist=new int[loc.length][10];
		int[][] hoslist=new int[plabuflist.length][10];
		int[] hoslisted=new int[plabuflist.length];
		for (int i=0;i<loc.length-1;i++) {
			if(hoslisted[loc[i]]==1) {
				hoseilist[loc[i]]=hoslist[loc[i]];
			}else {
				String[] ahosstrlist=new String[10];
				ahosstrlist=hosstrlist[loc[i]].split(",");

				for(int j=0;j<9;j++) {
					hoseilist[i][j]=Integer.valueOf(ahosstrlist[j]);
					hoslist[loc[i]][j]=Integer.valueOf(ahosstrlist[j]);
				}
				hoslisted[loc[i]]=1;
			}
		}


		File cfile=new File("./quickspincelllist"+No+".txt");
		String[] fincellist=new String[270];
				String celstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(cfile));
			String str =br.readLine();
			while(str!=null) {
				celstr=celstr+str;
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
		String[] aprstrlist=hosstr.split(";");
		int[][] trueapr=new int[loc.length][10];
		int[][] aprlist=new int[plabuflist.length][10];
		int[] aprlisted=new int[plabuflist.length];
		for (int i=0;i<loc.length-1;i++) {
			if(aprlisted[loc[i]]==1) {
				trueapr[loc[i]]=aprlist[loc[i]];
			}else {
				String[] aaprstrlist=new String[10];
				aaprstrlist=aprstrlist[loc[i]].split(",");

				for(int j=0;j<9;j++) {
					trueapr[i][j]=Integer.valueOf(aaprstrlist[j]);
					aprlist[loc[i]][j]=Integer.valueOf(aaprstrlist[j]);
				}
				aprlisted[loc[i]]=1;
			}
		}


		//System.out.print("end");//debug you
		NeuReturn[] ret=new NeuReturn[loc.length];
		PluDecReturn[] plu=new PluDecReturn[loc.length];
		for(int m=0;m<loc.length-1;m++) {
			int[] inputline0=inputline[m];
			int[] tanswerlist0=tanswerlist[m];
			int[] fanswerlist0=fanswerlist[m];
			int[] hoseilist0=hoseilist[m];
			int[] trueapr0=trueapr[m];
			int[] check00=check0[m];

			int[] inputlist= {0,0,0,0,0,0,0,0,0,0};
			for(int l=0;l<10;l++) {
				for(int i=0;i<10;i++) {
					inputlist[l]=(int)(inputlist[l]+inlist[i][l]*inputline0[i]*9/1.8726/6);///2*10,20,30/3*30,0,10->20+90,inlist[start][end]
				}
			}
			int[] neuronlist= {0,0,0,0,0,0,0,0,0,0};
			for (int i=0;i<10;i++) {
				neuronlist[i]=trueapr0[i]+inputlist[i];
			}

			//Check
			int[] check= {0,0};
			for(int i=0;i<10;i++) {
				if(10000>Math.abs((int)(neuronlist[i]-tanswerlist0[i]*33339/1.6+hoseilist0[i]))) {
					check[0]++;
				}if(10000>Math.abs((int)(neuronlist[i]-fanswerlist0[i]*33339/1.6-hoseilist0[i]))) {
					check[1]++;
				}
			}


			int[] trange = {0,0,0,0,0,0,0,0,0,0};
			int[] frange = {0,0,0,0,0,0,0,0,0,0};
			int[] thosanswer = {0,0,0,0,0,0,0,0,0,0};
			int[] fhosanswer = {0,0,0,0,0,0,0,0,0,0};
			int[] rangecheck= {0,0};

			for(int i=0;i<10;i++) {
				trange[i]=Math.abs((int)(neuronlist[i]-tanswerlist0[i]*33339/1.6+hoseilist0[i]));
				frange[i]=Math.abs((int)(neuronlist[i]-fanswerlist0[i]*33339/1.6-hoseilist0[i]));
				thosanswer[i]=(int)((neuronlist[i]+hoseilist0[i])/33339*1.6);
				fhosanswer[i]=(int)((neuronlist[i]-hoseilist0[i])/33339*1.6);
			}

			for(int i=0;i<10;i++) {
				if(trange[i]<=10000) {
					rangecheck[0]++;
				}if(frange[i]<=10000) {
					rangecheck[1]++;
				}
			}


			//System.out.print("end");//debug you
			ret[m]=new NeuReturn();
			ret[m].check=check;
			for (int i=0;i<10;i++) {
				ret[m].list[i]=(int)(neuronlist[i]/33339*1.6);
			}
			ret[m].hosei=hoseilist0;
			ret[m].tanswer=tanswerlist0;
			ret[m].fanswer=fanswerlist0;
			ret[m].check0=check00;
			ret[m].trange=trange;
			ret[m].frange=frange;
			ret[m].thosans=thosanswer;
			ret[m].fhosans=fhosanswer;
			ret[m].rangecheck=rangecheck;
			plu[m]=new PluDecReturn();
			plu[m]=pluraldicision(ret[m]);
			plu[m].location=loc[m];
		}
		String order="";
		order=writeorder(plu);
		//kokomade
		SpinNeuReturn spin=new SpinNeuReturn();
		spin.neureturn=ret;
		spin.order=order;
		return spin;

	}
	public static class SpinNeuReturn{
		public NeuReturn[] neureturn;
		public String order;
	}


	/**
	 * PluDecReturn istrue boolean whether range is in the range  range the difference bet calculated range and written range
	 * quickspinneuron
	 * @param ret
	 * @return
	 */
	public static PluDecReturn pluraldicision(NeuReturn ret) {
		PluDecReturn plu=new PluDecReturn();
		int sum=0;
		int[] anslist=new int[10];
		if(ret.check[0]>ret.check[1]){//t to f de dottiga range ga chikaika hikaku (rangecheck->check henkouten ari youkennshou)
			if(1.1*ret.rangecheck[0]>=ret.check0[0]) {// rangecheck ni touitu tamesichu
				anslist= ret.tanswer;
				for(int j=0;j<10;j++) {
					sum=sum+ret.tanswer[j];
				}
			}else {
				anslist= ret.fanswer;
			}
		}else {
			anslist=ret.fanswer;
		}
		if(ret.rangecheck[0]>ret.rangecheck[1]) {//check0->checklist,check=rangecheck??;check->check0??
			if(1.1*ret.rangecheck[0]>=ret.check0[0]) {
				plu.istrue=true;
				plu.range=ret.rangecheck[0]-ret.check0[0];
			}else {
				plu.istrue=false;
				plu.range=ret.rangecheck[1]-ret.check0[1];
			}
		}else {
			plu.istrue=false;
			plu.range=ret.rangecheck[1]-ret.check0[1];
		}
		return plu;
	}
	public static class PluDecReturn{
		public int location;
		public boolean istrue;
		public int range;
		public int time;// sakuseichuu
	}
	public static String writeorder(PluDecReturn[] plu) {
		StringBuffer ret=new StringBuffer();
		for(int i=0;i<plu.length;i++) {
			PluDecReturn current=new PluDecReturn();
			ret.append(current.location);
			ret.append("/");
			if(current.istrue) {
				ret.append("T");
			}else {
				ret.append("F");
			}
			ret.append("/");
			ret.append(current.range);
			ret.append("/");
			ret.append(current.time);
			ret.append(";");
		}
		return ret.toString();

	}
	public static class TmpConstruct{
		public int No;
		public HashMap<Integer,HashMap<Integer,InnerConst>> construct;//key1 ha jikan,key2 ha location
		public TmpConstruct(int No) {
			this.No=No;
		}
		public class InnerConst{
			boolean exist=false;
			int trueamount;
			int falseamount;
		}
		public void query() {//toiawase

		}
		public void additional(String order) {//data no tuika
			String[] orders=order.split(";");
			for (int i=0;i<orders.length;i++) {
				String[] elements=orders[i].split("/");//0:location 1:istrue 2:range 3:time
				int[] locs=isexist(Integer.parseInt(elements[3]),Integer.parseInt(elements[0]));//0:time 1:loc
				if(Math.abs(Integer.parseInt(elements[2]))<=1) {//trace sanshou
					if(elements[1]=="T") {
						this.construct.get(locs[0]).get(locs[1]).trueamount++;
					}if(elements[1]=="F") {
						this.construct.get(locs[0]).get(locs[1]).falseamount++;
					}
				}
			}
		}
		public int[] isexist(int time,int loc){
			if(this.construct.containsKey(time)) {
				if(this.construct.get(time).containsKey(loc)) {
					if(this.construct.get(time).get(loc).exist==true) {
						int[] ret= {time,loc};
						return ret;
					}else {

					}
				}else {
					this.construct.get(time).put(loc,new InnerConst());
					this.construct.get(time).get(loc).exist=true;
					int[] ret= {time,loc};
					return ret;
				}
			}else {
				this.construct.put(time,new HashMap<Integer,InnerConst>());
				this.construct.get(time).put(loc,new InnerConst());
				this.construct.get(time).get(loc).exist=true;
				int[] ret= {time,loc};
				return ret;

			}
			//int[] ret= {0,0};
			//return ret;
			return null;
		}
		public Return aggregate() {// constructs ni nyuuryoku surutameno method
			int counts=0;
			for(int time :this.construct.keySet()) {
				counts=counts+this.construct.get(time).size();
			}
			Return ret=new Return(counts);
			int i=0;
			for(int time :this.construct.keySet()) {
				for(int loc :this.construct.get(time).keySet()) {
					ret.time[i]=time;
					ret.loc[i]=loc;
					ret.trueamount[i]=this.construct.get(time).get(loc).trueamount;
					ret.falseamount[i]=this.construct.get(time).get(loc).falseamount;
					i++;
				}
			}
			return ret;
		}
		public class Return{
			int[] time;
			int[] loc;
			int[] trueamount;
			int[] falseamount;
			int count;
			public Return(int counts) {
				this.time=new int[counts];
				this.loc=new int[counts];
				this.trueamount=new int[counts];
				this.falseamount=new int[counts];
				this.count=counts;
			}
		}
	}
	public static class PluralConstructs{
		private int arraycounts;
		public HashMap<Integer,Integer> Nolist;//key ha number value ha tourokujun
		public ArrayList<HashMap<Integer,InnerConstructs>> construct;//array ha tourokujun key ha jikan
		public PluralConstructs() {
			this.Nolist=new HashMap<Integer,Integer>();
			this.construct=new ArrayList<HashMap<Integer,InnerConstructs>>();
			this.arraycounts=0;
		}
		public class InnerConstructs{
			boolean exist=false;
			int trueamount;
			int falseamount;
			public InnerConstructs(int truea,int falsea) {
				this.exist=true;
				this.trueamount=truea;
				this.falseamount=falsea;
			}
		}
		public void query(){

		}
		private void createarray(int No) {
			this.Nolist.put(No, this.arraycounts);
			this.construct.add(new HashMap<Integer,InnerConstructs>());
			this.arraycounts++;
		}
		public void additional(TmpConstruct.Return ret,int No) {
			if(this.Nolist.containsKey(No)==false) {
				createarray(No);
			}
			for(int i=0;i<ret.count;i++) {
				int jun=this.Nolist.get(No);
				if(this.construct.get(jun).containsKey(ret.time[i])) {
					this.construct.get(jun).get(ret.time[i]).trueamount=
							this.construct.get(jun).get(ret.time[i]).trueamount+ret.trueamount[i];
					this.construct.get(jun).get(ret.time[i]).falseamount=
							this.construct.get(jun).get(ret.time[i]).falseamount+ret.falseamount[i];
				}else {
					this.construct.get(jun).put(ret.time[i],
							new InnerConstructs(ret.trueamount[i],ret.falseamount[i]));
				}
			}
		}
	}
	public static void quickspinmemory(Queue line,int No,int[] intlist,int[] inflist,int[] outlist,int[] ouflist,SpinMemList mem) {
			Brain0 main=new Brain0();
			//ModifyTools mod=main.new ModifyTools();
			///mod.split1(34,35);mod.split1(52, 53);mod.split1(45, 46);
			///mod.decrease(30,32,3,3);
			///mod.decrease(50, 52, 2, 4);///40ga_0ika->null atode_shuusei
			///mod.addition(42, 45, 7);mod.addition(43, 46, 8);
			///mod.increase(41, 43, 3, 4);
			///mod.change1(11, 13, 14);
			int[][] syglist=new int[270][10];
			mapfilewrite();
			String[] mapread=fileRead();
			mapread(mapread);
			int[][] inlist=new int[10][10];
			///inlist=inlist();

			File filein=new File("./inlist.txt");

			String[] instrlist=new String[10];
					String instr="";
			try {
				BufferedReader br=new BufferedReader(new FileReader(filein));
				String str =br.readLine();
				while(str!=null) {
					instr=instr+str;
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
			instrlist= instr.split("/",0);
			for(int i=0;i<10;i++) {
				String[] tmplist=instrlist[i].split(",",0);
				for(int k=0;k<10;k++) {
					inlist[i][k]=Integer.parseInt(tmplist[k]);
				}
			}

			int inadd=0;int sygadd=0;
			for(int i=0;i<270;i++) {
				for(int p=0;p<10;p++) {
					sygadd=sygadd+syglist[i][p];
				}
			}
			for(int i=0;i<10;i++) {
				for(int p=0;p<10;p++) {
					inadd=inadd+inlist[i][p];
				}
			}
			int correction=sygadd/inadd;
			System.out.print(correction);
			int[] truelist=new int[10];int[] falselist =new int[10];
			int[] truelist2= {5,-5,0,4,2,-3,5,-9,6,0};
			int[]falselist2= {0,0,5,0,0,0,0,7,0,0};
			truelist=truelist2;falselist=falselist2;
			Queue trueline=main.new Queue();Queue falseline=main.new Queue();
			//trueline.amount.addAll(Arrays.asList(2,1,-10,6,0,-5,0,0,0,0));trueline.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
			//falseline.amount.addAll(Arrays.asList(0,0,0,0,4,-3,6,8,0,5));falseline.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

			trueline.amount.clear();falseline.amount.clear();
			int[] checklist=new int[2*mem.inflist.size()];
			int[] tfanswerlist =new int[20*mem.inflist.size()];
			int[] hoseilist=new int[10*mem.inflist.size()];
			int[] aprlist=new int[10*mem.inflist.size()];
			for(int o=0;o<mem.inflist.size();o++) {
				for(int i=0;i<10;i++) {
					trueline.amount.add(mem.intlist.get(o)[i]);
					falseline.amount.add(mem.inflist.get(o)[i]);


				}
//kokokara
				for(int i=0;i<10;i++) {
					trueline.amount.add(intlist[i]);falseline.amount.add(inflist[i]);
				}
				Queue tmpline=main.new Queue();
				tmpline=trueline;
				int[] tmplist= {0,0,0,0,0,0,0,0,0,0};
				for(int i=0;i<10;i++) {
					for(int k=0;k<10;k++) {
						tmplist[k]=tmplist[k]+inlist[i][k]*tmpline.amount.get(i);
					}
				}
				Queue tmpline2=main.new Queue();
				tmpline2=falseline;
				int[] tmplist2= {0,0,0,0,0,0,0,0,0,0};
				for(int i=0;i<10;i++) {
					for(int k=0;k<10;k++) {
						tmplist2[k]=tmplist2[k]+inlist[i][k]*tmpline2.amount.get(i);

					}
				}

				truelist=outlist;falselist=ouflist;
				truelist2=outlist;falselist2=ouflist;
				int[] tsample= {10,10,10,10,10,10,10,10,10,10,10};
				int[] fsample= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};
				truelist=tsample;falselist=fsample;
				truelist2=tsample;falselist2=fsample;
				int[] maplist=new int[270];
				Arrays.fill(maplist, 0);
				int[] check0= {0,0};
				correction=9;
				Hosei hlist=main.new Hosei();
				hlist=hosei(truelist2,tmplist,falselist2,tmplist2);
				truelist2=hlist.trueout;falselist2=hlist.falseout;//out wo hosei out ni kaeru
				int[] trueapr= {0,0,0,0,0,0,0,0,0,0};
				for(int l=0;l<10;l++) {

					trueapr[l]=truelist2[l]-(int)(tmplist[l]*correction/1.8726/6);

					//trueapr=0;falseapr=0;///tigau... 666780/2/10 666780/178034.4(=197816/10)/2
					if(10000>Math.abs(truelist2[l]-(int)(tmplist[l]*correction/1.8726/6)-trueapr[l])) {//100017/1.6
						check0[0]++;
					}
					if(10000>Math.abs(falselist2[l]-(int)(tmplist2[l]*correction/1.8726/6)-trueapr[l])) {
						check0[1]++;
					}
					Arrays.fill(check0, 0);
				}

				int[] question=new int[20];
					for(int m=0;m<10;m++) {
						question[m]=trueapr[m];
						question[m+10]=trueapr[m];
					}

					for(int p=0;p<10;p++) {
						question[p]=(int)(tmplist[p]*correction/1.8726/6)+question[p];
						question[p+10]=(int)(tmplist2[p]*correction/1.8726/6)+question[p+10];
					}
					for(int p=0;p<20;p++) {
						question[p]=question[p];///kairo+nyuryoku
					}

				int question2=0;
				for(int i=0;i<10;i++) {
					question2=question2+Math.abs(truelist2[i]*33339-question[i])+Math.abs(falselist2[i]*33339-question[i+10]);///sabun
				}
				int[] question5=new int[20];
				int[] question6=new int[20];
				for(int i=0;i<10;i++) {
					question6[i]=truelist2[i]*33339;question6[i+10]=falselist2[i]*33339;///shuturyoku
				}
				for(int i=0;i<10;i++) {
					question5[i]=truelist2[i]-question[i];
					question5[i+10]=falselist2[i]-question[i+10];///shuturyoku-kairo-nyuryoku
				}
				int[] check= {0,0};
				for(int i=0;i<10;i++) {
					if(10000>Math.abs(question5[i])) {
						check[0]++;
					}
				}
				for(int i=10;i<20;i++) {
					if(10000>Math.abs(question5[i])) {
						check[1]++;
					}
				}

				checklist[o*2]=check[0];checklist[o*2+1]=check[1];
				for (int i=0;i<10;i++) {
					tfanswerlist[20*0+i]=truelist[i];
					tfanswerlist[20*o+10+i]=falselist[i];
					hoseilist[10*o+i]=hlist.hosei[i];
					aprlist[10*o+i]=trueapr[i];
				}


//kokomade
				trueline.amount.clear();falseline.amount.clear();
			}
			StringBuffer answer=new StringBuffer("");


			answer.append(tfanswerlist[0]);
			for(int k=0;k<mem.inflist.size();k++) {

				for(int i=1;i<10;i++) {
					answer.append(",");
					answer.append(tfanswerlist[20*k+i]);
				}
				answer.append("/");
				answer.append(tfanswerlist[20*k+10]);
				for(int i=1;i<10;i++) {
					answer.append(",");
					answer.append(tfanswerlist[20*k+i]);
				}
				answer.append(";");
			}
			answer.deleteCharAt(answer.lastIndexOf(";"));
			String answerstr=answer.toString();
			try {
				File ansfile=new File("quickspinanswerlist"+No+".txt");
				FileWriter filewrite=new FileWriter(ansfile);
				filewrite.write(answerstr);
				filewrite.close();
			}catch(IOException e) {
				System.out.println(e);
			}
			StringBuffer check=new StringBuffer();
			for (int i=0;i<mem.inflist.size();i++) {
				check.append(checklist[2*i]);
				check.append(",");
				check.append(checklist[2*i+1]);
				check.append(";");
			}
			check.deleteCharAt(check.lastIndexOf(";"));
			String checkstr=check.toString();
			try {
				File checkfile=new File("quickspincheck"+No+".txt");
				FileWriter checkwrite =new FileWriter(checkfile);
				checkwrite.write(checkstr);
				checkwrite.close();
			}catch(IOException e) {
				System.out.println(e);
			}

			StringBuffer hosei=new StringBuffer();
			for(int k=0;k<mem.inflist.size();k++) {
				hosei.append(hoseilist[10*k]);
				for(int i=1;i<10;i++) {
					hosei.append(",");
					hosei.append(hoseilist[10*k+i]);
				}
				hosei.append(";");
			}
			hosei.deleteCharAt(hosei.lastIndexOf(";"));

			String hoseistr=hosei.toString();
			try {
				File hoseifile=new File("quickspinhosei"+No+".txt");
				FileWriter hoseiwrite =new FileWriter(hoseifile);
				hoseiwrite.write(hoseistr);
				hoseiwrite.close();
			}catch(IOException e) {
				System.out.println(e);
			}
			StringBuffer cell=new StringBuffer();
			for(int k=0;k<mem.inflist.size();k++) {
				cell.append(aprlist[10*k]);
				for(int i=1;i<10;i++) {
					cell.append(",");
					cell.append(hoseilist[10*k+i]);
				}
				cell.append(";");
			}
			cell.deleteCharAt(cell.lastIndexOf(";"));

			String aprstr=cell.toString();
			try {
				File cellfile=new File("quickcelllist"+No+".txt");
				FileWriter filewrite=new FileWriter(cellfile);
				filewrite.write(aprstr);
				filewrite.close();
			}catch(IOException e) {
				System.out.println(e);
			}
			System.out.print("qend");



		}
	public static class SpinMemList {
		public ArrayList<int[]> intlist;
		public ArrayList<int[]> inflist;
		public int[] outtlist;
		public int[] outflist;
		private SpinMemList(int[] outtrue,int[] outfalse){
			this.outtlist=outtrue;
			this.outflist=outfalse;
		}
		public void addin(int[] intrue1,int[] infalse1) {
			this.intlist.add(intrue1);
			this.inflist.add(infalse1);
		}
	}

	private static void mapfilewrite() {
		String writemap="";
		for(int i=10;i<100;i++) {
			for (int k=0;k<3;k++) {
				if ((i+k-1>=10)&&(i+k-1<=99)) {
					writemap=writemap+"1,"+(i+k-1)+",";
				}
			}
			writemap=writemap.substring(0,writemap.length()-1);
			writemap=writemap+"/";
		}

		try {
			File file=new File("map.txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(writemap);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	private static void mapread(String[] mapper) {
		for (int i=0;i<mapper.length;i++) {
			map.put(i, mapper[i]);//yocheck
		}
	}

	private static String[] fileRead() {
		File file=new File("./map.txt");

		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String str =br.readLine();
			String finstr="";
			while(str!=null) {
				finstr=finstr+str;
				str=br.readLine();
			}///null_ni_nattara_yomikomi_owaru_saigono_str_ha_kanarazu_null

			br.close();
			return finstr.split("/",0);

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}


	}

	private static Hosei hosei(int[] trueout,int[] truein,int[] falseout,int[] falsein) {
		int[] hoseilist= {0,0,0,0,0,0,0,0,0,0};
		int[] toutlist= {0,0,0,0,0,0,0,0,0,0};int[] foutlist= {0,0,0,0,0,0,0,0,0,0};
		for (int i=0;i<10;i++) {
			hoseilist[i]=(int)((trueout[i]*33339/1.6-truein[i]*9/1.8726/6-falseout[i]*33339/1.6+falsein[i]*9/1.8726/6)/2);
			toutlist[i]=(int)(trueout[i]*33339/1.6-hoseilist[i]);foutlist[i]=(int)(falseout[i]*33339/1.6+hoseilist[i]);

		}
		Brain0 main=new Brain0();
		Hosei finlist=main.new Hosei();
		finlist.hosei=hoseilist;
		finlist.trueout=toutlist;
		finlist.falseout=foutlist;
		return finlist;
		//seikai to no saga 1 inai nara seino kotaewo kaesu huseikai to no saga 1 inai nara huno kotaewo kaesu soreigaiha huseikaino ataiwo kaesu
	}

	public static HashMap<Integer,String> map =new HashMap<Integer,String>();

	public static void echomemory() {

	}
	public static class SampleClass implements Comparable<SampleClass>{
		//class ni kansuru setumei HP yori,,
		public int number;
		public boolean equals(Object o) {
			return EqualsBuilder.reflectionEquals(this,o);
		}
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}
		public int compareTo(SampleClass obj) {
			if(this.number<obj.number)return -1;
			if(this.number>obj.number)return 1;
			return 0;
		}
	}
}

