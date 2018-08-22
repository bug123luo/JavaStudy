package dudu.service.dto;

import java.io.Serializable;

public class SensorAccountData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5481832927007965569L;
	private String accountIds;
	private Page page;
	public String getAccountIds() {
		return accountIds;
	}
	public SensorAccountData setAccountIds(String accountIds) {
		this.accountIds = accountIds;
		return this;
	}
	public Page getPage() {
		return page;
	}
	public SensorAccountData setPage(Page page) {
		this.page = page;
		return this;
	}
}
