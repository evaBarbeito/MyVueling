package es.pue.myvueling.model.business.entities.geography;


public enum CardinalPoint {
    
    NORTH('N', "North", "Norte"), 
    SOUTH('S', "South", "Sur"), 
    WEST('W', "West", "Oeste"), 
    EAST('E', "East", "Este");
    
    //<editor-fold defaultstate="collapsed" desc="Atributos/Campos (Estado)">
    private final char symbol;
    private final String englishName;
    private final String spanishName;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    private CardinalPoint(char symbol, String englishName, String spanishName) {
        this.symbol = symbol;
        this.englishName = englishName;
        this.spanishName = spanishName;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos/Operaciones">
    public char getSymbol() {
        return symbol;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getSpanishName() {
        return spanishName;
    }
    //</editor-fold>

}
