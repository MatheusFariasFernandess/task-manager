package org.tcc.api.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Utils {
    public static Long semanaTarefa(LocalDate localDate){
        if(localDate!=null) {
            TemporalField temporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            return (long) localDate.get(temporalField);
        }
        return null;
    }
}
