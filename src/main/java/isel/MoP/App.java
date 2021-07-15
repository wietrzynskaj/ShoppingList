package isel.MoP;

import isel.MoP.Controller.Controller;
import org.apache.log4j.BasicConfigurator;

public class App {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Controller controller = new Controller();
        new InitFrame(controller);
    }
}
