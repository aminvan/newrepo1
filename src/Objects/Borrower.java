package Objects;

public class Borrower {

	String bid, password, name, address, phone, emailAddress, sinOrStNo, expiryDate, type;
	
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSinOrStNo() {
		return sinOrStNo;
	}

	public void setSinOrStNo(String sinOrStNo) {
		this.sinOrStNo = sinOrStNo;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Borrower(String bid, String name, String address, String phone, String emailAddress,
					String sinOrStNo, String expiryDate, String type)
	{
		this.bid = bid;
		this.name = name;
		this.address = address;
		this.emailAddress = emailAddress;
		this.sinOrStNo = sinOrStNo;
		this.expiryDate = expiryDate;
		this.type = type;
	}
	
	
}
