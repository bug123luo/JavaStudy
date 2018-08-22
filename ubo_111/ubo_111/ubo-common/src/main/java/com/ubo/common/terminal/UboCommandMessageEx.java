package com.ubo.common.terminal;

public class UboCommandMessageEx extends UboCommandMessage {

	private static final long serialVersionUID = 5481552227007965886L;

	private String zip;
	private int totalPkgNO;
	private int pkgNO;
	private int zipLength;
	private byte[] zipBody;
	
	public UboCommandMessageEx(String eqId, CommandType cmd, String body) {
		super(eqId, cmd, body);
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getTotalPkgNO() {
		return totalPkgNO;
	}

	public void setTotalPkgNO(int totalPkgNO) {
		this.totalPkgNO = totalPkgNO;
	}

	public int getPkgNO() {
		return pkgNO;
	}

	public void setPkgNO(int pkgNO) {
		this.pkgNO = pkgNO;
	}

	public int getZipLength() {
		return zipLength;
	}

	public void setZipLength(int zipLength) {
		this.zipLength = zipLength;
	}

	public byte[] getZipBody() {
		return zipBody;
	}

	public void setZipBody(byte[] zipBody) {
		this.zipBody = zipBody;
	}

	@Override
	public String toString() {
		return "UboCommandMessageEx [zip=" + zip + ", totalPkgNO=" + totalPkgNO + ", pkgNO=" + pkgNO + ", zipLength="
				+ zipLength + ", eqId=" + eqId + ", msgId=" + msgId + ", type=" + type + "]";
	}
	
	
	
}
