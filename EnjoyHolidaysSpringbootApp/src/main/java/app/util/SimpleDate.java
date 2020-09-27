
package app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*** @author Christian Mendieta*/

public class SimpleDate {
    
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    
    public Date getDateOf(String _fecha) throws ParseException{
        Date fecha = formato.parse(_fecha);
        if(_fecha.equals(formato.format(fecha)))
            return fecha;
        else
            return formato.parse("Error");
    }
    
    
    public boolean isAfter(Date fecha1, Date fecha2){
        if(!this.equals(fecha1, fecha2))
            return fecha1.after(fecha2);
        else
            return false;
    }
    
    
    public boolean isBefore(Date fecha1, Date fecha2){
        if(!this.equals(fecha1, fecha2))
            return fecha1.before(fecha2);
        else
            return false;
    }
    
    
    public boolean equals(Date fecha1, Date fecha2){
        String _fecha1 = formato.format(fecha1);
        String _fecha2 = formato.format(fecha2);
        return _fecha1.equals(_fecha2);
    }
    
    
    public boolean isValidPeriodToReserve(Date fechaInicio, Date fechaFin){
        Date fechaActual = new Date();
        if(this.isAfter(fechaInicio, fechaActual))
            if(this.isAfter(fechaFin, fechaInicio))
                return true;
            else
                return false;
        else
            return false;
    }
    
    
    public boolean isLegalAge(Date fechaNacimiento){
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
