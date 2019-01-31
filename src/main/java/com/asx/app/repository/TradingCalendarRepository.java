package com.asx.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asx.app.model.TradingCalendar;

@Repository
public interface TradingCalendarRepository extends JpaRepository<TradingCalendar, Integer> {

	List<TradingCalendar> findByDate(Date date);
}
