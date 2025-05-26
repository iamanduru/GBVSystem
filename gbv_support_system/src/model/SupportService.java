package model;

public class SupportService {
    private int serviceId;
    private String serviceName;
    private String serviceType; 
    private String availabilityHours;
    private String location;
    private String contactInfo;


    public SupportService() {
    }

 
    public SupportService(int serviceId, String serviceName, String serviceType, String availabilityHours, String location, String contactInfo) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.availabilityHours = availabilityHours;
        this.location = location;
        this.contactInfo = contactInfo;
    }

  
    public int getServiceId() {
        return serviceId;
    }
    public String getServiceName() {
        return serviceName;
    }
    public String getServiceType() {
        return serviceType;
    }
    public String getAvailabilityHours() {
        return availabilityHours;
    }
    public String getLocation() {
        return location;
    }
    public String getContactInfo() {
        return contactInfo;
    }

 
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public void setAvailabilityHours(String availabilityHours) {
        this.availabilityHours = availabilityHours;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
