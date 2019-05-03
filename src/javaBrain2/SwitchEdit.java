package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public class SwitchEdit {

	/**
	 * nearbycount+No+.txt kara yomikomi NeuroInfo no jouhou ni switch no henkou wo hanei si hozon suru
	 * timing?nearbycount.flush ga okonawareta timing kana?
	 * @param args
	 */
	public int defaultupcount=32;//32/10000*flush time(100 tanni)
	//public final double defaultstartamount=0.1;
	public double defaulterasebord=0.05;//0.05 under ->erase
	public boolean isnotdefault=false;
	public int ndupcount;
	public double nderasebord;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public SwitchEdit(){

	}
	public SwitchEdit(int upcount) {
		ndupcount=upcount;
		nderasebord=defaulterasebord;
		isnotdefault=true;
	}
	public SwitchEdit(int upcount,double erasebord) {
		ndupcount=upcount;
		nderasebord=erasebord;
		isnotdefault=true;
	}
	/**
	 * time no owarini flushtime wo koeta unit de 1kai okonau yotei
	 * upcount=defcount*(flushtime+passtime)/10000
	 * upcount ijou nara switch wo tuika suru amount ha 0.5*random->
	 * amount ga 0.05 ika nara switch wo sakujo suru->
	 * switch ga 10 ika mataha entcount ga upcount ijou nara random ni hitotu tikaku(nearbycount) nowo switch tosite tuika suru amount ha 0.5*random
	 * switch 10 ika to entcount de kiriwake?
	 * @param No
	 * @param passtime
	 */
	public void edit(int No,int passtime) {
		String timeinfo="";
		String delayinfo="";
		ArrayList<String[]> inputinfo=new ArrayList<String[]>();
		try {
			BufferedReader nearbyread=new BufferedReader(new FileReader(new File(CommonFunc.getPath("nearbycount", No, ".txt"))));
			timeinfo=nearbyread.readLine();
			delayinfo=nearbyread.readLine();
			String str=nearbyread.readLine();
			while(str!=null) {
				inputinfo.add(str.split(";"));
				str=nearbyread.readLine();
			}
			nearbyread.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		int flushtime=Integer.parseInt(timeinfo.split(";")[2]);
		String samstr="default";
		String samstr2="tilt:3;nearbybord:20";
		ArrayList<Integer> swaddNo=new ArrayList<Integer>();
		ArrayList<String> swadddelay=new ArrayList<String>();
		int upcount;
		double erasebord;
		if(isnotdefault==false) {
			upcount=defaultupcount*(flushtime+passtime)/10000;
			erasebord=defaulterasebord;
		}else {
			upcount=ndupcount*(flushtime+passtime)/10000;
			erasebord=nderasebord;

		}
		for(String[] list1:inputinfo) {
			for(int i=2;i<list1.length;i++) {
				if(Integer.parseInt(list1[i].split(">")[1])>upcount) {
					swaddNo.add(Integer.parseInt(list1[0]));
					swadddelay.add(list1[i].split(">")[0]);
				}
			}
		}
		NeuronInfo ni=new NeuronInfo(No);
		if(swaddNo.size()>0) {
			ni.readandinport(No);
			for(int i=0;i<swaddNo.size();i++) {
				ni.swAdd(swaddNo.get(i),Integer.parseInt( swadddelay.get(i)), CommonFunc.round3(Math.random()/2));
			}
			ni.export();
			try {
				ni.write();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		ni.readandinport(No);
		List<NeuronInfo.Switch> swlist=ni.getSwList();//0.05 ika wo kesu
		ArrayList<Integer> sweraseNo=new ArrayList<Integer>();
		ArrayList<Integer> swerasedelay=new ArrayList<Integer>();
		for(int i=0;i<swlist.size();i++) {
			if(swlist.get(i).amount<erasebord) {
				sweraseNo.add(swlist.get(i).swtoNo);
				swerasedelay.add(swlist.get(i).delay);
			}
		}
		if(sweraseNo.size()>0) {
			for(int i=0;i<sweraseNo.size();i++) {
				ni.swDelete(sweraseNo.get(i), swerasedelay.get(i));
			}
			ni.export();
			try {
				ni.write();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		ni.readandinport(No);
		if(ni.getSwList().size()>10) {
			CountRecord cr=new CountRecord(No);
			cr.read();
			if(cr.entirecount<=upcount) {
				return;
			}
		}
		String[] list2=delayinfo.split(";");
		ArrayList<String> list3=new ArrayList<String>();//1>100,1>200 no string wo tukutte ireru
		for(int i=0;i<list2.length;i++) {
			String[] list4=list2[i].split("/");
			switch(list4[1]) {
			case "1":list3.addAll(Arrays.asList( new String[]{list4[0]+">100",list4[0]+">200",list4[0]+">300"}));break;
			case "2":list3.addAll(Arrays.asList( new String[]{list4[0]+">200",list4[0]+">300",list4[0]+">400"}));break;
			case "3":list3.addAll(Arrays.asList( new String[]{list4[0]+">300",list4[0]+">400",list4[0]+">500"}));break;
			case "4":list3.addAll(Arrays.asList( new String[]{list4[0]+">400",list4[0]+">500",list4[0]+">600"}));break;
			case "5":list3.addAll(Arrays.asList( new String[]{list4[0]+">500",list4[0]+">600",list4[0]+">700"}));break;
			}
		}
		double rand=Math.random();
		int rand1=(int)(rand*10000);
		double rand2=rand*10000-rand1;
		String str2=list3.get((int)(rand2*list3.size()));
		String[] list5=str2.split(">");
		ni.swAdd(Integer.parseInt(list5[0]),Integer.parseInt(list5[1]),  CommonFunc.round3((double)rand1/20000));
		ni.export();
		try {
			ni.write();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void nearbymake(String outputgroup) {
		String[] readlist=CommonVal.outputp.readgroupbydivide(outputgroup);
		OutputPlace.AnalizeList[] analist=new OutputPlace.AnalizeList[readlist.length];
		OutputPlace.GroupInfo ginfo=CommonVal.outputp.getGinfo(outputgroup);
		for(int i=0;i<readlist.length;i++) {
			analist[i]=OutputPlace.analizelist(readlist[i]);
		}
		ArrayList<EnumMap<NBord,ArrayList<Integer>>> nearmap=new ArrayList<EnumMap<NBord,ArrayList<Integer>>>();
		for(int i=0;i<ginfo.entdivide;i++) {
			nearmap.set(i,new EnumMap<NBord,ArrayList<Integer>>(NBord.class));
			for(int j=0;j<ginfo.entdivide;j++) {
				if(Math.abs( ginfo.divide.get(i).ofsetx-ginfo.divide.get(j).ofsetx+ginfo.divide.get(j).sizex)<=5) {
					if(nearmap.get(i).get(NBord.OfX)==null) {
						nearmap.get(i).put(NBord.OfX,new ArrayList<Integer>());
						nearmap.get(i).get(NBord.OfX).add(j);
					}else {
						nearmap.get(i).get(NBord.OfX).add(j);
					}
				}if(Math.abs( ginfo.divide.get(i).ofsety-ginfo.divide.get(j).ofsety+ginfo.divide.get(j).sizey)<=5) {
					if(nearmap.get(i).get(NBord.OfY)==null) {
						nearmap.get(i).put(NBord.OfY,new ArrayList<Integer>());
						nearmap.get(i).get(NBord.OfY).add(j);
					}else {
						nearmap.get(i).get(NBord.OfY).add(j);
					}
				}if(Math.abs( ginfo.divide.get(i).ofsetz-ginfo.divide.get(j).ofsetz+ginfo.divide.get(j).sizez)<=5) {
					if(nearmap.get(i).get(NBord.OfZ)==null) {
						nearmap.get(i).put(NBord.OfZ,new ArrayList<Integer>());
						nearmap.get(i).get(NBord.OfZ).add(j);
					}else {
						nearmap.get(i).get(NBord.OfZ).add(j);
					}
				}if(Math.abs( ginfo.divide.get(i).ofsetx+ginfo.divide.get(i).sizex-ginfo.divide.get(j).ofsetx)<=5) {
					if(nearmap.get(i).get(NBord.X)==null) {
						nearmap.get(i).put(NBord.X,new ArrayList<Integer>());
						nearmap.get(i).get(NBord.X).add(j);
					}else {
						nearmap.get(i).get(NBord.X).add(j);
					}
				}if(Math.abs( ginfo.divide.get(i).ofsety+ginfo.divide.get(i).sizey-ginfo.divide.get(j).ofsety)<=5) {
					if(nearmap.get(i).get(NBord.Y)==null) {
						nearmap.get(i).put(NBord.Y,new ArrayList<Integer>());
						nearmap.get(i).get(NBord.Y).add(j);
					}else {
						nearmap.get(i).get(NBord.Y).add(j);
					}
				}if(Math.abs( ginfo.divide.get(i).ofsetz+ginfo.divide.get(i).sizez-ginfo.divide.get(j).ofsetz)<=5) {
					if(nearmap.get(i).get(NBord.Z)==null) {
						nearmap.get(i).put(NBord.Z,new ArrayList<Integer>());
						nearmap.get(i).get(NBord.Z).add(j);
					}else {
						nearmap.get(i).get(NBord.Z).add(j);
					}
				}
			}
		}
		String[] ireadlist=CommonVal.input.readgroupbydivide(outputgroup);
		InputPlace.GroupInfo iginfo=CommonVal.input.getGinfo(outputgroup);
		if(ginfo.entdivide!=iginfo.entdivide) {
			System.out.println("ginfo huitti nearbymake");
		}
		InputPlace.AnalizeList[] ianalist=new InputPlace.AnalizeList[iginfo.entdivide];
		for(int i=0;i<iginfo.entdivide;i++) {
			ianalist[i]=InputPlace.analizelist(ireadlist[i]);
		}
		ArrayList<ArrayList<EnumSet<NBord>>> iNBlist=new ArrayList<ArrayList<EnumSet<NBord>>>(ginfo.entdivide);
		for(int i=0;i<iginfo.entdivide;i++) {
			iNBlist.set(i, new ArrayList<EnumSet<NBord>>());
			for(int j=0;j<ianalist[i].No.size();j++) {
				iNBlist.get(i).set(j,EnumSet.noneOf(NBord.class));
				if(Math.abs( ianalist[i].x.get(j)-iginfo.divide.get(i).ofsetx)<=5) {
					iNBlist.get(i).get(j).add(NBord.OfX);
				}if(Math.abs( ianalist[i].y.get(j)-iginfo.divide.get(i).ofsety)<=5) {
					iNBlist.get(i).get(j).add(NBord.OfY);
				}if(Math.abs( ianalist[i].z.get(j)-iginfo.divide.get(i).ofsetz)<=5) {
					iNBlist.get(i).get(j).add(NBord.OfZ);
				}if(Math.abs( ianalist[i].x.get(j)-iginfo.divide.get(i).ofsetx-iginfo.divide.get(i).sizex)<=5) {
					iNBlist.get(i).get(j).add(NBord.X);
				}if(Math.abs( ianalist[i].y.get(j)-iginfo.divide.get(i).ofsety-iginfo.divide.get(i).sizey)<=5) {
					iNBlist.get(i).get(j).add(NBord.Y);
				}if(Math.abs( ianalist[i].z.get(j)-iginfo.divide.get(i).ofsetz-iginfo.divide.get(i).sizez)<=5) {
					iNBlist.get(i).get(j).add(NBord.X);
				}
			}
		}
		HashMap<Integer,ArrayList<String>> nbmap=new HashMap<Integer,ArrayList<String>>();
		for(int i=0;i<iginfo.entdivide;i++) {
			for(int k=0;k<analist[i].No.size();k++) {
				nbmap.put(analist[i].No.get(k),new ArrayList<String>());
				for(int j=0;j<ianalist[i].No.size();j++) {
					double pow=Math.pow(ianalist[i].x.get(j)-analist[i].x.get(k),2)+
							Math.pow(ianalist[i].y.get(j)-analist[i].y.get(k),2)+Math.pow(ianalist[i].z.get(j)-analist[i].z.get(k), 2);
					if(pow<=25) {
						nbmap.get(analist[i].No.get(k)).add(analist[i].No.get(k)+"/"+(int)(Math.sqrt(pow)));
					}
				}
				if(Math.abs(analist[i].x.get(k)-ginfo.divide.get(i).ofsetx)<=5) {
					for(EnumSet<NBord> inear: iNBlist.get(i)) {
						for(int j=0;j<inear.size();j++) {

								}
							}
						}
					}
				}

					}
	public enum NBord {
		OfX,
		OfY,
		OfZ,
		X,
		Y,
		Z;
	}
}
