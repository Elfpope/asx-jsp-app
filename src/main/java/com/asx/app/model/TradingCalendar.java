package com.asx.app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.asx.app.util.Constants;

/**
 * Model object to map the database table "Trading_Calendar"
 * 
 * @author junfeng
 */
@Entity
@Table(name = "Trading_Calendar")
public class TradingCalendar {

	@Id
	@GeneratedValue(generator = "trading_date_generator")
	@SequenceGenerator(name = "trading_date_generator", sequenceName = "trading_date_sequence", initialValue = 1000)
	private Integer id;

	@NotNull
	@Column(unique = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = Constants.DATE_PATTERN)
	private Date date;

	// @NotNull
	@Pattern(regexp = "[nNyY]")
	private String tradingDate;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the tradingDate
	 */
	public String getTradingDate() {
		return tradingDate;
	}

	/**
	 * @param tradingDate
	 *            the tradingDate to set
	 */
	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate.toUpperCase();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("[id: %d, date: %s, tradingDate: %s]", id,
				new SimpleDateFormat(Constants.DATE_PATTERN).format(date), tradingDate);
	}
}
