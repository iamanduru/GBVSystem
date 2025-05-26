package rmi;

import model.Report;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GBVService extends Remote {

    boolean insertReport(Report report) throws RemoteException;

    List<Report> getAllReports() throws RemoteException;

    boolean updateReport(Report report) throws RemoteException;

    boolean deleteReport(int reportId) throws RemoteException;

}
