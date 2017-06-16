package cn.itcast.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Customer {
	
	private Integer custId;
	private String custName;
	private String custLinkman;
	private String custPhone;
	private String custMobile;
	
	
	private BaseDict sourceBaseDict;//客户来源
	private BaseDict levelBaseDict;// 客户等级
	
	private String filePath;
	@JSONField(serialize=false)
	private List<Visit> visits = new ArrayList<>();
	
	public List<Visit> getVisits() {
		return visits;
	}
	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustLinkman() {
		return custLinkman;
	}
	public void setCustLinkman(String custLinkman) {
		this.custLinkman = custLinkman;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public BaseDict getSourceBaseDict() {
		return sourceBaseDict;
	}
	public void setSourceBaseDict(BaseDict sourceBaseDict) {
		this.sourceBaseDict = sourceBaseDict;
	}
	public BaseDict getLevelBaseDict() {
		return levelBaseDict;
	}
	public void setLevelBaseDict(BaseDict levelBaseDict) {
		this.levelBaseDict = levelBaseDict;
	}
	
	
	
}
