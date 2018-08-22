package com.ubo.common.terminal;

import java.io.Serializable;

public class LbsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2528632597570046478L;
	private int lac;
	private int cid;
	private int singalstrength;
	
	public LbsBean(int lac, int cid,int singalstrength) {
		this.lac = lac;
		this.cid = cid;
		this.singalstrength = singalstrength;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSingalstrength() {
		return singalstrength;
	}

	public void setSingalstrength(int singalstrength) {
		this.singalstrength = singalstrength;
	}
}
