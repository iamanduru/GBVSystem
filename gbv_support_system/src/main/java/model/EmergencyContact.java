package model;

import javax.persistence.*;

@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    @Column(name = "name", nullable = false)
    private String contactName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "organization")
    private String organization;

    @Column(name = "location")
    private String location;

    // Hibernate requires a no-arg constructor
    public EmergencyContact() { }

    public EmergencyContact(int contactId, String contactName, String phoneNumber, 
                            String organization, String location) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
        this.location = location;
    }

    // Getters
    public int getContactId() {
        return contactId;
    }
    public String getContactName() {
        return contactName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getOrganization() {
        return organization;
    }
    public String getLocation() {
        return location;
    }

    // Setters
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
