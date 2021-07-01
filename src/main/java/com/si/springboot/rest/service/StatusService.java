package com.si.springboot.rest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.si.springboot.rest.dto.Progress;
import com.si.springboot.rest.dto.Streak;
import com.si.springboot.rest.model.Status;
import com.si.springboot.rest.repository.DisciplineRepository;
import com.si.springboot.rest.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	DisciplineRepository disciplineRepository;

	public ResponseEntity<Status> saveStatus(Status status) {

		Status savedStatus = statusRepository.save(status);
		return new ResponseEntity<Status>(savedStatus, HttpStatus.OK);
	}

	public List<Status> getStatusByUsernameAndDate(String username, String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date formattedDate = null;
		
		try {
			formattedDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();	
		}
		
		return statusRepository.findByUsernameAndDate(username, formattedDate);
	}

	public List<Progress> getProgressByUsernameAndDisciplineId(String username, Long disciplineId) {
		List<Progress> result = statusRepository.getProgressByUsernameAndDisciplineId(username, disciplineId);
		
		if (result.size() == 0) {
			return null;
		}
		
		String disciplineName = disciplineRepository.findById(disciplineId).orElseGet(null).getName();
		
		// discipline start date
		Date startDate = result.get(0).getStatusDate();
		
		// build list of dates
		List<Date> disciplineDates = buildDateList(startDate);
		
		disciplineDates.forEach((date) -> {
			// search result for date
			boolean found = (result.stream()
		     .filter(r -> isSameDay(r.getStatusDate(), date))
		     .collect(Collectors.toList()).size()) == 1;
			
			// if date is not found, add to list
			if (!found) {
				result.add(new Progress(null, false, date, username, disciplineId, disciplineName));
			}
		});
		
		// return list in descending order
		result.sort(Comparator.comparing(Progress::getStatusDate).reversed());
					
		return result;
	}
	
	public Streak getStreakByUsernameAndDisciplineId(String username, Long disciplineId) {
		// TODO: create new query that only gets items that are complete 
		List<Progress> result = statusRepository.getProgressByUsernameAndDisciplineId(username, disciplineId);
		Long streak = 0L;
		
		// if list is empty, streak is 0
		if (result.size() == 0) {
			System.out.println("hello world");
			return new Streak(disciplineId, 0L);
		} else if (!isSameDay(result.get(result.size() - 1).getStatusDate(), new Date()) && 
				!isSameDay(result.get(result.size() - 1).getStatusDate(), getPrevDate(new Date()))) {
			// if the latest date is not today's date or yesterday's date, then there shouldn't be a streak of any type
			System.out.println("BROTHER ");
		} else {
			// count backwards from date
			for (int i = result.size() - 1; i >= 0; i--) {
				System.out.println(result.get(i).getStatus() + " -> " + result.get(i).getDisciplineName());
				if (result.get(i).getStatus()) {
					streak++;
					
					// if the previous day in the result set is not actually the previous day, break out of the loop
					if (i != 0 && 
							!isSameDay(result.get(i - 1).getStatusDate(), getPrevDate(result.get(i).getStatusDate()))) {
						break;
					}
				} else {
					break;
				}
			}
		}
		

		return new Streak(disciplineId, streak);
	}
	
	private List<Date> buildDateList(Date startDate) {
		List<Date> result = new ArrayList<Date>();	
		Date tempDate = startDate;
		
		// recursively build date list from start date
		while (!isToday(tempDate)) {
			result.add(tempDate);
			tempDate = getNextDate(tempDate);
		}
		// add today
		result.add(new Date());
		
		return result;
	}

	public static boolean isToday(Date date){
        Calendar today = Calendar.getInstance();
        Calendar specifiedDate  = Calendar.getInstance();
        specifiedDate.setTime(date);

        return today.get(Calendar.DAY_OF_MONTH) == specifiedDate.get(Calendar.DAY_OF_MONTH)
                &&  today.get(Calendar.MONTH) == specifiedDate.get(Calendar.MONTH)
                &&  today.get(Calendar.YEAR) == specifiedDate.get(Calendar.YEAR);
    }
	
	public static Date getNextDate(Date date) {
		// get a calendar instance
		Calendar calendar = Calendar.getInstance();
		// set time to param date
		calendar.setTime(date);
		// add one day to the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		    
		return calendar.getTime();
	 }
	
	public static Date getPrevDate(Date date) {
		// get a calendar instance
		Calendar calendar = Calendar.getInstance();
		// set time to param date
		calendar.setTime(date);
		// delete one day from the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		    
		return calendar.getTime();
	 }
	
	public static boolean isSameDay(Date date1, Date date2) {
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    return fmt.format(date1).equals(fmt.format(date2));
	}

}
