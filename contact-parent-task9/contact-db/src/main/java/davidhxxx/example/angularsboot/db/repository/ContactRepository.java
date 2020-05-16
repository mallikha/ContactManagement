package davidhxxx.example.angularsboot.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import davidhxxx.example.angularsboot.db.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
