package es.pue.myvueling.model.persistence.daos.contracts;

import es.pue.myvueling.model.business.entities.planning.Event;
import es.pue.myvueling.model.persistence.exceptions.DAOException;
import java.util.List;

public interface EventDAO {
    
    Event getEventById(long id) throws DAOException;
    List<Event> getEvents() throws DAOException;
    List<Event> getEvents(int offset, int count) throws DAOException;
    List<Event> getEvents(boolean visible) throws DAOException;
    List<Event> getEvents(boolean visible, int offset, int count) throws DAOException;
    List<Event> getEvents(String searchTerm) throws DAOException;
    List<Event> getEvents(String searchTerm, int offset, int count) throws DAOException;
    List<Event> getEvents(boolean visible, String searchTerm) throws DAOException;
    List<Event> getEvents(boolean visible, String searchTerm, int offset, int count) throws DAOException;
    
    List<Event> getCurrentWeekEvents() throws DAOException;
    List<Event> getCurrentMonthEvents() throws DAOException;
}
