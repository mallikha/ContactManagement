package davidhxxx.example.angularsboot.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@Entity
//@Table(name = "ADDRESS")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column
	private String addressLine;

	@Column
	private String city;

	@Column
	private String zip;

	@Column
	private String country;

	public Address() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine=" + addressLine + ", city=" + city + ", zip=" + zip + ", country="
				+ country + "]";
	}

}
