package model;

import javax.persistence.*;

@Entity
@Table(name = "support_services")
public class SupportService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "service_name", nullable = false, length = 150)
    private String serviceName;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Column(name = "location")
    private String location;

    @Column(name = "contact_info")
    private String contactInfo;

    // Hibernate requires a no-arg constructor
    public SupportService() { }

    // Convenience constructor (ID will be auto-generated)
    public SupportService(String serviceName,
                          String description,
                          String contactPhone,
                          String location,
                          String contactInfo) {
        this.serviceName = serviceName;
        this.description = description;
        this.contactPhone = contactPhone;
        this.location = location;
        this.contactInfo = contactInfo;
    }

    // --- Getters ---
    public int getServiceId() {
        return serviceId;
    }
    public String getServiceName() {
        return serviceName;
    }
    public String getDescription() {
        return description;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public String getLocation() {
        return location;
    }
    public String getContactInfo() {
        return contactInfo;
    }

    // --- Setters ---
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
