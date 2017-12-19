package Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Cost implements Serializable {

	private Integer costID;//
	private String name;
	private Integer baseDuraction;//����ʱ�� 
	private Double baseCost;//�������� 
	private Double unitCost;//��λ����
	private String status;//״̬
	private String descr;//�ʷ�˵��
	private Timestamp creatime;//����ʱ��
	private Timestamp startime;//��ͨʱ��
	private String costType;//�ʷ�����
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public Integer getCostID() {
		return costID;
	}
	public void setCostID(Integer costID) {
		this.costID = costID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBaseDuraction() {
		return baseDuraction;
	}
	public void setBaseDuraction(Integer baseDuraction) {
		this.baseDuraction = baseDuraction;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getCreatime() {
		return sf.format(creatime);
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	//�����п�
	public String getStartime() {
		if(startime==null) {
			return "��δ��ʼʹ��";
		}else {
		return sf.format(startime);
	}}
	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}	
}
