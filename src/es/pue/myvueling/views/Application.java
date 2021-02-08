package es.pue.myvueling.views;

import es.pue.myvueling.model.business.entities.planning.Color;
import es.pue.myvueling.model.business.entities.planning.Event;
import es.pue.myvueling.model.persistence.daos.impl.ColorJDBCDAO;
import es.pue.myvueling.model.persistence.daos.impl.EventJDBCDAO;
import es.pue.myvueling.model.persistence.exceptions.DAOException;
import java.util.List;
import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        
        ColorJDBCDAO colorDAO = new ColorJDBCDAO();
        EventJDBCDAO eventDAO = new EventJDBCDAO();
        
        try {
            Color c = colorDAO.getColorById(1);
            if (c != null) {
                System.out.println(c.toString());
            }
            
        } catch (DAOException ex) {
            System.out.printf("Error:: %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        stdin.nextLine();
        
        try {
            List<Color> colors = colorDAO.getColors();
            for (Color c : colors) {
                System.out.println(c.toString());
            }
            
        } catch (DAOException ex) {
            System.out.printf("Error:: %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        stdin.nextLine();
        
        try {
            Event e = eventDAO.getEventById(5);
            if (e != null) {
                System.out.println(e.toString());
            }
            
        } catch (DAOException ex) { 
            System.out.printf("Error:: %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        stdin.nextLine();
        
        try {
            List<Event> events = eventDAO.getEvents();
            for (Event e : events) {
                System.out.println(e.toString());
            }
        } catch (DAOException ex) {
            System.out.printf("Error:: %s %n", ex.getMessage());
        }
        
        System.out.println("Pulsa una tecla para continuar");
        stdin.nextLine();
        
        
    }
}
