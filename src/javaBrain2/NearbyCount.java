package javaBrain2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;

public class NearbyCount {
/**
 * nearbycount+No+.txt(<-output)
 * (ogroupname;)3540000;17000;50000(<-absolutetime;remaintime;flush time)
 * 34/1;43/2;55/3;67/5;...;
 * 34;0;100>23;200>1(<-input)
 * 43;2;300>12;400>1;500>2;600>1;700>1
 *
 * 1->100,200,300
 * 2->200,300,400
 * 3->300,400,500
 * 4->400,500,600
 * 5->500,600,700
 *
 * 0
 * 100 34,45,65,77
 * 200 32,33,35,56
 * 300 ...
 * ... ...
 * 700 ...
 * 0 kara jungurini nyuuryoku site
 * @param args
 */
	private enum Time{
		T0(0),
		T100(1),
		T200(2),
		T300(3),
		T400(4),
		T500(5),
		T600(6),
		T700(7);
		private int no;

		public Time next() {
			switch (this) {
			case T0: return Time.T100;
			case T100:	return Time.T200;
			case T200:	return Time.T300;
			case T300:	return Time.T400;
			case T400:	return Time.T500;
			case T500:	return Time.T600;
			case T600:	return Time.T700;
			case T700:	return Time.T0;
			}
			return null;
		}
		private Time(int no) {
			this.no=no;
		}
		public Time round(int time) {
			if(time>=100) {
				switch (time/100) {
				case 1: return Time.T100;
				case 2: return Time.T200;
				case 3: return Time.T300;
				case 4: return Time.T400;
				case 5: return Time.T500;
				case 6: return Time.T600;
				case 7: return Time.T700;
				}
				return null;
			}else {
				return Time.T100;
			}
		}
		public static Time byval(int No) {
			Time[] tlist=values();
			for(Time time:tlist) {
				if(No==time.no) {
					return time;
				}
			}
			return null;
		}
		public int getNo() {
			return this.no;
		}
		public Time prev() {
			switch (this) {
			case T0: return T700;
			case T100:return T0;
			case T200: return T100;
			case T300: return T200;
			case T400: return T300;
			case T500: return T400;
			case T600: return T500;
			case T700: return T600;
			default:
				return null;
			}
		}
		public Time plus(Time time) {
			if(this.getNo()+time.getNo()<=7) {
				return byval(this.getNo()+time.getNo());
			}else {
				return byval(this.getNo()+time.getNo()-7);
			}
		}
	}
	public HashMap<String,Integer> nowlist;
	public HashMap<Integer,Count[]> incountlist;//no->[time]->count
	public HashMap<Integer,Boolean> inNolist=new HashMap<Integer,Boolean>();
	public HashMap<String,HashMap<Integer,Noinfo>> outlist;//group->outno->time->outcount
	public HashMap<Integer,EntTime> entlist;
	private Time now=Time.T100;
	private int noworder=0;
	private BufferedReader fr;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public void add(int No,int time) {
		if(incountlist.containsKey(No)==false) {
			incountlist.put(No,new Count[8]);
		}
		incountlist.get(No)[noworder].count++;
		inNolist.put(No,true);
	}
	public void aggregate(int time) {
		int prev7=now.plus(Time.T100).getNo();
		for(int No :inNolist.keySet()) {
			//if(incountlist.get(No)[prev7]==null) {
			//	continue;//somosomo No no count jouhou ga nai baai
			//}
			try {
				fr=new BufferedReader(new FileReader(CommonFunc.getPath("nearbycount",No,".txt")));
				String str=fr.readLine();
				String[] list1=str.split(";");
				String outgroup=list1[0];
				int abstime=Integer.parseInt(list1[1]);
				int remtime=Integer.parseInt(list1[2]);
				int fltime=Integer.parseInt(list1[3]);
				if(outlist.get(outgroup).containsKey(No)==false) {
					//if(incountlist.get(No)[prev7].count==0) {
					//	continue;//hokano time no jouhou ha arukedo
					//}
					str=outlist.get(outgroup).get(No).nolist;
					String[] list2=str.split(";");
					for(String str2:list2) {
						String[] list3=str2.split("/");
						int inno=Integer.parseInt(list3[0]);
						EnumSet<Time> list4=EnumSet.noneOf(Time.class);
						switch(list3[1]) {
						case "1":list4.add(Time.T100);list4.add(Time.T200);list4.add(Time.T300);break;
						case "2":list4.add(Time.T200);list4.add(Time.T300);list4.add(Time.T400);break;
						case "3":list4.add(Time.T300);list4.add(Time.T400);list4.add(Time.T500);break;
						case "4":list4.add(Time.T400);list4.add(Time.T500);list4.add(Time.T600);break;
						case "5":list4.add(Time.T500);list4.add(Time.T600);list4.add(Time.T700);break;
						default :System.out.println("error nearbycount");
						}
						if(outlist.get(outgroup).get(No).inlist==null) {
							outlist.get(outgroup).get(No).inlist=new HashMap<Integer,InCount>();
						}
						for(Time time2:list4) {
							Time inplus=time2.plus(Time.byval(prev7));
							if(incountlist.get(inno)[inplus.getNo()].count==0) {
								continue;
							}
							if(outlist.get(outgroup).get(No).inlist.get(inno).time.contains(time2)) {
								outlist.get(outgroup).get(No).inlist.get(inno).count
								.set(outlist.get(outgroup).get(No).inlist.get(inno).time.indexOf(time2),outlist
										.get(outgroup).get(No).inlist.get(inno).count
										.get(outlist.get(outgroup).get(No).inlist.get(inno).time.indexOf(time2))+
										incountlist.get(No)[prev7].count*incountlist.get(inno)[inplus.getNo()].count);
							}else {
								outlist.get(outgroup).get(No).inlist.get(inno).time.add(time2);
								outlist.get(outgroup).get(No).inlist.get(inno).count.add(incountlist.get(No)[prev7].count
										*incountlist.get(inno)[inplus.getNo()].count);
							}
						}
					}
					int limittime=abstime+remtime-CommonVal.absolutetime;
					if(limittime<=0) {
						fr.readLine();
						flush(fltime,fr,No,outgroup);
						//abstime=Commomval.absolutetime;
						//remtime=fltime;
					}
					fr.close();
					continue;
				}else {
					str=fr.readLine();
					outlist.get(outgroup).put(No, new Noinfo());
					outlist.get(outgroup).get(No).nolist=str;
					String[] list2=str.split(";");
					for(String str2:list2) {
						String[] list3=str2.split("/");
						int inno=Integer.parseInt(list3[0]);
						EnumSet<Time> list4=EnumSet.noneOf(Time.class);
						switch(list3[1]) {
						case "1":list4.add(Time.T100);list4.add(Time.T200);list4.add(Time.T300);break;
						case "2":list4.add(Time.T200);list4.add(Time.T300);list4.add(Time.T400);break;
						case "3":list4.add(Time.T300);list4.add(Time.T400);list4.add(Time.T500);break;
						case "4":list4.add(Time.T400);list4.add(Time.T500);list4.add(Time.T600);break;
						case "5":list4.add(Time.T500);list4.add(Time.T600);list4.add(Time.T700);break;
						default :System.out.println("error nearbycount");
						}
						if(outlist.get(outgroup).get(No).inlist==null) {
							outlist.get(outgroup).get(No).inlist=new HashMap<Integer,InCount>();
						}
						for(Time time2:list4) {
							Time inplus=time2.plus(Time.byval(prev7));
							if(incountlist.get(inno)[inplus.getNo()].count==0) {
								continue;
							}
							if(outlist.get(outgroup).get(No).inlist.get(inno).time.contains(time2)) {
								outlist.get(outgroup).get(No).inlist.get(inno).count
								.set(outlist.get(outgroup).get(No).inlist.get(inno).time.indexOf(time2),outlist
										.get(outgroup).get(No).inlist.get(inno).count
										.get(outlist.get(outgroup).get(No).inlist.get(inno).time.indexOf(time2))+
										incountlist.get(No)[prev7].count*incountlist.get(inno)[inplus.getNo()].count);
							}else {
								outlist.get(outgroup).get(No).inlist.get(inno).time.add(time2);
								outlist.get(outgroup).get(No).inlist.get(inno).count.add(incountlist.get(No)[prev7].count
										*incountlist.get(inno)[inplus.getNo()].count);
							}
						}
					}
				}
				int limittime=abstime+remtime-CommonVal.absolutetime;
				if(limittime<=0) {
					flush(fltime,fr,No,outgroup);
					//abstime=Commomval.absolutetime;
					//remtime=fltime;
				}else {

				}
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


	}
	/**
	 * flush time no limit de kiroku sarenakatta unit no nearbycount wo subete kiroku suru
	 * switch no henkou ha okonawanai
	 */
	public void aggkiroku() {

	}
	/**
	 * memorino count wo file ni kiroku suru method
	 * limit time ga kita toki youto tochuude kiroku suru
	 * youtoni jouken bunki dekiru you atode tukurikaetai
	 * @param flushtime
	 * @param reader
	 * @param No
	 * @param outgroup
	 * @throws IOException
	 */
	public void flush(int flushtime,BufferedReader reader,int No,String outgroup) throws IOException{
		class Enums{
			public Time time;
			public int count=0;
			public Enums(Time delay) {
				this.time=delay;
			}
		}
		String str=reader.readLine();
		String nstr="";
		HashMap<Integer,ArrayList<Enums>> count=new HashMap<Integer,ArrayList<Enums>>();//outno->delay->inno
		ArrayList<int[]> innodelay=new ArrayList<int[]>();
		while(str!=null) {
			nstr=nstr+str;
			str=reader.readLine();
			String[] list1=str.split(";");
			int inno=Integer.parseInt(list1[0]);
			int[] tmp={inno,Integer.parseInt(list1[1])};
			innodelay.add(tmp);
			EnumSet<Time> enum2=State.getValue(list1[1]).settime();
			enum2.forEach(delay->count.get(inno).add(new Enums(delay)));
			for(int i=2;i<list1.length;i++) {
				String[] list2=list1[i].split(">");
				Time delay=Time.valueOf(Time.class, "T"+list2[0]);
				for(int j=0;j<count.get(inno).size();j++) {
					if(count.get(inno).get(j).time==delay) {
						count.get(inno).get(j).count+=Integer.parseInt(list2[1]);
					}
				}
			}
		}
		HashMap<Integer,InCount> outlist2=outlist.get(outgroup).get(No).inlist;
		for(int inno:outlist.get(outgroup).get(No).inlist.keySet()) {
			if(count.containsKey(inno)==false) {
				continue;
			}
			for(int i=0;i<3;i++) {//time ga 3tu made ni seigen sitearu chuui
				for(int j=0;j<count.get(inno).size();j++) {
					if(count.get(inno).get(j).time==outlist2.get(inno).time.get(i)) {
						count.get(inno).get(j).count+=outlist2.get(inno).count.get(i);
					}
				}
			}
		}

		FileWriter fw=new FileWriter(new File(CommonFunc.getPath( "nearbycount",No,".tmp.txt")));
		StringBuilder sb=new StringBuilder();
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(outgroup+';'+CommonVal.absolutetime+';'+flushtime+';'+flushtime);//group wo kakuka douka check!
		bw.newLine();
		compare();//limit ga 0 nara neuron wo (updown) henkou suru method
		for(int[] innol:innodelay) {
			sb.append(innol[0]).append('>').append(innol[1]).append(';');
		}
		sb.deleteCharAt(sb.lastIndexOf(";"));
		bw.write(sb.toString());
		bw.newLine();
		sb.replace(0, sb.length(), "");
		for(int[] inno3:innodelay) {
			sb.append(inno3[0]).append(';').append(inno3[1]).append(';');
			count.get(inno3[0]).forEach(enums->{
				sb.append(enums.time.toString().substring(1,4));
				sb.append(">").append(enums.time).append(';');
			});
			sb.deleteCharAt(sb.length()-1);
			bw.write(sb.toString());
			bw.newLine();
		}
		bw.close();
		String spa=FileSystems.getDefault().getSeparator();
		Files.copy(Paths.get(CommonFunc.getPath("nearbycount",No,".tmp.txt")),
				Paths.get(CommonFunc.getPath("nearbycount",No,".txt")), StandardCopyOption.REPLACE_EXISTING);
		Files.deleteIfExists(Paths.get(CommonFunc.getPath("nearbycount",No,".tmp.txt")));
		outlist.get(outgroup).get(No).inlist=new HashMap<Integer,InCount>();
	}
	public static enum State{//(flush)while bun no jouken bunki
		D1{
			public EnumSet<Time> settime(){
				return EnumSet.of(Time.T100, Time.T200, Time.T300);
			}
		},
		D2{
			public EnumSet<Time> settime(){
				return EnumSet.of(Time.T200, Time.T300, Time.T400);
			}

		},
		D3{
			public EnumSet<Time> settime(){
				return EnumSet.of(Time.T300, Time.T400, Time.T500);
			}
		},
		D4{
			public EnumSet<Time> settime(){
				return EnumSet.of(Time.T400, Time.T500, Time.T600);
			}
		},
		D5{
			public EnumSet<Time> settime(){
				return EnumSet.of(Time.T500, Time.T600, Time.T700);
			}
		},
		DN{
			public EnumSet<Time> settime(){
				return EnumSet.of(Time.T0);
			}
		};
		private State(){

		};
		public static State getValue(String dstr) {
			switch (dstr) {
			case "1":return State.D1;
			case "2":return State.D2;
			case "3":return State.D3;
			case "4":return State.D4;
			case "5":return State.D5;
			default:return State.DN;
			}
		}
		public abstract EnumSet<Time> settime();
	}
	/**
	 * limit ga 0 nara neuron wo (updown) henkou suru method
	 */
	public void compare() {

	}
	private void next() {
		inNolist.clear();
		now.next();
		noworder=now.getNo();
	}
	public class NearbyUnit {
		ArrayList<Integer> No;
		EnumMap<Time,Integer> delay;
	}
	public class Count{
		int count=0;
	}
	public class InCount{
		ArrayList<Time> time;
		ArrayList<Integer> count;
	}
	public class Noinfo{
		HashMap<Integer,InCount> inlist;
		String nolist;
	}
	public class EntTime{
		int now;
		int enttime;
	}
}
