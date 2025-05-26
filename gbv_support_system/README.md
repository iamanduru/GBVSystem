# GBV Reporting and Support System

---

## Project Overview
This system addresses the widespread underreporting of Gender-Based Violence (GBV) cases by providing a **secure**, **anonymous**, and **user-friendly** platform for survivors to submit incident reports and access emergency contacts and support services. It also offers an **admin dashboard** for managing support services and incident reports.

---

## Technologies Used
- **Java Swing**: Front-end user interface
- **Java JDBC API**: Database communication
- **MySQL**: Database backend
- **MVC + DAO Pattern**: Application structure

---

## Architecture and Structure
**Model-View-Controller (MVC)** structure:
- **Model**: `Report.java`, `SupportService.java`, `EmergencyContact.java`, `ReportCategory.java`
- **View**: `SurvivorReportFormView.java`, `AdminDashboardView.java`, `ViewReportView.java`, `ManageReportsView.java`, etc.
- **Controller**: `ReportController.java`, `SupportServiceController.java`, `ViewReportController.java`
- **DAO**: `ReportDAO.java`, `SupportServiceDAO.java`, `EmergencyContactDAO.java`
- **Utility**: `DBConnection.java`, `ValidationUtil.java`

---

## Database Design

Tables:
- `reports`: Stores incident reports
- `report_categories`: Lookup table for GBV categories
- `support_services`: Contact details for survivor support
- `emergency_contacts`: Emergency hotlines
- `feedback`: Survivor feedback (future work)
- `users`: Admin login info (future work)

Foreign Key:
- `reports.category_id` references `report_categories.category_id`

---

## Main Functionalities

### Survivor Side
- Submit anonymous or named reports.
- View emergency contacts and support services.
- Strong validation on submission.

### Admin Side
- Login to access the dashboard.
- View incident reports with full descriptions.
- Update or delete reports.
- Manage support services and emergency contacts.

---

## Validation and Security
- Input validation using `ValidationUtil.java` (description length, location format, etc.)
- Safe database queries using `PreparedStatement`.
- Survivor names are optional for anonymity.

---

## Special Features and Usability
- Wrapped text in JTable for long descriptions.
- Tooltips for viewing full text easily.
- Clear success and error messages via `JOptionPane`.
- Consistent and intuitive GUI design.

---

## Challenges Faced and Solutions
| Challenge | Solution |
|-----------|----------|
| Long text cutoff in JTable | Used `TextAreaRenderer` to wrap text |
| Anonymity in reports | Made name field optional |
| Robust field validation | Implemented `ValidationUtil` |
| Prevent SQL Injection | Used `PreparedStatement` everywhere |

---

## Future Improvements
- Add real authentication for admins.
- Generate tracking numbers for reports.
- Add search and filtering for reports.
- Build a mobile version for easier survivor access.
- Add email/SMS notifications.

---

## Summary
This project demonstrates the practical application of **Java Swing**, **MySQL**, and **MVC architecture** to build a real-world solution addressing a critical social issue: improving the reporting and support system for GBV survivors.

It is **secure**, **user-friendly**, **modular**, and **expandable** for future enhancements.

---



## Future
-Live locations if necessary