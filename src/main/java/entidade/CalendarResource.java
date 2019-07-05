package entidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/calendar")
public class CalendarResource {
	
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CalendarDTO>> list()
	{
		List<Calendar> calendar = calendarService.list();

		List<CalendarDTO> dto = new ArrayList<>();
		for (Calendar calendar1 : calendar) {
			CalendarDTO calendarDTO = new CalendarDTO(calendar1);
			dto.add(calendarDTO);
		}

		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public ResponseEntity<List<CalendarDTO>> findList(@RequestParam(value="startTime")  @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime)
	{
		List<Calendar> calendar = calendarService.findList(startTime);

		List<CalendarDTO> dto = new ArrayList<>();
		for (Calendar x : calendar) {
			CalendarDTO calendarDTO = new CalendarDTO(x);
			dto.add(calendarDTO);
		}

		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Calendar calendar)
	{
		calendar = calendarService.create(calendar);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(calendar.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
