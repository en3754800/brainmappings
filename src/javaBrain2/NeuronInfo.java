package javaBrain2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NeuronInfo {
/**
 * file keisiki
 * neuroninfo+No+.xml
 * (root=unit)(inputxml)border/entirecountborder;inputgroup(AA);inputiti(iiti)((x)3.200/(y)2.455/(z)1.200);
 * 				outputgroup(AA);outputiti(oiti)(4.222/2.455/1.200);switchcountborder;false(=represser)  <-x/y/z
 * (list name=switch){(name=dest)swtoNo1/delay/amount;
 * (name=dest)swtoNo2/delay/amount;
 * (name=dest)34/200/0.356}      -->.xml
 * borderchenged->border.txt
 * iiti,igroupchanged->inputplace.txt
 * oiti,ogroupchanged->outputplace.txt(misakusei)
 * switchchenged->desttimeamo.txt(toNo,delay,amount) count.txt(toNo)
 * encborderchenged->count.txt
 * swcborderchenged->count.txt
 * @param args
 */
	public int neuNo;
	private Info neuinfo;
	private InputXML xml;
	private int changed;
	private boolean readfile=false;
	private boolean readsw=false;
	private boolean swchanged=false;
	class Switch{
		int swtoNo;
		int delay;
		double amount;
		public Switch(int toNo,int delay,double amount) {
			swtoNo=toNo;
			this.delay=delay;
			this.amount=amount;
		}
	}
	public NeuronInfo(int no) {
		this.neuNo=no;
	}
	private void changedValChanged(ValChanged val) {
		if(val.compare(changed)) {
		}else {
			changed+=val.addVal();
		}
	}
	private enum ValChanged{
		Border(1),
		Uflushtime(2),
		InputG(3),
		InputIti(4),
		OutputG(5),
		OutputIti(6),
		Flushtime(7),
		Repressor(8),
		ToNo(9),
		ToDelay(10),
		ToAmount(11);
		private int no;
		public int getNo() {
			return no;
		}
		private ValChanged(int no){
			this.no=no;
		}
		public boolean compare(int obj) {
			return obj % (2^this.no) >=(2^(this.no-1));
		}
		public int addVal() {
			return 2^this.no;
		}
	}
	public double getBorder() {
		return neuinfo.border;
	}
	public Boolean setBorder(double border) {
		if(neuinfo.border==0) {
			if(xml.getBorder()!=0) {
				if(xml.getEntcborder()!=border) {
					neuinfo.border=border;
				}else {
					return false;
				}
			}else {
				neuinfo.border=border;
			}
		}else {
			if(neuinfo.border!=border) {
				neuinfo.border=border;
			}else {
				return false;
			}
		}
		changedValChanged(ValChanged.Border);
			return true;
	}
	public int getEntcBorder() {
		return neuinfo.entirecountborder;
	}
	public Boolean setEntcBorder(int entcborder) {
		if(neuinfo.entirecountborder==0) {
			if(xml.getEntcborder()!=0) {
				if(xml.getEntcborder()!=entcborder) {
					neuinfo.entirecountborder=entcborder;
				}else {
					return false;
				}
			}else {
				neuinfo.entirecountborder=entcborder;
			}
		}else {
			if(neuinfo.entirecountborder!=entcborder) {
				neuinfo.entirecountborder=entcborder;
			}else {
				return false;
			}
		}
		changedValChanged(ValChanged.EntcBorder);
		return true;
	}
	public String getInputGroup() {
		return neuinfo.inputgroup;
	}
	public Boolean setInputGroup(String inputgroup) {
		if(neuinfo.inputgroup==null) {
			if(xml.getInputgroup()!=null) {
				if(xml.getInputgroup()!=inputgroup) {
					neuinfo.inputgroup=inputgroup;
				}else {
					return false;
				}
			}else {
				neuinfo.inputgroup=inputgroup;
			}
		}else {
			if(neuinfo.inputgroup!=inputgroup) {
				neuinfo.inputgroup=inputgroup;
			}else {
				return false;
			}
		}
		changedValChanged(ValChanged.InputG);
		return true;
	}

	public double[] getInputIti() {
		double[] iti=new double[3];
		iti[0]=neuinfo.inputiti.getX();
		iti[1]=neuinfo.inputiti.getY();
		iti[2]=neuinfo.inputiti.getZ();
		return iti;
	}
	/**
	 * henshu yotei?
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setInputIti(double x,double y,double z) {
		neuinfo.inputiti.setX(x);
		neuinfo.inputiti.setY(y);
		neuinfo.inputiti.setZ(z);
		changedValChanged(ValChanged.InputIti);
	}
	public int getSwiCountborder() {
		return neuinfo.swicborder;
	}
	public void setSwiCountborder(int swicountborder) {
		neuinfo.swicborder=swicountborder;
		changedValChanged(ValChanged.SwiCBorder);
	}
	/**
	 * copy from inputgroup check yotei?
	 * @param outputgroup
	 * @return
	 */
	public Boolean setOutputGroup(String outputgroup) {
		if(neuinfo.outputgroup==null) {
			if(xml.getOutputgroup()!=null) {
				if(xml.getOutputgroup()!=outputgroup) {
					neuinfo.outputgroup=outputgroup;
				}else {
					return false;
				}
			}else {
				neuinfo.outputgroup=outputgroup;
			}
		}else {
			if(neuinfo.outputgroup!=outputgroup) {
				neuinfo.outputgroup=outputgroup;
			}else {
				return false;
			}
		}
		changedValChanged(ValChanged.InputG);
		return true;
	}
	public void setIkkatu(InputXML xml2,double[] iiti,double[] oiti) {
		if(changed!=0) {
			if((xml2.border>0)&&(xml2.entcborder!=0)&&(xml2.inputgroup!="")&&(xml2.outputgroup!="")) {
				xml=xml2;
				xml.inputiti=new Coordinate();
				xml.inputiti.x=iiti[0];
				xml.inputiti.y=iiti[1];
				xml.inputiti.z=iiti[2];
				xml.outputiti=new Coordinate();
				xml.outputiti.x=oiti[0];
				xml.outputiti.y=oiti[1];
				xml.outputiti.z=oiti[2];
				export();
			}
		}
	}
	public boolean SwChanged() {
		return swchanged;
	}
	public List<Switch> getSwList(){
		return neuinfo.switches;
	}
	public static void main(String[]args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public NeuronInfo read(int No){
		File file=new File(CommonFunc.getPath( "neuroninfo",No,".xml"));
		neuNo=No;
		ParentXML par=JAXB.unmarshal(file, ParentXML.class);
		if(par.type.equals(xml)) {
			xml=(InputXML) par.type;
		}
		readfile=true;
		readsw=true;
		return this;
	}
	/**
	 * file wo yomikonde changed ni henkouga nakereba neuinfo ni inport suru
	 * @param No
	 */
	public void readandinport(int No) {
		read(No);
		if(changed==0) {
			inport();
		}else {
			System.out.println("InfoChangedException!");
		}
	}
	/**
	 * NeuronInfo.xml no jouhou wo uwagakide subete NeuronInfo.neuinfo ni kakikomu
	 * neuinfo no jouhou ha subete uwagaki sarerunode chuui
	 * @return
	 */
	private NeuronInfo inport(){
		neuinfo.border=xml.border;
		neuinfo.entirecountborder=xml.entcborder;
		neuinfo.inputgroup=xml.inputgroup;
		neuinfo.inputiti=xml.inputiti;
		neuinfo.outputgroup=xml.outputgroup;
		neuinfo.outputiti=xml.outputiti;
		neuinfo.represser=xml.repressor;
		List<Switch> tmpsw=new ArrayList<Switch>();
		for(int i=0;i<xml.sw.size();i++) {
			tmpsw.add(new Switch(xml.sw.get(i).tono,xml.sw.get(i).delay,xml.sw.get(i).amount));
		}
		neuinfo.switches=null;
		neuinfo.switches=tmpsw;
		return this;
	}
	/**
	 * switch nomi inportsuru
	 * @return
	 */
	public NeuronInfo swinport() {
		if(readsw==false) {
			read(neuNo);
			readsw=true;
		}
		List<Switch> tmpsw=new ArrayList<Switch>();
		for(int i=0;i<xml.sw.size();i++) {
			tmpsw.add(new Switch(xml.sw.get(i).tono,xml.sw.get(i).delay,xml.sw.get(i).amount));
		}
		neuinfo.switches=tmpsw;
		return this;
	}
	public void swAdd(int No,int delay,double amount) {
		if(readsw==false) {
			swinport();
		}
		int match=-1;
		for(int i=0;i<neuinfo.switches.size();i++) {
			if(neuinfo.switches.get(i).swtoNo==No) {
				match=i;
			}
		}
		if(match!=-1) {
			neuinfo.switches.add(match+1, new Switch(No,delay,amount));
		}else {
			neuinfo.switches.add(new Switch(No,delay,amount));
		}
		swchanged=true;
	}
	public void swDelete(int No,int delay) {
		if(readsw==false) {
			swinport();
		}
		for(int i=0;i<neuinfo.switches.size();i++) {
			if((neuinfo.switches.get(i).swtoNo==No)&&(neuinfo.switches.get(i).delay==delay)) {
				neuinfo.switches.remove(i);
			}
		}
		swchanged=true;
	}
	public void swdelayChange(int No,int predelay,int postdelay) {
		if(readsw==false) {
			swinport();
		}
		for(int i=0;i<neuinfo.switches.size();i++) {
			if((neuinfo.switches.get(i).swtoNo==No)&&(neuinfo.switches.get(i).delay==predelay)) {
				neuinfo.switches.get(i).delay=postdelay;
			}
		}
		swchanged=true;
	}
	public void swamountChange(int No,int delay,double postamount) {
		if(readsw==false) {
			swinport();
		}
		for(int i=0;i<neuinfo.switches.size();i++) {
			if((neuinfo.switches.get(i).swtoNo==No)&&(neuinfo.switches.get(i).delay==delay)) {
				neuinfo.switches.get(i).amount=postamount;
			}
		}
		swchanged=true;
	}
	/**
	 * neuinfo no data ha subete sakujo siteru
	 * @param swlist
	 */
	public void swChange(List<Switch> swlist) {
		neuinfo.switches=null;
		neuinfo.switches=swlist;
		swchanged=true;
	}
	public NeuronInfo export() {
		if(readfile==false) {
			read(neuNo);
		}
		if(ValChanged.Border.compare(changed)) {
			xml.setBorder(neuinfo.border);
		}if(ValChanged.EntcBorder.compare(changed)) {
			xml.setEntcborder(neuinfo.entirecountborder);
		}if(ValChanged.InputG.compare(changed)) {
			xml.setInputgroup(neuinfo.inputgroup);
		}if(ValChanged.InputIti.compare(changed)) {
			xml.setInputiti(neuinfo.inputiti);
		}if(ValChanged.OutputG.compare(changed)) {
			xml.setOutputgroup(neuinfo.outputgroup);
		}if(ValChanged.OutputIti.compare(changed)) {
			xml.setOutputiti(neuinfo.outputiti);
		}if(ValChanged.SwiCBorder.compare(changed)) {
			xml.setSwcountborder(neuinfo.swicborder);
		}if(ValChanged.Repressor.compare(changed)) {
			xml.setRepressor(neuinfo.represser);
		}
		if(swchanged=true) {
			List<SwitchI> tmplist=new ArrayList<SwitchI>(neuinfo.switches.size());
			for(int i=0;i<neuinfo.switches.size();i++) {
				tmplist.get(i).tono=neuinfo.switches.get(i).swtoNo;
				tmplist.get(i).delay=neuinfo.switches.get(i).delay;
				tmplist.get(i).amount=neuinfo.switches.get(i).amount;
			}
			int[] sort=new int[neuinfo.switches.size()];
			for(int i=0;i<neuinfo.switches.size();i++) {
				sort[i]=neuinfo.switches.get(i).swtoNo;
			}
			SwitchI[] tmparr=tmplist.toArray(new SwitchI[0]);
			quick_sort(tmparr,sort,0,sort.length-1);
			tmplist=Arrays.asList(tmparr);
			int matchco=0;//count
			int matchor=-1;//order
			for(int i=0;i<neuinfo.switches.size();i++) {
				if(tmparr[i].tono==tmparr[matchor].tono) {
					matchco++;
				}else {
					if(matchco>1) {
						int[] matchde=new int[i-1-matchor];
						for(int j=0;j<i-1-matchor;i++) {
							matchde[j]=tmparr[j].delay;
						}
						SwitchI[] tmpard=Arrays.copyOfRange(tmparr, matchor, i-1);
						quick_sort(tmpard,matchde,0,i-2-matchor);
						for(int j=matchor;i<i-1;j++) {
							tmparr[j]=tmpard[j-matchor];
						}
					}
					matchco=1;
					matchor=i;
				}
			}
			xml.setSw(tmplist);
		}
		if(ValChanged.ToDelay.compare(changed)) {
			/**
			 * delay dake kaetara sakini hozon sita houga yosasou
			 * tono no junbanga tadasiikotowo kakunin site junbanga
			 * tadasiijunn de arukotowo kakunin sita to suru
			 */
			//List<SwitchI> xmllist=xml.getSw();
			//for(int i=0;i<neuinfo.switches.size();i++) {
			//	if(xml.getSw().get(i).getTono()==neuinfo.switches.get(i).swtoNo) {
			//		xmllist.get(i).setDelay(neuinfo.switches.get(i).swtoNo);
			//	}
			//}
			//xml.setSw(xmllist);
		}
		if(ValChanged.ToAmount.compare(changed)) {
			for(int i=0;i<neuinfo.switches.size();i++) {

			}
			//xml.setInputgroup(neuinfo.inputgroup);
		}
		return this;
	}
	/**
	 * file ni kakikomu read(or readandinport) henkou export wo okonatte okanaito data ga
	 * hunsitu simasu
	 * henkou wo hanei suruniha updatefiles() mo jikkou suru hituyouga arimasu
	 * @throws IOException
	 */
	public void write() throws IOException{
		try {
			ParentXML par=new ParentXML();
			par.type=xml;
			JAXB.marshal(par,CommonFunc.getPath( "neuroninfo",neuNo,".xml"));
		}catch(DataBindingException e){
			throw new IOException(e);
		}
	}
	/**
	 * count No .txt to desttimeamo No .txt border No .txt wo saisin no file ni kousin suru
	 * xml.sw wo source to suru
	 */
	public void updatefiles() {
		updatememDA();
		updatecount();
		updateborder();
	}
	/**
	 * this.xml kara hituyouna jouhou wo gousei site kakikomu
	 * export de junban wo soroete irunoga zentei joukenn
	 */
	private void updatecount() {
		CountRecord cr=new CountRecord(neuNo);
		boolean crReadandChange=false;
		if(swchanged==true) {
			if(ValChanged.ToNo.compare(changed)) {
				cr.read();
				CountRecord.Switch sw=cr.sw;
				CountRecord.Switch newsw=new CountRecord.Switch(xml.sw.size());
				newsw.entcborder=xml.entcborder;
				newsw.entirecount=cr.entirecount;
				for(int i=0;i<xml.sw.size();i++) {
					newsw.toNo[i]=xml.sw.get(i).tono;
					newsw.delay[i]=xml.sw.get(i).delay;
					for(int j=0;j<sw.toNo.length;j++) {
						if((newsw.toNo[i]==sw.toNo[j])&&(newsw.delay[i]==sw.delay[j])) {
							newsw.count[i]=sw.count[j];
							break;
						}
					}
					if((i>0)&&(newsw.toNo[i-1]==newsw.toNo[i])) {
						newsw.toNoorder[i]=newsw.toNoorder[i-1]+1;
					}
				}
				cr.sw=newsw;
				crReadandChange=true;
			}
		}if(ValChanged.SwiCBorder.compare(changed)) {
			if(crReadandChange==false) {
				cr.read();
			}
			cr.sw.entcborder=xml.swcountborder;
			crReadandChange=true;
		}if(ValChanged.EntcBorder.compare(changed)) {
			//count gawa ni taiou suru noga nainode sakusei horyuuchuu
		}
		cr.write();
	}
	/**
	 * export ga owatteiru kotoga zentei
	 */
	private void updatememDA() {
		if(swchanged==true) {
			DelayAmount da=new DelayAmount(neuNo);
			HashMap<DelayAmount.NoandOrder,DelayAmount.Unit> un=new HashMap<DelayAmount.NoandOrder,DelayAmount.Unit>();
			int[] order=new int[xml.sw.size()];
			for(int i=0;i<xml.sw.size();i++) {
				if((i>1)&&(xml.sw.get(i).tono==xml.sw.get(i-1).tono)) {
					order[i]=order[i-1]+1;
				}
				DelayAmount.Unit unit=new DelayAmount.Unit();
				unit.time=xml.sw.get(i).delay;unit.amount=xml.sw.get(i).amount;
				un.put(new DelayAmount.NoandOrder(xml.sw.get(i).tono,order[i]),unit);
			}
			da.unitNo=un;
			da.write();
		}
	}
	private void updateborder() {
		if(ValChanged.Border.compare(changed)==true) {
			SingleNeuron.writeborder(neuNo, xml.border);
		}
	}
	public String[] pickElem(String elemname,int retlength) {
		File saxf=new File(CommonFunc.getPath( "neuroninfo",neuNo,".xml"));
		SAXParserFactory factory=SAXParserFactory.newInstance();
		String[] ret=new String[retlength];
		try {
			SAXParser parser=factory.newSAXParser();
			NISAXhandler handler=new NISAXhandler();
			parser.parse(saxf,handler);
			ret=(String[])handler.tarvalue.toArray();
		} catch (ParserConfigurationException | SAXException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return ret;
	}
	private class Info{
		public double border;
		public int entirecountborder;
		public String inputgroup;
		public Coordinate inputiti;
		public String outputgroup;
		public Coordinate outputiti;
		public int swicborder;
		public boolean represser;
		public List<Switch> switches;
	}
	/**
	 * jaxb de xml tono henkanwo surutokino oya class
	 * @author okada.yousuke
	 *
	 */
	@XmlRootElement(name="unit")
	public class ParentXML{
		@XmlElements(
			value={
				@XmlElement(name="inputxml",type=InputXML.class),
				@XmlElement(name="purkinjexml",type=Purkinje.PurkinjeXML.class)
			}
		)
		private UnitType type;
		public void setType(UnitType type){
			this.type=type;
		}
		public UnitType getType() {
			return this.type;
		}

	}
	public class UnitType{

	}

	@XmlType(propOrder= {"border","entcborder","inputgroup","inputiti",
			"outputgroup","outputiti","swcountborder","repressor","sw"})
	public class InputXML extends UnitType{
		public InputXML() {};
		private double border;
		private int entcborder;
		private String inputgroup;
		private Coordinate inputiti;
		private String outputgroup;
		private Coordinate outputiti;
		private int swcountborder;
		private boolean repressor;
		private List<SwitchI> sw;
		public double getBorder() {
			return border;
		}
		public void setBorder(double border) {
			this.border=border;
		}
		public int getEntcborder() {
			return entcborder;
		}
		public void setEntcborder(int entcborder) {
			this.entcborder=entcborder;
		}
		public String getInputgroup() {
			return inputgroup;
		}
		public void setInputgroup(String inputgroup) {
			this.inputgroup=inputgroup;
		}
		@XmlElement(name="iiti")
		public Coordinate getInputiti() {
			return inputiti;
		}
		public void setInputiti(Coordinate inputiti) {
			this.inputiti=inputiti;
		}
		public String getOutputgroup() {
			return outputgroup;
		}
		public void setOutputgroup(String outputgroup) {
			this.outputgroup=outputgroup;
		}
		@XmlElement(name="oiti")
		public Coordinate getOutputiti() {
			return outputiti;
		}
		public void setOutputiti(Coordinate outputiti) {
			this.outputiti=outputiti;
		}
		public int getSwcountborder() {
			return swcountborder;
		}
		public void setSwcountborder(int swcountborder) {
			this.swcountborder=swcountborder;
		}
		public boolean getRepressor() {
			return repressor;
		}
		public void setRepressor(boolean repressor) {
			this.repressor=repressor;
		}
		@XmlElementWrapper(name="downstream")
		@XmlElement(name="dest")
		public List<SwitchI> getSw(){
			return sw;
		}
		public void setSw(List<SwitchI> list) {
			this.sw=list;
		}

	}
	public class SwitchI{
		private int tono;
		private int delay;
		private double amount;
		public SwitchI() {}
		public SwitchI(int tono,int delay,double amount) {
			this.tono=tono;
			this.delay=delay;
			this.amount=amount;
		}
		public int getTono() {
			return tono;
		}
		public void setTono(int tono) {
			this.tono=tono;
		}
		public int getDelay() {
			return tono;
		}
		public void setDelay(int delay) {
			this.delay=delay;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount=amount;
		}
	}
	@XmlType(propOrder= {"x","y","z"})
	public class Coordinate{
		private double x;
		private double y;
		private double z;
		public Coordinate() {}
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x=x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y=y;
		}
		public double getZ() {
			return z;
		}
		public void setZ(double z) {
			this.z=z;
		}
	}
	public class NISAXhandler extends DefaultHandler{//oya youso to hukusuu no youso niha tukaenai
		public String tarattri;
		public String tarattrival;
		public String tarname;
		public ArrayList<String> tarvalue;
		protected String tmpval;
		protected boolean istarget;
		public void startDocument() {
			istarget=false;
		}
		public void endDocument() {
		}
		public void startElement(String uri,String localName,String qName,Attributes attributes) {
			if(qName==tarname) {
				istarget=true;
			}
		}
		public void endElement(String uri,String localName,String qName) {
			if(qName==tarname) {
				istarget=false;
			}
		}
		public void characters(char[] ch,int start,int length) {
			tarvalue.add( new String(ch,start,length));
		}

	}
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



}
