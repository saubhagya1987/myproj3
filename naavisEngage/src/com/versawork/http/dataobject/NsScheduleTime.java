package com.versawork.http.dataobject;



import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsScheduleTime {

	// private static final long serialVersionUID = 1L;

	protected String reminderTime;
	protected Integer scheduleId;
	protected String  reminderDay;
	
	


	public String getReminderDay() {
		return reminderDay;
	}

	public void setReminderDay(String reminderDay) {
		this.reminderDay = reminderDay;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

}
