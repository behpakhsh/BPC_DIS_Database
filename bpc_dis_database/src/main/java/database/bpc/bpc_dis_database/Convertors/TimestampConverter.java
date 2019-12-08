package database.bpc.bpc_dis_database.Convertors;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import androidx.room.TypeConverter;

@SuppressLint("SimpleDateFormat")
public class TimestampConverter {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
            try {
                return dateFormat.parse(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @TypeConverter
    public static String dateToTimestamp(Date value) {
        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
            return value == null ? null : dateFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}