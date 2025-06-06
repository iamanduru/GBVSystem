
package controller;

import dao.SupportServiceDAO;
import model.SupportService;

import java.util.List;

public class SupportServiceController {
    private final SupportServiceDAO dao;

    public SupportServiceController() {
        this.dao = new SupportServiceDAO();
    }

    public boolean addSupportService(SupportService service) {
        return dao.addSupportService(service);
    }

    public List<SupportService> getAllSupportServices() {
        return dao.getAllServices();
    }
}
