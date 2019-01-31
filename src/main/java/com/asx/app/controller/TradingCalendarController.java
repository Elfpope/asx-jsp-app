package com.asx.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asx.app.model.TradingCalendarDto;
import com.asx.app.service.TradingCalendarService;
import com.asx.app.util.Constants;

@Controller
public class TradingCalendarController {

	private static final Logger LOG = LoggerFactory.getLogger(TradingCalendarController.class);

	@Autowired
	private TradingCalendarService tradingCalendarService;

	@GetMapping({ "/", "index" })
	public ModelAndView showHome() {
		LOG.debug("Served a request to home page.");
		return new ModelAndView("index", "tradingCalendarDto", new TradingCalendarDto());
	}

	@PostMapping("/addTradingDate")
	public String submit(@ModelAttribute("tradingCalendarDto") TradingCalendarDto tradingCalendarDto,
			BindingResult result, ModelMap model) {
		String message = "Serving a request to addTradingDate page ...";
		LOG.debug(message);

		Date recordDate = tradingCalendarDto.getRecordDate();
		if (!tradingCalendarService.recordDateCheck(recordDate)) {
			message = "Record date must be a Business Date";
			result.rejectValue("recordDate", "invalid-record-date", message);

			LOG.debug("Aborted summitting a new trading date as the invalid record date with details [{}].", message);
			return "index";
		}

		Date paymentDate = tradingCalendarDto.getPaymentDate();
		if (!tradingCalendarService.paymentDateCheck(recordDate, paymentDate)) {
			message = "Payment date must be at least one day after the Record Date";
			result.rejectValue("paymentDate", "invalid-payment-date", message);

			LOG.debug("Aborted summitting a new trading date as the invalid payment date with details [{}].", message);
			return "index";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_PATTERN);
		String recordDateStr = formatter.format(tradingCalendarDto.getRecordDate());
		String paymentDateStr = formatter.format(tradingCalendarDto.getPaymentDate());

		model.addAttribute("recordDate", recordDateStr);
		model.addAttribute("paymentDate", paymentDateStr);

		tradingCalendarService.handleSubmission(tradingCalendarDto);
		LOG.debug("Served a request to create a trading date [{}].", recordDateStr);

		return "success";
	}

}
