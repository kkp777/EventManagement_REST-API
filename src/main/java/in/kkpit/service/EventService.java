package in.kkpit.service;

import java.util.List;

import in.kkpit.entity.Event;

public interface EventService {
	Event createEvent(Event event);
	List<Event>getAllEvent();
	Event getEventById(Long id);
	Event updateEvent(Event event,Long id);
	void deleteEvent(Long id);
}
