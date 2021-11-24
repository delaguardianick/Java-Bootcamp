
import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.view.FlooringMasteryView;
import com.sg.flooringmastery.view.UserIO;
import com.sg.flooringmastery.view.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/*
    Main driver of the app
    Sets up all constructors and starts the controller
*/
public class App {
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext();
        
                appContext.scan("com.sg.flooringmastery");
                appContext.refresh();
                        
                
        FlooringMasteryController controller = appContext.
                getBean("flooringMasteryController",
                        FlooringMasteryController.class);
        
        controller.run();
        
//        String VMTextFile = "vendingMachine.txt";
//        FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
//        
//        
//        FlooringMasteryServiceLayer myService = new 
//            FlooringMasteryServiceLayerImpl(myDao);
//        
//        UserIO myIO = new UserIOConsoleImpl();
//        FlooringMasteryView myView = new FlooringMasteryView(myIO);
//
//        FlooringMasteryController controller = new 
//            FlooringMasteryController(myService, myView);
//        
//        controller.run();
    }
}
