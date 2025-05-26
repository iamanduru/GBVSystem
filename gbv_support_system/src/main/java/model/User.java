package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    // Required by Hibernate
    public User() { }

    // Convenience constructor (ID will be auto-generated)
    public User(String username,
                String password,
                String fullName,
                String role,
                String contactEmail) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.contactEmail = contactEmail;
    }

    // --- Getters ---
    public int getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFullName() {
        return fullName;
    }
    public String getRole() {
        return role;
    }
    public String getContactEmail() {
        return contactEmail;
    }

    // --- Setters ---
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
