package model;

public class EmergencyContact {
    private int contactId;
    private String contactName;
    private String phoneNumber;
    private String organization;
    private String location;

    public EmergencyContact(int contactId, String contactName, String phoneNumber, String organization, String location) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
        this.location = location;
    }

   
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
