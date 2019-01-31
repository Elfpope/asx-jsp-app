package com.asx.app.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test {@link DateUtils}
 * 
 * @author junfeng
 */
public class TestDateUtils {

	/**
	 * Test {@link DateUtils#toDate(String)}
	 * <p>
	 * - Input parsable dateString
	 * </p>
	 */
	@Test
	public void testToDate() {
		// Month value of Calendar is 0-based. e.g., 0 for January.
		Calendar calendar = new GregorianCalendar(2016, 11, 30);
		Date date = DateUtils.toDate("30/12/2016");
		Assert.assertThat(date, is(equalTo(calendar.getTime())));

		calendar = new GregorianCalendar(2018, 5, 21);
		date = DateUtils.toDate("21/06/2018");
		Assert.assertThat(date, is(equalTo(calendar.getTime())));
	}

	/**
	 * Test {@link DateUtils#toDate(String)}
	 * <p>
	 * - Input non-parsable dateString
	 * </p>
	 */
	@Test
	public void testToDate_whenInputNonparsableDateString() {
		String dateStr = "2016/12/30";
		Date date = DateUtils.toDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "2016-12-30";
		date = DateUtils.toDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "2016 Dec 30";
		date = DateUtils.toDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "30-12-2016";
		date = DateUtils.toDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "30-Dec-2016";
		date = DateUtils.toDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "30 Dec 2016";
		date = DateUtils.toDate(dateStr);
		Assert.assertThat(date, is(nullValue()));
	}

	/**
	 * Test {@link DateUtils#toLocalDate(String)}
	 * <p>
	 * - Input parsable dateString
	 * </p>
	 */
	@Test
	public void testToLocalDate() {
		String dateStr = "30/12/2016";
		LocalDate date = DateUtils.toLocalDate(dateStr);

		Assert.assertThat(date.getYear(), is(equalTo(2016)));
		Assert.assertThat(date.getMonthValue(), is(equalTo(12)));
		Assert.assertThat(date.getDayOfMonth(), is(equalTo(30)));

		dateStr = "21/06/2018";
		date = DateUtils.toLocalDate(dateStr);

		Assert.assertThat(date.getYear(), is(equalTo(2018)));
		Assert.assertThat(date.getMonthValue(), is(equalTo(06)));
		Assert.assertThat(date.getDayOfMonth(), is(equalTo(21)));
	}

	/**
	 * Test {@link DateUtils#toLocalDate(String)}
	 * <p>
	 * - Input non-parsable dateString
	 * </p>
	 */
	@Test
	public void testToLocalDate_whenInputNonparsableDateString() {
		String dateStr = "2016/12/30";
		LocalDate date = DateUtils.toLocalDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "2016-12-30";
		date = DateUtils.toLocalDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "2016 Dec 30";
		date = DateUtils.toLocalDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "30-12-2016";
		date = DateUtils.toLocalDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "30-Dec-2016";
		date = DateUtils.toLocalDate(dateStr);
		Assert.assertThat(date, is(nullValue()));

		dateStr = "30 Dec 2016";
		date = DateUtils.toLocalDate(dateStr);
		Assert.assertThat(date, is(nullValue()));
	}

}
