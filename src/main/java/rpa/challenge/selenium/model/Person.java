package rpa.challenge.selenium.model;

public class Person {

	private String firstName;
	private String lastName;
	private String companyName;
	private String roleInCompany;
	private String address;
	private String email;
	private String phoneNumber;
	
	public Person() {
		
	}

	public Person(String firstName, String lastName, String companyName, String roleInCompany, String address,
			String email, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.roleInCompany = roleInCompany;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRoleInCompany() {
		return roleInCompany;
	}

	public void setRoleInCompany(String roleInCompany) {
		this.roleInCompany = roleInCompany;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", companyName=" + companyName
				+ ", roleInCompany=" + roleInCompany + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
}
