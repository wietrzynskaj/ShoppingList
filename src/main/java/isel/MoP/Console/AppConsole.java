package isel.MoP.Console;

import org.apache.log4j.BasicConfigurator;

public class AppConsole {


    public static void main(String[] args) {
        BasicConfigurator.configure();
        ControllerConsole controllerConsole = new ControllerConsole();
        controllerConsole.run();
    }
}
