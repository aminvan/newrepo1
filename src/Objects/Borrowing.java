package Objects;

public class Borrowing {

	public String borid, callNumber, copyNo, bid, outDate, inDate;
	public Borrowing()
	{
		
	}
	public Borrowing (String borid, String callNumber, String copyNo, String bid, String outDate, String inDate)
	{
		this.callNumber = callNumber;
		this.borid = borid;
		this.callNumber = callNumber;
		this.copyNo = copyNo;
		this.bid = bid;
		this.outDate = outDate;
		this.inDate = inDate;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getCopyNo() {
		return copyNo;
	}

	public void setCopyNo(String copyNo) {
		this.copyNo = copyNo;
	}

	public String getBorid() {
		return borid;
	}

	public void setBorid(String borid) {
		this.borid = borid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
}
