package in.kkpit.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.kkpit.entity.Event;
import in.kkpit.repo.EventRepo;
import in.kkpit.service.EventService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventRepo eventRepository;
	
	@Override
	public Event createEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public List<Event> getAllEvent() {
		return eventRepository.findAll();
	}

	@Override
	public Event getEventById(Long id) {
		return eventRepository.findById(id).orElseThrow(()->new RuntimeException("Event is not found"));
	}

	@Override
	public Event updateEvent(Event event, Long id) {
	Event e=getEventById(id);
	e.setName(event.getName()!=null?event.getName():e.getName());
	e.setDescription(event.getDescription()!=null?event.getDescription():e.getDescription());
	e.setStartDate(event.getStartDate()!=null?event.getStartDate():e.getStartDate());
	e.setEndDate(event.getEndDate()!=null?event.getEndDate():e.getEndDate());
	e.setFee(event.getFee()!=0.0?event.getFee():e.getFee());
	eventRepository.save(e);
	return e;
	}

	@Override
	public void deleteEvent(Long id) {
		Event e=getEventById(id);
	eventRepository.delete(e);
	}

}
