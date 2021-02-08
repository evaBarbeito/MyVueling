package es.pue.myvueling.model.persistence.daos.impl;

import es.pue.myvueling.model.business.entities.planning.Color;
import es.pue.myvueling.model.persistence.daos.contracts.ColorDAO;
import es.pue.myvueling.model.persistence.exceptions.DAOException;
import es.pue.myvueling.model.persistence.utilities.JDBCUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ColorJDBCDAO implements ColorDAO {

    @Override
    public Color getColorById(long id) throws DAOException {

        Color color = null;
        PreparedStatement sentSQL= null;
        ResultSet reader=null;
        Connection connection=null;
        
        try {
        	
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar?serverTimezone=Europe/Paris", "root", "admin");
            sentSQL = connection.prepareStatement("SELECT id, name, red, green, blue FROM colors WHERE id= ?");
                
          
            //exemple de try amb recursos a inicialitzar
            // ja no cal el finally, doncs tanca recursos automàticament, de manera inversa a com s'han instanciat
        //try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar?serverTimezone=Europe/Paris", "root", "admin");
        //    CallableStatement sentSQL = connection.prepareCall("CALL getColorById(?)")) {
        	       	
            sentSQL.setLong(1, id);
            reader = sentSQL.executeQuery(); // aquí es podria fer un try amb resources també
                if (reader.next()) {
                    // ORM: [--,--,--,--,--,--] -----> []Color
                    color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"), reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                }            
            
        }
        catch (SQLException ex) {
            //Logger
            throw new DAOException(ex);
        }
        finally {
        	try {
        	if (reader !=null) reader.close();
        	if (sentSQL !=null) sentSQL.close();	
        	if (connection !=null) connection.close();
        	} catch (SQLException ex) {
        		throw new DAOException(ex);
        	}
        }
        	
        
        return color;
    }
    
    @Override
    public List<Color> getColors() throws DAOException {
        
        List<Color> colors = new ArrayList<>();
        
        // exemple de Procediment emmagatzemat, stored procedure
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar?serverTimezone=Europe/Paris", "root", "admin");
             CallableStatement sentSQL = connection.prepareCall("CALL getColors()");
             ResultSet reader = sentSQL.executeQuery()) {
            
                while (reader.next()) {
                    // ORM: [--,--,--,--,--,--] -----> []Color
                    var color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"), reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                    colors.add(color);
                }            
        }
        catch (SQLException ex) {
            //Logger
            throw new DAOException(ex);
        }
        
        return colors;
    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Color> getColors(String searchTerm) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Color> getColors(String searchTerm, int offset, int count) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
