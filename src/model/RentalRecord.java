package model;

public class RentalRecord {
	private String recordId;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private float rentFee = 0.00f;
	private float lateFee = 0.00f;
	private String customerId;

	public RentalRecord(String recordId, DateTime date, DateTime estimatedReturnDate) {
		this.recordId = recordId;
		this.rentDate = date;
		this.estimatedReturnDate = estimatedReturnDate;
	}

	public RentalRecord(String recordId, DateTime rentDate, DateTime estimatedReturnDate, DateTime actualReturnDate,
			float rentFee, float lateFee) {
		this.recordId = recordId;
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.rentFee = rentFee;
		this.lateFee = lateFee;
	}

	public DateTime getRentDate() {
		return rentDate;
	}

	public void setRentDate(DateTime rentDate) {
		this.rentDate = rentDate;
	}

	public DateTime getEstimatedReturnDate() {
		return estimatedReturnDate;
	}

	public void setEstimatedReturnDate(DateTime estimatedReturnDate) {
		this.estimatedReturnDate = estimatedReturnDate;
	}

	public DateTime getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(DateTime actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public float getRentFee() {
		return rentFee;
	}

	public void setRentFee(float rentFee) {
		this.rentFee = rentFee;
	}

	public float getLateFee() {
		return lateFee;
	}

	public void setLateFee(float lateFee) {
		this.lateFee = lateFee;
	}

	@Override
	public String toString() {
		if (this.getActualReturnDate() == null)
			return recordId + ":" + rentDate + ":" + estimatedReturnDate + ":" + "none" + ":" + "none" + ":" + "none";
		else
			return recordId + ":" + rentDate + ":" + estimatedReturnDate + ":" + actualReturnDate + ":" + rentFee + ":"
					+ lateFee;
	}
}