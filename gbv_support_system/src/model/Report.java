package model;

import java.time.LocalDateTime;

public class Report {
    private int reportId;
    private String survivorAlias;
    private int categoryId;
    private String location;
    private String description;
    private LocalDateTime dateReported;
    private boolean anonymous;
    private int userId;

   
    public Report(String survivorAlias, int categoryId, String location, String description, 
                  LocalDateTime dateReported, boolean anonymous, int userId) {
        this.survivorAlias = survivorAlias;
        this.categoryId = categoryId;
        this.location = location;
        this.description = description;
        this.dateReported = dateReported;
        this.anonymous = anonymous;
        this.userId = userId;
    }

    public Report(int reportId, String survivorAlias, int categoryId, String location, String description, 
                  LocalDateTime dateReported, boolean anonymous, int userId) {
        this.reportId = reportId;
        this.survivorAlias = survivorAlias;
        this.categoryId = categoryId;
        this.location = location;
        this.description = description;
        this.dateReported = dateReported;
        this.anonymous = anonymous;
        this.userId = userId;
    }


    public int getReportId() { 
        return reportId; 
    }
    public String getSurvivorAlias() { 
        return survivorAlias; 
    }
    public int getCategoryId() { 
        return categoryId; 
    }
    public String getLocation() { 
        return location; 
    }
    public String getDescription() { 
        return description; 
    }
    public LocalDateTime getDateReported() { 
        return dateReported; 
    }
    public boolean isAnonymous() { 
        return anonymous; 
    }
    public int getUserId() { 
        return userId; 
    }

    // Setters
    public void setReportId(int reportId) { 
        this.reportId = reportId; 
    }
    public void setSurvivorAlias(String survivorAlias) { 
        this.survivorAlias = survivorAlias; 
    }
    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }
    public void setDateReported(LocalDateTime dateReported) { 
        this.dateReported = dateReported; 
    }
    public void setAnonymous(boolean anonymous) { 
        this.anonymous = anonymous; 
    }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }
}
