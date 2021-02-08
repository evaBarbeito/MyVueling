package es.pue.myvueling.model.business.entities;

import es.pue.myvueling.model.business.utilities.NumberUtils;


public class Entity {

    private long id = NumberUtils.UNSAVED_VALUE;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (this.getId() != NumberUtils.UNSAVED_VALUE) {
            throw new UnsupportedOperationException("No se puede cambiar el id de un objeto ya asignado");
        }
        if (id <= NumberUtils.INTEGER_ZERO) {    
            throw new IllegalArgumentException("El id a asignar a una entidad debe ser un valor positivo");
        }
        
        this.id = id;
    }

}
