package es.pue.myvueling.model.persistence.utilities;

import es.pue.myvueling.model.business.entities.planning.Color;
import es.pue.myvueling.model.business.entities.planning.Event;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public final class JDBCUtils {

    
    private JDBCUtils() {
    }

    public static Connection openConnection() throws SQLException, IOException {
        Properties props = new Properties();
        props.load(new FileReader("config/jdbc.properties"));
        return DriverManager.getConnection(props.getProperty("mysql.url"),
                                           props.getProperty("mysql.username"),
                                           props.getProperty("mysql.password"));
    }
    
    
    public static Color getColor(ResultSet reader) throws SQLException {
       Color c =  new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"), reader.getInt("blue"));
       c.setId(reader.getLong("id"));
       return c;
    }
    
    public static Event getEvent(ResultSet reader) throws SQLException {
       Color backgroundColor = new Color(reader.getString("bc.name"), reader.getInt("bc.red"), reader.getInt("bc.green"), reader.getInt("bc.blue"));
       backgroundColor.setId(reader.getLong("bc.id"));
       
       Color textColor = new Color(reader.getString("tc.name"), reader.getInt("tc.red"), reader.getInt("tc.green"), reader.getInt("tc.blue"));
       textColor.setId(reader.getLong("tc.id"));
       
       Event e =  new Event(reader.getString("e.name"), reader.getDate("e.date").toLocalDate(), reader.getTime("e.startTime").toLocalTime(), reader.getTime("e.endTime").toLocalTime(), reader.getString("e.place"), reader.getString("e.description"), backgroundColor, textColor, reader.getBoolean("e.visible"));
       e.setId(reader.getLong("e.id"));
       e.setRegistrationDate(reader.getTimestamp("e.registrationDate").toLocalDateTime());
       return e;
    }
    
}
