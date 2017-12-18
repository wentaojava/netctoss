package Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Cost implements Serializable {

	private Integer costID;//
	private String name;
	private Integer baseDuraction;//基本时长 
	private Double baseCost;//基本费用 
	private Double unitCost;//单位费用
	private String status;//状态
	private String descr;//资费说明
	private Timestamp creatime;//创建时间
	private Timestamp startime;//开通时间
	private String costType;//资费类型
	
	
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
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public Timestamp getStartime() {
		return startime;
	}
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
