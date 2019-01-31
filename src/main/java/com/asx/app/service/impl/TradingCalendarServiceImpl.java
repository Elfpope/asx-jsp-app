package com.asx.app.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.asx.app.model.TradingCalendar;
import com.asx.app.model.TradingCalendarDto;
import com.asx.app.repository.TradingCalendarRepository;
import com.asx.app.service.TradingCalendarService;
import com.asx.app.util.Constants;
import com.asx.app.util.DateUtils;

@Service
public class TradingCalendarServiceImpl implements TradingCalendarService {

	private static final Logger LOG = LoggerFactory.getLogger(TradingCalendarServiceImpl.class);

	@Autowired
	private TradingCalendarRepository tradingCalendarRepo;

	@Override
	public boolean recordDateCheck(Date recordDate) {
		return isBusinessDay(recordDate) && isTradingDate(recordDate);
	}

	@Override
	public boolean paymentDateCheck(Date recordDate, Date paymentDate) {
		LocalDate recordLocalDate = DateUtils.toLocalDate(recordDate);
		LocalDate paymentLocalDate = DateUtils.toLocalDate(paymentDate);

		if (recordLocalDate == null || paymentLocalDate == null) {
			return false;
		}

		return recordLocalDate.isBefore(paymentLocalDate)
				&& ChronoUnit.DAYS.between(recordLocalDate, paymentLocalDate) >= Constants.DAYS_TO_PAY;
	}

	@Override
	public void handleSubmission(TradingCalendarDto tradingCalendarDto) {
		if (tradingCalendarDto != null) {
			TradingCalendar tradingCalendar = new TradingCalendar();
			tradingCalendar.setDate(tradingCalendarDto.getRecordDate());
			tradingCalendar.setTradingDate(Constants.IS_TRADING_DATE);

			tradingCalendarRepo.save(tradingCalendar);
			LOG.debug("Saved a TradingCalendar {}.", tradingCalendar);
		} else {
			LOG.debug("Abort saving a TradingCalendar as inputing a null object.");
		}
	}

	private boolean isTradingDate(Date date) {
		if (date == null) {
			return false;
		}

		List<TradingCalendar> tradingDates = tradingCalendarRepo.findByDate(date);
		return CollectionUtils.isEmpty(tradingDates)
				|| (tradingDates.size() == 1 && Constants.IS_TRADING_DATE.equals(tradingDates.get(0).getTradingDate()));
	}

	private boolean isBusinessDay(Date date) {
		LocalDate localDate = DateUtils.toLocalDate(date);
		if (localDate == null) {
			return false;
		}

		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		return Arrays
				.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
				.contains(dayOfWeek);
	}

}
