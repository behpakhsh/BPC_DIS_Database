package database.bpc.bpc_dis_database.Convertors;

import android.annotation.SuppressLint;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimestampConverter {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
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
            return value == null ? null : dateFormat.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}