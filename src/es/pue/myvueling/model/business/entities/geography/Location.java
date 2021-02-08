package es.pue.myvueling.model.business.entities.geography;

import es.pue.myvueling.model.business.utilities.NumberUtils;
import es.pue.myvueling.model.business.utilities.StringUtils;
import java.util.Locale;
import java.util.Random;


public final class Location {

    //<editor-fold defaultstate="collapsed" desc="Estado: Atributos/Campos">
    private double latitude;
    private double longitude;

    public static final double MIN_LATITUDE = -90.0D;
    public static final double MAX_LATITUDE = 90.0D;
    public static final double MIN_LONGITUDE = -180.0D;
    public static final double MAX_LONGITUDE = 180.0D;
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double EARTH_RADIUS_MILES = 3958.75587;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Comportamiento: Métodos/Operaciones">
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Location(double latitude, double longitude) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }
    
    public Location() {
        this(0.0, 0.0);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    
    public double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(double latitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException(String.format("La latitud %.2f está fuera del rango [%.2f ; %+.2f]", latitude, MIN_LATITUDE, MAX_LATITUDE));
        }
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException(String.format("La longitud %.2f está fuera del rango [%.2f ; %+.2f]", longitude, MIN_LONGITUDE, MAX_LONGITUDE));
        }
       
        this.longitude = longitude;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos de objeto">
    
    //<editor-fold defaultstate="collapsed" desc="Método: getDecimalLatitude">
    public String getDecimalLatitude(boolean localeFormat, int precision) {
        return formatToDecimal(this.getLatitude(), localeFormat, precision);
    }

    public String getDecimalLatitude(boolean localeFormat) {
        return getDecimalLatitude(localeFormat, NumberUtils.INTEGER_ZERO);
    }
    
    public String getDecimalLatitude(int precision) {
        return getDecimalLatitude(false, precision);
    }
    
    public String getDecimalLatitude() {
        return getDecimalLatitude(false, NumberUtils.INTEGER_ZERO);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: getDecimalLongitude">
    public String getDecimalLongitude(boolean localeFormat, int precision) {
        return formatToDecimal(this.getLongitude(), localeFormat, precision);
    }
    
    public String getDecimalLongitude(boolean localeFormat) {
        return getDecimalLongitude(localeFormat, NumberUtils.INTEGER_ZERO);
    }
    
    public String getDecimalLongitude(int precision) {
        return getDecimalLongitude(false, precision);
    }
    
    public String getDecimalLongitude() {
        return getDecimalLongitude(false, NumberUtils.INTEGER_ZERO);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: toDecimal">
    public String toDecimal(boolean localeFormat, int precision) {
        return String.format("%s  %s", this.getDecimalLatitude(localeFormat, precision), this.getDecimalLongitude(localeFormat, precision));
    }
    
    public String toDecimal(boolean localeFormat) {
        return toDecimal(localeFormat, NumberUtils.INTEGER_ZERO);
    }
    
    public String toDecimal(int precision) {
        return toDecimal(false, precision);
    }
    
    public String toDecimal() {
        return toDecimal(false, NumberUtils.INTEGER_ZERO);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: getSexagesimalLatitude()">
    public String getSexagesimalLatitude(boolean localeFormat, int precision) {
        return formatToSexagesimal(this.getLatitude(), this.getLatitude() >= NumberUtils.INTEGER_ZERO ? CardinalPoint.NORTH : CardinalPoint.SOUTH, localeFormat, precision);
    }
    
    public String getSexagesimalLatitude(boolean localeFormat) {
        return getSexagesimalLatitude(localeFormat, NumberUtils.INTEGER_ZERO);
    }
    
    public String getSexagesimalLatitude(int precision) {
        return getSexagesimalLatitude(false, precision);
    }
    
    public String getSexagesimalLatitude() {
        return getSexagesimalLatitude(false, NumberUtils.INTEGER_ZERO);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: getSexagesimalLongitude()">
    public String getSexagesimalLongitude(boolean localeFormat, int precision) {
        return formatToSexagesimal(this.getLongitude(), this.getLongitude() >= NumberUtils.INTEGER_ZERO ? CardinalPoint.EAST : CardinalPoint.WEST, localeFormat, precision);
    }
    
    public String getSexagesimalLongitude(boolean localeFormat) {
        return getSexagesimalLongitude(localeFormat, NumberUtils.INTEGER_ZERO);
    }
    
    public String getSexagesimalLongitude(int precision) {
        return getSexagesimalLongitude(false, precision);
    }
    
    public String getSexagesimalLongitude() {
        return getSexagesimalLongitude(false, NumberUtils.INTEGER_ZERO);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: toSexagesimal()">
    public String toSexagesimal(boolean localeFormat, int precision) {
        return String.format("%s  %s", this.getSexagesimalLatitude(localeFormat, precision), this.getSexagesimalLongitude(localeFormat, precision));
    }
    
    public String toSexagesimal(boolean localeFormat) {
        return toSexagesimal(localeFormat, NumberUtils.INTEGER_ZERO);
    }
    
    public String toSexagesimal(int precision) {
        return toSexagesimal(false, precision);
    }
    
    public String toSexagesimal() {
        return toSexagesimal(false, NumberUtils.INTEGER_ZERO);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: getDistanceTo()">
    public double getDistanceInKmTo(Location loc) {
        return this.getDistanceTo(loc, EARTH_RADIUS_KM);
    }
    
    public double getDistanceInMilesTo(Location loc) {
        return this.getDistanceTo(loc, EARTH_RADIUS_MILES);
    }
    
    private double getDistanceTo(Location loc, double radius) {
        if (loc == null) {
            throw new NullPointerException("Referencia nula no permitida");
        }
        
        double lat1 = Math.toRadians(this.getLatitude());
        double lat2 = Math.toRadians(loc.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lon2 = Math.toRadians(loc.getLongitude());
        double dLon = lon2 - lon1;
        
        return radius * Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos(dLon) + Math.sin(lat1) * Math.sin(lat2));
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método: getMidpointTo()">
    public Location getMidpointTo(Location loc) {
        if (loc == null) {
            throw new NullPointerException("Referencia nula no permitida");
        }
        
        double lat1 = Math.toRadians(this.getLatitude());
        double lat2 = Math.toRadians(loc.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lon2 = Math.toRadians(loc.getLongitude());
        double dLon = lon2 - lon1;
        
        double bx = Math.cos(lat2) * Math.cos(dLon);
        double by = Math.cos(lat2) * Math.sin(dLon);
        double lat = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt(Math.pow((Math.cos(lat1)+ bx), 2) + Math.pow(by, 2)));
        double lon = lon1 + Math.atan2(by, Math.cos(lat1) + bx);
        return new Location(Math.toDegrees(lat), Math.toDegrees(lon));
    }
    //</editor-fold>
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Sobreescritura de métodos (overrides)">
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("- Coord.: %s %n", this.toDecimal(2)));
        sb.append(String.format("- Coord.: %s %n", this.toSexagesimal(2)));
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        
        Location other = (Location)obj;
        return this.getLatitude() == other.getLatitude() && this.getLongitude() == other.getLongitude();
    }

    @Override
    public int hashCode() {
        return this.toDecimal().hashCode();
    }

    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos estáticos">
   
    public static Location getRandom() {
        Random rnd = new Random();
        return new Location(rnd.doubles(1, MIN_LATITUDE, MAX_LATITUDE).findFirst().getAsDouble(), rnd.doubles(1, MIN_LONGITUDE, MAX_LONGITUDE).findFirst().getAsDouble());
    }
    
    public static double getDistanceInKmBetween(Location l1, Location l2) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException("Referencia a objeto nula no permitida");
        }
        
        return l1.getDistanceInKmTo(l2);
    }
    
    public static double getDistanceInMilesBetween(Location l1, Location l2) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException("Referencia a objeto nula no permitida");
        }
        
        return l1.getDistanceInMilesTo(l2);
    }
    
    public static Location getMidpointBetween(Location l1, Location l2) {
        if (l1 == null || l2 == null) {
            throw new NullPointerException("Referencia a objeto nula no permitida");
        }
        
        return l1.getMidpointTo(l2);
    }
    
    
    private static String formatToDecimal(double value, boolean localeFormat, int precision){
        if (precision < NumberUtils.INTEGER_ZERO) {
            throw new IllegalArgumentException("No se admite un valor negativo para la precisión decimal");
        }
        
        return String.format(localeFormat ? Locale.getDefault() : Locale.ENGLISH, precision == NumberUtils.INTEGER_ZERO ? "%f%c" : String.format("%%.%df%%c", precision), value, StringUtils.DEGREES);
    }
    
    private static String formatToSexagesimal(double value, CardinalPoint cp, boolean localeFormat, int precision){
        if (precision < NumberUtils.INTEGER_ZERO) {
            throw new IllegalArgumentException("No se admite un valor negativo para la precisión decimal");
        }
        
        value = Math.abs(value);
        int degrees = (int)value;
        value = ((value % 1) * 60);
        int minutes = (int)value;
        double seconds = (value % 1) * 60;
        
        return String.format(localeFormat ? Locale.getDefault() : Locale.ENGLISH, precision == NumberUtils.INTEGER_ZERO ? "%d%c %d%c %f%c %c" : String.format("%%d%%c %%d%%c %%.%df%%c %%c", precision) , degrees, StringUtils.DEGREES, minutes, StringUtils.MINUTES, seconds, StringUtils.SECONDS, cp.getSymbol());
    }
    
    //</editor-fold>
    
    //</editor-fold>

}
