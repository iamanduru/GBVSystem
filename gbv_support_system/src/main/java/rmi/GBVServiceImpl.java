package rmi;

import dao.ReportDAOHibernate;
import model.Report;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GBVServiceImpl extends UnicastRemoteObject implements GBVService {

    private final ReportDAOHibernate dao = new ReportDAOHibernate();

    public GBVServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean insertReport(Report report) throws RemoteException {
        return dao.insertReport(report);
    }

    @Override
    public List<Report> getAllReports() throws RemoteException {
        return dao.getAllReports();
    }

    @Override
    public boolean updateReport(Report report) throws RemoteException {
        return dao.updateReport(report);
    }

    @Override
    public boolean deleteReport(int reportId) throws RemoteException {
        return dao.deleteReport(reportId);
    }
}
