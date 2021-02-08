package es.pue.myvueling.model.persistence.daos.impl;

import es.pue.myvueling.model.business.entities.planning.Event;
import es.pue.myvueling.model.persistence.daos.contracts.EventDAO;
import es.pue.myvueling.model.persistence.exceptions.DAOException;
import es.pue.myvueling.model.persistence.utilities.JDBCUtils;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


public class EventJDBCDAO implements EventDAO {

    @Override
    public Event getEventById(long id) throws DAOException {
        Event event = null;
        
        try(Connection connection = JDBCUtils.openConnection();
            CallableStatement sql = connection.prepareCall("CALL getEventById(?)");) {
                sql.setLong(1, id);
                try(ResultSet reader = sql.executeQuery()) {
                    if (reader.next()) {
                        //ORM: -|-|-|-|-|-| ----> []Color
                        event = JDBCUtils.getEvent(reader);
                    }
                }
        } catch (SQLException | IOException ex) {
            throw new DAOException(ex);
        }
        return event;
    }

    @Override
    public List<Event> getCurrentWeekEvents() throws DAOException{
        List<Event> events = new ArrayList<>();
        
        try(Connection connection = JDBCUtils.openConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT e.id, e.name, e.date, e.startTime, e.endTime, e.place, e.description, e.backgroundColor, e.textColor, e.visible, e.registrationDate,bc.id, bc.name, bc.red, bc.green, bc.blue, tc.id, tc.name, tc.red, tc.green, tc.blue FROM events e INNER JOIN Colors bc ON e.backgroundColor = bc.id INNER JOIN Colors tc ON e.textColor = tc.id WHERE e.date BETWEEN ? AND ?");) {
                sql.setDate(1, Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY)));
                sql.setDate(2, Date.valueOf(LocalDate.now().with(DayOfWeek.SUNDAY)));
                try(ResultSet reader = sql.executeQuery()) {
                    while (reader.next()) {
                        //ORM: -|-|-|-|-|-| ----> []Color
                        events.add(JDBCUtils.getEvent(reader));
                    }
                }
        } catch (SQLException | IOException ex) {
            throw new DAOException(ex);
        }
        return events;
    }

    @Override
    public List<Event> getCurrentMonthEvents() throws DAOException {
        List<Event> events = new ArrayList<>();
        
        try(Connection connection = JDBCUtils.openConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT e.id, e.name, e.date, e.startTime, e.endTime, e.place, e.description, e.backgroundColor, e.textColor, e.visible, e.registrationDate,bc.id, bc.name, bc.red, bc.green, bc.blue, tc.id, tc.name, tc.red, tc.green, tc.blue FROM events e INNER JOIN Colors bc ON e.backgroundColor = bc.id INNER JOIN Colors tc ON e.textColor = tc.id WHERE e.date BETWEEN ? AND ?");) {
                sql.setDate(1, Date.valueOf(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())));
                sql.setDate(2, Date.valueOf(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())));
                try(ResultSet reader = sql.executeQuery()) {
                    while (reader.next()) {
                        //ORM: -|-|-|-|-|-| ----> []Color
                        events.add(JDBCUtils.getEvent(reader));
                    }
                }
        } catch (SQLException | IOException ex) {
            throw new DAOException(ex);
        }
        return events;
    }
    
    @Override
    public List<Event> getEvents() throws DAOException {
        List<Event> events = new ArrayList<>();
        
        try(Connection connection = JDBCUtils.openConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT e.id, e.name, e.date, e.startTime, e.endTime, e.place, e.description, e.backgroundColor, e.textColor, e.visible, e.registrationDate,bc.id, bc.name, bc.red, bc.green, bc.blue, tc.id, tc.name, tc.red, tc.green, tc.blue FROM events e INNER JOIN Colors bc ON e.backgroundColor = bc.id INNER JOIN Colors tc ON e.textColor = tc.id");
            ResultSet reader = sql.executeQuery()) {
                while (reader.next()) {
                    //ORM: -|-|-|-|-|-| ----> []Color
                    events.add(JDBCUtils.getEvent(reader));
                }
        } catch (SQLException | IOException ex) {
            throw new DAOException(ex);
        }
        return events;
    }

    @Override
    public List<Event> getEvents(int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents(boolean visible) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents(boolean visible, int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents(String searchTerm) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents(String searchTerm, int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents(boolean visible, String searchTerm) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> getEvents(boolean visible, String searchTerm, int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
