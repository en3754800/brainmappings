/**
 *
 */
package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 * @author okada.yousuke
 *
 */
public class Brain0 {


	public static HashMap<Integer,String> map =new HashMap<Integer,String>();
	private static int[][] keisanyou = {{0,0,0,0,0,1,1,2,2,3,3,3,4,4,4,4,5,5,5,5},
			{0,0,0,1,1,2,2,2,3,3,3,3,4,4,4,4,5,5,5,6,6},
	{0,0,1,1,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6},
	{1,1,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,7,7},
	{1,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,7,7,8,8},
	{2,2,3,3,4,5,5,5,6,6,6,7,7,7,8,8,9,9,10,10},
	{2,3,3,4,5,5,6,6,7,7,7,8,8,9,9,10,11,11,12,12},
	{3,3,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13},
	{3,4,4,5,6,6,7,8,8,9,9,10,10,11,11,12,13,14,14,15},
	{3,4,5,6,6,7,8,9,9,10,10,11,12,12,13,14,14,15,15,16},
	{3,4,5,6,7,8,8,9,10,11,11,12,12,13,14,15,15,15,16,17},
	{3,4,5,6,7,8,9,10,11,12,12,13,13,14,15,15,16,16,17,17},
	{4,5,6,7,8,9,10,11,12,13,13,14,14,15,15,16,16,17,17,18},
	{4,5,6,7,8,10,11,12,13,14,14,15,15,16,16,17,17,17,18,18},
	{4,5,7,8,9,11,12,13,14,15,15,16,16,16,17,17,18,18,19,19},
	{4,5,7,8,10,11,12,14,15,16,16,17,17,17,18,18,18,19,19,19},
	{4,5,7,9,10,11,12,14,15,17,17,17,17,18,18,18,19,19,19,20},
	{5,6,8,9,10,11,13,14,16,17,17,17,18,18,18,19,19,19,20,20},
	{5,7,8,10,11,13,14,16,17,18,18,18,18,19,19,19,19,20,20,20},
	{5,7,9,10,11,13,15,16,17,18,18,18,19,19,19,20,20,20,20,20}};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String mapper0 ="10,2/10,3/10,4/10,5/10,6/10,7/10,8/10,9/10,10/10,11/10,12/10,13/10,14/10,15/10,16/10,17/10,18/10,19/10,20/10,21/10,22/10,23/10,24/10,25/10,26/10,27/10,28/10,29/10,30/10,31/10,32/";
		String[] mapper=mapper0.split("/",0);
		int neuronNo=54;

		mapper=fileRead();
		mapread(mapper);///KAIRO HAIRETUWO YOMIKOMU

		///map.put(22,"3,23,1,24,2,25,4,26");
		int amount=7;///信号の強さ
		int[] neuronAm= {3,12,2,4};///
		int[] neuronSt= {23,24,25,26} ;

		Brain0 main=new Brain0();
		ReturnNeuro sam=main.new ReturnNeuro();
		sam=keisan(23,neuronAm,neuronSt);
		Queue line=main.new Queue();
		line.amount.add(10);
		line.state.add(0);
		///ModifyTools mod1=main.new ModifyTools();
		///mod1.addition(2, 5, 13);
		///mod1.change1(2,3,6);

		Queue2 line2=main.new Queue2();
		int[] inneutans= {-9,-10,-10,-10,-10,-10,-10,-10,-10,-10};
		int[] inneufans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};
		int[] outneufans= {10,10,10,10,10,10,10,10,10,10};
		int[] outputoutfans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};
		//33339->1.6,9/1.8->6 de waru
		//a,b,c,d to sita toki c->c+(a+d-b-c)/2 d->d-(a+d-b-c)/2 de okikaeru

		quickmemory(line,neuronNo,inneutans,inneufans,outneufans,outputoutfans);
		//quickneuron(line2,neuronNo);

		int initialize=1;
		if (initialize==1) {
			for (int i=0;i<100;i++) {
				///memory(line,i);
			}
		}
		/**
		 * tukaenai chuui
		 */
	}public static NeuReturn neuron(Queue2 line,int No,int[] list) {
		String[] memorymap;
		memorymap=memoryRead("mapmemory"+No+".txt");
		//int[] sampleline={2,1,-10,6,0,-5,0,0,0,0};
		int[] sampleline={0,0,0,0,4,-3,6,8,1,5};
		sampleline=list;
		//mapread(memorymap);//koujichuu(1,10<-koko(state)wotuika hituyou ari)
		int[][] syglist=new int[270][10];
		int[][] inlist=new int[10][10];

		//Road
		File file=new File("./syglist"+No+".txt");
		String[] finstrlist=new String[270];
				String finstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String str =br.readLine();
			while(str!=null) {
				finstr=finstr+str;
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
		finstrlist= finstr.split("/",0);
		for(int i=0;i<270;i++) {
			String[] tmplist=finstrlist[i].split(",",0);
			for(int k=0;k<10;k++) {
				syglist[i][k]=Integer.parseInt(tmplist[k]);
			}
		}

		File filein=new File("./inlist"+No+".txt");
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
		File fileans=new File("./answerlist"+No+".txt");
		int[] tanswerlist=new int[10];int[] fanswerlist=new int[10];
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
		String[] abufstr=new String[2];
		abufstr=astr.split("/");
		String[] abuflist=new String[10];
		abuflist=abufstr[0].split(",");
		for(int i=0;i<10;i++) {
			tanswerlist[i]=Integer.valueOf(abuflist[i]);
		}
		abuflist=abufstr[1].split(",");
		for(int i=0;i<10;i++) {
			fanswerlist[i]=Integer.valueOf(abuflist[i]);
		}
		File filechk=new File("./check"+No+".txt");
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
		String[] checkstr=cstr.split(",");
		int[] check0 =new int[2];
		check0[0]=Integer.parseInt(checkstr[0]);check0[1]=Integer.parseInt(checkstr[1]);
		//Sum
		int[] trueapr= {0,0,0,0,0,0,0,0,0,0};
		String[] branch=new String[3];
		for (int l=0;l<10;l++) {
			for (int k=0;k<90;k++) {
				//branch=map.get(k).split(",",0);
				branch=memorymap[k].split(",");

				trueapr[l]=trueapr[l]+syglist[3*k][l]*Integer.parseInt(branch[0]);//trueapr...-666780~666780(60%?)
				trueapr[l]=trueapr[l]+syglist[3*k+1][l]*Integer.parseInt(branch[1]);
				trueapr[l]=trueapr[l]+syglist[3*k+2][l]*Integer.parseInt(branch[2]);
			}

		}

		int[] hoseilist= {0,0,0,0,0,0,0,0,0,0};
		File hfile=new File("./hosei"+No+".txt");
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
		finhoslist= hosstr.split(",",0);
		for(int i=0;i<10;i++) {
			hoseilist[i]=Integer.parseInt(finhoslist[i]);
		}




		int[] inputline=sampleline;
		int[] inputlist= {0,0,0,0,0,0,0,0,0,0};
		for(int l=0;l<10;l++) {
			for(int i=0;i<10;i++) {
				inputlist[l]=(int)(inputlist[l]+inlist[i][l]*inputline[i]*9/1.8726/6);///2*10,20,30/3*30,0,10->20+90,inlist[start][end]
			}
		}
		int[] neuronlist= {0,0,0,0,0,0,0,0,0,0};
		for (int i=0;i<10;i++) {
			neuronlist[i]=trueapr[i]+inputlist[i];
		}

		//Check
		int[] check= {0,0};
		for(int i=0;i<10;i++) {
			if(10000>Math.abs((int)(neuronlist[i]-tanswerlist[i]*33339/1.6+hoseilist[i]))) {
				check[0]++;
			}if(10000>Math.abs((int)(neuronlist[i]-fanswerlist[i]*33339/1.6-hoseilist[i]))) {
				check[1]++;
			}
		}


		int[] trange = {0,0,0,0,0,0,0,0,0,0};
		int[] frange = {0,0,0,0,0,0,0,0,0,0};
		int[] thosanswer = {0,0,0,0,0,0,0,0,0,0};
		int[] fhosanswer = {0,0,0,0,0,0,0,0,0,0};
		int[] rangecheck= {0,0};

		for(int i=0;i<10;i++) {
			trange[i]=Math.abs((int)(neuronlist[i]-tanswerlist[i]*33339/1.6+hoseilist[i]));
			frange[i]=Math.abs((int)(neuronlist[i]-fanswerlist[i]*33339/1.6-hoseilist[i]));
			thosanswer[i]=(int)((neuronlist[i]+hoseilist[i])/33339*1.6);
			fhosanswer[i]=(int)((neuronlist[i]-hoseilist[i])/33339*1.6);
		}

		for(int i=0;i<10;i++) {
			if(trange[i]<=10000) {
				rangecheck[0]++;
			}if(frange[i]<=10000) {
				rangecheck[1]++;
			}
		}
		//kokomade
		//System.out.print("end");//debug you
		NeuReturn ret=new NeuReturn();
		ret.check=check;
		for (int i=0;i<10;i++) {
			ret.list[i]=(int)(neuronlist[i]/33339*1.6);
		}
		ret.hosei=hoseilist;
		ret.tanswer=tanswerlist;
		ret.fanswer=fanswerlist;
		ret.check0=check0;
		ret.trange=trange;
		ret.frange=frange;
		ret.thosans=thosanswer;
		ret.fhosans=fhosanswer;
		ret.rangecheck=rangecheck;
		return ret;
	}
	public static NeuReturn quickneuron(Queue2 line,int No,int[] list,int time) {
		String[] memorymap;
		//int[] sampleline={2,1,-10,6,0,-5,0,0,0,0};
		int[] sampleline={0,0,0,0,4,-3,6,8,1,5};
		sampleline=list;
		//mapread(memorymap);//koujichuu(1,10<-koko(state)wotuika hituyou ari)
		int[][] syglist=new int[270][10];
		int[][] inlist=new int[10][10];

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
		File fileans=new File("./quickanswerlist"+No+".txt");
		int[] tanswerlist=new int[10];int[] fanswerlist=new int[10];
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
		String[] abufstr=new String[2];
		abufstr=astr.split("/");
		String[] abuflist=new String[10];
		abuflist=abufstr[0].split(",");
		for(int i=0;i<10;i++) {
			tanswerlist[i]=Integer.valueOf(abuflist[i]);
		}
		abuflist=abufstr[1].split(",");
		for(int i=0;i<10;i++) {
			fanswerlist[i]=Integer.valueOf(abuflist[i]);
		}
		File filechk=new File("./quickcheck"+No+".txt");
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
		String[] checkstr=cstr.split(",");
		int[] check0 =new int[2];
		check0[0]=Integer.parseInt(checkstr[0]);check0[1]=Integer.parseInt(checkstr[1]);
		//Sum


		int[] hoseilist= {0,0,0,0,0,0,0,0,0,0};
		File hfile=new File("./quickhosei"+No+".txt");
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
		finhoslist= hosstr.split(",",0);
		for(int i=0;i<10;i++) {
			hoseilist[i]=Integer.parseInt(finhoslist[i]);
		}

		int[] trueapr= {0,0,0,0,0,0,0,0,0,0};

		File cfile=new File("./quickcelllist"+No+".txt");
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
		fincellist= celstr.split(",",0);
		for(int i=0;i<10;i++) {
			trueapr[i]=Integer.parseInt(fincellist[i]);
		}






		int[] inputline=sampleline;
		int[] inputlist= {0,0,0,0,0,0,0,0,0,0};
		for(int l=0;l<10;l++) {
			for(int i=0;i<10;i++) {
				inputlist[l]=(int)(inputlist[l]+inlist[i][l]*inputline[i]*9/1.8726/6);///2*10,20,30/3*30,0,10->20+90,inlist[start][end]
			}
		}
		int[] neuronlist= {0,0,0,0,0,0,0,0,0,0};
		for (int i=0;i<10;i++) {
			neuronlist[i]=trueapr[i]+inputlist[i];
		}

		//Check
		int[] check= {0,0};
		for(int i=0;i<10;i++) {
			if(10000>Math.abs((int)(neuronlist[i]-tanswerlist[i]*33339/1.6+hoseilist[i]))) {
				check[0]++;
			}if(10000>Math.abs((int)(neuronlist[i]-fanswerlist[i]*33339/1.6-hoseilist[i]))) {
				check[1]++;
			}
		}


		int[] trange = {0,0,0,0,0,0,0,0,0,0};
		int[] frange = {0,0,0,0,0,0,0,0,0,0};
		int[] thosanswer = {0,0,0,0,0,0,0,0,0,0};
		int[] fhosanswer = {0,0,0,0,0,0,0,0,0,0};
		int[] rangecheck= {0,0};

		for(int i=0;i<10;i++) {
			trange[i]=Math.abs((int)(neuronlist[i]-tanswerlist[i]*33339/1.6+hoseilist[i]));
			frange[i]=Math.abs((int)(neuronlist[i]-fanswerlist[i]*33339/1.6-hoseilist[i]));
			thosanswer[i]=(int)((neuronlist[i]+hoseilist[i])/33339*1.6);
			fhosanswer[i]=(int)((neuronlist[i]-hoseilist[i])/33339*1.6);
		}

		for(int i=0;i<10;i++) {
			if(trange[i]<=10000) {
				rangecheck[0]++;
			}if(frange[i]<=10000) {
				rangecheck[1]++;
			}
		}
		//kokomade
		//System.out.print("end");//debug you

		// jikan
		int nexttime=time+100;//jikan no keika wo kokode keisan suru
		// jikan end

		NeuReturn ret=new NeuReturn();
		ret.check=check;
		for (int i=0;i<10;i++) {
			ret.list[i]=(int)(neuronlist[i]/33339*1.6);
		}
		ret.hosei=hoseilist;
		ret.tanswer=tanswerlist;
		ret.fanswer=fanswerlist;
		ret.check0=check0;
		ret.trange=trange;
		ret.frange=frange;
		ret.thosans=thosanswer;
		ret.fhosans=fhosanswer;
		ret.rangecheck=rangecheck;
		ret.time=nexttime;
		return ret;
	}

	public static void memory(Queue line,int No,int[] intlist,int[] inflist,int[] outlist,int[] ouflist) {

		int j=0;
		int amount;int[] neuronAm=new int[10];int[] neuronSt=new int[10];///read_hanniwo_settei
		Brain0 main=new Brain0();
		//ModifyTools mod=main.new ModifyTools();
		///mod.split1(34,35);mod.split1(52, 53);mod.split1(45, 46);
		///mod.decrease(30,32,3,3);
		///mod.decrease(50, 52, 2, 4);///40ga_0ika->null atode_shuusei
		///mod.addition(42, 45, 7);mod.addition(43, 46, 8);
		///mod.increase(41, 43, 3, 4);
		///mod.change1(11, 13, 14);
		ArrayList<Integer> sygsta=new ArrayList<Integer>();
		ArrayList<Integer> sygamo=new ArrayList<Integer>();
		int[] over90=new int[10];int[] output= {0,0,0,0,0};
		boolean learned =false;
		int trys=5;
		int m=0;
		int mainlearned=0;
		int o=0;
		int trys2=0;
		int[][] syglist=new int[270][10];
		mapfilewrite();
		String[] mapread=fileRead();
		mapread(mapread);
		int[][] inlist=new int[10][10];
		///inlist=inlist();
		///syglist=syglist();
		File file=new File("./syglist"+No+".txt");
		if(file.exists()) {

		}else {
			File bigsygfile=new File("./syglist.txt");
			if(bigsygfile.exists()) {
				try {
					Brain0.copyFile(bigsygfile, file);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				syglist=syglist(No);
				String sygtmp=String.valueOf(syglist[0][0]);
				for(int i=0;i<10;i++) {
					sygtmp=sygtmp+","+syglist[0][i];
				}
				for(int i=1;i<270;i++) {
					sygtmp=sygtmp+"/";
					sygtmp=sygtmp+syglist[i][0];
					for(int jj=0;jj<10;jj++) {
						sygtmp=sygtmp+","+syglist[i][jj];
					}

				}
				try {
					File sygfile=new File("syglist"+No+".txt");
					FileWriter filewrite=new FileWriter(sygfile);
					filewrite.write(sygtmp);
					filewrite.close();
				}catch(IOException e) {
					System.out.println(e);
				}
			}

		}
		String[] finstrlist=new String[270];
				String finstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String str =br.readLine();
			while(str!=null) {
				finstr=finstr+str;
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
		finstrlist= finstr.split("/",0);
		for(int i=0;i<270;i++) {
			String[] tmplist=finstrlist[i].split(",",0);
			for(int k=0;k<10;k++) {
				syglist[i][k]=Integer.parseInt(tmplist[k]);
			}
		}

		File filein=new File("./inlist"+No+".txt");
		if(filein.exists()) {

		}else {
			File biginfile=new File("./inlist.txt");
			if(biginfile.exists()) {
				try {
					Brain0.copyFile(biginfile, filein);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				inlist=inlist();
				String intmp=String.valueOf(inlist[0][0]);
				for(int i=0;i<10;i++) {
					intmp=intmp+","+inlist[0][i];
				}
				for(int i=1;i<10;i++) {
					intmp=intmp+"/";
					intmp=intmp+inlist[i][0];
					for(int jj=1;jj<10;jj++) {
						intmp=intmp+","+inlist[i][jj];
					}

				}
				try {
					File infile=new File("inlist"+No+".txt");
					FileWriter filewrite=new FileWriter(infile);
					filewrite.write(intmp);
					filewrite.close();
				}catch(IOException e) {
					System.out.println(e);
				}
			}

		}

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
		trueline.amount.addAll(Arrays.asList(2,1,-10,6,0,-5,0,0,0,0));trueline.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		falseline.amount.addAll(Arrays.asList(0,0,0,0,4,-3,6,8,0,5));falseline.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

		trueline.amount.clear();falseline.amount.clear();
		for(int i=0;i<10;i++) {
			trueline.amount.add(intlist[i]);falseline.amount.add(inflist[i]);
		}
		Queue tmpline=main.new Queue();
		tmpline=trueline;
		int[] tmplist= {0,0,0,0,0,0,0,0,0,0};
		for(int i=0;i<10;i++) {
			for(int k=0;k<10;k++) {
				tmplist[k]=tmplist[k]+inlist[tmpline.state.get(i)][k]*tmpline.amount.get(i);

			}
		}
		Queue tmpline2=main.new Queue();
		tmpline2=falseline;
		int[] tmplist2= {0,0,0,0,0,0,0,0,0,0};
		for(int i=0;i<10;i++) {
			for(int k=0;k<10;k++) {
				tmplist2[k]=tmplist2[k]+inlist[tmpline2.state.get(i)][k]*tmpline2.amount.get(i);

			}
		}

		truelist=outlist;falselist=ouflist;
		truelist2=outlist;falselist2=ouflist;
		int approxi=1000000000;
		int checkmulti=0;
		int[] maplist=new int[270];
		Arrays.fill(maplist, 0);
		int[] tmpmap=new int[270];
		int[] prvmap=new int[270];
		int[] check0= {0,0};
		Hosei hlist=main.new Hosei();
		hlist=hosei(truelist2,tmplist,falselist2,tmplist2);
		truelist2=hlist.trueout;falselist2=hlist.falseout;//out wo hosei out ni kaeru
		for(int i=0;i<20000;i++) {
			for(int k=0;k<270;k++) {
				tmpmap[k]=(int)(21*Math.random()-10);
			}
			int tmpappro=0;

			for(int l=0;l<10;l++) {
				int trueapr=0;int falseapr=0;
				for (int k=0;k<270;k++) {
					trueapr=trueapr+syglist[k][l]*tmpmap[k];falseapr=falseapr+syglist[k][l]*tmpmap[k];//trueapr...-666780~666780(60%?)
				}
				tmpappro=Math.abs(truelist2[l]*33339-(int)(tmplist[l]*correction/1.8726)-trueapr)+Math.abs(falselist2[l]*33339-(int)(tmplist2[l]*correction/1.8726)-falseapr)+tmpappro;
				//trueapr=0;falseapr=0;///tigau... 666780/2/10 666780/178034.4(=197816/10)/2
				if(10000>Math.abs(truelist2[l]-(int)(tmplist[l]*correction/1.8726/6)-trueapr)) {//100017/1.6
					check0[0]++;
				}
				if(10000>Math.abs(falselist2[l]-(int)(tmplist2[l]*correction/1.8726/6)-falseapr)) {
					check0[1]++;
				}
			}
			if(checkmulti<=check0[0]*check0[1]) {
				checkmulti=check0[0]*check0[1];
				prvmap=tmpmap.clone();
			}
			Arrays.fill(check0, 0);
			tmpappro=0;
		}

int[] question=new int[20];
for (int i=0;i<270;i++) {
	for(m=0;m<10;m++) {
		question[m]=question[m]+prvmap[i]*syglist[i][m];
		question[m+10]=question[m+10]+prvmap[i]*syglist[i][m];
	}
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
int[]question7=new int[20];
for(int i=0;i<10;i++) {
	question7[i]=question[i]/33339;
	question7[i+10]=question[i+10]/33339;
}
int[] question3=new int[20];
for (int i=0;i<270;i++) {
	for(m=0;m<10;m++) {
		question3[m]=question3[m]+tmpmap[i]*syglist[i][m];
		question3[m+10]=question3[m+10]+tmpmap[i]*syglist[i][m];
	}
}
	for(int p=0;p<10;p++) {
		question3[p]=(int)(tmplist[p]*correction/1.8726)+question3[p];
		question3[p+10]=(int)(tmplist2[p]*correction/1.8726)+question3[p+10];
	}
	for(int p=0;p<20;p++) {
		question3[p]=question3[p];///tmp,kairo+nyuryoku
	}

int question4=0;
for(int i=0;i<10;i++) {
	question4=question4+Math.abs(truelist2[i]*36400-question3[i])+Math.abs(falselist2[i]*36400-question3[i+10]);///sabun
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
		writemapprv(prvmap,"mapmemory"+No+".txt");

		String answer="";
		answer=String.valueOf(truelist[0]);
		for(int i=1;i<10;i++) {
			answer=answer+","+String.valueOf(truelist[i]);
		}
		answer=answer+"/"+String.valueOf(falselist[0]);
		for(int i=1;i<10;i++) {
			answer=answer+","+String.valueOf(falselist[i]);
		}
		try {
			File ansfile=new File("answerlist"+No+".txt");
			FileWriter filewrite=new FileWriter(ansfile);
			filewrite.write(answer);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		try {
			File checkfile=new File("check"+No+".txt");
			FileWriter checkwrite =new FileWriter(checkfile);
			checkwrite.write(check[0]+","+check[1]);
			checkwrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}

		String hosei="";
		hosei=String.valueOf(hlist.hosei[0]);
		for(int i=1;i<10;i++) {
			hosei=hosei+","+String.valueOf(hlist.hosei[i]);
		}
		try {
			File hoseifile=new File("hosei"+No+".txt");
			FileWriter hoseiwrite =new FileWriter(hoseifile);
			hoseiwrite.write(hosei);
			hoseiwrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}


		int[] branch=new int[270];
		int[] trueapr= {0,0,0,0,0,0,0,0,0,0};
		for (int l=0;l<10;l++) {
			for (int k=0;k<90;k++) {
				branch=prvmap;
				trueapr[l]=trueapr[l]+syglist[3*k][l]*branch[3*k];//trueapr...-666780~666780(60%?)
				trueapr[l]=trueapr[l]+syglist[3*k+1][l]*branch[3*k+1];
				trueapr[l]=trueapr[l]+syglist[3*k+2][l]*branch[3*k+2];
			}

		}

		int[] inputline= {2,1,-10,6,0,-5,0,0,0,0};
		int[] inputlist= {0,0,0,0,0,0,0,0,0,0};
		for(int l=0;l<10;l++) {
			for(int i=0;i<10;i++) {
				inputlist[l]=inputlist[l]+(int)(inlist[i][l]*inputline[i]*9/1.8726);///2*10,20,30/3*30,0,10->20+90,inlist[start][end]
			}
		}
		int[] neuronlist= {0,0,0,0,0,0,0,0,0,0};
		for (int i=0;i<10;i++) {
			neuronlist[i]=trueapr[i]+inputlist[i];
		}

		//Check
		int[] check2= {0,0};
		for(int i=0;i<10;i++) {
			if(100017>Math.abs(neuronlist[i]-truelist2[i]*33339)) {
				check2[0]++;
			}if(100017>Math.abs(neuronlist[i]-falselist2[i]*33339)) {
				check2[1]++;
			}
		}





		///kokomade
approxi=100000000;
System.out.print("end");







	}public static void quickmemory(Queue line,int No,int[] intlist,int[] inflist,int[] outlist,int[] ouflist) {
		int j=0;
		int amount;int[] neuronAm=new int[10];int[] neuronSt=new int[10];///read_hanniwo_settei
		Brain0 main=new Brain0();
		//ModifyTools mod=main.new ModifyTools();
		///mod.split1(34,35);mod.split1(52, 53);mod.split1(45, 46);
		///mod.decrease(30,32,3,3);
		///mod.decrease(50, 52, 2, 4);///40ga_0ika->null atode_shuusei
		///mod.addition(42, 45, 7);mod.addition(43, 46, 8);
		///mod.increase(41, 43, 3, 4);
		///mod.change1(11, 13, 14);
		ArrayList<Integer> sygsta=new ArrayList<Integer>();
		ArrayList<Integer> sygamo=new ArrayList<Integer>();
		int[] over90=new int[10];int[] output= {0,0,0,0,0};
		boolean learned =false;
		int trys=5;
		int m=0;
		int mainlearned=0;
		int o=0;
		int trys2=0;
		int[][] syglist=new int[270][10];
		mapfilewrite();
		String[] mapread=fileRead();
		mapread(mapread);
		int[][] inlist=new int[10][10];
		///inlist=inlist();
		String[] finstrlist=new String[270];

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
		trueline.amount.addAll(Arrays.asList(2,1,-10,6,0,-5,0,0,0,0));trueline.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		falseline.amount.addAll(Arrays.asList(0,0,0,0,4,-3,6,8,0,5));falseline.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

		trueline.amount.clear();falseline.amount.clear();
		for(int i=0;i<10;i++) {
			trueline.amount.add(intlist[i]);falseline.amount.add(inflist[i]);
		}
		Queue tmpline=main.new Queue();
		tmpline=trueline;
		int[] tmplist= {0,0,0,0,0,0,0,0,0,0};
		for(int i=0;i<10;i++) {
			for(int k=0;k<10;k++) {
				tmplist[k]=tmplist[k]+inlist[tmpline.state.get(i)][k]*tmpline.amount.get(i);

			}
		}
		Queue tmpline2=main.new Queue();
		tmpline2=falseline;
		int[] tmplist2= {0,0,0,0,0,0,0,0,0,0};
		for(int i=0;i<10;i++) {
			for(int k=0;k<10;k++) {
				tmplist2[k]=tmplist2[k]+inlist[tmpline2.state.get(i)][k]*tmpline2.amount.get(i);

			}
		}

		truelist=outlist;falselist=ouflist;
		truelist2=outlist;falselist2=ouflist;

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
	for(m=0;m<10;m++) {
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

		String answer="";
		answer=String.valueOf(truelist[0]);
		for(int i=1;i<10;i++) {
			answer=answer+","+String.valueOf(truelist[i]);
		}
		answer=answer+"/"+String.valueOf(falselist[0]);
		for(int i=1;i<10;i++) {
			answer=answer+","+String.valueOf(falselist[i]);
		}
		try {
			File ansfile=new File("quickanswerlist"+No+".txt");
			FileWriter filewrite=new FileWriter(ansfile);
			filewrite.write(answer);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		try {
			File checkfile=new File("quickcheck"+No+".txt");
			FileWriter checkwrite =new FileWriter(checkfile);
			checkwrite.write(check[0]+","+check[1]);
			checkwrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}

		String hosei="";
		hosei=String.valueOf(hlist.hosei[0]);
		for(int i=1;i<10;i++) {
			hosei=hosei+","+String.valueOf(hlist.hosei[i]);
		}
		try {
			File hoseifile=new File("quickhosei"+No+".txt");
			FileWriter hoseiwrite =new FileWriter(hoseifile);
			hoseiwrite.write(hosei);
			hoseiwrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}

		String cell="";
		for(int i=1;i<10;i++) {
			cell=cell+","+String.valueOf(trueapr[i]);
		}
		try {
			File cellfile=new File("quickcelllist"+No+".txt");
			FileWriter filewrite=new FileWriter(cellfile);
			filewrite.write(cell);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		System.out.print("qend");



	}
	/**
	 * make syglist.txt
	 * @param No
	 * @return
	 */
	public static int[][] syglist(int No) {
		int[][] syglist=new int[270][10];
		for(int i=0;i<90;i++) {
			Brain0 main=new Brain0();
			Queue line2=main.new Queue();
			if(i>0) {
					line2.amount.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
					line2.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
					while(line2.amount.isEmpty()==false) {
					ReturnNeuro red2=read(line2.state.get(0));
					ReturnNeuro ret2 =keisan2(line2.amount.get(0),red2.amount,red2.state,i,i+9,line2.state.get(0));
					for(int k=0;k<ret2.state.length;k++) {
						if(ret2.state[k]>=90) {
							syglist[3*i][ret2.state[k]-90]=syglist[3*i][ret2.state[k]-90]+ret2.amount[k];
						}else {
							line2.amount.add(ret2.amount[k]);line2.state.add(ret2.state[k]);
						}
					}
					line2.amount.remove(0);line2.state.remove(0);
				}
			}
			line2.amount.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
			line2.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
			while(line2.amount.isEmpty()==false) {
				ReturnNeuro red2=read(line2.state.get(0));
				ReturnNeuro ret2 =keisan2(line2.amount.get(0),red2.amount,red2.state,i,i+10,line2.state.get(0));
				for(int k=0;k<ret2.state.length;k++) {
					if(ret2.state[k]>=90) {
						syglist[3*i+1][ret2.state[k]-90]=syglist[3*i+1][ret2.state[k]-90]+ret2.amount[k];
					}else {
						line2.amount.add(ret2.amount[k]);line2.state.add(ret2.state[k]);
					}
				}
				line2.amount.remove(0);line2.state.remove(0);
			}
			if(i<89) {
				line2.amount.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
				line2.state.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
				while(line2.amount.isEmpty()==false) {
					ReturnNeuro red2=read(line2.state.get(0));
					ReturnNeuro ret2 =keisan2(line2.amount.get(0),red2.amount,red2.state,i,i+11,line2.state.get(0));
					for(int k=0;k<ret2.state.length;k++) {
						if(ret2.state[k]>=90) {
							syglist[3*i+2][ret2.state[k]-90]=syglist[3*i+2][ret2.state[k]-90]+ret2.amount[k];
						}else {
							line2.amount.add(ret2.amount[k]);line2.state.add(ret2.state[k]);
						}
					}
					line2.amount.remove(0);line2.state.remove(0);
				}
			}
			System.out.print("end");
		}


		String writelist="";
		for(int i=0;i<270;i++) {
			for (int k=0;k<10;k++) {
				writelist=writelist+syglist[i][k]+",";
			}
			writelist=writelist.substring(0,writelist.length()-1);
			writelist=writelist+"/";
		}

		try {
			File file=new File("syglist"+No+".txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(writelist);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}


		return syglist;
	}
	/**
	 * make inlist.txt
	 * need map.txt(written by mapwrite.txt)
	 * @return
	 */
	public static int[][] inlist() {
		int[][] inlist=new int[10][10];
		for(int j=0;j<10;j++) {


			Brain0 main=new Brain0();
			Queue line2=main.new Queue();
			line2.amount.addAll(Arrays.asList(1));
			line2.state.addAll(Arrays.asList(j));
			while(line2.amount.isEmpty()==false) {
				ReturnNeuro red2=read(line2.state.get(0));
				ReturnNeuro ret2 =keisan3(line2.amount.get(0),red2.amount,red2.state);
				for(int k=0;k<ret2.state.length;k++) {
					if(ret2.state[k]>=90) {
						inlist[j][ret2.state[k]-90]=inlist[j][ret2.state[k]-90]+ret2.amount[k];
					}else {
						line2.amount.add(ret2.amount[k]);line2.state.add(ret2.state[k]);
					}
				}
				line2.amount.remove(0);line2.state.remove(0);
			}
		}


		String writelist="";
		for(int i=0;i<10;i++) {
			for (int k=0;k<10;k++) {
				writelist=writelist+inlist[i][k]+",";
			}
			writelist=writelist.substring(0,writelist.length()-1);
			writelist=writelist+"/";
		}

		try {
			File file=new File("inlist.txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(writelist);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}


		return inlist;
	}
	public static Hosei hosei(int[] trueout,int[] truein,int[] falseout,int[] falsein) {
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
	}public class Hosei{
		int[] hosei;
		int[] trueout;
		int[] falseout;
	}
	/**
	 * husiyou
	 * @param line
	 * @return
	 */
	public static RunReturn run(Queue line){
		int[] syglist2=new int[90];
		int[] pass2=new int[90];
		while (line.amount.isEmpty()==false) {
			int amount=line.amount.get(0);
			pass2[line.amount.get(0)]=1;
			ReturnNeuro red =read(line.state.get(0));//bunki no omomi
			int[] neuronAm=red.amount;
			int[] neuronSt=red.state;
			ReturnNeuro ret =keisan(amount,neuronAm,neuronSt);
			for (int i=0;i<ret.amount.length;i++) {
				if(ret.state[i]>=90) {
					syglist2[ret.state[i]-90]=syglist2[ret.state[i]-90]+ret.amount[i];
				}else {
					line.amount.add(ret.amount[i]);line.state.add(ret.state[i]);
				}
			}
///						System.out.print(line.state.get(0)+":"+line.amount.get(0)+";");
			line.amount.remove(0);
			line.state.remove(0);
		}
		Brain0 main=new Brain0();
		RunReturn rtn=main.new RunReturn();
		rtn.syglist=syglist2;
		rtn.pass=pass2;
		return rtn;

	}
	/**
	 * write map.txt(plane)
	 *
	 * 1->1/11,1/12
	 * 2->1/11,1/12,1/13
	 * 3->1/12,1/13,1/14
	 * ..
	 * 89->1/98,1/99
	 *
	 */
	public static void mapfilewrite() {
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
	/**
	 * map wo siyou(written by mapfilewrite())
	 * @param sta
	 * @return
	 */
	public static ReturnNeuro read(int sta){
		///		int[] neuronAm= {3,1,2,4};///
		///		int[] neuronSt= {23,24,25,26} ;

				String[] data=map.get(sta).split(",",0);
				int[] finAm=new int[data.length/2];
				int[] finSt=new int[data.length/2];
				for (int i=0;i<data.length/2;i++) {
					try {
						finAm[i]=Integer.parseInt(data[2*i]);
					}catch(NumberFormatException e) {
						finAm[i]=0;
					}
					finSt[i]=Integer.parseInt(data[2*i+1]);
				}
				Brain0 main=new Brain0();
				ReturnNeuro fin=main.new ReturnNeuro();

				fin.amount=finAm;
				fin.state=finSt;
				return fin;
		}
	/**
	 * hushiyou
	 * @param sta
	 * @return
	 */
	public static ReturnNeuro readneu(int sta){
			///		int[] neuronAm= {3,1,2,4};///
			///		int[] neuronSt= {23,24,25,26} ;

					String[] data=map.get(sta).split(",",0);
					int[] finAm=new int[data.length/2];
					int[] finSt=new int[data.length/2];
					for (int i=0;i<data.length/2;i++) {
						try {
							finAm[i]=Integer.parseInt(data[2*i]);
						}catch(NumberFormatException e) {
							finAm[i]=0;
						}
						finSt[i]=Integer.parseInt(data[2*i+1]);
					}
					Brain0 main=new Brain0();
					ReturnNeuro fin=main.new ReturnNeuro();

					fin.amount=finAm;
					fin.state=finSt;
					return fin;
			}
	/**
	 * husiyou
	 */
	public static void writemap() {//0 kara 99 made HashMap map wo yomikomi map file ni kinyuu
				String writemap;
				writemap=map.get(0);
				for(int i=1;i<=99;i++) {
					writemap=writemap+"/"+map.get(i);
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
			/**
			 * //minimum[10]=answert/f[10]-inlist[10]*inputt/f[10]-
			 * 			(syglist[270][10]*mapprv(random[270]))->celllist
			 * mapname tukawareterunoha mapmemory+No+.txt
			 * @param prvmap
			 * @param mapname
			 */
			public static void writemapprv(int[] prvmap,String mapname) {
				String writemap;
				writemap=String.valueOf(prvmap[0])+","+String.valueOf(prvmap[1])+","+String.valueOf(prvmap[2]);
				for(int i=1;i<=89;i++) {
					writemap=writemap+"/"+String.valueOf(prvmap[3*i])+","+String.valueOf(prvmap[3*i+1])+","+String.valueOf(prvmap[3*i+2]);
				}
				try {
					File file=new File(mapname);
					FileWriter filewrite=new FileWriter(file);
					filewrite.write(writemap);
					filewrite.close();
				}catch(IOException e) {
					System.out.println(e);
				}
			}
			/**
			 * amo 3 neuAm {3,5} neuSt {20,22} ->return .amount {3+3,5+3} .state {20,22}
			 * @param amo
			 * @param neuAm
			 * @param neuSt
			 * @return
			 */
			public static ReturnNeuro keisan(int amo,int[] neuAm,int[]neuSt){


				int[] newneuAm= new int[neuAm.length];
				int[] newneuSt=neuSt;
				///newneuAm[0]=neuAm[0]*0.11;
				for (int i=0 ;i<neuAm.length;i++) {
					newneuAm[i]=amo+neuAm[i];
				}

				int finneuAm[]=new int[newneuAm.length];
				int finneuSt[]=new int[newneuAm.length];
				for (int k=0;k<newneuAm.length;k++) {
					finneuAm[k]=newneuAm[k];
					finneuSt[k]=newneuSt[k];
				}
				Brain0 main=new Brain0();
				ReturnNeuro value =main.new ReturnNeuro();
				value.amount=finneuAm;
				value.state=finneuSt;
				return value;
			}
			public static ReturnNeuroneu keisanneu(double amo,int[] neuAm,int[]neuSt){


				double[] newneuAm= new double[neuAm.length];
				int[] newneuSt=neuSt;
				///newneuAm[0]=neuAm[0]*0.11;
				for (int i=0 ;i<neuAm.length;i++) {
					newneuAm[i]=amo+neuAm[i];
				}

				double finneuAm[]=new double[newneuAm.length];
				int finneuSt[]=new int[newneuAm.length];
				for (int k=0;k<newneuAm.length;k++) {
					finneuAm[k]=newneuAm[k];
					finneuSt[k]=newneuSt[k];
				}
				Brain0 main=new Brain0();
				ReturnNeuroneu value =main.new ReturnNeuroneu();
				value.amount=finneuAm;
				value.state=finneuSt;
				return value;
			}

			public static ReturnNeuro keisan2(int amo,int[] neuAm,int[]neuSt,int start,int end,int state){

				int[] newneuAm= new int[neuAm.length];
				int[] newneuSt=neuSt;
				///newneuAm[0]=neuAm[0]*0.11;
				if(start==state) {
					for(int i=0;i<newneuSt.length;i++) {
						if(newneuSt[i]==end) {
							newneuAm[i]=amo+1;
						}else {
							newneuAm[i]=amo;
						}
					}
				}else {
					for (int i=0 ;i<neuAm.length;i++) {
						newneuAm[i]=amo;

					}
				}
				int finneuAm[]=new int[newneuAm.length];
				int finneuSt[]=new int[newneuAm.length];
				for (int k=0;k<newneuAm.length;k++) {
					finneuAm[k]=newneuAm[k];
					finneuSt[k]=newneuSt[k];
				}
				Brain0 main=new Brain0();
				ReturnNeuro value =main.new ReturnNeuro();
				value.amount=finneuAm;
				value.state=finneuSt;
				return value;

			}
			public static ReturnNeuro keisan3(int amo,int[] neuAm,int[]neuSt){

				int[] newneuAm= new int[neuAm.length];
				int[] newneuSt=neuSt;
				///newneuAm[0]=neuAm[0]*0.11;
				for(int i=0;i<newneuSt.length;i++) {

					newneuAm[i]=amo;

				}
				int finneuAm[]=new int[newneuAm.length];
				int finneuSt[]=new int[newneuAm.length];
				for (int k=0;k<newneuAm.length;k++) {
					finneuAm[k]=newneuAm[k];
					finneuSt[k]=newneuSt[k];
				}
				Brain0 main=new Brain0();
				ReturnNeuro value =main.new ReturnNeuro();
				value.amount=finneuAm;
				value.state=finneuSt;
				return value;

			}

			public static int random(int... neu){
				int d=(int)(Math.random()*neu.length);///要素数7の場合、0〜6の乱数ができる。

				int fin=neu[d];
				return fin;
			}
			public static int[] stacheck(int... sta) {
				ArrayList<Integer> list=new ArrayList<Integer>();
				for(int i=0;i<sta.length;i++) {
					if((sta[i]>=0)&&(sta[i]<=99)) {
						list.add(sta[i]);
					}
				}
				int[] finlist =new int[list.size()];
				for(int i=0;i<list.size();i++) {
					finlist[i]=list.get(i);
				}
				return finlist;
			}	public class ReturnNeuro{
				public int[] amount;
				public int[] state;
			}	public class ReturnNeuroneu{
				public double[] amount;
				public int[] state;
			}
			public class Queue{
				public ArrayList<Integer> amount=new ArrayList<Integer>();
				public ArrayList<Integer> state=new ArrayList<Integer>();
			}public class Queue2{
				public ArrayList<Double> amount=new ArrayList<Double>();
				public ArrayList<Integer> state=new ArrayList<Integer>();
			}
			public class RunReturn{
				public int[] syglist=new int[10];
				public int[] pass=new int[90];
			}public static class NeuReturn{
				public int[] check=new int[2];
				public int[] list=new int[10];
				public int[] tanswer=new int[10];
				public int[] fanswer=new int[10];
				public int[] check0=new int[2];
				public int[] hosei=new int[10];
				public int[] trange=new int[10];
				public int[] frange=new int[10];
				public int[] thosans=new int[10];
				public int[] fhosans=new int[10];
				public int[] rangecheck=new int[2];
				public int time;
				public double amount;
			}
			/**
			 * sakini fileRead() wo tukau
			 * @param mapper
			 */
			public static void mapread(String[] mapper) {
				for (int i=0;i<mapper.length;i++) {
					map.put(i, mapper[i]);
				}
			}
			public static String[] fileRead() {
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
			public static String[] memoryRead(String mapname) {
				File file=new File("./"+mapname);

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

			}public static void copyFile(File in,File out)throws Exception {
				BufferedReader br=new BufferedReader(new FileReader(in));
				FileWriter filewrite=new FileWriter(out);
						try {
							String str =br.readLine();
							String finstr="";
							while(str!=null) {
								finstr=finstr+str;
								str=br.readLine();
							}///null_ni_nattara_yomikomi_owaru_saigono_str_ha_kanarazu_null
							br.close();
							filewrite.write(finstr);
							filewrite.close();
						}catch(Exception e){
							throw e;
						}
						finally {
							if(br!=null) br.close();
							if(filewrite!=null) filewrite.close();
						}

			}




}
/*public class main0 {
	public static HashMap<Integer,String> map =new HashMap<Integer,String>();
	private static int[][] keisanyou = {{0,0,0,0,0,1,1,2,2,3,3,3,4,4,4,4,5,5,5,5},
			{0,0,0,1,1,2,2,2,3,3,3,3,4,4,4,4,5,5,5,6,6},
	{0,0,1,1,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6},
	{1,1,2,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,7,7},
	{1,2,2,3,3,4,4,4,5,5,5,5,6,6,6,6,7,7,8,8},
	{2,2,3,3,4,5,5,5,6,6,6,7,7,7,8,8,9,9,10,10},
	{2,3,3,4,5,5,6,6,7,7,7,8,8,9,9,10,11,11,12,12},
	{3,3,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13},
	{3,4,4,5,6,6,7,8,8,9,9,10,10,11,11,12,13,14,14,15},
	{3,4,5,6,6,7,8,9,9,10,10,11,12,12,13,14,14,15,15,16},
	{3,4,5,6,7,8,8,9,10,11,11,12,12,13,14,15,15,15,16,17},
	{3,4,5,6,7,8,9,10,11,12,12,13,13,14,15,15,16,16,17,17},
	{4,5,6,7,8,9,10,11,12,13,13,14,14,15,15,16,16,17,17,18},
	{4,5,6,7,8,10,11,12,13,14,14,15,15,16,16,17,17,17,18,18},
	{4,5,7,8,9,11,12,13,14,15,15,16,16,16,17,17,18,18,19,19},
	{4,5,7,8,10,11,12,14,15,16,16,17,17,17,18,18,18,19,19,19},
	{4,5,7,9,10,11,12,14,15,17,17,17,17,18,18,18,19,19,19,20},
	{5,6,8,9,10,11,13,14,16,17,17,17,18,18,18,19,19,19,20,20},
	{5,7,8,10,11,13,14,16,17,18,18,18,18,19,19,19,19,20,20,20},
	{5,7,9,10,11,13,15,16,17,18,18,18,19,19,19,20,20,20,20,20}};

	public static void main(String[] args) {

		int a = 0;
		///ここでスタートの位置を読み取る。
		int start=1;
	///保存された信号の番号


		String mapper0 ="10,2/10,3/10,4/10,5/10,6/10,7/10,8/10,9/10,10/10,11/10,12/10,13/10,14/10,15/10,16/10,17/10,18/10,19/10,20/10,21/10,22/10,23/10,24/10,25/10,26/10,27/10,28/10,29/10,30/10,31/10,32/";
		String[] mapper=mapper0.split("/",0);

		mapper=fileRead();
		mapread(mapper);///KAIRO HAIRETUWO YOMIKOMU

		///map.put(22,"3,23,1,24,2,25,4,26");
		int amount=7;///信号の強さ
		int[] neuronAm= {3,12,2,4};///
		int[] neuronSt= {23,24,25,26} ;

		main main=new main();
		ReturnNeuro sam=main.new ReturnNeuro();
		sam=keisan(23,neuronAm,neuronSt);
		Queue line=main.new Queue();
		line.amount.add(10);
		line.state.add(0);
		///ModifyTools mod1=main.new ModifyTools();
		///mod1.addition(2, 5, 13);
		///mod1.change1(2,3,6);

		///neuron(line);
		memory(line);
		///plasticity();
		int deletestate=22;
		int deletedest=24;
		ModifyTools mod =main.new ModifyTools();
		mod.delete1(deletestate,deletedest);

	}public static void neuron(Queue line) {

		int j=0;
		int amount;int[] neuronAm=new int[10];int[] neuronSt=new int[10];///read_hanniwo_settei
		int m=0;
		while(m<=5) {
			while ((line.amount.isEmpty()==false)&&(j<100)) {
				amount=line.amount.get(0);
				ReturnNeuro red =read(line.state.get(0));
				neuronAm=red.amount;
				neuronSt=red.state;
				if (((int)line.state.get(0)>90)&&((int)line.state.get(0)<=99)) {
					System.out.print("Sygnal :"+line.amount.get(0)+" State :"+line.state.get(0)+"/");
				}
				ReturnNeuro ret =keisan(amount,neuronAm,neuronSt);
				for (int i=0;i<ret.amount.length;i++) {
					line.amount.add(ret.amount[i]);
					line.state.add(ret.state[i]);
				}
				line.amount.remove(0);
				line.state.remove(0);
				System.out.print(amount);
				if(ret.amount.length>=2) {
					System.out.print(":"+ret.amount[0]);
				}
				for (int i=1;i<ret.amount.length;i++) {
					System.out.print(","+ret.amount[i]);

				}
				System.out.print("/");

				j++;
			}
			int[] linamo=new int[line.state.size()];int[] linsta=new int[line.amount.size()];
			for (int i=0;i<line.amount.size();i++) {
				linamo[i]=line.amount.get(i);
			}
			for (int i=0;i<line.state.size();i++) {
				linsta[i]=line.state.get(i);
			}
			HashMap<Integer,Integer> linhas=new HashMap<Integer,Integer>();
			for(int i=0;i<linamo.length;i++) {
				if(linhas.get(linsta[i])==null) {
					linhas.put(linsta[i],0);
				}
				linhas.put(linsta[i], linhas.get(linsta[i])+linamo[i]);///shuuryoujino_sygnal_list
			}
			line.state.clear();line.amount.clear();
			for (int keys :linhas.keySet()) {
				line.state.add(keys);
				line.amount.add(linhas.get(keys));
			};
			j=0;
			m++;
		}
		System.out.print(line.state.get(0));

	}public static void memory(Queue line) {

		int j=0;
		int amount;int[] neuronAm=new int[10];int[] neuronSt=new int[10];///read_hanniwo_settei
		main main=new main();
		ModifyTools mod=main.new ModifyTools();
		///mod.split1(34,35);mod.split1(52, 53);mod.split1(45, 46);
		///mod.decrease(30,32,3,3);
		///mod.decrease(50, 52, 2, 4);///40ga_0ika->null atode_shuusei
		///mod.addition(42, 45, 7);mod.addition(43, 46, 8);
		///mod.increase(41, 43, 3, 4);
		///mod.change1(11, 13, 14);
		ArrayList<Integer> sygsta=new ArrayList<Integer>();
		ArrayList<Integer> sygamo=new ArrayList<Integer>();
		int[] over90=new int[10];int[] output= {0,0,0,0,0};
		boolean learned =false;
		int trys=5;
		int m=0;
		int mainlearned=0;
		int o=0;
		int trys2=0;
		while((mainlearned<=5)&&(o<=10)) {///10kaino_aidani_dekinakattara_yameru
			trys2=0;
			if(learned==false) {
				String[] mapper2=fileRead();
				mapread(mapper2);///KAIRO HAIRETUWO YOMIKOMU
			}
			learned=false;
			while(trys2<=20) {
				learned=false;
				ArrayList<Integer> outlist=new ArrayList<Integer>();
				line.amount.clear();line.state.clear();
				line.amount.addAll(Arrays.asList(10));
				line.state.addAll(Arrays.asList(0));///shoki_singou_ireru
				while((learned==false)&&(m<=trys)) {///5(=trys)kaimade_sikousuru
					while ((line.amount.isEmpty()==false)&&(j<100)) {
						amount=line.amount.get(0);
						ReturnNeuro red =read(line.state.get(0));
						neuronAm=red.amount;
						neuronSt=red.state;
						///try {
							if (((int)line.state.get(0)>=90)&&((int)line.state.get(0)<=99)) {
								System.out.print("Sygnal :"+line.amount.get(0)+" State :"+line.state.get(0)+"/");
								int[] where= {-1,0};
								switch(line.state.get(0)) {
								case 90:
									output[0]=output[0]+line.amount.get(0);
									where[0]=0;where[1]=output[0];break;

								case 91:
									output[0]=output[0]+line.amount.get(0);
									where[0]=0;where[1]=output[0];break;
								case 92:
									output[1]=output[1]+line.amount.get(0);
									where[0]=1;where[1]=output[1];break;
								case 93:
									output[1]=output[1]+line.amount.get(0);
									where[0]=1;where[1]=output[1];break;
								case 94:
									output[2]=output[2]+line.amount.get(0);
									where[0]=2;where[1]=output[2];break;
								case 95:
									output[2]=output[2]+line.amount.get(0);
									where[0]=2;where[1]=output[2];break;
								case 96:
									output[3]=output[3]+line.amount.get(0);
									where[0]=2;where[1]=output[2];break;
								case 97:
									output[3]=output[3]+line.amount.get(0);
									where[0]=3;where[1]=output[3];break;
								case 98:
									output[4]=output[4]+line.amount.get(0);
									where[0]=4;where[1]=output[4];break;
								case 99:
									output[4]=output[4]+line.amount.get(0);
									where[0]=4;where[1]=output[4];break;
								default :
								}
								if(where[1]>20) {
									printoutput(where[1],where[0]);
									learned=true;
									outlist.add(where[0]);outlist.add(where[1]);
									Arrays.fill(output, 0);
								}
				///				throw new to90over();
							}
					///	}catch(to90over e) {
					///		over90[line.state.get(0)-90]=over90[line.state.get(0)-90]+line.amount.get(0);
					///	}
						ReturnNeuro ret =keisan(amount,neuronAm,neuronSt);
						for (int i=0;i<ret.amount.length;i++) {
							line.amount.add(ret.amount[i]);
							line.state.add(ret.state[i]);
						}
///						System.out.print(line.state.get(0)+":"+line.amount.get(0)+";");
						sygsta.add(line.state.get(0));sygamo.add(line.amount.get(0));
						line.amount.remove(0);
						line.state.remove(0);

						try {
///							System.out.print(ret.amount[0]);
							for (int i=1;i<ret.amount.length;i++) {
///								System.out.print(","+ret.amount[i]);

							}
						}catch(ArrayIndexOutOfBoundsException e) {
							System.out.print(0);
						}
						System.out.print("/");

						j++;
					}
					System.out.print("/"+m+"try/");
					try {
					for(int i=0;i<10;i++) {
						System.out.print(outlist.get(i)+",");
					}
					}catch(IndexOutOfBoundsException e) {

					}
					System.out.println("/");
					try {
						for(int i=0;i<over90.length;i++) {
							if(over90[i]>=5) {
								throw new sygnalReached();
							}
						}
					}catch(sygnalReached e) {
						learned=true;
					}
					int[] linamo=new int[line.state.size()];int[] linsta=new int[line.amount.size()];
					for (int i=0;i<line.amount.size();i++) {
						linamo[i]=line.amount.get(i);
					}
					for (int i=0;i<line.state.size();i++) {
						linsta[i]=line.state.get(i);
					}
					HashMap<Integer,Integer> linhas=new HashMap<Integer,Integer>();
					for(int i=0;i<linamo.length;i++) {
						if(linhas.get(linsta[i])==null) {
							linhas.put(linsta[i],0);
						}
						linhas.put(linsta[i], linhas.get(linsta[i])+linamo[i]);///shuuryoujino_sygnal_list
					}
					line.state.clear();line.amount.clear();
					for (int keys :linhas.keySet()) {
						line.state.add(keys);
						line.amount.add(linhas.get(keys));
					};
					j=0;
					m++;
				}
				System.out.println("try ended"+learned+"/");
				try {
					System.out.print(line.state.get(0));
				}catch(IndexOutOfBoundsException e) {
					System.out.print("Empty");
				}
				int[] syghas=new int[100];
				Arrays.fill(syghas, 0);
				for(int i=0;i<sygamo.size();i++) {
					syghas[sygsta.get(i)]= syghas[sygsta.get(i)]+sygamo.get(i);///kairo_zentaino_sygnal_no_list
				}
				sygsta.clear();sygamo.clear();
				boolean success=false;
				for(int i=0;i<10;i++) {
					if (over90[i]>=5) {
						System.out.print("Sygnal.run: "+over90[i]+"State: 9"+i+"writed/");
						writemap();
						success=true;
					}
				}
				if(success==false) {///umaku_ikanakatta_baai
					wrong(syghas,m);
				}else {
					mainlearned++;
					right(syghas,m);
				}
				Arrays.fill(over90, 0);
				m=0;

			trys2++;
			}
			o++;
		}
	}
	public static void plasticity(){
		String[] plsmapper=fileRead();
		mapread(plsmapper);///KAIRO HAIRETUWO YOMIKOMU
		main main=new main();
		ModifyTools mod=main.new ModifyTools();
		int[] keys=new int[map.keySet().size()];
		for (int i=0;i<map.keySet().size();i++) {
			keys[i]=i;
		}

		for (int i=0;i<10;i++) {
			int minus =random(keys);
			ReturnNeuro red=main.new ReturnNeuro();
			red=read(minus);
			if(red.state.length>1) {

				int minussta=random(red.state);
				mod.decrease(minus,minussta,1,10);
			}
		}
		for (int i=0;i<10;i++) {
			int plus =random(keys);
			ReturnNeuro red=main.new ReturnNeuro();
			red=read(plus);
			if(red.state.length>1) {
				int plussta=random(red.state);
				mod.increase(plus,plussta,1,10);
			}
		}
		for (int i=0;i<10;i++) {
			int delete =random(keys);
			ReturnNeuro red=main.new ReturnNeuro();
			red=read(delete);
			if(red.state.length>1) {
				int deletesta=random(red.state);
				mod.delete1(delete,deletesta);
			}
		}
		for (int i=0;i<10;i++) {
			int addition =random(keys);
			mod.addition(addition,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),5);
		}
		for (int i=0;i<map.keySet().size();i++) {
			if(map.get(i).equals("null")) {
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),3);
			}
			ReturnNeuro zerokill=main.new ReturnNeuro();
			zerokill=read(i);
			ArrayList<Integer> killedst=new ArrayList<Integer>();ArrayList<Integer> killedam=new ArrayList<Integer>();
			for(int j=0;j<zerokill.state.length;j++) {
				if(zerokill.amount[j]==0) {

				}else if(zerokill.state[j]==i) {


				}else {
					killedst.add(zerokill.state[j]);
					killedam.add(zerokill.amount[j]);
				}
			}
			String str="";
			try {
				str=killedam.get(0)+","+killedst.get(0);
			}catch(IndexOutOfBoundsException e) {
				str="null";
			}
			for(int j=1;j<killedst.size();j++) {
				str=str+","+killedam.get(j)+","+killedst.get(j);
			}
			map.put(i, str);
		}
		writemap();
	}
	public static void wrong(int[] syghas,int m){

		main main=new main();
		ModifyTools mod=main.new ModifyTools();
		for(int i=0;i<100;i++) {
			if(syghas[i]>100*m) {///20ijouno_neuronwo_kaku3_herasite_max_state_wo_split_suru
				mod.decrease(i,3);
				///ReturnNeuro redch =main.new ReturnNeuro();///random_de_2herasu
				///redch=read(i);
				///int max=redch.amount[0];
				///for (int k=1;k<redch.state.length;k++) {
				///	max=Math.max(max,redch.amount[k]);
				///}for(int k=0;k<redch.state.length;k++) {
		///			if(redch.amount[k]==max) {
			///			mod.split1(i,redch.state[k]);
				///	}
		///		}
				int p=0;
				while(p<=1) {

					ReturnNeuro redch =main.new ReturnNeuro();
					redch=read(i);
					if(redch.state.length<=2) {
						p++;
						continue;
					}
					int delsta=random(redch.state);
					mod.delete1(i, delsta);
					p++;
				}

			}else if((syghas[i]>1)&&(syghas[i]<=5*m)) {
				mod.increase(i,3);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),14);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),14);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),14);


			}else if((syghas[i]>5*m)&&(syghas[i]<=20*m)) {
				mod.increase(i,2);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),12);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),12);

			}else if((syghas[i]>20*m)&&(syghas[i]<=50*m)) {
				mod.decrease(i, 1);
				int p=0;
				while(p<=0) {

					ReturnNeuro redch =main.new ReturnNeuro();
					redch=read(i);
					if(redch.state.length<=4) {
						p++;
						continue;
					}
					int delsta=random(redch.state);
					mod.delete1(i, delsta);
					p++;
				}
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),10);
				ReturnNeuro redcng=main.new ReturnNeuro();
				redcng=read(i);
				mod.change1(i,random(redcng.state),random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)));

			}else if((syghas[i]>50*m)&&(syghas[i]<=100*m)) {
				mod.increase(i, 2);
				int p=0;
				while(p<=1) {

					ReturnNeuro redch =main.new ReturnNeuro();
					redch=read(i);
					if(redch.state.length<=4) {
						p++;
						continue;
					}
					int delsta=random(redch.state);
					mod.delete1(i, delsta);
					p++;
				}

			}
		}
		int[] sortsyghas=syghas;///ookiihoukara_junnni_naraberu
		int[] sortmax=new int[syghas.length];
		for(int i=0;i<syghas.length;i++) {
			sortmax[i]=i;
		}
		int tmp;int tmp2;
		for(int i=0;i<sortsyghas.length-1;i++) {
			for (int k=0;k<sortsyghas.length-1-i;k++) {
				if(sortsyghas[k]<sortsyghas[k+1]) {
					tmp=sortsyghas[k];
					sortsyghas[k]=sortsyghas[k+1];
					sortsyghas[k+1]=tmp;
					tmp2=sortmax[k];
					sortmax[k]=sortmax[k+1];
					sortmax[k+1]=tmp2;///sortmax->state,sortsyghas->amount;
				}
			}
		}
		int[] top10=Arrays.copyOfRange(sortmax, 0, 10);
		for(int i=0;i<9;i++) {
			for(int k=0;k<=9;k++) {
				if(i!=k) {
					mod.decrease(top10[i], top10[k], 1, 5);
				}
			}
		}


	}public static void right(int[] syghas,int m) {

		main main=new main();
		ModifyTools mod=main.new ModifyTools();
		for(int i=0;i<100;i++) {
			if(syghas[i]>100*m) {///20ijouno_neuronwo_kaku3_herasite_max_state_wo_split_suru
				mod.decrease(i,3);
				int p=0;
				while(p<=1) {

					ReturnNeuro redch =main.new ReturnNeuro();
					redch=read(i);
					if(redch.state.length<=2) {
						p++;
						continue;
					}
		///			int max=redch.amount[0];
		///			for (int k=1;k<redch.state.length;k++) {
		///				max=Math.max(max,	redch.amount[k]);
		///			}for(int k=0;k<redch.state.length;k++) {
		///				if(redch.amount[k]==max) {
		///					mod.split1(i,redch.state[k]);
		///				}
		///			}
					int delsta=random(redch.state);
					mod.delete1(i, delsta);
					p++;
				}
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),10);

			}else if((syghas[i]>1)&&(syghas[i]<=5*m)) {
				mod.increase(i, 2);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),12);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),12);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),12);

			}else if((syghas[i]>5*m)&&(syghas[i]<=20*m)) {
				mod.decrease(i, 2);
				int p=0;
				while(p<=1) {

					ReturnNeuro redch =main.new ReturnNeuro();
					redch=read(i);
					if(redch.state.length<=2) {
						p++;
						continue;
					}
					int delsta=random(redch.state);
					mod.delete1(i, delsta);
					p++;
				}

			}else if((syghas[i]>20*m)&&(syghas[i]<=50*m)) {
				mod.increase(i,3);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),3);

			}else if((syghas[i]>50*m)&&(syghas[i]<=100*m)) {
				mod.increase(i, 2);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),7);
				mod.addition(i,random(stacheck(i-11,i-10,i-9,i-1,i+1,i+9,i+10,i+11)),7);

			}
		}
		int[] sortsyghas=syghas;///ookiihoukara_junnni_naraberu
		int[] sortmax=new int[syghas.length];
		for(int i=0;i<syghas.length;i++) {
			sortmax[i]=i;
		}
		int tmp;int tmp2;
		for(int i=0;i<sortsyghas.length-1;i++) {
			for (int k=0;k<sortsyghas.length-1-i;k++) {
				if(sortsyghas[k]<sortsyghas[k+1]) {
					tmp=sortsyghas[k];
					sortsyghas[k]=sortsyghas[k+1];
					sortsyghas[k+1]=tmp;
					tmp2=sortmax[k];
					sortmax[k]=sortmax[k+1];
					sortmax[k+1]=tmp2;///sortmax->state,sortsyghas->amount;
				}
			}
		}
		int[] top10=Arrays.copyOfRange(sortmax, 0, 10);
		for(int i=0;i<=4;i++) {
			mod.addition(random(top10),random(top10),10);
		}

	}public static void printoutput(int output,int number) {
		System.out.print("amount: "+output+",state: "+number+"/");
	}
	public class ReturnNeuro{
		public int[] amount;
		public int[] state;
	}
	public class Queue{
		public ArrayList<Integer> amount=new ArrayList<Integer>();
		public ArrayList<Integer> state=new ArrayList<Integer>();
	}
	public static void mapread(String[] mapper) {
		for (int i=0;i<mapper.length;i++) {
			map.put(i, mapper[i]);
		}
	}
	public static String[] fileRead() {
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

	public static ReturnNeuro read(int sta){
///		int[] neuronAm= {3,1,2,4};///
///		int[] neuronSt= {23,24,25,26} ;

		String[] data=map.get(sta).split(",",0);
		int[] finAm=new int[data.length/2];
		int[] finSt=new int[data.length/2];
		for (int i=0;i<data.length/2;i++) {
			try {
				finAm[i]=Integer.parseInt(data[2*i]);
			}catch(NumberFormatException e) {
				finAm[i]=0;
			}
			finSt[i]=Integer.parseInt(data[2*i+1]);
		}
		main main=new main();
		ReturnNeuro fin=main.new ReturnNeuro();
		HashMap<Integer,Integer> finList=new HashMap<Integer,Integer>();
		ArrayList<Integer> keyList=new ArrayList<Integer>();
		for(int i=0;i<finSt.length;i++) {
			if(finAm[i]!=0) {
				finList.put(finSt[i], finAm[i]);
				keyList.add(finSt[i]);
			}
		}
		int[] finstate=new int[finList.size()];int[] finamount=new int[finList.size()];
		for(int i=0;i<finList.size();i++) {
			finamount[i]=finList.get(keyList.get(i));
			finstate[i]=keyList.get(i);
		}
		fin.amount=finamount;
		fin.state=finstate;
		return fin;
	}public static void writemap() {
		String writemap;
		writemap=map.get(0);
		for(int i=1;i<=99;i++) {
			writemap=writemap+"/"+map.get(i);
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

	public static ReturnNeuro keisan(int amo,int[] neuAm,int[]neuSt){

		double[] newneuAm= new double[neuAm.length];
		int[] newneuSt=neuSt;
		///newneuAm[0]=neuAm[0]*0.11;
		for (int i=0 ;i<neuAm.length;i++) {
			if(amo<=20) {

///				if(neuAm[i]<0) {
	///				newneuAm[i]=0;
	//			}else if(neuAm[i]<10) {
	//				newneuAm[i]=neuAm[i]*0.11*amo+0.5;
	//			}else if(neuAm[i]<=20) {
///					newneuAm[i]=amo+(20-amo)*(neuAm[i]-9)/10;
	//			}else {
//					newneuAm[i]=1000-980*Math.pow(Math.E, (20-neuAm[i]*amo/10)/980);
//				}
				if(neuAm[i]==0) {
					newneuAm[i]=0;
				}else {
					newneuAm[i]=keisanyou[amo-1][neuAm[i]-1];
				}
			}else if(amo>20) {
				newneuAm[i]=1000-980*Math.pow(Math.E, (20-neuAm[i]*amo/10)/980);
			}

///			if (newneuAm[i]>10) {
///				newneuAm[i]=10;
///			}else if (newneuAm[i]<1) {///amount*neuAmountが1未満の場合0をつけて後で消す。
///				newneuAm[i]=0;
///			}
		}
		int[] midneuAm= new int[newneuAm.length];
		int[] midneuSt= new int[newneuSt.length];
		int j=0;
		for (int i=0;i<newneuAm.length;i++) {
			if (newneuAm[i]>0) {
				midneuAm[j]=(int)newneuAm[i];
				midneuSt[j]=newneuSt[j];
				j++;
			}
		}
		int finneuAm[]=new int[j];
		int finneuSt[]=new int[j];
		for (int k=0;k<j;k++) {
			finneuAm[k]=midneuAm[k];
			finneuSt[k]=midneuSt[k];
		}
		main main=new main();
		ReturnNeuro value =main.new ReturnNeuro();
		value.amount=finneuAm;
		value.state=finneuSt;
		return value;

	}
	public static int random(int... neu){
		int d=(int)(Math.random()*neu.length);///要素数7の場合、0〜6の乱数ができる。

		int fin=neu[d];
		return fin;
	}
	public static int[] stacheck(int... sta) {
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<sta.length;i++) {
			if((sta[i]>=0)&&(sta[i]<=99)) {
				list.add(sta[i]);
			}
		}
		int[] finlist =new int[list.size()];
		for(int i=0;i<list.size();i++) {
			finlist[i]=list.get(i);
		}
		return finlist;
	}

	public class ModifyTools{
		public void delete1(int delneu,int deldst){
			String[] info=map.get(delneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			ArrayList<Integer> list= new ArrayList<Integer>();
			for (int i=0;i<info.length/2;i++) {
				infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
				if(Integer.parseInt(info[2*i+1])!=deldst) {
					list.add(Integer.parseInt(info[2*i+1]));
				}
			}
			infos.remove(deldst);
			String str="";
			try {
				str=infos.get(list.get(0))+","+list.get(0);///0の場合を別記
			}catch(IndexOutOfBoundsException e) {
				str="null";
			}
			for (int i=1;i<list.size();i++) {
				str=str +","+infos.get(list.get(i))+","+list.get(i);///逆。stateとamount
			};
			map.put(delneu,str);
		}
		public void increase1(int incneu,int incdst) {
			String[] info=map.get(incneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			int[] list= new int[info.length/2];
			for (int i=0;i<info.length/2;i++) {
				infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
				list[i]=Integer.parseInt(info[2*i+1]);
			}
			infos.put(incdst,infos.get(incdst)+1);
			String str=infos.get(list[0])+","+list[0];///0の場合を別記
			for (int i=1;i<list.length;i++) {
				str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
			};
			map.put(incneu,str);
		}
		public void increaselot(int incneu,int incdst,int amount) {
			String[] info=map.get(incneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			int[] list= new int[info.length/2];
			for (int i=0;i<info.length/2;i++) {
				infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
				list[i]=Integer.parseInt(info[2*i+1]);
			}
			if (infos.get(incdst)+amount>=21){
				infos.put(incdst,20);///10seigen
			}else {
				infos.put(incdst,infos.get(incdst)+amount);
			}
			String str=infos.get(list[0])+","+list[0];///0の場合を別記
			for (int i=1;i<list.length;i++) {
				str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
			};
			map.put(incneu,str);
		}

		public void split1(int sptneu,int sptdst) {
			String[] info=map.get(sptneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			int[] list= new int[info.length/2];
			for (int i=0;i<info.length/2;i++) {
				infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
				list[i]=Integer.parseInt(info[2*i+1]);
			}
			int[] sptlist={sptneu+1,sptneu-1,sptneu+9,sptneu+10,sptneu+11,sptneu-9,sptneu-10,sptneu-11};
			ArrayList<Integer> finlist=new ArrayList<Integer>();
			for (int i=0;i<sptlist.length;i++) {
				if((sptlist[i]<0)||(sptlist[i]>99)) {

				}else {
					finlist.add(sptlist[i]);
				}
			}
			int[] finlist2=new int[finlist.size()];
			for (int i = 0;i<finlist.size();i++) {
				finlist2[i]=finlist.get(i);
			}
			int sptpare=random(finlist2);
			int sptsta=-1;
			if(infos.get(sptpare)==null) {
				infos.put(sptpare,0);
				sptsta=sptpare;
			}
			if(infos.get(sptdst)==null) {
				infos.put(sptdst,0);
			}
			infos.put(sptdst,infos.get(sptdst)/2);
			infos.put(sptpare,infos.get(sptpare)+infos.get(sptdst));///waru2_hahituyounai
			String str="";
			try {
				str=infos.get(list[0])+","+list[0];///0の場合を別記
				for (int i=1;i<list.length;i++) {
					str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
				};
				if(sptsta>=0) {
					str=str+","+infos.get(sptsta)+","+sptsta;
				}

			}catch(ArrayIndexOutOfBoundsException e) {
				if(sptsta>=0) {
					str=infos.get(sptsta)+","+sptsta;
				}else {
					str="null";
				}
			}
			map.put(sptneu,str);
		}public void split1spc(int sptneu,int sptstr,int sptdst) {
			String[] info=map.get(sptneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			int[] list= new int[info.length/2];
			for (int i=0;i<info.length/2;i++) {
				infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
				list[i]=Integer.parseInt(info[2*i+1]);
			}
			infos.put(sptstr,infos.get(sptstr)/2);
			infos.put(sptdst,infos.get(sptdst)+infos.get(sptstr));
			String str=infos.get(list[0])+","+list[0];///0の場合を別記
			for (int i=1;i<list.length;i++) {
				str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
			};
			map.put(sptneu,str);
		}
		public void decrease(int dcrneu,int amount) {
				String[] info=map.get(dcrneu).split(",",0);
				HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
				ArrayList<Integer> list= new ArrayList<Integer>();
				for (int i=0;i<info.length/2;i++) {
						if (Integer.parseInt(info[2*i])>amount) {
							infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i])-amount);
							list.add(Integer.parseInt(info[2*i+1]));
						}else {
							System.out.print("minus");
						}
				}
				String str="";

				for (int i=0;i<list.size();i++) {
					str=str +","+infos.get(list.get(i))+","+list.get(i);///逆。stateとamount
				};
				try {
					str=str.substring(1);
				}catch(StringIndexOutOfBoundsException e) {
					str="null";
				}
				map.put(dcrneu,str);
			}
		public void decrease(int dcrneu,int dcrdst,int range,int amount) {
			if (range==1) {
				String[] info=map.get(dcrneu).split(",",0);
				HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
				int[] list= new int[info.length/2];
				for (int i=0;i<info.length/2;i++) {
					infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
					list[i]=Integer.parseInt(info[2*i+1]);
				}
				if(infos.get(dcrdst)==null) {
					infos.put(dcrdst,0);
				}
				if(infos.get(dcrdst)>amount) {
					infos.put(dcrdst,infos.get(dcrdst)-amount);
				}else {
					infos.put(dcrdst,0);
				}
				String str="";

				for (int i=0;i<list.length;i++) {
					if((list[i]!=0)&&(infos.get(list[i])>0)) {
						str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
					}
				};
				try {
					str=str.substring(1);
				}catch(StringIndexOutOfBoundsException e) {
					str="null";
				}
				map.put(dcrneu,str);
			}else {
				int[] neulst2=new int[5];
				int[] neulst3=new int[13];
				if (range==2) {
					neulst2[0]=dcrneu-10;
					neulst2[1]=dcrneu-1;
					neulst2[2]=dcrneu;
					neulst2[3]=dcrneu+1;
					neulst2[4]=dcrneu+10;
					for(int dcrneu2: neulst2) {
						String[] info=map.get(dcrneu2).split(",",0);
						HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
						int[] list= new int[info.length/2];
						for (int i=0;i<info.length/2;i++) {
							if (Integer.parseInt(info[2*i])>amount) {
								infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i])-amount);
								list[i]=Integer.parseInt(info[2*i+1]);
							}else {

							}
						}
						String str="";

						for (int i=0;i<list.length;i++) {
							if((list[i]!=0)&&(infos.get(list[i])>0)) {
								str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
							}
						};
						try {
							str=str.substring(1);
						}catch(StringIndexOutOfBoundsException e) {
							str="null";
						}
						map.put(dcrneu2,str);
					};
				}else if(range==3) {
					neulst3[0]=dcrneu-20;
					neulst3[1]=dcrneu-11;
					neulst3[2]=dcrneu-10;
					neulst3[3]=dcrneu-9;
					neulst3[4]=dcrneu-2;
					neulst3[5]=dcrneu-1;
					neulst3[6]=dcrneu;
					neulst3[7]=dcrneu+1;
					neulst3[8]=dcrneu+2;
					neulst3[9]=dcrneu+9;
					neulst3[10]=dcrneu+10;
					neulst3[11]=dcrneu+11;
					neulst3[12]=dcrneu+20;
					for(int dcrneu2: neulst3) {
						String[] info=map.get(dcrneu2).split(",",0);
						HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
						int[] list= new int[info.length/2];
						for (int i=0;i<info.length/2;i++) {
							if (Integer.parseInt(info[2*i])>amount) {
								infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i])-amount);
								list[i]=Integer.parseInt(info[2*i+1]);
							}else {

							}
						}
						String str="";

						for (int i=0;i<list.length;i++) {
							if((list[i]!=0)&&(infos.get(list[i])>0)) {
								str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
							}
						};
						try {
							str=str.substring(1);
						}catch(StringIndexOutOfBoundsException e) {
							str="null";
						}
						map.put(dcrneu2,str);
					};
				}

			}
		}
		public void increase(int incneu,int amount) {
			String[] info=map.get(incneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			int[] list= new int[info.length/2];
			for (int i=0;i<info.length/2;i++) {
				if (Integer.parseInt(info[2*i])+amount<=20) {
					infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i])+amount);
				}else {
					infos.put(Integer.parseInt(info[2*i+1]),20);
				}
				list[i]=Integer.parseInt(info[2*i+1]);
			}
			String str="";
			try {
				str=infos.get(list[0])+","+list[0];///0の場合を別記
			}catch(ArrayIndexOutOfBoundsException e) {
				str="null";
			}
			for (int i=1;i<list.length;i++) {
				str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
			};

			map.put(incneu,str);
		}
		public void increase(int incneu,int incdst,int range,int amount) {
			if (range==1) {
				String[] info=map.get(incneu).split(",",0);
				HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
				int[] list= new int[info.length/2];
				for (int i=0;i<info.length/2;i++) {
					infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
					list[i]=Integer.parseInt(info[2*i+1]);
				}
				int listend=0;
				if(infos.get(incdst)!=null) {
					if(infos.get(incdst)+amount>20) {
						infos.put(incdst,20);
					}else {
						infos.put(incdst,infos.get(incdst)+amount);
					}
				}else {
					infos.put(incdst,amount);
					listend=incdst;
				}
				String str="";
				try {
					str=infos.get(list[0])+","+list[0];///0の場合を別記
					for (int i=1;i<list.length;i++) {
						str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
					};
					if(listend!=0) {
						str=str+","+infos.get(listend)+","+listend;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					if(listend!=0) {
						str=infos.get(listend)+","+listend;
					}else {
						str="null";
					}
				}

				map.put(incneu,str);
			}else {
				int[] neulst2=new int[5];
				int[] neulst3=new int[13];
				if (range==2) {
					neulst2[0]=incneu-10;
					neulst2[1]=incneu-1;
					neulst2[2]=incneu;
					neulst2[3]=incneu+1;
					neulst2[4]=incneu+10;
					for(int incneu2: neulst2) {
						String[] info=map.get(incneu2).split(",",0);
						HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
						int[] list= new int[info.length/2];
						for (int i=0;i<info.length/2;i++) {
							if (Integer.parseInt(info[2*i])+amount<=20) {
								infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i])-amount);
								list[i]=Integer.parseInt(info[2*i+1]);
							}else {
								infos.put(Integer.parseInt(info[2*i+1]),20);
								list[i]=Integer.parseInt(info[2*i+1]);
							}
						}
						String str=infos.get(list[0])+","+list[0];///0の場合を別記
						for (int i=1;i<list.length;i++) {
							str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
						};
						map.put(incneu2,str);
					};
				}else if(range==3) {
					neulst3[0]=incneu-20;
					neulst3[1]=incneu-11;
					neulst3[2]=incneu-10;
					neulst3[3]=incneu-9;
					neulst3[4]=incneu-2;
					neulst3[5]=incneu-1;
					neulst3[6]=incneu;
					neulst3[7]=incneu+1;
					neulst3[8]=incneu+2;
					neulst3[9]=incneu+9;
					neulst3[10]=incneu+10;
					neulst3[11]=incneu+11;
					neulst3[12]=incneu+20;
					for(int incneu3: neulst3) {
						String[] info=map.get(incneu3).split(",",0);
						HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
						int[] list= new int[info.length/2];
						for (int i=0;i<info.length/2;i++) {
							if (Integer.parseInt(info[2*i])+amount<=20) {
								infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i])+amount);
								list[i]=Integer.parseInt(info[2*i+1]);
							}else {
								infos.put(Integer.parseInt(info[2*i+1]),20);
								list[i]=Integer.parseInt(info[2*i+1]);
							}
						}
						String str=infos.get(list[0])+","+list[0];///0の場合を別記
						for (int i=1;i<list.length;i++) {
							str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
						};
						map.put(incneu3,str);
					};
				}

			}
		}
		public void addition(int addneu,int adddst,int amount) {
			if(addneu!=adddst) {
				String[] info=map.get(addneu).split(",",0);///sudeni_arunoni_atai_tuika_ka_atarasiku_tuikasuru
				HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
				int[] list= new int[info.length/2];
				for (int i=0;i<info.length/2;i++) {
					infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
					list[i]=Integer.parseInt(info[2*i+1]);
				}
				int listend=-1;
				if (infos.get(adddst)!=null) {
					if(infos.get(adddst)+amount>20) {
						infos.put(adddst,20);
					}else {
						infos.put(adddst,infos.get(adddst)+amount);
					}
				}else {
					infos.put(adddst,amount);
					listend=adddst;
				}
				String str="";
				try {
					str=infos.get(list[0])+","+list[0];///0の場合を別記
					for (int i=1;i<list.length;i++) {
						str=str +","+infos.get(list[i])+","+list[i];///逆。stateとamount
					};
					if(listend!=-1) {
						str=str+","+infos.get(listend)+","+listend;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					if(listend!=-1) {
						str=infos.get(listend)+","+listend;
					}else {
						str="null";
					}
				}

				map.put(addneu,str);
			}
		}public void change1(int cngneu,int cngsta,int cngdst) {
			String[] info=map.get(cngneu).split(",",0);
			HashMap<Integer,Integer> infos=new HashMap<Integer,Integer>();
			ArrayList<Integer> list= new ArrayList<Integer>();
			for (int i=0;i<info.length/2;i++) {
				infos.put(Integer.parseInt(info[2*i+1]),Integer.parseInt(info[2*i]));
				if(Integer.parseInt(info[2*i+1])==cngsta) {

				}else {
					list.add(Integer.parseInt(info[2*i+1]));
				}
			};
			int listend=-1;
			if((infos.get(cngdst)!=null)&&(infos.get(cngsta)!=null)) {
				if(infos.get(cngdst)+infos.get(cngsta)<=20) {
					infos.put(cngdst,infos.get(cngdst)+infos.get(cngsta));
				}else {
					infos.put(cngdst,20);
				}
				infos.remove(cngsta);
			}else if(infos.get(cngdst)!=null){///start_ga_settei_saretenai_toki_suru-

			}else if(infos.get(cngsta)!=null){
				infos.put(cngdst,infos.get(cngsta));
				listend=cngdst;
				infos.remove(cngsta);
			}///ryouhou_null_demo_nanimosinai

			String str="";
			if(list.isEmpty()==false) {
				str=infos.get(list.get(0))+","+list.get(0);///0の場合を別記
					for (int i=1;i<list.size();i++) {
						str=str +","+infos.get(list.get(i))+","+list.get(i);///逆。stateとamount
					};

				if(listend>=0) {
					str=str+","+infos.get(listend)+","+listend;
				}
			}else {

				if(listend>=0) {
					str=str+infos.get(listend)+","+listend;
				}

			}

			map.put(cngneu,str);
		}
	}

}class to90over extends Exception {
	///private int number;
	///public to90over(int i) {
		// TODO 自動生成されたコンストラクター・スタブ
	///	this.number=i;
	///}
	///public int getNum() {
	///	return number;
	///}


}class sygnalReached extends Exception{

}
*/
