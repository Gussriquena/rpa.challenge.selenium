package rpa.challenge.selenium.model;

public class Person {

	private String firstName;
	private String lastName;
	private String companyName;
	private String roleInCompany;
	private String address;
	private String email;
	private String phoneNumber;
	private boolean successProcessed;
	
	public Person() {
		
	}

	public Person(String firstName, String lastName, String companyName, String roleInCompany, String address,
			String email, String phoneNumber, boolean successProcessed) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.roleInCompany = roleInCompany;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.successProcessed = successProcessed;
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
	
	public boolean isSuccessProcessed() {
		return successProcessed;
	}

	public void setSuccessProcessed(boolean successProcessed) {
		this.successProcessed = successProcessed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((roleInCompany == null) ? 0 : roleInCompany.hashCode());
		result = prime * result + (successProcessed ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (roleInCompany == null) {
			if (other.roleInCompany != null)
				return false;
		} else if (!roleInCompany.equals(other.roleInCompany))
			return false;
		if (successProcessed != other.successProcessed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", companyName=" + companyName
				+ ", roleInCompany=" + roleInCompany + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", successProcessed=" + successProcessed + "]";
	}
}
