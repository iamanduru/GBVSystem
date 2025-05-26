package util;

import rmi.GBVService;
import rmi.GBVServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            // Start RMI registry on port 6000
            LocateRegistry.createRegistry(6000);

            // Instantiate and bind our service
            GBVService service = new GBVServiceImpl();
            Naming.rebind("//localhost:6000/GBVService", service);

            System.out.println("GBVService bound on port 6000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
