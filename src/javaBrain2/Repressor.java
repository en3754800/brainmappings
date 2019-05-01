package javaBrain2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import javaBrain2.DelayAmount.DAReturn;
import javaBrain2.NeuronInfo.Coordinate;

public class Repressor {
/**
 * repressor neuroninfo wo sukosi kaihen site atukau
 * NeuronInfo+No+.txt
 * (root=unit)(repressorxml)border/entirecountborder;inputgroup(AA);inputiti(iiti)((x)3.200/(y)2.455/(z)1.200);
 * 				outputgroup(AA);outputiti(oiti)(4.222/2.455/1.200);switchcountborder;true(=represser)  <-x/y/z
 * (list name=switch){(name=dest)swtoNo1/delay;
 * (name=dest)swtoNo2/delay;
 * (name=dest)34/200}      -->.xml
 * borderchenged->border.txt
 * iiti,igroupchanged->inputplace.txt
 * oiti,ogroupchanged->outputplace.txt(misakusei)
 * switchchenged->desttimeamo.txt(toNo,delay,amount)(DelayAmount.java) count.txt(toNo)(CountRecord.java)
 * neuron no count zentai de synapus no updown wo okonau
 * @param args
 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public int No;
	public RepressorXML xml;
	public void memAadd(int No) {
		int nowtime=CommonVal.absolutetime;
		DelayAmount da=new DelayAmount(No);
		DAReturn dr=new DelayAmount(No).conversion(nowtime);
		for(int i=0;i<dr.No.length;i++) {
			CommonVal.memAnalize.list.time.get(dr.time[i]).unit.get(dr.No[i]).repressed=true;
			CommonVal.contAnalize.add(No,dr.No[i],dr.time[i],da.unitNo.get(new DelayAmount.NoandOrder(dr.No[i], dr.order[i])).time);
		}
	}
	public void couRmake() {
		CountRecord cr=new CountRecord(this.No);
		NeuronInfo ni=new NeuronInfo(this.No);
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
	public class RepressorXML extends NeuronInfo.UnitType{
		public RepressorXML(){new NeuronInfo(0).super();}

		private double border;
		private int entcborder;
		private String inputgroup;
		private Coordinate inputiti;
		private String outputgroup;
		private Coordinate outputiti;
		private int swcountborder;
		private boolean repressor;
		private List<SwitchR> sw;
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
		public List<SwitchR> getSw(){
			return sw;
		}
		public void setSw(List<SwitchR> list) {
			this.sw=list;
		}
	}
	public class SwitchR{
		private int tono;
		private int delay;
		public SwitchR() {}
		public SwitchR(int tono,int delay) {
			this.tono=tono;
			this.delay=delay;
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
	}


}
