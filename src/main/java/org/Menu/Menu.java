package org.Menu;
import org.Menu.Application;
import org.fitsay.tourist_trips.Database.DatabaseHandler;
public class Menu extends DatabaseHandler  {
    public void menu(){
        Connect();
        Application x = new Application();
        x.Launch();
        Disconnect();
    }
}
