package com.asx.app.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.asx.app.model.TradingCalendar;
import com.asx.app.repository.TradingCalendarRepository;
import com.asx.app.util.Constants;

/**
 * Test {@link TradingCalendarServiceImpl}
 * 
 * @author junfeng
 */
public class TestTradingCalendarServiceImpl {

	@Mock
	private TradingCalendarRepository mockRepo;

	private SimpleDateFormat formatter;

	private TradingCalendarServiceImpl tradingCalendarService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		formatter = new SimpleDateFormat(Constants.DATE_PATTERN);

		tradingCalendarService = new TradingCalendarServiceImpl();
		tradingCalendarService.setTradingCalendarRepo(mockRepo);
	}

	/**
	 * Test {@link TradingCalendarServiceImpl#recordDateCheck(Date)}
	 * <p>
	 * Cover below scenarios:
	 * <ul>
	 * <li>Input a weekend date</li>
	 * <li>Input a business date which is a trading date</li>
	 * <li>Input a business date which is NOT a trading date</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testRecordDateCheck() {
		try {
			// Input a weekend date
			Date recordDate = formatter.parse("05/10/2019");
			Assert.assertFalse(tradingCalendarService.recordDateCheck(recordDate));

			// Input a business date which is a trading date
			recordDate = formatter.parse("03/10/2019");
			Mockito.when(mockRepo.findByDate(recordDate)).thenReturn(new ArrayList<>());
			Assert.assertTrue(tradingCalendarService.recordDateCheck(recordDate));

			// Input a business date which is NOT a trading date
			recordDate = formatter.parse("01/01/2019");

			TradingCalendar tradingCalendar = new TradingCalendar();
			tradingCalendar.setDate(recordDate);
			tradingCalendar.setTradingDate("N");

			Mockito.when(mockRepo.findByDate(recordDate)).thenReturn(Arrays.asList(tradingCalendar));
			Assert.assertFalse(tradingCalendarService.recordDateCheck(recordDate));

		} catch (ParseException exception) {
			Assert.fail("Failed to execute the unit test due to " + exception.getMessage());
		}
	}

	/**
	 * Test {@link TradingCalendarServiceImpl#paymentDateCheck(Date, Date)}
	 * <p>
	 * Cover below scenarios:
	 * <ul>
	 * <li>Input a payment date before the record date</li>
	 * <li>Input a payment date after the record date</li>
	 * </ul>
	 * </p>
	 */
	@Test
	public void testPaymentDateCheck() {
		try {
			// Input a payment date before the record date
			Date recordDate = formatter.parse("05/10/2019");
			Date paymentDate = formatter.parse("03/10/2019");
			Assert.assertFalse(tradingCalendarService.paymentDateCheck(recordDate, paymentDate));

			// Input a payment date after the record date
			recordDate = formatter.parse("05/10/2019");
			paymentDate = formatter.parse("07/10/2019");
			Assert.assertTrue(tradingCalendarService.paymentDateCheck(recordDate, paymentDate));

		} catch (ParseException exception) {
			Assert.fail("Failed to execute the unit test due to " + exception.getMessage());
		}
	}
}
