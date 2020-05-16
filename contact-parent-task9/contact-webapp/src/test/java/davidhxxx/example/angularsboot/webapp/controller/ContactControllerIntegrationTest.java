package davidhxxx.example.angularsboot.webapp.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import davidhxxx.example.angularsboot.db.repository.ContactRepository;
import davidhxxx.example.angularsboot.service.dto.ContactDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ContactControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Value("${server.contextPath}")
	private String contextPath;

	@Autowired
	private ContactRepository contactRepository;

	@Before
	public void setup() {
		contactRepository.deleteAll();
	}

	@Test
	public void getEntitiesReturnCreatedEntities() throws Exception {

		ContactDTO contact = new ContactDTO();
		contact.setFirstName("Martin");
		contact.setLastName("Sugar");
		LocalDate date = LocalDate.of(2017, 1, 1);
		contact.setBirthday(date);
		// action
		ResponseEntity<ContactDTO> responseEntity = template.postForEntity(getBaseUrl(), contact, ContactDTO.class);

		// assertion
		Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Long idRetrieved = retrieveIdFrom(responseEntity);
		ResponseEntity<ContactDTO[]> responseEntityGetEntities = template.getForEntity(getBaseUrl(),
				ContactDTO[].class);
		List<ContactDTO> contacts = Arrays.asList(responseEntityGetEntities.getBody());
		Assert.assertEquals(1, contacts.size());
		ContactDTO actualContact = contacts.get(0);
		Assert.assertEquals(idRetrieved, actualContact.getId());
		Assert.assertEquals(contact.getFirstName(), actualContact.getFirstName());
		Assert.assertEquals(contact.getLastName(), actualContact.getLastName());
		Assert.assertEquals(contact.getBirthday(), actualContact.getBirthday());
	}

	@Test
	public void updateEntity() throws Exception {

		ContactDTO contact = new ContactDTO();
		contact.setFirstName("Martin");
		contact.setLastName("Sugar");
		LocalDate date = LocalDate.of(2017, 1, 1);
		contact.setBirthday(date);
		ResponseEntity<ContactDTO> responseEntity = template.postForEntity(getBaseUrl(), contact, ContactDTO.class);
		Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		// action
		Long idLastCreatedContact = retrieveIdFrom(responseEntity);
		contact = new ContactDTO();
		contact.setId(idLastCreatedContact);
		contact.setFirstName("Martino");
		contact.setLastName("Sugars");
		template.put(getBaseUrl() + "/" + idLastCreatedContact, contact);

		// assertion
		ResponseEntity<ContactDTO[]> responseEntityGetEntities = template.getForEntity(getBaseUrl(),
				ContactDTO[].class);
		List<ContactDTO> contacts = Arrays.asList(responseEntityGetEntities.getBody());
		Assert.assertEquals(1, contacts.size());
		ContactDTO actualContact = contacts.get(0);
		Assert.assertEquals(idLastCreatedContact, actualContact.getId());
		Assert.assertEquals(contact.getFirstName(), actualContact.getFirstName());
		Assert.assertEquals(contact.getLastName(), actualContact.getLastName());

	}

	@Test
	public void deleteEntity() throws Exception {

		// contact creation One that will remain
		ContactDTO firstContact = new ContactDTO();
		firstContact.setFirstName("john");
		firstContact.setLastName("doe");
		ResponseEntity<ContactDTO> responseEntity = template.postForEntity(getBaseUrl(), firstContact,
				ContactDTO.class);
		Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Long idContactThatWillRemain = retrieveIdFrom(responseEntity);

		// contact creation Two that we will delete
		ContactDTO secondContact = new ContactDTO();
		secondContact.setFirstName("jane");
		secondContact.setLastName("calimity");
		responseEntity = template.postForEntity(getBaseUrl(), secondContact, ContactDTO.class);
		Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		Long idLastCreatedContact = retrieveIdFrom(responseEntity);
		template.delete(getBaseUrl() + "/" + idLastCreatedContact);

		// assertion
		ResponseEntity<ContactDTO[]> responseEntityGetEntities = template.getForEntity(getBaseUrl(),
				ContactDTO[].class);
		List<ContactDTO> contacts = Arrays.asList(responseEntityGetEntities.getBody());
		Assert.assertEquals(1, contacts.size());
		ContactDTO actualContact = contacts.get(0);
		Assert.assertEquals(idContactThatWillRemain, actualContact.getId());
		Assert.assertEquals(firstContact.getFirstName(), actualContact.getFirstName());
		Assert.assertEquals(firstContact.getLastName(), actualContact.getLastName());
	}

	private long retrieveIdFrom(ResponseEntity<ContactDTO> responseEntity) {
		String path = responseEntity.getHeaders().getLocation().getPath();
		int indexOf = path.lastIndexOf("/");
		long idLastCreatedContact = Long.valueOf(path.substring(indexOf + 1));
		return idLastCreatedContact;
	}

	private String getBaseUrl() {
		return contextPath + "/api/contacts";
	}

}