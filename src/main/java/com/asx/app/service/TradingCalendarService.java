package com.asx.app.service;

import java.util.Date;

import com.asx.app.model.TradingCalendarDto;

public interface TradingCalendarService {

	boolean recordDateCheck(Date recordDate);

	boolean paymentDateCheck(Date recordDate, Date paymentDate);

	void handleSubmission(TradingCalendarDto tradingCalendarDto);
}
