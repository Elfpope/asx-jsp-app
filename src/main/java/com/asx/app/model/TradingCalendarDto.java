package com.asx.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.asx.app.util.Constants;

/**
 * DTO object is used in the front-end for validation and serialization purpose.
 * 
 * @author junfeng
 */
public class TradingCalendarDto implements Serializable {

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = Constants.DATE_PATTERN)
	private Date recordDate;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = Constants.DATE_PATTERN)
	private Date paymentDate;

	/**
	 * @return the recordDate
	 */
	public Date getRecordDate() {
		return recordDate;
	}

	/**
	 * @param recordDate
	 *            the recordDate to set
	 */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
