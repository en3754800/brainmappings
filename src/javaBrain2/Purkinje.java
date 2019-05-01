package javaBrain2;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import javaBrain2.NeuronInfo.Coordinate;
import javaBrain2.NeuronInfo.SwitchI;

public class Purkinje {
/**
 * heikousenni(karyuusaibou) no inputjouhou to outputjouhou
 * tojousenni no inputjouhou outputjouhou
 * purkinjesaibou no inputjouhou outputjouhou
 * 3tu no fairu de 3 unit no kairo wo seigyo suru
 * @param args
 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public class PurkinjeXML extends NeuronInfo.UnitType{
		public PurkinjeXML(){new NeuronInfo(0).super();}
		private PurkType type;
		@XmlElements(
				value={
					@XmlElement(name="granuxml",type=granuXML.class),
					@XmlElement(name="assendxml",type=assendXML.class),
					@XmlElement(name="purkxml",type=purkXML.class)
				}
			)
		public PurkType getType() {
			return this.type;
		}
		public void setType(PurkType type) {
			this.type=type;
		}
	}
	public class PurkType{

	}
	public class granuXML extends PurkType{
		private double border;
		private int flubordertime;
		private String inputgroup;
		private Coordinate inputiti;
		private String prukgroup;
		private Coordinate prukiti;
		private int delay;
		private List<SwichG> sw;//wrappar downstream ename dest

	}
	public class assendXML extends PurkType{
		private double border;
		private int flubordertime;
		private String inputgroup;
		private Coordinate inputiti;
		private String prukgroup;
		private Coordinate prukiti;
		private int delay;
		private List<Integer> purkno;
		private List<Integer> granno;

	}
	public class purkXML extends PurkType{
		private double border;
		private int flubordertime;
		private String prukgroup;
		private Coordinate prukiti;
		private int assno;
		private String outputgroup;
		private Coordinate outputiti;
		private boolean repressor;
		private List<SwitchI> sw;//wrappar downstream ename dest

	}
	public class SwichG{
		private int tono;
		private double amount;
	}
}
