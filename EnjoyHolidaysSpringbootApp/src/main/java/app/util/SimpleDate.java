
package app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*** @author Christian Mendieta*/

public class SimpleDate {
    
    private final static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    
    public static Date getDateOf(String _fecha) throws ParseException{
        Date fecha = formato.parse(_fecha);
        if(_fecha.equals(formato.format(fecha)))
            return fecha;
        else
            return formato.parse("Error");
    }
    
    
    public static boolean isAfter(Date fecha1, Date fecha2){
        if(!equals(fecha1, fecha2))
            return fecha1.after(fecha2);
        else
            return false;
    }
    
    
    public static boolean isBefore(Date fecha1, Date fecha2){
        if(!equals(fecha1, fecha2))
            return fecha1.before(fecha2);
        else
            return false;
    }
    
    
    public static boolean equals(Date fecha1, Date fecha2){
        String _fecha1 = formato.format(fecha1);
        String _fecha2 = formato.format(fecha2);
        return _fecha1.equals(_fecha2);
    }
    
    
    public static boolean isValidPeriodToReserve(Date fechaInicio, Date fechaFin){
        Date fechaActual = new Date();
        if(isAfter(fechaInicio, fechaActual))
            if(isAfter(fechaFin, fechaInicio))
                return true;
            else
                return false;
        else
            return false;
    }
    
    
    public static boolean isLegalAge(Date fechaNacimiento){
        Calendar fechaNac = new GregorianCalendar();
        Calendar fechaActual = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);
        int totalAnios = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int totalMeses = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int totalDias = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        if (totalMeses < 0 || (totalMeses == 0 && totalDias < 0)) 
            totalAnios = totalAnios - 1;
        return (totalAnios >= 18);
    }
    
}
