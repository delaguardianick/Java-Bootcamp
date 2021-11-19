
import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.view.FlooringMasteryView;
import com.sg.flooringmastery.view.UserIO;
import com.sg.flooringmastery.view.UserIOConsoleImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gordak
 */
public class App {
    public static void main(String[] args) {
        
//        String VMTextFile = "vendingMachine.txt";
        FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
        
        
        FlooringMasteryServiceLayer myService = new 
            FlooringMasteryServiceLayerImpl(myDao);
        
        UserIO myIO = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIO);

        FlooringMasteryController controller = new 
            FlooringMasteryController(myService, myView);
        
        controller.run();
    }
}
