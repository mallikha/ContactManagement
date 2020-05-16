package davidhxxx.example.angularsboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davidhxxx.example.angularsboot.db.model.Contact;
import davidhxxx.example.angularsboot.db.repository.ContactRepository;
import davidhxxx.example.angularsboot.service.dto.ContactDTO;
import davidhxxx.example.angularsboot.service.util.ModelMapperService;

@Service
public class ContactService {

	private ContactRepository contactRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public ContactService(ContactRepository contactRepository, ModelMapperService modelMapperService) {
		this.contactRepository = contactRepository;
		this.modelMapperService = modelMapperService;
	}

	public List<ContactDTO> findAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactDTO> contactsDTO = modelMapperService.mapList(contacts, ContactDTO.class);
		return contactsDTO;
	}

	public Long insertContact(ContactDTO contactDTO) {
		Contact contact = modelMapperService.map(contactDTO, Contact.class);
		contactRepository.save(contact);
		return contact.getId();
	}

	public void updateContact(ContactDTO contactDTO) {
		Contact contact = modelMapperService.map(contactDTO, Contact.class);
		contactRepository.save(contact);
	}

	public void deleteContact(Long contactId) {
		contactRepository.delete(contactId);
	}

}
