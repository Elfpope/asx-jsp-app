package com.asx.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It encapsulates utility method for date-related calculation.
 * 
 * @author junfeng
 */
public abstract class DateUtils {

	private static Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * Convert the given {@link Date} to {@link LocalDate}.
	 * 
	 * @param date
	 *            to be converted
	 * @return {@link Date} value based on the given {@link LocalDate}
	 */
	public static LocalDate toLocalDate(Date date) {
		LocalDate result = null;
		if (date != null) {
			result = toLocalDate(new SimpleDateFormat(Constants.DATE_PATTERN).format(date));
		}

		return result;
	}

	/**
	 * Convert the given dateString to {@link LocalDate}.
	 * 
	 * @param dateString
	 *            to be converted
	 * @return {@link LocalDate} value based on the given dateString
	 */
	public static LocalDate toLocalDate(String dateString) {
		if (isValidString(dateString, Constants.DATE_STRING_REGEX)) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
				return LocalDate.parse(dateString, formatter);
			} catch (DateTimeParseException e) {
				LOG.error("Encounter error when converting input dateString {} to {} with pattern {}.", dateString,
						LocalDate.class.getName(), Constants.DATE_PATTERN);
				return null;
			}
		}

		LOG.error("Skip convertion as the input dateString {} is invalid.", dateString);
		return null;
	}

	/**
	 * Convert the given dateString to {@link Date}.
	 * 
	 * @param dateString
	 *            to be converted
	 * @return {@link Date} value based on the given dateString
	 */
	public static Date toDate(String dateString) {
		if (isValidString(dateString, Constants.DATE_STRING_REGEX)) {
			try {
				return new SimpleDateFormat(Constants.DATE_PATTERN).parse(dateString);
			} catch (IllegalArgumentException | ParseException exception) {
				LOG.error("Encounter error when converting input dateString {} to {} with pattern {}.", dateString,
						Date.class.getName(), Constants.DATE_PATTERN);
				return null;
			}
		}

		LOG.error("Skip convertion as the input dateString {} is invalid.", dateString);
		return null;
	}

	/**
	 * Check if the given string matches the given regular expression.
	 * 
	 * @param string
	 *            to check
	 * @param regex
	 *            used to check the string against
	 * @return {@code true} if the given string matches the given regular expression; otherwise {@code false}
	 */
	private static boolean isValidString(String string, String regex) {
		if (string == null) {
			return false;
		}

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
}
