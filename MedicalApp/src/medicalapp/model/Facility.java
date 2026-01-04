package medicalapp.model;

public class Facility {
    private String facilityId;
    private String name;
    private String type;
    private String address;
    private String postcode;
    private String phone;
    private String email;
    private String openingHours;
    private String manager;
    private String capacity;
    private String specialties;

    public Facility(String facilityId, String name, String type, String address, String postcode,
                    String phone, String email, String openingHours, String manager,
                    String capacity, String specialties) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.email = email;
        this.openingHours = openingHours;
        this.manager = manager;
        this.capacity = capacity;
        this.specialties = specialties;
    }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }

    public String getSpecialties() { return specialties; }
    public void setSpecialties(String specialties) { this.specialties = specialties; }

    @Override
    public String toString() {
        return name;
    }
}
