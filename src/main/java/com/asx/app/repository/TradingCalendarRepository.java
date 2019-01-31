package com.asx.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asx.app.model.TradingCalendar;

/**
 * It is a repository to serve access {@link TradingCalendar} in the persistence layer.
 * 
 * @author junfeng
 */
@Repository
public interface TradingCalendarRepository extends JpaRepository<TradingCalendar, Integer> {

	/**
	 * Find the {@link TradingCalendar} matching the given {@link Date}.
	 * 
	 * @param date
	 *            to search
	 * @return a collection containing only the matching date; or an empty collection
	 */
	List<TradingCalendar> findByDate(Date date);
}
