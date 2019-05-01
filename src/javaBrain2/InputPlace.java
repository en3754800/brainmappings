package javaBrain2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

public class InputPlace {
	private ArrayList<Integer> gindex;
	private ArrayList<String> gname;
	private ArrayList<GroupInfo> ginfo;
	private ArrayList<ArrayList<Integer>> dindex;
	private ArrayList<Integer> wgindex;
	private ArrayList<String> wgname;
	private ArrayList<GroupInfo> wginfo;
	private ArrayList<ArrayList<Integer>> wdindex;
	private boolean wcopied=false;
	private ByteBuffer bb;
	private WriteGroup wgcontent=new WriteGroup();
/**
 * file keisiki
 * inputplace.txt
 * 0l list
 * 0z 00000001 groupID1(AA)
 * 0z 00000346 groupID2(BB)
 * 0z 00001012 groupID3(CC)
 *
 * 0g groupID1(AA) 100.000 20.000 10.000(<-size) 10.000 10.000 10.000(<-divided)
 * 0g 00000102 00000156 00000204(<-divide no iti)
 * 0d(<-divide) 1 0.000 0.000 0.000(<-ofset) 10.000 10.000 10.000(<-size)
 * 0x 2.324 4.236 1.114 00000340//(number ni henkou)neuronID(0Ad3865(<-0d,0x,0g,0z ga detekitara seisei sinaosu))// 32 12 2(<-folder bangou)
 * 0d end
 * bite buffer wo tukau FileChannel class wo tukauto yosasou
 * y=1/(2-x) up
 * y=2-1/x down (up limit=1, down limit=0(minus ni nattara 0 wo kagen tosuru))
 * f(t)=1-1/t(sankou)
 *
 * unicode 16(UTF-16)
 * public static final String NEWLINE=System.getProperty("line.separator");//kaigyouno teigi
 * @param args
 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		InputPlace ip=new InputPlace();
		ip.firstmakefile();


	}
	public void allocate() {
		bb=ByteBuffer.allocate(1024);

	}
	public String readdivide(String groupname,int divide) {
		if(gname.contains(groupname)) {
			if(dindex.get(gname.indexOf(groupname)).contains(divide)==false) {
				return "";
			}
			int gnindex=gname.indexOf(groupname);
			long bytescale=dindex.get(gnindex).get(divide);
			long dividesize=0;
			if(dindex.get(gnindex).size()>divide+1) {
				dividesize=dindex.get(gnindex).get(divide+1)-bytescale;
			}else {
				dividesize=ginfo.get(gnindex).byteend-bytescale;
			}
			String grstr="";
			bb.clear();
			try {
				FileChannel inputch=new FileInputStream("inputplace.txt").getChannel();
				if(dividesize==0) {
					dividesize=inputch.size();
				}
				long index=0;
				while(index<dividesize) {
					int length;
					if(index+1024<dividesize) {
						length=1024;
					}else {
						length=(int)(dividesize-index);
					}
					bb=inputch.map(FileChannel.MapMode.READ_ONLY, index+bytescale, length);
					grstr=grstr+bb.toString();
					index+=length;
				}

				inputch.close();
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return grstr;
		}else {
			return "";
		}
	}
	public String[] readgroupbydivide(String groupname) {//new
		if(gname.contains(groupname)) {
			int dividecount=ginfo.get(gname.indexOf(groupname)).entdivide;
			String[] ret=new String[dividecount];
			for(int i=0;i<dividecount;i++) {
				ret[i]=readdivide(groupname,i);
			}
			return ret;
		}
		return null;
	}
	public GroupInfo getGinfo(String groupname) {//new
		if(gname.contains(groupname)) {
			return ginfo.get(gname.indexOf(groupname));
		}else {
			System.out.println("does not contain this group getGinfo");
			return null;
		}
	}

	public String readgroup(String groupname) {
		if(gname.contains(groupname)) {
			int gnindex=gname.indexOf(groupname);
			long bytescale=gindex.get(gnindex);
			long groupsize=0;
			if(gindex.size()>gnindex+1) {
				groupsize=gindex.get(gnindex+1)-bytescale;
			}
			String grstr="";
			bb.clear();
			try {
				FileChannel inputch=new FileInputStream("inputplace.txt").getChannel();
				if(groupsize==0) {
					groupsize=inputch.size();
				}
				long index=0;
				while(index<groupsize) {

					int length;
					if(index+1024<groupsize) {
						length=1024;
					}else {
						length=(int)(groupsize-index);
					}
					bb=inputch.map(FileChannel.MapMode.READ_ONLY, index+bytescale, length);
					grstr=grstr+bb.toString();
					index+=length;
				}

				inputch.close();
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return grstr;
		}else {
			return "";
		}
	}
	public void readbytelist() {
		String rootlist="";
		String grouplist="";
		gindex=new ArrayList<Integer>();
		gname=new ArrayList<String>();
		ginfo=new ArrayList<GroupInfo>();
		dindex=new ArrayList<ArrayList<Integer>>();
		try {
			FileChannel inputch=new FileInputStream("inputplace.txt").getChannel();
			bb=inputch.map(FileChannel.MapMode.READ_ONLY, 0, 1024);
			rootlist=bb.toString();
			String[] str1=rootlist.split("\n");
			for(int i=0;i<str1.length;i++) {
				if(str1[i].startsWith("0l")) {
					for(int j=i+1;j<str1.length-i;j++) {
						if(str1[j].startsWith("0z")){
							ginfo.add(new GroupInfo());
							String[] str2=str1[j].split(" ");
							gindex.add(Integer.parseInt(str2[1]));
							gname.add(str2[2]);
						}else {
							break;
						}
					}
					break;
				}
			}
			for(int i=0;i<gname.size();i++) {
				bb=inputch.map(FileChannel.MapMode.READ_ONLY, gindex.get(i), 1024);
				grouplist=bb.toString();
				String[] str3=grouplist.split("\n");
				if(str3[0].startsWith("0g ")) {
					String[] str4=str3[0].split(" ");
					if(gname.get(i)==str4[1]) {
						ginfo.get(i).gname=str4[1];
						ginfo.get(i).bytestate=gindex.get(i);
						ginfo.get(i).sizex=Double.parseDouble(str4[2]);
						ginfo.get(i).sizey=Double.parseDouble(str4[3]);
						ginfo.get(i).sizez=Double.parseDouble(str4[4]);
						ginfo.get(i).dividex=Double.parseDouble(str4[5]);
						ginfo.get(i).dividey=Double.parseDouble(str4[6]);
						ginfo.get(i).dividez=Double.parseDouble(str4[7]);
						if(str3[1].startsWith("0g")) {
							String[] str5=str3[1].split(" ");
							ginfo.get(i).entdivide=str5.length-1;
							for(int j=0;j<ginfo.get(i).entdivide;j++) {
								dindex.get(i).add(Integer.parseInt(str5[j+1]));
								ginfo.get(i).divide.add(new DivideInfo());
								bb=inputch.map(FileChannel.MapMode.READ_ONLY, dindex.get(i).get(j), 1024);
								String str6=bb.toString();
								if(str6.startsWith("0d")) {
									String[] str7=str6.split("\n");
									String[] str8=str7[0].split(" ");
									ginfo.get(i).divide.get(j).bytestate=dindex.get(i).get(j);
									ginfo.get(i).divide.get(j).order=Integer.parseInt(str8[1]);
									ginfo.get(i).divide.get(j).ofsetx=Double.parseDouble(str8[2]);
									ginfo.get(i).divide.get(j).ofsety=Double.parseDouble(str8[3]);
									ginfo.get(i).divide.get(j).ofsetz=Double.parseDouble(str8[4]);
									ginfo.get(i).divide.get(j).sizex=Double.parseDouble(str8[5]);
									ginfo.get(i).divide.get(j).sizey=Double.parseDouble(str8[6]);
									ginfo.get(i).divide.get(j).sizez=Double.parseDouble(str8[7]);
								}else {
									System.out.println("error divide");
								}
							}
						}else {
							System.out.println("error group");
						}
					}else {
						System.out.println("error");
					}
				}
			}
			inputch.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
	public void wcopy() {
		wgindex=gindex;
		wgname=gname;
		wginfo=ginfo;
		wdindex=dindex;
		wcopied=true;
	}
	public ArrayList<Integer> mentionlist(String group,double x,double y,double z,double rad) {
		if(gname.contains(group)) {
			int index=gname.indexOf(group);
			int divide=ginfo.get(index).entdivide;
			ArrayList<Integer> dividelist=new ArrayList<Integer>();
			double powrad=Math.pow(rad, 2);
			dlist: for(int i=0;i<divide;i++) {
				double ofsetx=ginfo.get(index).divide.get(i).ofsetx;
				double ofsety=ginfo.get(index).divide.get(i).ofsety;
				double ofsetz=ginfo.get(index).divide.get(i).ofsetz;
				double sizex=ginfo.get(index).divide.get(i).sizex;
				double sizey=ginfo.get(index).divide.get(i).sizey;
				double sizez=ginfo.get(index).divide.get(i).sizez;
				ArrayList<double[]> list1=new ArrayList<double[]>();
				list1.add(new double[] {ofsetx,ofsety,ofsetz});
				list1.add(new double[] {ofsetx+sizex,ofsety,ofsetz});
				list1.add(new double[] {ofsetx,ofsety+sizey,ofsetz});
				list1.add(new double[] {ofsetx,ofsety,ofsetz+sizez});
				list1.add(new double[] {ofsetx+sizex,ofsety+sizey,ofsetz});
				list1.add(new double[] {ofsetx,ofsety+sizey,ofsetz+sizez});
				list1.add(new double[] {ofsetx+sizex,ofsety,ofsetz+sizez});
				list1.add(new double[] {ofsetx+sizex,ofsety+sizey,ofsetz+sizez});
				for(int j=0;j<8;j++) {
					if(powrad>Math.pow(x-list1.get(j)[0], 2)+Math.pow(y-list1.get(j)[1], 2)+
							Math.pow(z-list1.get(j)[2], 2)) {
						dividelist.add(i);
						continue dlist;
					}
				}
			}
			ArrayList<Integer> ret=new ArrayList<Integer>();
			for(int i=0;i<dividelist.size();i++) {
				AnalizeList list2=analizelist(readdivide(group,dividelist.get(i)));
				for(int j=0;j<list2.No.size();j++) {
					if(powrad>(Math.pow(x-list2.x.get(j), 2)+Math.pow(y-list2.y.get(j), 2)+Math.pow(z-list2.z.get(j), 2))) {
						ret.add(list2.No.get(j));
					}
				}
			}
			return ret;
		}else {
			System.out.print("the group not exist ,inputplace");
			return null;
		}
	}
	public void write() {
		if(wcopied==false) {
			System.out.println("error inputplace write");
			return;
		}
		try {
			FileInputStream br=new FileInputStream("inputplace.txt");
			FileChannel rc=br.getChannel();
			FileWriter iw=new FileWriter("inputplace.tmp.txt");
			StringBuilder list1=new StringBuilder();
			list1.append("0l list\n");
			for(int i=0;i<wgindex.size();i++) {
				list1.append("0z ");
				list1.append(String.format("%08d", wgindex.get(i)));
				list1.append(' ');
				list1.append(wgname.get(i));
				list1.append('\n');
			}
			list1.append('\n');
			iw.write(list1.toString());
			list1.delete(0,list1.length());
			for(int i=0;i<wgname.size();i++) {
				list1.append("0g ");
				list1.append(wgname.get(i));
				list1.append(' ');
				list1.append(wginfo.get(i).sizex).append(' ').append(wginfo.get(i).sizey)
				.append(' ').append(wginfo.get(i).sizez);
				list1.append(' ').append(wginfo.get(i).dividex).append(' ')
				.append(wginfo.get(i).dividey).append(' ').append(wginfo.get(i).dividez).append('\n');
				for(int j=0;j<dindex.get(i).size();j++) {
					list1.append("0d ").append(j+1).append(' ').append(wginfo.get(i).divide.get(j).ofsetx)
					.append(' ').append(wginfo.get(i).divide.get(j).ofsety).append(' ')
					.append(wginfo.get(i).divide.get(j).ofsetz).append(' ')
					.append(wginfo.get(i).divide.get(j).sizex).append(' ')
					.append(wginfo.get(i).divide.get(j).sizey).append(' ')
					.append(wginfo.get(i).divide.get(j).sizez).append('\n');
					iw.write(list1.toString());
					list1.delete(0,list1.length() );
					if((wgcontent.gorder==i)&&(wgcontent.dorder==j)) {
						iw.write(wgcontent.content);
					}else {
						int end=0;
						if(j+1<wginfo.get(i).entdivide) {
							end=wginfo.get(i).divide.get(j+1).bytestate-1;
						}
						String tmpstr=bufferread(rc,wginfo.get(i).divide.get(j).bytestate,end);
						tmpstr=tmpstr.substring(tmpstr.indexOf('\n')+1);
						tmpstr=tmpstr.substring(tmpstr.indexOf('\n')+1);
						iw.write(tmpstr);
					}
				}
			}
			rc.close();
			br.close();
			iw.close();
			FileChannel ich=new FileInputStream("inputplace.tmp.txt").getChannel();
			FileChannel och=new FileOutputStream("inputplace.txt").getChannel();
			readbytelist();
			ich.transferTo(0,ich.size(), och);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void close() {

	}
	private String bufferread(FileChannel ch,int start,int end) throws IOException {
		long size=end-start+1;
		if(size==0) {
			size=ch.size();
		}
		long index=0;
		String retstr="";
		while(index<size) {

			int length;
			if(index+1024<size) {
				length=1024;
			}else {
				length=(int)(size-index);
			}
			bb=ch.map(FileChannel.MapMode.READ_ONLY, index+start, length);
			retstr=retstr+bb.toString();
			index+=length;
		}
		return retstr;
	}
	private void firstmakefile() {
		StringBuffer list1=new StringBuffer();
		list1.append("0l list\n");
		list1.append("0z 00000000 ");
		list1.append("GroupA");
		list1.append("\n");
		list1.append("\n");
		String sam1=list1.toString();
		int bytesam1=sam1.getBytes().length;
		int replace=list1.indexOf("00000000");
		list1.replace(replace, replace+8, String.format("%08d", bytesam1+1));
		sam1=list1.toString();
		StringBuffer list2=new StringBuffer();
		list2.append("GroupA");
		list2.append(" ");
		list2.append(String.format("%.3f",100));
		list2.append(" ");
		list2.append(String.format("%.3f",20));
		list2.append(" ");
		list2.append(String.format("%.3f", 10));
		list2.append(" ");
		list2.append(String.format("%.3f",30));
		list2.append(" ");
		list2.append(String.format("%.3f", 20));
		list2.append(" ");
		list2.append(String.format("%.3f", 10));
		list2.append("\n");
		list2.append("0d 1 ");
		list2.append(String.format("%.3f",0));
		list2.append(" ");
		list2.append(String.format("%.3f",0));
		list2.append(" ");
		list2.append(String.format("%.3f",0));
		list2.append(" ");
		list2.append(String.format("%.3f",30));
		list2.append(" ");
		list2.append(String.format("%.3f",20));
		list2.append(" ");
		list2.append(String.format("%.3f",0));
		list2.append("\n");
		list2.append("0x ");
		String sam2=list2.toString();
		int bytesam2=sam2.getBytes().length;
		try {
			File file=new File("inputplace-sam.txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(sam1);
			filewrite.write(sam2);
			filewrite.write(bytesam2);
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	/**
	 * string keisikino list wo kaiseki site AnalizeList class ni irete kaesu
	 * @param liststr
	 * @return
	 */
	public static AnalizeList analizelist(String liststr) {
		InputPlace ip=new InputPlace();
		AnalizeList ret=ip.new AnalizeList();
		ret.x=new ArrayList<Double>();
		ret.y=new ArrayList<Double>();
		ret.z=new ArrayList<Double>();
		ret.No=new ArrayList<Integer>();
		String[] list1=liststr.split("\n");
		ArrayList<String> list2=new ArrayList<String>();
		for(int i=2;i<list1.length;i++) {
			String[] list3=list1[i].split(" ");
			list2.addAll(Arrays.asList(list3));
		}
		for(int i=0;i<(list2.size()-1)/4;i++) {
			ret.x.add(Double.parseDouble(list2.get(4*i+1)));
			ret.y.add(Double.parseDouble(list2.get(4*i+2)));
			ret.z.add(Double.parseDouble(list2.get(4*i+3)));
			ret.No.add(Integer.parseInt(list2.get(4*i+4)));
		}
		return ret;
	}
	/**
	 * unit wo atarasiku inputplace ni ireru ukeire keisiki ha
	 * AddUnit matomete irerrareru
	 * @param gname
	 * @param dorder -> divide order
	 * @param units
	 */
	public void divideadd(String gname,int dorder,AddUnit... units) {
		if(wcopied==false) {
			wcopy();
		}
		if(gname.contains(gname)) {
			wgcontent.gname=gname;
			wgcontent.gorder=gname.indexOf(gname);
			wgcontent.dorder=dorder;
			int end=0;
			if(dorder<ginfo.get(wgcontent.gorder).entdivide+1) {
				end=ginfo.get(wgcontent.gorder).divide.get(dorder+1).bytestate;
			}else {
				end=ginfo.get(wgcontent.gorder).byteend;
			}
			AnalizeList ana = null;
			int oldsize=0;
			try {
				FileChannel fc=new FileInputStream("inputplace.txt").getChannel();
				String str1=bufferread(fc,ginfo.get(wgcontent.gorder).divide.get(dorder).bytestate,end);
				ana=analizelist(str1);
				str1.substring(str1.indexOf('\n')+1);
				str1.substring(str1.indexOf('\n')+1);
				oldsize=str1.getBytes().length;
				fc.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			for(int i=0;i<units.length;i++) {
				ana.x.add(units[i].x);
				ana.y.add(units[i].y);
				ana.y.add(units[i].z);
				ana.No.add(units[i].No);
			}
			StringBuilder sb=new StringBuilder("0x ");
			for(int i=0;i<ana.No.size();i++) {
				sb.append(ana.x.get(i)).append(' ').append(ana.y.get(i)).append(' ')
				.append(ana.z.get(i)).append(' ').append(ana.No.get(i)).append(' ');
			}
			sb.deleteCharAt(sb.lastIndexOf(" "));
			wgcontent.content=sb.toString();
			int newsize=wgcontent.content.getBytes().length;
			int changesize=newsize-oldsize;
			if(ginfo.get(wgcontent.gorder).entdivide>wgcontent.dorder+1) {
				bytelistchange(wgcontent.gorder,wgcontent.dorder+1,changesize);
			}else if(wgcontent.gorder<gindex.size()){
				bytelistchange(wgcontent.gorder+1,0,changesize);
			}else {
				//saigono group-divide nanode hituyou nasi
			}
		}else {
			System.out.println("error divideadd");
		}
	}
	/**
	 * group henkou no tameno hinagata ni target no group mei to divide bangou wo ireru
	 * kokoni ireta group to divide dake sika ikkaini henkouha dekinai hukusuu group ni
	 * wakareru henkou ha hukusuukai write suru
	 * @param wname
	 * @param divide
	 */
	public void wgchange(String wname,int divide) {
		wgcontent.gname=wname;
		wgcontent.gorder=wgname.indexOf(wname);
		wgcontent.dorder=divide;
	}
	/**
	 * sitei sita divide kara sitawo junni keisan suru method
	 * tasitai ->pluscount +44
	 * hikitai ->pluscount -60
	 * @param gorder -> group order
	 * @param dorder -> divide order
	 * @param pluscount
	 */
	private void bytelistchange(int gorder,int dorder,int pluscount) {
		for(int i=dorder;i<ginfo.get(gorder).entdivide;i++) {
			wginfo.get(gorder).divide.get(dorder).bytestate+=pluscount;
		}
		for(int i=gorder+1;i<gindex.size();i++) {
			for(int j=0;j<ginfo.get(gorder).entdivide;j++) {
				wginfo.get(gorder).divide.get(dorder).bytestate+=pluscount;
			}
		}
	}
	public class AddUnit{
		public double x;
		public double y;
		public double z;
		public int No;
	}
	public class AnalizeList{
		public ArrayList<Double> x;
		public ArrayList<Double> y;
		public ArrayList<Double> z;
		public ArrayList<Integer> No;
	}
	private class WriteGroup{
		public String gname="";
		public int gorder=0;
		public int dorder=0;
		public String content="";
	}
	public class GroupInfo{
		public String gname;
		public int order;
		public int bytestate;
		public int byteend;
		public int entdivide;
		public double sizex;
		public double sizey;
		public double sizez;
		public double dividex;
		public double dividey;
		public double dividez;
		public ArrayList<DivideInfo> divide;
	}
	public class DivideInfo{
		public int order;
		public int bytestate;
		public double ofsetx;
		public double ofsety;
		public double ofsetz;
		public double sizex;
		public double sizey;
		public double sizez;
	}
}
