package es.pue.myvueling.model.persistence.daos.contracts;

import es.pue.myvueling.model.business.entities.planning.Color;
import es.pue.myvueling.model.persistence.exceptions.DAOException;
import java.util.List;

public interface ColorDAO {
    
    Color getColorById(long id) throws DAOException;
    List<Color> getColors() throws DAOException;
    List<Color> getColors(int offset, int count) throws DAOException;
    List<Color> getColors(String searchTerm) throws DAOException;
    List<Color> getColors(String searchTerm, int offset, int count) throws DAOException;
    
}
