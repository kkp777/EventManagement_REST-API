package in.kkpit.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.kkpit.entity.Event;
import in.kkpit.entity.User;
import in.kkpit.service.EventService;
import in.kkpit.service.UserService;

@RestController
@RequestMapping("/event")
public class EventControlller {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping
	public ResponseEntity<Event>createEvent(@RequestBody Event e ){
		
		return new ResponseEntity<>(eventService.createEvent(e),HttpStatus.CREATED);

	}
	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents(){
		return new ResponseEntity<>(eventService.getAllEvent(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event>getEventById(@PathVariable Long id){
		return new ResponseEntity<>(eventService.getEventById(id),HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Event>updateEvent(@RequestBody Event e,@PathVariable Long id){
		return new ResponseEntity<>(eventService.updateEvent(e,id),HttpStatus.OK);
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteEvent(@RequestParam Long id) {
		eventService.deleteEvent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
	
}
