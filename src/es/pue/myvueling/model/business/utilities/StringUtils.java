package es.pue.myvueling.model.business.utilities;


public final class StringUtils {

    public static final char DEGREES = '\u00B0';
    public static final char MINUTES = '\u2032';
    public static final char SECONDS = '\u2033';
    
    
    private StringUtils() {
    }

    public static boolean isEmpty(String name) {
        return name == null || name.trim().isEmpty();
    }
    
}
