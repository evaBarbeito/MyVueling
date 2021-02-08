package es.pue.myvueling.model.business.utilities;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public final class NumberUtils {
    
    //<editor-fold defaultstate="collapsed" desc="Constructor privado">
    
    private NumberUtils() {
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Atributos/Campos estáticos">
    public static final double DOUBLE_ZERO = 0.0;
    public static final int INTEGER_ZERO = 0;
    public static final int INTEGER_ONE = 1;
    public final static long UNSAVED_VALUE = -1;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos/Operaciones estáticas">
    
    public static double avg(double op1, double... ops) {
        return (ops == null || ops.length == 0) ? op1 : DoubleStream.concat(DoubleStream.of(op1), DoubleStream.of(ops)).average().getAsDouble();
    }
    
    public static long max(long op1, long op2, long... ops) {
        
        return (ops == null || ops.length == 0) ? Math.max(op1, op2) : LongStream.concat(LongStream.of(op1, op2), LongStream.of(ops)).max().getAsLong();
    }
    
    public static int max(int op1, int op2, int... ops) {
        
        return (ops == null || ops.length == 0) ? Math.max(op1, op2) : IntStream.concat(IntStream.of(op1, op2), IntStream.of(ops)).max().getAsInt();
    }
    
    public static double max(double op1, double op2, double... ops) {
        
        return (ops == null || ops.length == 0) ? Math.max(op1, op2) : DoubleStream.concat(DoubleStream.of(op1, op2), DoubleStream.of(ops)).max().getAsDouble();
    }
    
    public static long min(long op1, long op2, long... ops) {
        
        return (ops == null || ops.length == 0) ? Math.min(op1, op2) : LongStream.concat(LongStream.of(op1, op2), LongStream.of(ops)).min().getAsLong();
    }
    
    public static int min(int op1, int op2, int... ops) {
        
        return (ops == null || ops.length == 0) ? Math.min(op1, op2) : IntStream.concat(IntStream.of(op1, op2), IntStream.of(ops)).min().getAsInt();
    }
    
    public static double min(double op1, double op2, double... ops) {
        
        return (ops == null || ops.length == 0) ? Math.min(op1, op2) : DoubleStream.concat(DoubleStream.of(op1, op2), DoubleStream.of(ops)).min().getAsDouble();
    }
    
    
    public static double median(double op1, double... ops) {
        if (ops == null || ops.length == 0) {
            return op1;
        }
        
        double[] values = DoubleStream.concat(DoubleStream.of(op1), DoubleStream.of(ops))
                                      .sorted()
                                      .toArray();
        
        int middle = values.length / 2;
        return (values.length % 2 != 0) ? values[middle] : avg(values[middle-1], values[middle]);
    }
    
   
    
    //</editor-fold>
}
