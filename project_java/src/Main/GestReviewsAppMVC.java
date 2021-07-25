package Main;

//import java.nio.file.*;

import Controller.ControllerJ;
import View.View_MainMenu;

public class GestReviewsAppMVC{
    public static void main(String[] args){
        IGestReviews gestRev = new GestReviews(); 
        View_MainMenu view = new View_MainMenu(); 
        ControllerJ controlador = new ControllerJ();

        /* Path cfg_path = Paths.get(System.getProperty("user.dir") + "/config.cfg");
        if (!Files.isReadable(cfg_path)) 
            System.exit(0);
        StreamReader sr_config = new StreamReader(cfg_path.toString());
        sr_config.parseConfig(gestRev); */

        controlador.setModel(gestRev);
        controlador.setView(view);
        controlador.start();

    }
}
