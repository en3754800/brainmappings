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
import java.util.UUID;

import javaBrain2.Brain0.NeuReturn;
import javaBrain2.Brain0controll.Think.ThinkReturn;

/**
 * @author okada.yousuke
 *
 */
public class Brain0controll {

	/**
	 * @param args
	 */
	static int[][] number= {{-8,-7,9,-7,4,-10,-6,6,5,2},
		{3,7,10,-7,0,5,0,10,-1,1},
		{-6,-7,-7,3,9,-8,-5,-6,10,-4},
		{8,-2,8,8,1,-2,-3,-1,-4,6},
		{-3,-8,-2,4,-1,-10,7,-7,3,-9},
		{-9,-6,10,-5,10,-7,-3,-8,4,-9},
		{-8,-1,6,6,-5,7,7,2,-9,-8},
		{3,-10,3,10,-4,0,-1,-2,10,5},
		{0,7,1,2,-5,-7,-3,7,9,4},
		{6,-2,10,2,-4,7,3,-7,-7,0}};



	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int[][] convertlist=new int[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				convertlist[i][j]=(int)(Math.random()*21)-10;
				//System.out.print(convertlist[i][j]);
			}
		}
		Initbase ini=initialize();
		Brain0controll bcont=new Brain0controll();
		Brainbase base=bcont.new Brainbase();
		base.construction(ini);
		ThinkReturn tr=new Think.ThinkReturn();
		tr.destlist.add("1-10");
		tr.syglist.add(number[2]);
		Think.integthink(tr,base);
		//memory();
		int[] number0= {0,0,0,0,0,0,0,0,0,0};
		int[] number1= {1,1,1,1,1,1,1,1,1,1};
		int[] number2= {2,2,2,2,2,2,2,2,2,2};
		int[] number3= {3,3,3,3,3,3,3,3,3,3};
		int[] number4= {4,4,4,4,4,4,4,4,4,4};
		int[] number5= {5,5,5,5,5,5,5,5,5,5};
		int[] number6= {6,6,6,6,6,6,6,6,6,6};
		int[] number7= {7,7,7,7,7,7,7,7,7,7};
		int[] number8= {8,8,8,8,8,8,8,8,8,8};
		int[] number9= {9,9,9,9,9,9,9,9,9,9};

		/**
		 * kihon kouzou initialize->memory(basho,true,false)->run(basho,input)
		 */
		memory(0,number[0],number0);
		memory(1,number[1],number1);
		memory(2,number[2],number2);
		memory(3,number[3],number3);
		memory(4,number[4],number4);
		memory(5,number[5],number5);
		memory(6,number[6],number6);
		memory(7,number[7],number7);
		memory(8,number[8],number8);
		memory(9,number[9],number9);
		for(int i=0;i<10;i++) {
			quickdirection(i,i+"-"+(i+10)+"/"+(i*3+10)+"-"+(i*3+12));
		}
		int[] samplelist= {3,7,9,4};
		run("1-10",number[samplelist[0]],300);
		run("1-10",number[samplelist[1]],300);
		run("1-10",number[samplelist[2]],400);
		run("1-10",number[samplelist[3]],400);


	}public class Brainbase{
		AssignMem assign;
		Brain0controll control;
		int thinkmemstatus=0;
		void construction(Initbase ini) {
			assign=ini.assign;
			control=ini.bcont;
		}
		void tomemchange() {//thinkmemstatus no henka wo kanti suru,mataha henka saseru   shallowthink

		}
	}
	/**
	 *  1kai yomikomi desicion wo okonau(seigo hantei wo suru)
	 *  RunReturn answerstr String answer(returnnum<-koujichu<-answerlist ni sitai) range String range
	 *  destination String 24,25,26*45,46,47*dest1*dest2 now 24,35,56(<-nyuuryoku no destination wo subete uketugu)
	 *  list(detail) kaesareta shuturyoku 3,5,7,6,9,0,5,6,3,4 nado shuturyoku1*shuturyoku2
	 *  count destination(nyuuryoku) no nagasa now unit karano shuturyoku no list(,=koujichuu)
	 *  zenhan ha kiroku sita hensuu wo utusu shori to omoware
	 *  destination ni sample(<-nyuuryoku list ga tukaeru youni koujichuu) no list no nyuuryoku wo irete true de str ni kiroku suru destination ga owaru made tudukeru
	 *  korewo tukau method trace
	 *  1 block subete ni singou wo okuru kouzou
	 * @param destination
	 * @param list2
	 * @return
	 */
	public static RunReturn run(String destination,int[] list2,int time) {//destination ha in no iti   !RunReturn wo not string no keisikini henkou dekiru atode tamesite miyou
		String answerstr="";
		String rangestr="";
		String deststr="";
		String detailstr="";
		String nowstr="";
		String timestr="";
		int returncount=0;
		Brain0 main=new Brain0();
		Brain0.Queue2 line=main.new Queue2();
		Brain0.NeuReturn answer=new NeuReturn();
		Brain0.NeuReturn answer2=new NeuReturn();
		Brain0controll cont=new Brain0controll();
		OutReturn runlist=cont.new OutReturn();
		OutReturn runlist2=cont.new OutReturn();
		DecisionReturn declist=cont.new DecisionReturn();
		int[] list= {3,5,9,1,-4,5,-6,0,-3,-8};

		int[] deslist=Management.destparseStrtoList(destination);

		answerstr=answerstr+"/";
		rangestr=rangestr+"/";
		deststr=deststr+"/";
		detailstr=detailstr+"/";
		nowstr=nowstr+"/";

		int[] answerlist= {0,0,0,0,0,0,0,0,0,0};
		int[] rangelist= {0,0,0,0,0,0,0,0,0,0};
		int[][] detaillist=new int[10][10];
		int[] nowlist=new int[10];
		int[] timelist=new int[10];
		int anscount=0;
		String[]retdeslist=new String[10];
		for(int i=0;i<deslist.length;i++) {
			if(deslist[i]>0) {
				if(anscount==9) {/**anscount ga 9 tamattara str ni kiroku suru**/
					System.out.print(answerlist);
					for(int j=0;j<10;j++) {
						answerstr=answerstr+answerlist[j]+",";
						rangestr=rangestr+rangelist[j]+",";
						deststr=deststr+"*"+retdeslist[j];
						nowstr=nowstr+"*"+nowlist[j];
						detailstr=detailstr+"*";
						for (int m=0;m<10;m++) {
							detailstr=detailstr+detaillist[j][m]+",";
						}
						timestr=timestr+"*"+timelist[j];
					}
					Arrays.fill(answerlist, 0);
					Arrays.fill(rangelist, 0);
					Arrays.fill(retdeslist, 0);
					Arrays.fill(nowlist, 0);
					for(int n=0;n<10;n++) {
						Arrays.fill(detaillist[n], 0);
					}
					Arrays.fill(timelist,0);
					anscount=0;
				}
				runlist=output(Brain0.quickneuron(line,deslist[i],list2,time));//OutReturn->time haitteiru
				declist=decision(runlist);
				if(declist.istrue=true) {
					System.out.print(declist.returnnum);
					answerlist[anscount]=declist.returnnum;
					rangelist[anscount]=declist.range;
					detaillist[anscount]=declist.list;//koujichuu
					nowlist[anscount]=deslist[i];
					timelist[anscount]=runlist.time;
					String[] retdeslist2 =readdest(deslist[i]);
					retdeslist[i]=retdeslist2[1];
					anscount++;
					returncount++;

				}
			}

		};
		System.out.print(answerlist);
		for(int j=0;j<anscount+1;j++) {
			answerstr=answerstr+answerlist[j]+",";
			rangestr=rangestr+rangelist[j]+",";
			deststr=deststr+"*"+retdeslist[j];
			nowstr=nowstr+","+nowlist[j];
			detailstr=detailstr+"*";
			for (int m=0;m<10;m++) {
				detailstr=detailstr+detaillist[j][m]+",";
			}
		}



		RunReturn rreturn=new RunReturn();
		rreturn.answer=answerstr;
		rreturn.range=rangestr;
		rreturn.destination=deststr;
		rreturn.list=detailstr;
		rreturn.count=deslist.length;
		rreturn.now=nowstr;
		return rreturn;

	}
	/**
	 * rangecheck wo okonai koumoku gotoni t/f no kotae wo kuwae returnnum ni heikin wo ireru(youkennshou)
	 * bf=output(newron(line,34,syglist)
	 * @param ret
	 * @return
	 */
	public static OutReturn output(NeuReturn ret){
		Brain0controll main =new Brain0controll();
		OutReturn ret2=main.new OutReturn();
		int sum=0;
		if(ret.rangecheck[0]>ret.rangecheck[1]) {
			if(1.1*ret.rangecheck[0]>=ret.check0[0]) {
				ret2.list= ret.tanswer;
				for(int j=0;j<10;j++) {
					sum=sum+ret.tanswer[j];
				}
			}else {
				ret2.list= ret.fanswer;
			}
		}else {
			ret2.list= ret.fanswer;
		}
		ret2.check[0]= ret.check0[0];ret2.check[1]=ret.check0[1];ret2.check[2]=ret.rangecheck[0];ret2.check[3]=ret.rangecheck[1];
		ret2.returnnum=(int)(sum/10);
		ret2.time=ret.time;
		return ret2;
	}
	/**
	 * OutReturn no check wo motoni true pattern ni gatti siteireba true wo kaesi sonotaha false wo kaesu
	 * returnnum to list ha copy siteiru
	 * @param ret
	 * @return
	 */
	public static DecisionReturn decision(OutReturn ret) {
		Brain0controll cont =new Brain0controll();
		DecisionReturn dec=cont.new DecisionReturn();
		if(ret.check[2]>ret.check[3]) {
			if(1.1*ret.check[2]>=ret.check[0]) {
				dec.istrue=true;
				dec.range=ret.check[2]-ret.check[0];
			}else {
				dec.istrue=false;
				dec.range=ret.check[3]-ret.check[1];
			}
		}else {
			dec.istrue=false;
			dec.range=ret.check[3]-ret.check[1];
		}
		dec.returnnum=ret.returnnum;
		dec.list=ret.list;
		return dec;
	}
	/**
	 * husiyou
	 * @param num
	 * @param intruelist
	 * @param outtruelist
	 */
	public static void memory(int num,int[] intruelist,int[]outtruelist) {
		Brain0 main=new Brain0();
		Brain0.Queue line=main.new Queue();
		int[] inneubase= {0,0,0,0,0,0,0,0,0,0};
		int[] inneutans= {10,10,10,10,10,10,10,10,10,10};
		int[] inneufans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};
		int[] outneufans= {0,0,0,0,0,0,0,0,0,0};
		int[] outputoutfans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};

		Brain0.memory(line,num,intruelist,inneubase,inneutans,inneufans);
		Brain0.memory(line,num+50,inneutans,inneufans,outtruelist,outputoutfans);

	}

	public static void quickmemory(int num,int[] intruelist,int[]outtruelist) {
		Brain0 main=new Brain0();
		Brain0.Queue line=main.new Queue();
		int[] inneubase= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};
		int[] inneutans= {10,10,10,10,10,10,10,10,10,10};
		int[] outneufans= {0,0,0,0,0,0,0,0,0,0};
		int[] outfans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};

		Brain0.quickmemory(line,num,intruelist,inneubase,outtruelist,outfans);

	}

	public static Initbase initialize(){
		Brain0 main=new Brain0();
		Brain0.Queue line=main.new Queue();
		int[] inneubase= {0,0,0,0,0,0,0,0,0,0};
		int[] inneutans= {10,10,10,10,10,10,10,10,10,10};
		int[] inneufans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};
		int[] outneufans= {0,0,0,0,0,0,0,0,0,0};
		int[] outputoutfans= {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};

		AssignMem as=new AssignMem();
		as.construct();
		Brain0controll br=new Brain0controll();
		Brain0controll inibr=new Brain0controll();
		Initbase ini=inibr.new Initbase();
		ini.assign=as;
		ini.bcont=br;

		/**
		 * for debug
		 */
		for(int i=0;i<50;i++) {
			Brain0.memory(line, i,inneubase,inneubase,inneutans,inneufans);
			Brain0.memory(line, i+50, inneutans, inneufans, outneufans, outputoutfans);
			System.out.print(".");
			Brain0.quickmemory(line, i,inneubase,inneubase,inneutans,inneufans);
			Brain0.quickmemory(line, i+50, inneutans, inneufans, outneufans, outputoutfans);
		}
		return ini;
	}
	public class Initbase{
		AssignMem assign;
		Brain0controll bcont;
	}
	/**
	 * destination No .txt ni destination wo nyuuryoku suru zenbun hituyou!
	 * komakai kakikae ha Think->correctdest ga tukasadoru
	 * @param No
	 * @param destination
	 */
	public static void direction(int No, String destination) {

		try {
			File dirfile=new File("destination"+No+".txt");
			FileWriter filewrite=new FileWriter(dirfile);
			filewrite.write(destination);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}


	}
	/**
	 * quickdestination No .txt ni destination wo nyuuryoku suru zenbun hituyou!
	 * @param No
	 * @param destination
	 */
	public static void quickdirection(int No, String destination) {

		try {
			File dirfile=new File("quickdestination"+No+".txt");
			FileWriter filewrite=new FileWriter(dirfile);
			filewrite.write(destination);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}


	}
	/**
	 * destination No .txt wo yomikomu
	 * @param No
	 * @return
	 */
	public static String[] readdest(int No){
		File filedes=new File("./quickdestination"+No+".txt");
		String[] desstrlist=new String[2];
				String desstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(filedes));
			String str =br.readLine();
			while(str!=null) {
				desstr=desstr+str;
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
		desstrlist= desstr.split("/",0);
		return desstrlist;

	}
	/**
	 *
	 * @author okada.yousuke
	 *
	 */
	public static class Management{
		public static boolean managed=false;
		@SuppressWarnings("static-access")
		public boolean[] search(int[] list,Brainbase base) {

			boolean[]rlist= new boolean[list.length];
			for(int i=0;i<list.length;i++) {
				if(base.assign.exist[list[i]]==1) {
					rlist[list[i]]=false;
				}else {
					rlist[list[i]]=true;
				}
			}
			return rlist;
		}
		/**
		 * managelist.txt wo yomikomu memory no sonzai suru ryouiki wo simesu
		 * @return
		 */
		public int[] readlist() {
			File fileman=new File("./managelist.txt");/** list keisiki 1/1/1/1/0/0/0/0/0/1/1/0/0/0/ **/
					String[] manstrlist=new String[2];
					String manstr="";
			try {
				BufferedReader br=new BufferedReader(new FileReader(fileman));
				String str =br.readLine();
				while(str!=null) {
					manstr=manstr+str;
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
			manstrlist= manstr.split("/",0);
			int[] manlist=new int[manstrlist.length];
			for(int i=0;i<manstrlist.length;i++) {
				manlist[i]=Integer.parseInt(manstrlist[i]);
			}
			return manlist;
		}
		/**
		 * managelist.txt wo kaku
		 * @param list
		 */
		public void writelist(int[] list) {

			String managestr="";
			managestr=String.valueOf(list[0]);
			for(int i=1;i<list.length;i++) {
				managestr=managestr+"/"+list[i];
			}

			try {
				File ansfile=new File("managelist.txt");
				FileWriter filewrite=new FileWriter(ansfile);
				filewrite.write(managestr);
				filewrite.close();
			}catch(IOException e) {
				System.out.println(e);
			}


		}
		/**
		 * destination keisiki no String wo int list ni naosu
		 * @param str
		 * @return
		 */
		public static int[] destparseStrtoList(String str) {
			String[] startlist=str.split(",");
			int[][] midlist=new int[startlist.length][];
			for(int i=0;i<startlist.length;i++) {
				Arrays.fill(midlist[i], -1);
			}
			for(int i=0;i<startlist.length;i++) {
				if(startlist[i].indexOf("-")>=0) {
					String[] midstartlist=startlist[i].split("-");
					int count=0;
					int start=Integer.parseInt(midstartlist[0]);
					int end=Integer.parseInt(midstartlist[1]);
					for(int j=start;j<=end;j++) {
						midlist[Integer.parseInt(startlist[i])][count]=i;
						count++;
					}
				}else {
					midlist[i][0]=Integer.parseInt(startlist[i]);
				}
			}
			int upcase=0;
			for(int[] list:midlist){
				inside: for(int j=0;j<20;j++) {
					if(list[j]==-1){
						break inside;
					}
					upcase++;
				}
			}
			int[] retlist=new int[upcase];
			int count=0;
			for(int[] list:midlist){
				inside: for(int j=0;j<20;j++) {
					if(list[j]==-1){
						break inside;
					}
					retlist[count]=list[j];
					count++;
				}
			}
			return retlist;
		}
		/**
		 * destination keisiki no int list wo String ni kaesu
		 * @param list
		 * @return
		 */
		public static String destparseListtoStr(int[] list) {
			int[] list2=list;
			Think.quick_sort(list2, list, 0, list.length-1);
			int[] midlist=new int[list.length*2];
			Arrays.fill(midlist, -1);
			int midcount=0;
			midlist[0]=list[0];
			for(int i=0;i<list.length;i++) {
				if(list[i+1]==list[i]+1) {
					midlist[midcount*2+1]=list[i+1];
				}else {
					midcount++;
					midlist[midcount*2]=list[i+1];
				}
			}
			String retstr="";
			list: for(int i=0;i<list.length;i++) {
				if(midlist[2*i+1]>0) {
					retstr=retstr+String.valueOf(midlist[2*i])+"-"+String.valueOf(midlist[2*i+1])+",";
				}else if(midlist[2*i]>0) {
					retstr=retstr+String.valueOf(midlist[2*i]);
				}else {
					break list;
				}
			}


			return retstr;
		}
	}
	public class OutReturn{
		public int returnnum;
		public int[] list;
		public int[] check=new int[4];
		public int time;
	}
	public class DecisionReturn{
		public boolean istrue;
		public int[] list;
		public int returnnum;
		public int range;//0 ga itiban tikai suujiga ookiku mataha mainasu ni naruhodo hanareteru(singou tiisai)
	}
	public static class RunReturn{
		public String answer;// sample /3,5,7~10,3,5,~10/2:~
		public String range;// sample /0,2,~10,2~
		public String destination;// sample /*24-26,28*57-59*~10*57-59~
		public String list;// sample /*1,3,6,4,-6,-8,0,5,6,0*3,5,7,9,0,-3,-5,-7,0,0*~10*~10:
		public int count;
		public String now;// sample /35,42,46,44,75
		//time huyou
		public static int[] answerlist(String ans) {
			String[] midans=ans.split("/");
			String[] midans2=midans[1].split(",");
			int[] ret=new int[midans2.length];
			for (int i=0;i<midans2.length;i++) {
				ret[i]=Integer.parseInt(midans2[i]);
			}
			return ret;
		}		public static int[] rangelist(String ran) {
			String[] midran=ran.split("/");
			String[] midran2=midran[1].split(",");
			int[] ret=new int[midran2.length];
			for (int i=0;i<midran2.length;i++) {
				ret[i]=Integer.parseInt(midran2[i]);
			}
			return ret;
		}
		public static String[] destlist(String dest) {
			String[] middest=dest.split("/");
			String[] ret=middest[1].split("*");
			return ret;
		}		public static int[][] listlist(String list) {
			String[] midlist=list.split("/");
			String[] midlist2=midlist[1].split("*");
			int[][] ret=new int[midlist2.length][10];
			for (int i=0;i<midlist2.length;i++) {
				String[]midlist3=midlist2[i].split(",");
				for(int l=0;l<10;l++) {
					ret[i][l]=Integer.parseInt(midlist3[l]);

				}
			}
			return ret;
		}	public static int[] nowlist(String now) {
			String[] midnow=now.split("/");
			String[] midnow2=midnow[1].split(",");
			int[] ret=new int[midnow2.length];
			for (int i=0;i<midnow2.length;i++) {
				ret[i]=Integer.parseInt(midnow2[i]);

			}
			return ret;
		}



	}
	public static int[] randgene() {

		int[] rand=new int[10];
		for(int i=0;i<10;i++) {
			rand[i]=(int)(21*Math.random()-10);
		}
		return rand;
	}
	public static class Think{
		/**
		 * nyuuryokuha startthink no destlist to syglist de sitei suru
		 * startthink wo mazu shallowthink ni kakete start to suru
		 * think to mem ni jouhouwo ireru
		 * now ga 50 miman no baai
		 * thinkcontrol ga 2 nara integmemory wo tukau(misiyou) 50 miman denakereba tomezunizokkou
		 * shallowthink site 50 miman nara shallowmem ni ijou nara deepmem ni mem wo kiroku suru
		 * count site 99 made ittara shallowmem to deepmem de sorezore tomemlist wo jikkou suru
		 * shallowthink de 100kai integthinkde 100kai trace wo kidou sitara owaru seigen ari
		 * deepmem shallow mem wo kesu method ga nasasou
		 * @param startthink
		 * @param base
		 */
		public static void integthink(ThinkReturn startthink,Brainbase base) {
			ShMemReturn integthink=new ShMemReturn();
			ThinkReturn deepmem =new ThinkReturn();
			ThinkReturn shallowmem =new ThinkReturn();
			ShMemReturn tmpthink=new ShMemReturn();
			ShthinkMem dumpthink=new ShthinkMem();
			ArrayList<String>switchshlist=new ArrayList<String>();
			ArrayList<String>switchdelist=new ArrayList<String>();
			int count=0;
			int topicnow;
			int thinkcontrol=1;
			boolean tomemory=false;
			base.thinkmemstatus=1;
			ShMemReturn shmem=new ShMemReturn();
			shmem.toShMemadd(startthink);
			tmpthink=shallowthink(shmem,dumpthink,base);
			integthink.add(tmpthink);
			for(int i=0;i<tmpthink.nowlist.size();i++) {
				if(tmpthink.nowlist.get(i)<50) {/**50 ika no basho no baai **/
					shallowmem.syglist.add(tmpthink.syglist.get(i));
					shallowmem.destlist.add(tmpthink.destlist.get(i));
					shallowmem.startlist.add(tmpthink.startlist.get(i));
					shallowmem.nowlist.add(tmpthink.nowlist.get(i));
					shallowmem.inlist.add(integthink.syglist.get(0));
					switchshlist.add(startthink.nowlist.get(0)+"-"+tmpthink.nowlist.get(i));
				}else {
					deepmem.syglist.add(tmpthink.syglist.get(i));
					deepmem.destlist.add(tmpthink.destlist.get(i));
					deepmem.startlist.add(tmpthink.startlist.get(i));
					deepmem.nowlist.add(tmpthink.nowlist.get(i));
					deepmem.inlist.add(integthink.syglist.get(0));
					switchdelist.add(startthink.nowlist.get(0)+"-"+tmpthink.nowlist.get(i));
				}
			}
			tmpthink.clear();
			IntegMemory mem=new IntegMemory();
			ToMemList shtomem=new ToMemList();
			ToMemList detomem=new ToMemList();
			outside: while((integthink.syglist.isEmpty()==false)&&(count<100)) {
				topicnow=integthink.nowlist.get(0);

				if(topicnow<1000) {//1000 ni henkou yotei
					switch(base.thinkmemstatus){
					case 1:

					case 0:
						break outside;
					case 2:

						integmemory(mem,base);//sakuseichuu??
						break outside;
					}
				}
				tmpthink=shallowthink(integthink,dumpthink,base);
				integthink.add(tmpthink);
				for(int i=0;i<tmpthink.nowlist.size();i++) {
					if(tmpthink.nowlist.get(i)<1000) {//1000 ni henkou yotei (saishuutekini border hensuu ni makaseru
						shallowmem.syglist.add(tmpthink.syglist.get(i));
						shallowmem.destlist.add(tmpthink.destlist.get(i));
						shallowmem.startlist.add(tmpthink.startlist.get(i));
						shallowmem.nowlist.add(tmpthink.nowlist.get(i));
						shallowmem.inlist.add(integthink.syglist.get(0));
						switchshlist.add(topicnow+"-"+tmpthink.nowlist.get(i));
					}else {
						deepmem.syglist.add(tmpthink.syglist.get(i));
						deepmem.destlist.add(tmpthink.destlist.get(i));
						deepmem.startlist.add(tmpthink.startlist.get(i));
						deepmem.nowlist.add(tmpthink.nowlist.get(i));
						deepmem.inlist.add(integthink.syglist.get(0));
						switchdelist.add(topicnow+"-"+tmpthink.nowlist.get(i));
					}


				}
				tmpthink.clear();
				if(count==99) {
					shtomem.mem.inmemorylist=(int[][])shallowmem.inlist.toArray();
					shtomem.mem.outmemorylist=(int[][])shallowmem.syglist.toArray();
					shtomem.mem.destlist=(String[])shallowmem.destlist.toArray();
					shtomem.mem.startlist=(String[])shallowmem.startlist.toArray();
					shtomem.switchlist=(String[])switchshlist.toArray();
					tomemlist(shtomem,base);
					shtomem.clear();//itiou tukuttoku
					detomem.mem.inmemorylist=(int[][]) deepmem.inlist.toArray();
					detomem.mem.outmemorylist=(int[][])deepmem.syglist.toArray();
					detomem.mem.destlist=(String[])deepmem.destlist.toArray();
					detomem.mem.startlist=(String[])deepmem.startlist.toArray();
					detomem.switchlist=(String[])switchdelist.toArray();
					tomemlist(detomem,base);
					tomemory=true;
					detomem.clear();
				}
				count++;


			}


		}
		/**
		 * ToMememList no jouhou wo motoni integmemory wo kidou suru
		 * (integthink->shallowthink->) tomemlist->integmemory->tomemry
		 * total bothside(tot1,2,3,4,5)
		 * in/out joui 2 ->  in1->rand->out (new unit sakusei)
		 * in/out joui 5made ->correspond(in1->out1,2,3,4,5,in2->out1,2,3,4,5),correspond(in1,2,3,4,5->out1,in1,2,3,4,5->out2)
		 * @param memlist
		 * @param base
		 */
		public static void tomemlist(ToMemList memlist,Brainbase base) {
			HashMap<Integer,Integer> memadd=new HashMap<Integer,Integer>();
			HashMap<Integer,ToMemSet> memset=new HashMap<Integer,ToMemSet>();
			for(int i=0;i<memlist.mem.nowlist.length;i++) {//mem ni now no atai ga sonzai sureba add ni 1 wo tuika suru
				if(memadd.containsKey(memlist.mem.nowlist[i])==false){
					memadd.put(memlist.mem.nowlist[i], 0);
				}
				ToMemSet tmpset=new ToMemSet();
				tmpset.inlist=memlist.mem.inmemorylist[i];
				tmpset.start=memlist.mem.startlist[i];
				memadd.put(memlist.mem.nowlist[i],memadd.get(memlist.mem.nowlist[i])+1);
				memset.put(memlist.mem.nowlist[i], tmpset);

			}
			HashMap<Integer,Integer> destadd=new HashMap<Integer,Integer>();
			HashMap<Integer,ArrayList<String>> destsw=new HashMap<Integer,ArrayList<String>>();
			HashMap<Integer,int[]> destout=new HashMap<Integer,int[]>();
			HashMap<Integer,ArrayList<int[]>> destin=new HashMap<Integer,ArrayList<int[]>>();
			HashMap<Integer,ArrayList<ToMemSet>> destset=new HashMap<Integer,ArrayList<ToMemSet>>();
			for(int i=0;i<memlist.switchlist.length;i++) {
				int tmp=Integer.parseInt(memlist.switchlist[i].split("-")[1]);
				if(destadd.containsKey(tmp)==false){
					destadd.put(tmp, 0);
				}
				destadd.put(tmp,destadd.get(tmp)+1);
				destsw.get(tmp).add(memlist.switchlist[i]);
				ToMemSet tmpset=new ToMemSet();
				tmpset.inlist=memlist.mem.inmemorylist[i];
				tmpset.outlist=memlist.mem.outmemorylist[i];
				tmpset.start=memlist.mem.startlist[i];
				tmpset.dest=memlist.mem.destlist[i];
				tmpset.now=memlist.mem.nowlist[i];
				destset.get(tmp).add( tmpset);
				destout.put(tmp,memlist.mem.outmemorylist[i]);
				destin.get(tmp).add(memlist.mem.inmemorylist[i]);
			}
			HashMap<Integer,Integer> stadd=new HashMap<Integer,Integer>();
			HashMap<Integer,ArrayList<String>> stsw=new HashMap<Integer,ArrayList<String>>();
			HashMap<Integer,int[]> stin=new HashMap<Integer,int[]>();
			HashMap<Integer,ArrayList<int[]>> stout=new HashMap<Integer,ArrayList<int[]>>();
			HashMap<Integer,ArrayList<ToMemSet>> stset=new HashMap<Integer,ArrayList<ToMemSet>>();
			for(int i=0;i<memlist.switchlist.length;i++) {
				int tmp=Integer.parseInt(memlist.switchlist[i].split("-")[0]);
				if(stadd.containsKey(tmp)){
					stadd.put(tmp, 0);
				}
				stadd.put(tmp,stadd.get(tmp)+1);
				stsw.get(tmp).add(memlist.switchlist[i]);
				stin.put(tmp, memlist.mem.inmemorylist[i]);
				ToMemSet tmpset=new ToMemSet();
				tmpset.inlist=memlist.mem.inmemorylist[i];
				tmpset.outlist=memlist.mem.outmemorylist[i];
				tmpset.start=memlist.mem.startlist[i];
				tmpset.dest=memlist.mem.destlist[i];
				tmpset.now=memlist.mem.nowlist[i];
				stset.get(tmp).add( tmpset);
				stout.get(tmp).add(memlist.mem.outmemorylist[i]);
			}
			int[] memaddkey=new int[memadd.size()];
			int[] memaddvalue=new int[memadd.size()];
			int count=0;
			for(int key:memadd.keySet()){//memaddkey to memaddvalue ni nowkey to key no ryou wo wariateru
				memaddkey[count]=key;
				memaddvalue[count]=memadd.get(key);
				count++;
			}
			quick_sort(memaddkey,memaddvalue,0,memaddkey.length-1);

			int[] memretkey=new int[5];
			IntegMemory meminteg=new IntegMemory();
			for (int i=0;i<5;i++) {
				memretkey[i]=memaddkey[i];
				meminteg.inmemorylist[i]=memset.get(memaddkey[i]).inlist;
				meminteg.startlist[i]=memset.get(memaddkey[i]).start;
				meminteg.nowlist[i]=memaddkey[i];
			}
			meminteg.bothside=true;
			integmemory(meminteg,base);
			for (int i=0;i<6;i++) {//destsw ni tuite kouji yotei??

			}
			int[] destaddkey=new int[destadd.size()];
			int[] destaddvalue=new int[destadd.size()];
			count=0;
			for(int key:destadd.keySet()){
				destaddkey[count]=key;
				destaddvalue[count]=destadd.get(key);
				count++;
			}
			quick_sort(destaddkey,destaddvalue,0,destaddkey.length-1);
			int[] staddkey=new int[stadd.size()];
			int[] staddvalue=new int[stadd.size()];
			count=0;
			for(int key:stadd.keySet()){
				staddkey[count]=key;
				staddvalue[count]=stadd.get(key);
				count++;
			}
			quick_sort(staddkey,staddvalue,0,staddkey.length-1);/** signal no ryou de staddkey wo sort suru**/

			int stkey0=staddkey[0];
			ToMemSet[] stoutretsyg=new ToMemSet[5];
			if(staddkey[1]>1) {
				int stkey1=staddkey[1];
				HashMap<int[],Integer>stoutsyghas=new HashMap<int[],Integer>();
				HashMap<int[],ToMemSet>stsetouthas=new HashMap<int[],ToMemSet>();
				for(int i=0;i<stout.size();i++) {
					if(stoutsyghas.containsKey(stout.get(stkey0).get(i))) {
						stoutsyghas.put(stout.get(stkey0).get(i),0);
					}
					stoutsyghas.put(stout.get(stkey0).get(i),stoutsyghas.get(stout.get(stkey0).get(i))+1);
					stsetouthas.put(stset.get(stkey0).get(i).outlist,stset.get(stkey0).get(i));
				}
				ToMemSet[] stoutsygset=new ToMemSet[stoutsyghas.size()];
				int[]stoutsygvalue=new int[stoutsyghas.size()];
				int countsyg=0;
				for(int[] key:stoutsyghas.keySet()) {
					stoutsygset[countsyg]=stsetouthas.get(key);///koujichuu
					stoutsygvalue[countsyg]=stoutsyghas.get(key);
					countsyg++;
				}
				quick_sort(stoutsygset,stoutsygvalue,0,stoutsygvalue.length-1);

				for(int i=0;i<5;i++) {
					stoutretsyg[i]=stoutsygset[i];
				}
				HashMap<int[],Integer>stoutsyghas2=new HashMap<int[],Integer>();
				HashMap<int[],ToMemSet>stsetouthas2=new HashMap<int[],ToMemSet>();
				for(int i=0;i<stout.size();i++) {
					if(stoutsyghas2.containsKey(stout.get(stkey1).get(i))) {
						stoutsyghas2.put(stout.get(stkey1).get(i),0);
					}
					stoutsyghas2.put(stout.get(stkey1).get(i),stoutsyghas2.get(stout.get(stkey1).get(i))+1);
					stsetouthas2.put(stset.get(stkey1).get(i).outlist,stset.get(stkey1).get(i));
				}
				int[]stoutsygvalue2=new int[stoutsyghas2.size()];
				int countsyg2=0;
				ToMemSet[] stoutsygset2=new ToMemSet[stoutsyghas.size()];
				for(int[] key:stoutsyghas2.keySet()) {
					stoutsygset2[countsyg]=stsetouthas2.get(key);///koujichuu
					stoutsygvalue2[countsyg2]=stoutsyghas.get(key);
					countsyg2++;
				}
				quick_sort(stoutsygset2,stoutsygvalue2,0,stoutsygvalue2.length-1);
				ToMemSet[] stoutretsyg2=new ToMemSet[5];
				for(int i=0;i<5;i++) {
					stoutretsyg2[i]=stoutsygset2[i];
				}



			int destkey0=destaddkey[0];
			if(destaddkey[1]>1) {
				int destkey1=destaddkey[1];
				HashMap<int[],Integer>destinsyghas=new HashMap<int[],Integer>();
				HashMap<int[],ToMemSet>destsetinhas=new HashMap<int[],ToMemSet>();
				HashMap<int[],HashMap<Integer,Integer>> destinstlist=new HashMap<int[],HashMap<Integer,Integer>>();
				for(int i=0;i<destin.size();i++) {
					if(destinsyghas.containsKey(destin.get(destkey0).get(i))) {
						destinsyghas.put(destin.get(destkey0).get(i),0);
					}
					destinsyghas.put(destin.get(destkey0).get(i),destinsyghas.get(destin.get(destkey0).get(i))+1);
					destsetinhas.put(destset.get(destkey0).get(i).inlist,destset.get(destkey0).get(i));
					destinstlist.get(destin.get(destkey0).get(i)).put(Integer.valueOf(destset.get(destkey0).get(i).start),1);

				}
				ToMemSet[] destinsygset=new ToMemSet[stoutsyghas.size()];
				int[]destinsygvalue=new int[destinsyghas.size()];
				int countsyg3=0;
				for(int[] key:destinsyghas.keySet()) {
					destinsygset[countsyg3]=destsetinhas.get(key);
					destinsygvalue[countsyg3]=destinsyghas.get(key);
					countsyg3++;
				}
				quick_sort(destinsygset,destinsygvalue,0,destinsygvalue.length-1);
				ToMemSet[] destinretsyg=new ToMemSet[5];
				for(int i=0;i<5;i++) {
					destinretsyg[i]=destinsygset[i];
					int[] ranlist=new int[destinstlist.get(destinsygset[i].outlist).size()];
					for(int key:destinstlist.get(destinsygset[i].outlist).keySet()) {
						ranlist[i]=key;
					}
					destinretsyg[i].start=String.valueOf(Brain0.random(ranlist));
				}
				HashMap<int[],Integer>destinsyghas2=new HashMap<int[],Integer>();
				HashMap<int[],ToMemSet>destsetinhas2=new HashMap<int[],ToMemSet>();
				HashMap<int[],HashMap<Integer,Integer>> destinstlist2=new HashMap<int[],HashMap<Integer,Integer>>();
				for(int i=0;i<destin.size();i++) {
					if(destinsyghas2.containsKey(destin.get(destkey1).get(i))) {
						destinsyghas2.put(destin.get(destkey1).get(i),0);
					}
					destinsyghas2.put(destin.get(destkey1).get(i),destinsyghas2.get(destin.get(destkey1).get(i))+1);
					destsetinhas2.put(destset.get(destkey1).get(i).inlist,destset.get(destkey1).get(i));
					destinstlist2.get(destin.get(destkey1).get(i)).put(Integer.valueOf(destset.get(destkey1).get(i).start),1);
				}
				ToMemSet[] destinsygset2=new ToMemSet[stoutsyghas.size()];
				int[]destinsygvalue2=new int[destinsyghas2.size()];
				int countsyg4=0;
				for(int[] key:destinsyghas2.keySet()) {
					destinsygset[countsyg3]=destsetinhas.get(key);
					destinsygvalue2[countsyg4]=destinsyghas.get(key);
					countsyg4++;
				}
				quick_sort(destinsygset2,destinsygvalue2,0,destinsygvalue2.length-1);
				ToMemSet[] destinretsyg2=new ToMemSet[5];
				for(int i=0;i<5;i++) {
					destinretsyg2[i]=destinsygset2[i];
					int[] ranlist=new int[destinstlist2.get(destinsygset2[i].outlist).size()];
					for(int key:destinstlist2.get(destinsygset2[i].outlist).keySet()) {
						ranlist[i]=key;
					}
					destinretsyg2[i].start=String.valueOf(Brain0.random(ranlist));

				}
				int socount1=0;
				count: for (int i=0;i<5;i++) {
					if(stoutretsyg[i]==null) {
						break count;
					}else {
						socount1++;
					}
				}
				int socount2=0;
				count: for (int i=0;i<5;i++) {
					if(stoutretsyg2[i]==null) {
						break count;
					}else {
						socount2++;
					}
				}

				int dicount1=0;
				count: for (int i=0;i<5;i++) {
					if(destinretsyg[i]==null) {
						break count;
					}else {
						dicount1++;
					}
				}
				int dicount2=0;
				count: for (int i=0;i<5;i++) {
					if(destinretsyg2[i]==null) {
						break count;
					}else {
						dicount2++;
					}
				}

				IntegMemory sointeg=new IntegMemory();
				for(int i=0;i<socount1;i++) {
					sointeg.inmemorylist[i]=stin.get(stkey0);
					sointeg.outmemorylist[i]=stoutretsyg[i].outlist;
					sointeg.destlist[i]=stoutsygset[i].dest;
					sointeg.nowlist[i]=Integer.parseInt(stoutsygset[i].start);
				}
				for(int i=0;i<socount2;i++) {
					sointeg.inmemorylist[i+socount1]=stin.get(stkey1);
					sointeg.outmemorylist[i+socount1]=stoutretsyg2[i].outlist;
					sointeg.destlist[i+socount1]=stoutsygset2[i].dest;
					sointeg.nowlist[i+socount1]=stoutsygset2[i].now;
				}
				sointeg.correspond=true;
				integmemory(sointeg,base);
				IntegMemory diinteg=new IntegMemory();
				for (int i=0;i<dicount1;i++) {
					diinteg.inmemorylist[i]=destinretsyg[i].inlist;
					diinteg.outmemorylist[i]=destout.get(destkey0);
					diinteg.destlist[i]=destinsygset[i].dest;
					diinteg.nowlist[i]=destinsygset[i].now;
				}
				for (int i=0;i<dicount2;i++) {
					diinteg.inmemorylist[i+dicount1]=destinretsyg2[i].inlist;
					diinteg.outmemorylist[i+dicount1]=destout.get(destkey1);
					diinteg.destlist[i+dicount1]=destinsygset2[i].dest;
					diinteg.nowlist[i+dicount1]=stoutsygset2[i].now;
				}
				diinteg.correspond=true;
				integmemory(diinteg,base);
				/**
				 * in1,2,3,4,5->out1,in1,2,3,4,5->out2
				 * in1->out1,2,3,4,5,in2->out1,2,3,4,5
				 * correspomd nasi else de taiou dekiru? kentou yotei
				 */

				int[] keylist= {staddkey[0],staddkey[1],destaddkey[0],destaddkey[1]};

				for(int j=0;j<=1;j++) {
					for(int k=0;k<=1;k++) {
						IntegMemory newoutinteg=new IntegMemory();
						newoutinteg.destlist[0]=destset.get(keylist[2+k]).get(0).dest;
						newoutinteg.outmemorylist[0]=destset.get(keylist[2+k]).get(0).outlist;
						String assign=Brain0controll.Management.destparseListtoStr(assignmem(1,base));
						newoutinteg.startlist[0]=assign;
						int[] rand=new int[10];
						rand=randgene();
						newoutinteg.inmemorylist[0]=rand;
						//newoutinteg.bothside=true;
						integmemory(newoutinteg,base);
						IntegMemory newininteg=new IntegMemory();

						newininteg.startlist[0]=stset.get(keylist[j]).get(0).start;
						newininteg.inmemorylist[0]=stset.get(keylist[j]).get(0).inlist;
						newininteg.outmemorylist[0]=rand;
						newininteg.destlist[0]=assign;
						//newininteg.bothside=true;
						integmemory(newininteg,base);

					}
				}

			}
		}




		}
		static class ToMemSet{
			int[] inlist;
			int[] outlist;
			String start;
			String dest;
			int now;
		}
		/**
		 * quicksort key->narabekaeru hensuu d->junban no teigi left->0 right->d.length-1
		 * @param key
		 * @param d
		 * @param left
		 * @param right
		 */
	    static void quick_sort(int[] key,int[] d, int left, int right) {
	        if (left>=right) {
	            return;
	        }
	        int p = d[(left+right)/2];
	        int l = left, r = right, tmp,tmpkey;
	        while(l<=r) {
	            while(d[l] < p) { l++; }
	            while(d[r] > p) { r--; }
	            if (l<=r) {
	                tmp = d[l]; d[l] = d[r]; d[r] = tmp;
	                tmpkey=key[l];key[l]=key[r];key[r]=tmpkey;
	                l++; r--;
	            }
	        }
	        quick_sort(key,d, left, r);  // ピボットより左側をクイックソート
	        quick_sort(key,d, l, right); // ピボットより右側をクイックソート
	    }
/**
 * quicksort key->narabekaeru hensuu d->junban no teigi left->0 right->d.length-1
 * @param key
 * @param d
 * @param left
 * @param right
 */
	    static void quick_sort(String[] key,int[] d, int left, int right) {
	        if (left>=right) {
	            return;
	        }
	        int p = d[(left+right)/2];
	        int l = left, r = right, tmp;String tmpkey;
	        while(l<=r) {
	            while(d[l] < p) { l++; }
	            while(d[r] > p) { r--; }
	            if (l<=r) {
	                tmp = d[l]; d[l] = d[r]; d[r] = tmp;
	                tmpkey=key[l];key[l]=key[r];key[r]=tmpkey;
	                l++; r--;
	            }
	        }
	        quick_sort(key,d, left, r);  // ピボットより左側をクイックソート
	        quick_sort(key,d, l, right); // ピボットより右側をクイックソート
	    }
/**
 * quicksort key->narabekaeru hensuu d->junban no teigi left->0 right->d.length-1
 * @param key
 * @param d
 * @param left
 * @param right
 */
	    static void quick_sort(Object[] key,int[] d, int left, int right) {
	        if (left>=right) {
	            return;
	        }
	        int p = d[(left+right)/2];
	        int l = left, r = right, tmp;Object tmpkey;
	        while(l<=r) {
	            while(d[l] < p) { l++; }
	            while(d[r] > p) { r--; }
	            if (l<=r) {
	                tmp = d[l]; d[l] = d[r]; d[r] = tmp;
	                tmpkey=key[l];key[l]=key[r];key[r]=tmpkey;
	                l++; r--;
	            }
	        }
	        quick_sort(key,d, left, r);  // ピボットより左側をクイックソート
	        quick_sort(key,d, l, right); // ピボットより右側をクイックソート
	    }
/**
 * mom IntegMemory switchlist String[]
 * @author okada.yousuke
 *
 */

		public static class ToMemList{
			public IntegMemory mem;
			/**
			 * anterior-now
			 * keisiki 24-26,35-46
			 */
			public String[] switchlist;
			public void clear() {
				this.mem=null;
				this.switchlist=null;
			}
		}
		/**
		 * ThinkReturn keisiki de hensuu wo uketori 100made junnni trace siteiku
		 * tothink ga 2 to naruto loop wp mukete integmemory wo kidousuru
		 * imaha tomemory ga true nanode tuneni integmemory ga kidou suru(shuusei yotei)
		 * 100made trace sitara (kidou yoteiga arunara integmemory wo kidou site)kekka wo kaesite owaru
		 * 100 ikou nokotta jouhouha trace ni subete watasareru
		 * ShthinkMem niha kioku you no list wo tukutte ireru
		 * now ga 1000(->ShthinkMem.statechanged()) yori sita ha tmpmemory ryouiki to kanngaetearu(henkou yotei
		 * @param startthink
		 * @param base
		 * @return
		 */
		public static ShMemReturn shallowthink(ShMemReturn startthink,ShthinkMem memdump,Brainbase base){//memory class wo jissou site junban jouhou wo sokoni kiroku suru yotei(koujichuu)
			boolean tomemory=false;
			int tothink=0;
			tomemory=true;
			int tracecount=0;
			ArrayList<int[]> memsyglist=new ArrayList<int[]>();
			ArrayList<String> memdestlist=new ArrayList<String>();
			ArrayList<String> memstartlist=new ArrayList<String>();
			ArrayList<Integer> memnowlist=new ArrayList<Integer>();
			boolean todeep=false;
			ShMemReturn tnk=new ShMemReturn();
			tnk=startthink;//copy startthink -> tnk

			ArrayList<int[]> syglist=startthink.syglist;
			ArrayList<String> destlist=startthink.destlist;
			ArrayList<String> startlist=startthink.startlist;
			ArrayList<Integer> nowlist=startthink.nowlist;
			ArrayList<int[]> nowsyglist=startthink.nowsyglist;
			ArrayList<Integer> timelist=startthink.timelist;
			int[] samlist= {0,0,0,0,0,0,0,0,0,0};
	/**		TraceReturn ret1=trace("3-10",samlist);
			syglist.addAll(ret1.list);
			destlist.addAll(ret1.dest);
			startlist.addAll(ret1.start);
			nowlist.addAll(ret1.now);
			nowsyglist.addAll(ret1.nowsyg);**/

			outside: while((tnk.destlist.isEmpty()==false)&&(tracecount<100)) {
				tothink=1;
				if(nowlist.get(0)<50) {
					todeep=true;
				}else {

				}
				switch(tothink) {
				case 1:
				case 0://yameru kanngae
					break outside;
				case 2://kioku suru
					tomemory=true;
					break outside;
				}
	/**			if(tomemory==true) {
					memdestlist.add(destlist.get(0));
					memsyglist.add(syglist.get(0));
					memnowlist.add(nowlist.get(0));
					memstartlist.add(startlist.get(0));

				}**/
				TraceReturn ret3=trace(tnk.destlist.get(0),tnk.syglist.get(0),tnk.timelist.get(0));
				ShMemReturn tmptnk=new ShMemReturn();
				tmptnk.toShMemadd(ret3.toThinkReturn(tnk.nowlist.get(0)),true);
				//for(int i=0;i<tmptnk.destlist.size();i++) {//chouhuku siteru
				//	tmptnk.prenum.add(tnk.nowlist.get(0));
				//}
				tnk.add( tmptnk);
				memdump.list.add(tmptnk);
				memdump.shadd(tmptnk,tnk.prenum.get(0));// list.precounts mo nyuuryoku dekiteru
				tnk.firstdelete();
				base.tomemchange();//sakuseichuu

				tracecount++;

			}



		/**	if(tomemory=true) {
;
				IntegMemory tom=new IntegMemory();
				// sakusei yotei no basho dattakedo sakujo yotei
				integmemory(tom,base);
		}	**/
			return tnk;
		}
		public static class ShMemReturn extends ThinkReturn{
			ArrayList<Integer> prenum;
			public void toShMemadd(ThinkReturn tnk) {
				this.destlist.addAll(tnk.destlist);
				this.inlist.addAll(tnk.inlist);
				this.nowlist.addAll(tnk.nowlist);
				this.nowsyglist.addAll(tnk.nowsyglist);
				this.prenumlist.addAll(tnk.prenumlist);
				this.startlist.addAll(tnk.startlist);
				this.syglist.addAll(tnk.syglist);
				this.timelist.addAll(tnk.timelist);
				for(int i=0;i<tnk.destlist.size();i++) {
					this.prenum.add(-1);
				}
			}
			/**
			 * memnumlist ni atai ga haitte iru ThinkReturn no ShMemReturn heno hikitugikata
			 * memnumlist->prenum (not prenumlist)
			 * @param tnk
			 * @param memcount
			 */
			public void toShMemadd(ThinkReturn tnk,boolean memcount) {
				this.destlist.addAll(tnk.destlist);
				this.inlist.addAll(tnk.inlist);
				this.nowlist.addAll(tnk.nowlist);
				this.nowsyglist.addAll(tnk.nowsyglist);
				this.prenumlist.addAll(tnk.prenumlist);
				this.startlist.addAll(tnk.startlist);
				this.syglist.addAll(tnk.syglist);
				this.timelist.addAll(tnk.timelist);
				this.prenum.addAll(tnk.memnumlist);
			}
			public void add(ShMemReturn add) {
				this.syglist.addAll(add.syglist);
				this.destlist.addAll(add.destlist);
				this.startlist.addAll(add.startlist);
				this.nowlist.addAll(add.nowlist);
				this.nowsyglist.addAll(add.nowsyglist);
				this.timelist.addAll(add.timelist);
				this.prenumlist.addAll(add.prenumlist);
				this.prenum.addAll(add.prenum);
			}public void clear() {
				this.syglist.clear();
				this.destlist.clear();
				this.startlist.clear();
				this.nowlist.clear();
				this.nowsyglist.clear();
				this.timelist.clear();
				this.prenum.clear();
				this.prenumlist.clear();
			}
			public void firstdelete() {
				this.syglist.remove(0);
				this.destlist.remove(0);
				this.startlist.remove(0);
				this.nowlist.remove(0);
				this.nowsyglist.remove(0);
				this.timelist.remove(0);
				this.prenumlist.remove(0);
				this.prenum.remove(0);
			}
		}
		public static class ShthinkMem{
			public ArrayList<Integer> startlist;
			public ArrayList<Integer> endlist;
			public UnitUUID uuid;
			public ThinkReturnMem list;
			private int nowcount;
			public ShthinkMem() {
				nowcount=0;
			}
			public void shadd(ShMemReturn think,int pre) {
				for(int i=0;i<think.destlist.size();i++) {
					this.list.precounts.add(pre);
					this.startlist.add(pre);
					this.endlist.add(nowcount);
					switch (statechenged(think.prenumlist.get(i),think.nowlist.get(i))){
					case 1:
						this.uuid.shmemID.add(this.uuid.shmemID.get(think.prenum.get(i)));
						this.uuid.statesh.add(2);
						this.uuid.dememID.add("0");
						this.uuid.statede.add(0);
					case 2:
						this.uuid.shmemID.add("0");
						this.uuid.statesh.set(think.prenum.get(i), 3);
						this.uuid.statesh.add(0);
						this.uuid.dememID.add(UnitUUID.generateID());
						this.uuid.statede.add(1);
					case 3:
						this.uuid.shmemID.add(UnitUUID.generateID());
						this.uuid.statesh.add(1);
						this.uuid.dememID.add("0");
						this.uuid.statede.add(0);
						this.uuid.statede.set(think.prenum.get(i), 3);
					case 4:
						this.uuid.shmemID.add("0");
						this.uuid.statesh.add(0);
						this.uuid.dememID.add(this.uuid.dememID.get(think.prenum.get(i)));
						this.uuid.statede.add(2);
					}
					nowcount++;
				}
//uuid wo check site sakusei made okonau yotei
			}
			/**
			 * previous <1000		=>1000
			 * now <1000 >=1000	<1000 <=1000
			 * return 1 2	3 4
			 * 1000 yori sitaha tmpmemory wo soutei
			 * @param previous
			 * @param now
			 * @return
			 */
			private static int statechenged(int previous,int now) {
				if(previous<1000) {
					if(now<1000) {
						return 1;
					}else {
						return 2;
					}
				}else {
					if(now<1000) {
						return 3;
					}else {
						return 4;
					}
				}
			}
			public static class ThinkReturnMem extends ThinkReturn{
				protected ArrayList<Integer> precounts;//ShthinkMem no naka deno junban

			}
			public static class UnitUUID{
				public ArrayList<String> shmemID;
				public ArrayList<Integer> statesh;
				public ArrayList<String> dememID;
				public ArrayList<Integer> statede;
				public static String generateID() {
					return UUID.randomUUID().toString();
				}

			}

	}
	public static class ThinkReturn{
			public ArrayList<int[]> syglist=new ArrayList<int[]>();
			public ArrayList<int[]> inlist=new ArrayList<int[]>();
			public ArrayList<String> destlist=new ArrayList<String>();
			public ArrayList<String> startlist=new ArrayList<String>();
			public ArrayList<Integer> nowlist=new ArrayList<Integer>();
			public ArrayList<int[]> nowsyglist =new ArrayList<int[]>();
			public ArrayList<Integer> prenumlist=new ArrayList<Integer>();
			public ArrayList<Integer> timelist=new ArrayList<Integer>();
			private ArrayList<Integer> memnumlist=new ArrayList<Integer>();
			public void clear() {
				this.syglist.clear();
				this.destlist.clear();
				this.startlist.clear();
				this.nowlist.clear();
				this.nowsyglist.clear();
				this.timelist.clear();
				this.nowsyglist.clear();
			}
			public void add(ThinkReturn add) {
				this.syglist.addAll(add.syglist);
				this.destlist.addAll(add.destlist);
				this.startlist.addAll(add.startlist);
				this.nowlist.addAll(add.nowlist);
				this.nowsyglist.addAll(add.nowsyglist);
				this.timelist.addAll(add.timelist);
				this.prenumlist.addAll(add.prenumlist);
			}
			public void firstdelete() {
				this.syglist.remove(0);
				this.destlist.remove(0);
				this.startlist.remove(0);
				this.nowlist.remove(0);
				this.nowsyglist.remove(0);
				this.timelist.remove(0);
				this.prenumlist.remove(0);

			}

		}
		/**
		 * Integmemory ->kiroku suru memory unit no list
		 * bothside assignmem de wariate ->tomemory ni haiti ->jikkou
		 *  ->destination wo shuusei		-> in1 -> in2,3,4,5,6
		 *  IntegMemory in start,end etc..  -> in2 -> in1,3,4,5,6
		 *  			out x					...
		 * correspond		in1->out1 in2->out2 in3->out3 ...
		 * else			in1->out1,2,3,4,5,6 in2->out1,2,3,4,5,6 ...
		 *
		 * correspond
		 * @param mem
		 * @param base
		 */
		public static void integmemory(IntegMemory mem,Brainbase base) {
			Think thk=new Think();
			if (mem.bothside==true) {//inlist no data nomi atukau
				for(int i=0;i<mem.inmemorylist.length;i++) {
					int[] assignlist=assignmem(mem.inmemorylist.length-1,base);
					correctdest(assignlist,mem.nowlist[i]);
					ToMemory newmem=thk.new ToMemory();
					newmem.inmemorylist[0]=mem.inmemorylist[i];
					int[][] outlist=new int[mem.inmemorylist.length-1][10];
					if(i>=1) {
						System.arraycopy(mem.inmemorylist, 0, outlist, 0, i);
					}
					System.arraycopy(mem.inmemorylist, i+1, outlist, i,mem.inmemorylist.length-i-1 );
					newmem.outmemorylist=outlist;
					String[] outdest=new String[mem.inmemorylist.length-1];
					if(i>=1){
						System.arraycopy(mem.startlist, 0, outdest, 0, i);
					}
					System.arraycopy(mem.startlist, i+1, outdest, i, mem.startlist.length-i-1);
					newmem.destlist=outdest;
					newmem.startlist[0]=Management.destparseListtoStr(assignlist);
					tomemory(newmem);
				}

			}else if(mem.correspond==true){//1 tai 1 taiou no memory list wo tukuru/(start hushiyou
				int[] assignment=assignmem(mem.inmemorylist.length,base);
				ToMemory newmem=thk.new ToMemory();
				String assistr=Management.destparseListtoStr(assignment);
				for(int i=0;i<assignment.length;i++) {
					correctdest(assignment,mem.nowlist[i]);
					newmem.startlist[i]=assistr;
				}
				newmem.inmemorylist=mem.inmemorylist;
				newmem.outmemorylist=mem.outmemorylist;
				newmem.startlist=mem.destlist;
				tomemory(newmem);
			}else{
				for(int i=0;i<mem.inmemorylist.length;i++) {//hukusuuno in kara hukusuu no out wo kanngaeru tokini tukau
					int[] assignlist=assignmem(mem.outmemorylist.length,base);
					correctdest(assignlist,mem.nowlist[i]);
					ToMemory newmem=thk.new ToMemory();
					newmem.inmemorylist[0]=mem.inmemorylist[i];
					newmem.outmemorylist=mem.outmemorylist;
					newmem.startlist[0]=Management.destparseListtoStr(assignlist);
					newmem.destlist=mem.destlist;
					tomemory(newmem);
				}
			}
		}
		@SuppressWarnings({ "static-access" })
		public static int[] assignmem(int size,Brainbase base) {
			///int[] returnlist= {34,35,36};
			int[] retass=new int[2];
			retass=base.assign.newass(size);
			int[] retass2=new int[retass[1]-retass[0]+1];
			for(int i=0;i<retass2.length;i++) {
				retass2[i]=retass[0]+i;
			}
			return retass;
		}
		/**
		 * target no dest ni goal no list wo tuika suru
		 * destination No .txt no keisiki: 23,24,25,26(start)/78,79,90,95(destination)
		 * @param goal
		 * @param target
		 */
		public static void correctdest(int[] goal,int target) {

				File filein=new File("./quickdestination"+target+".txt");
				String[] gostrlist=new String[10];
						String gostr="";
				try {
					BufferedReader br=new BufferedReader(new FileReader(filein));
					String str =br.readLine();
					while(str!=null) {
						gostr=gostr+str;
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
				gostrlist=gostr.split("/");
				String start=gostrlist[0];
				String dest=gostrlist[1];
				int[] destlist=Management.destparseStrtoList(dest);
				int[] newdestlist=new int[destlist.length+goal.length];
				for(int i=0;i<destlist.length;i++) {
					newdestlist[i]=destlist[i];
				}for(int i=0;i<goal.length;i++) {
					newdestlist[destlist.length+i]=goal[i];
				}
				String newdeststr=Management.destparseListtoStr(newdestlist);
				quickdirection(target,start+"/"+newdeststr);


		}
		public static class IntegMemory{
			int[][] inmemorylist;
			int[][] outmemorylist;
			String[] startlist;
			String[] destlist;
			int[] nowlist;
			boolean bothside=true;
			boolean correspond=false;
		}
		/**
				 * quickmemory to direction no jikkou bubun(newass ha betu teigi)
				 * inmemorylist->true->outmemorylist
				 * 		0000	->false->random
				 * startlist->unit->destlist
				 * @param mem
				 */
				public static void tomemory(ToMemory mem) {
		/**			int[] sammemory= {1,4,3,5,7,8,6,7,5,0};
					int[] sammemory2= {-5,4,-3,0,-6,-2,-8,5,3,0};
					String startlist="35-40";
					int[] samstartlist= {35,36,37,38,39,40};
					String[] samdestlist= {"47-50","60-63","70-72","56-58","47-50","60-63"};
					samstartlist=Management.destparseStrtoList(startlist);

					for(int i=0;i<samstartlist.length;i++) {
						memory(samstartlist[i],sammemory,sammemory2);
						direction(samstartlist[i],startlist+"/"+samdestlist[i]);
					}
		**/
					for(int i=0;i<mem.startlist.length;i++) {
						int[] tmpmemorylist=Management.destparseStrtoList(mem.startlist[i]);
						quickmemory(tmpmemorylist[i],mem.inmemorylist[i],mem.outmemorylist[i]);
						quickdirection(tmpmemorylist[i],mem.startlist[i]+"/"+mem.destlist[i]);

					}

				}
		public class ToMemory{
			int[][] inmemorylist;
			int[][] outmemorylist;
			String[] startlist;
			String[] destlist;
		}
		/**
		 * run no atai wo list ni site kaesu range<=1 ni gentei
		 * TraceReturn list shuturyoku dest destination start hensuu start now unit no basho nowsyg hensuu list(nyuuryoku) time imano jikan
		 * @param start
		 * @param list
		 * @return
		 */
		public static TraceReturn trace(String start,int[] list,int time) {
			RunReturn ret=new RunReturn();
			int[] samno= {3,4,5,6,7};
			int[] saminlist= {3,5,-4,-6,0,5,-7,9,2,0};
			ret=run( start,list,time);
			int count=0;
			int[][] syglist=RunReturn.listlist(ret.list);
			int[] range=RunReturn.rangelist(ret.range);
			String[] dest=RunReturn.destlist(ret.destination);
			int[] now=RunReturn.nowlist(ret.now);
			int sygcount=ret.count;
			boolean decidereturn=false;
			TraceReturn ret2=new TraceReturn();

			for(int i=0;i<sygcount;i++) {

				if(Math.abs(range[i])<=1) {
					ret2.list.add(syglist[i]);
					ret2.dest.add(dest[i]);
					ret2.start.add(start);
					ret2.now.add(now[i]);
					ret2.nowsyg.add(list);
					ret2.time.add(time);
				}

			}

			return ret2;

		}
		public static class TraceReturn{
			ArrayList<int[]> list;
			ArrayList<String> dest;
			ArrayList<String> start;
			ArrayList<Integer> now;
			ArrayList<int[]> nowsyg;
			ArrayList<Integer> time;//time hituyou
			public int count;
			public ThinkReturn toThinkReturn(int prenum) {
				ThinkReturn tnk=new ThinkReturn();
				tnk.syglist=this.list;
				tnk.destlist=this.dest;
				tnk.startlist=this.start;
				tnk.nowlist=this.now;
				tnk.nowsyglist=this.nowsyg;
				for(int i=0;i<count;i++) {
					tnk.prenumlist.add(prenum);
				}
				tnk.timelist=this.time;
				return tnk;

			}
		}
		public static void memlist(ThinkReturn memlist){//sakuseichuu

		}
	}

}



