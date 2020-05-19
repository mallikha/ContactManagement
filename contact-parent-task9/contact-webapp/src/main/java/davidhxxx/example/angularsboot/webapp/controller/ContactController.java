package davidhxxx.example.angularsboot.webapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import davidhxxx.example.angularsboot.service.dto.ContactDTO;
import davidhxxx.example.angularsboot.service.impl.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

	
	private ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService){
		this.contactService = contactService;
	}

	/**
	 * GET /contacts -> get all
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(HttpServletRequest request, HttpServletResponse response) {
		
		List<ContactDTO> contactsDTOs = contactService.findAllContacts();
		return new ResponseEntity<List<ContactDTO>>(contactsDTOs, HttpStatus.OK);
	}

	/**
	 * POST /contacts -> create one
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ContactDTO contactDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String contactId = contactService.insertContact(contactDTO);
		final ResponseEntity<Void> responseEntity = ResponseEntity
				.created(new URI("/api/contacts/" + contactId.toString())).build();
		return responseEntity;
	}

	/**
	 * PUT /contacts/{id} -> update
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody ContactDTO contactDTO,
			HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {

		if (id == null || contactDTO.getId() == null || !id.equals(contactDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		contactService.updateContact(contactDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * DELETE /contacts/{id} -> delete one
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		contactService.deleteContact(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
