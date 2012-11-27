package Objects;

public class Borrowing {

	public int callNumber, copyNo, bid, borid;
	public String outDate, inDate;
	public Borrowing()
	{
		
	}
	public Borrowing (int borid, int callNumber, int copyNo, int bid, String outDate, String inDate)
	{
		this.callNumber = callNumber;
		this.borid = borid;
		this.callNumber = callNumber;
		this.copyNo = copyNo;
		this.bid = bid;
		this.outDate = outDate;
		this.inDate = inDate;
	}

	public int getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(int callNumber) {
		this.callNumber = callNumber;
	}

	public int getCopyNo() {
		return copyNo;
	}

	public void setCopyNo(int copyNo) {
		this.copyNo = copyNo;
	}

	public int getBorid() {
		return borid;
	}

	public void setBorid(int borid) {
		this.borid = borid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
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
