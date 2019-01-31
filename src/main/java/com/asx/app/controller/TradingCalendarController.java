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
		return new ModelAndView("index", "tradingCalendarDto", new TradingCalendarDto());
	}

	@PostMapping("/addTradingDate")
	public String submit(@ModelAttribute("tradingCalendarDto") TradingCalendarDto tradingCalendarDto,
			BindingResult result, ModelMap model) {

		Date recordDate = tradingCalendarDto.getRecordDate();
		if (!tradingCalendarService.recordDateCheck(recordDate)) {
			result.rejectValue("recordDate", "invalid-record-date", "Record date must be a Business Date.");
			return "index";
		}

		Date paymentDate = tradingCalendarDto.getPaymentDate();
		if (!tradingCalendarService.paymentDateCheck(recordDate, paymentDate)) {
			result.rejectValue("paymentDate", "invalid-payment-date",
					"Payment date must be at least one day after the Record Date.");
			return "index";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_PATTERN);
		model.addAttribute("recordDate", formatter.format(tradingCalendarDto.getRecordDate()));
		model.addAttribute("paymentDate", formatter.format(tradingCalendarDto.getPaymentDate()));

		tradingCalendarService.handleSubmission(tradingCalendarDto);
		return "success";
	}

}
