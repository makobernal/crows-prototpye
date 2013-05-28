package com.engicrowd.crows.models.events;

import java.util.Calendar;

public class BaseEvent {

	private String id;
	private Calendar timestamp;
	private Calendar date;
	private BaseEvent next;
	private BaseEvent previous;
}