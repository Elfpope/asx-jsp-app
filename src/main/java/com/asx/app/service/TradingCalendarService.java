package com.asx.app.service;

import java.util.Date;

import com.asx.app.model.TradingCalendarDto;

/**
 * It is the server API for any trading date service.
 * 
 * @author junfeng
 */
public interface TradingCalendarService {

	/**
	 * Check if the given {@link Date} is a valid trading date.
	 * 
	 * @param recordDate
	 *            to check
	 * @return {@code true} if the given {@link Date} is a valid trading date; otherwise {@code false}
	 */
	boolean recordDateCheck(Date recordDate);

	/**
	 * Check if the given payment date is valid against the given record date.
	 * 
	 * @param recordDate
	 *            to check against
	 * @param paymentDate
	 *            to check
	 * @return {@code true} if the given payment date is valid against the given record date; otherwise {@code false}
	 */
	boolean paymentDateCheck(Date recordDate, Date paymentDate);

	/**
	 * Handle the trading date submission.
	 * 
	 * @param tradingCalendarDto
	 *            to examine
	 */
	void handleSubmission(TradingCalendarDto tradingCalendarDto);
}
