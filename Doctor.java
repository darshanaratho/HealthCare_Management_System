package HealthCareManagementSystem;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private String contact;
    private String email;
    private int experience;

    public Doctor(int id, String name, String specialization, String contact, String email, int experience) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.email=email;
        this.experience=experience;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getContact() { return contact; }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Specialization: " + specialization + 
                           ", Contact: " + contact+", Email: "+email+", Experience:"+experience);
    }
}
