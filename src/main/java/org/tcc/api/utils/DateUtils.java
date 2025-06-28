package org.tcc.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class DateUtils {
    public static final String DATA_HORA= "dd/MM/yyyy HH:mm:ss";
    public static final String DATA= "dd/MM/yyyy";

    public static LocalDate stringToLocalDate(String data) {
        if(data==null)
            return null;
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDateTime stringToLocalDateTime(String data) {
        if(data==null)
            return null;
        return LocalDateTime.of(stringToLocalDate(data), LocalTime.MIN);
    }

    public static Date stringToDate(String data) throws ParseException {
        if(data==null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(data);
        return date;
    }
    public static String localDateToString(LocalDate data){
        if(data==null)
            return null;
        return data.format(DateTimeFormatter.ofPattern(DATA));
    }

    public static String localDateTimeToString(LocalDateTime data){
        if(data==null)
            return null;
        return data.format(DateTimeFormatter.ofPattern(DATA_HORA));
    }

    public static String mesExtenso(Integer mes){
        HashMap<Integer,String> mesExtenso = new HashMap<>();

        mesExtenso.put(1,"Janeiro");
        mesExtenso.put(2,"Fevereiro");
        mesExtenso.put(3,"Março");
        mesExtenso.put(4,"Abril");
        mesExtenso.put(5,"Maio");
        mesExtenso.put(6,"Junho");
        mesExtenso.put(7,"Julho");
        mesExtenso.put(8,"Agosto");
        mesExtenso.put(9,"Setembro");
        mesExtenso.put(10,"Outubro");
        mesExtenso.put(11,"Novembro");
        mesExtenso.put(12,"Dezembro");


        return mesExtenso.get(mes);
    }

}
