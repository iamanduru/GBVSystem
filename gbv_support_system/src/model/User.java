package model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String contactEmail;

    public User(int userId, String username, String password, String fullName, String role, String contactEmail) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.contactEmail = contactEmail;
    }

     
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
