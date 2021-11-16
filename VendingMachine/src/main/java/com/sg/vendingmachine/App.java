/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Gordak
 */
public class App {
    public static void main(String[] args) {
        
       /*
        Using annotation SpringDI
        Starting the scan at App.java and going down the subfolders
        Looking for @Component classes and @Autowired constructors
        
        Initializing and running the controller after components are found.
        */
        AnnotationConfigApplicationContext appContext = 
                new AnnotationConfigApplicationContext();
        
                appContext.scan("com.sg.vendingmachine");
                appContext.refresh();
                
                
        VendingMachineController controller = appContext.
                getBean("vendingMachineController",
                VendingMachineController.class);
        
            controller.run();
        
//        String VMTextFile = "vendingMachine.txt";
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//        
//        VendingMachineAuditDao myAuditDao = 
//                new VendingMachineAuditDaoFileImpl();
//        
//        VendingMachineServiceLayer myService = new 
//            VendingMachineServiceLayerImpl(myDao, myAuditDao );
//        
//        UserIO myIO = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIO);
//
//        VendingMachineController controller = new 
//            VendingMachineController(myService, myView);
//        
//        controller.run();

    }
}
