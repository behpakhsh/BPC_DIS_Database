package database.bpc.bpc_dis_database.Convertors;

import android.annotation.SuppressLint;

import androidx.room.TypeConverter;

import java.util.Date;

import bpc.dis.utilities.StringUtilities.StringUtilities;

@SuppressLint("SimpleDateFormat")
public class TimestampConverter {

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            if (StringUtilities.isNotNumber(value)) {
                return new Date(Long.parseLong("1603710578325"));
            } else {
                return new Date(Long.parseLong(value));
            }
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String dateToTimestamp(Date date) {
        return date == null ? null : String.valueOf(date.getTime());
    }

}