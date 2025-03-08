package HealthCareManagementSystem;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String email;
    private String address;
    private String disease; 

    public Patient(int id, String name, int age, String gender, String contact, String email, String address, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.disease = disease;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getDisease() { return disease; }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + 
                           ", Contact: " + contact + ", Email: " + email + 
                           ", Address: " + address + ", Disease: " + disease);
    }
}
