package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javaBrain2.InputPlace.AnalizeList;

public class UnitEdit {
/**
 * unitnumber.txt
 * 0-700;800-2000;3000-4000
 * (deleted)100-500;1000-1500;
 *
 * group.txt
 * (name)GroupA
 * (size)10.000;20.000;100.000
 *
 * @param args
 */
	public String unitnumber;
	public ArrayList<Integer> unitnumstart;
	public ArrayList<Integer> unitnumend;
	public DecimalFormat df=new DecimalFormat("#0.###");
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public UnitEdit() {

	}
	public void addunits(int count,String igroup,String ogroup,double ix,double iy,double iz,double irad,double ox,double oy,double oz,double orad) {
		double repressorrate=0.3;
		int No=0;
		for(int i=0;i<10000;i++) {
			int startno=i*500;
			if(checknumber(startno,startno+count-1)) {
				No=startno;
				break;
			}
		}
		for(int i=0;i<count;i++) {
			double random=Math.random();
			int reprate=(int)(random*1000/1);
			random=random*1000%1;
			addNI ni=new addNI(true);
			ni.inputgroup=igroup;
			ni.iiti=new double[3];
			ni.iiti[0]=round3(ix+irad*2*(int)(random*1000)/1000-irad);
			ni.iiti[1]=round3(iy+irad*2*(int)(random*1000000%1000)/1000-irad);
			ni.iiti[2]=round3(iz+irad*2*(int)(random*1000000000%1000000)/1000-irad);
			random=Math.random();
			ni.outputgroup=ogroup;
			ni.oiti[0]=round3(ix+irad*2*(int)(random*1000)/1000-irad);
			ni.oiti[1]=round3(iy+irad*2*(int)(random*1000000%1000)/1000-irad);
			ni.oiti[2]=round3(iz+irad*2*(int)(random*1000000000%1000000)/1000-irad);
			if((double)reprate/1000<=repressorrate) {
				ni.repressor=true;
			}else {
				ni.repressor=false;
			}
			addNeuroInfo(No+i,ni);
			couRmake(No+i);
			DAmake(No+i);
		}
		unitNoadd(No,No+count-1);
		unitNowrite();
	}
	public void addNeuroInfo(int no,addNI addinfo) {
		NeuronInfo ni=new NeuronInfo(no);
		NeuronInfo.InputXML xml=ni.new InputXML();
		xml.setBorder(addinfo.border);
		xml.setEntcborder(addinfo.entcborder);
		xml.setInputgroup(addinfo.inputgroup);
		xml.setOutputgroup(addinfo.outputgroup);
		xml.setRepressor(addinfo.repressor);
		ni.setIkkatu(xml,addinfo.iiti,addinfo.oiti);
		try {
			ni.write();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public void groupchange(String pastname,String newname) {
		try {
			String str="";
			boolean chenged=false;
			File wfile=new File("group.tmp.txt");
			File rfile=new File("group.txt");
			BufferedReader fileread=new BufferedReader(new FileReader(rfile));
			FileWriter filewrite=new FileWriter(wfile);
			str=fileread.readLine();
			if(str==pastname+"\n") {
				filewrite.write(newname+"\n");
				chenged=true;
			}else {
				filewrite.write(str);
			}
			filewrite.write("\n");
			filewrite.close();
			Path wpath=Paths.get("."+CommonVal.separator+"group.tmp.txt");
			Path rpath=Paths.get("."+CommonVal.separator+"group.txt");
			Files.copy(wpath, rpath,StandardCopyOption.REPLACE_EXISTING);
			Files.delete(wpath);
			AnalizeList glist=InputPlace.analizelist( CommonVal.input.readgroup(pastname));
			for (int No:glist.No) {
				NeuronInfo ip =new NeuronInfo(No);
				ip.readandinport(No);
				ip.setInputGroup(newname);
				ip.export();
				ip.write();
			}
			//outputplace ga dekitara naiyou wo tuika suru
		}catch(IOException e) {
			System.out.println(e);
		}

	}
	public void outinduct() {

	}
	public boolean checknumber(int start, int end) {//no wo ataete sorega gen number list ni aruka douka wo siraberu
		for(int i=0;i<unitnumstart.size();i++) {
			int bool=0;
			if(start<unitnumend.get(i)) {
				bool+=1;
			}
			if(end>unitnumstart.get(i)) {
				bool+=1;
			}
			if(bool%2==1) {
				return false;
			}
		}
		return true;

	}
	public class addNI{
		double border;
		int entcborder;
		String inputgroup;
		double[] iiti=new double[3];
		String outputgroup;
		double[] oiti=new double[3];
		int swicountborder;
		boolean repressor;
		public addNI(boolean construct) {
			if(construct==true) {
				border=0.1;
				entcborder=300000;
				swicountborder=30000;
				repressor=false;
			}
		}
		public addNI() {

		}
	}
	public void unitnummake() {
		try {
			File file=new File("unitnumber.txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write("");
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public void groupmake() {
		try {
			File file=new File("group.txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write("");
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public void couRmake(int No) {
		CountRecord cr=new CountRecord(No);
		NeuronInfo ni=new NeuronInfo(No);
		cr.Nocount=0;
		cr.entirecount=ni.getEntcBorder();
		cr.sw=new CountRecord.Switch(ni.getSwList().size());
		cr.sw.entcborder=ni.getSwiCountborder();
		cr.sw.entcborder=0;
		List<NeuronInfo.Switch> sl=ni.getSwList();
		for(int i=0;i<sl.size();i++) {
			cr.sw.toNo[i]=sl.get(i).swtoNo;
			cr.sw.count[i]=0;
			cr.sw.delay[i]=sl.get(i).delay;
			if((i>0)&&(cr.sw.toNo[i]==cr.sw.toNo[i-1])) {
				cr.sw.toNoorder[i]=cr.sw.toNoorder[i-1]+1;
			}else {
				cr.sw.toNoorder[i]=0;
			}
		};
		cr.write();
	}
	public void DAmake(int No) {
		try {
			File file=new File(CommonFunc.getPath( "desttimeamo",No,".txt"));
			FileWriter filewrite=new FileWriter(file);
			filewrite.write("");
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public void groupadd(String groupname,double xsize,double ysize,double zsize) {
		try {
			File wfile=new File("group.tmp.txt");
			File rfile=new File("group.txt");
			BufferedReader fileread=new BufferedReader(new FileReader(rfile));
			FileWriter filewrite=new FileWriter(wfile);
			filewrite.write(fileread.readLine());
			filewrite.write(groupname+"\n");
			filewrite.write(xsize+";"+ysize+";"+zsize);
			filewrite.close();
			Path wpath=Paths.get("."+CommonVal.separator+"group.tmp.txt");
			Path rpath=Paths.get("."+CommonVal.separator+"group.txt");
			Files.copy(wpath, rpath,StandardCopyOption.REPLACE_EXISTING);
			Files.delete(wpath);
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public void unitNoadd(int startno,int endno) {
		for(int i=0;i<unitnumstart.size();i++) {
			if(unitnumstart.get(i)<startno) {
				unitnumstart.add(i,startno);
				unitnumend.add(i,endno);
				break;
			}
		}
		if(unitnumstart.size()==0) {
			unitnumstart.add(startno);
			unitnumend.add(endno);
		}
	}
	public void unitNowrite() {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<unitnumstart.size();i++) {
			sb.append(unitnumstart.get(i));
			sb.append("-");
			sb.append(unitnumend.get(i));
		}
		try {
			File file=new File("unitnumber.txt");
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(sb.toString());
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	public void unitNoread() {
		String str="";
		try {
			File file=new File("unitnumber.txt");
			BufferedReader fileread=new BufferedReader(new FileReader(file));
			String tmpstr=fileread.readLine();
			while(str!=null) {
				str=str+tmpstr;
				tmpstr=fileread.readLine();
			}
			fileread.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		String[] strlist=str.split(";");
		for(String nos:strlist) {
			unitnumstart.add(Integer.parseInt(nos.split("-")[0]));
			unitnumend.add(Integer.parseInt(nos.split("-")[1]));
		}
	}
	public double round3(double value) {

		return Double.parseDouble(df.format(value));
	}
}
