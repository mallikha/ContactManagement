package davidhxxx.example.angularsboot.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import davidhxxx.example.angularsboot.db.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
