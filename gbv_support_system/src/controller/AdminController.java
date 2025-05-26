package controller;

import dao.UserDAO;

public class AdminController {

    public static boolean login(String username, String password) {
        return UserDAO.validateAdminCredentials(username, password);
    }
}
