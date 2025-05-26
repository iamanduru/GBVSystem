package model;

public class ReportCategory {
    private int categoryId;
    private String categoryName;

    public ReportCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    //Getters and Setters
    public int getCategoryId() { 
        return categoryId; 
    }
    public String getCategoryName() { 
        return categoryName; 
    }

    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId; 
    }
    public void setCategoryName(String categoryName) { 
        this.categoryName = categoryName; 
    }

}
