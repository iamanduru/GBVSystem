package model;

import javax.persistence.*;

@Entity
@Table(name = "report_categories")
public class ReportCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;
    
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    // Required by JPA/Hibernate
    public ReportCategory() { }

    // Convenience constructor (ID is auto-generated)
    public ReportCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    // --- Getters & Setters ---
    public int getCategoryId() { 
        return categoryId; 
    }
    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId; 
    }

    public String getCategoryName() { 
        return categoryName; 
    }
    public void setCategoryName(String categoryName) { 
        this.categoryName = categoryName; 
    }
}
