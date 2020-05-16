package davidhxxx.example.angularsboot.service.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String addressLine;

	private String city;

	private String zip;

	private String country;

	public AddressDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String adressLine) {
		this.addressLine = adressLine;
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

}
