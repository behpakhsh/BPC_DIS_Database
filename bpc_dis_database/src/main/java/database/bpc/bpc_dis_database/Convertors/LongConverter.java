package database.bpc.bpc_dis_database.Convertors;

import androidx.room.TypeConverter;

import java.util.List;

import serialization.bpc.bpc_dis_serialization.Gson.GSONManager;

public class LongConverter {

    @TypeConverter
    public static List<Long> fromTimestamp(String value) {
        if (value != null) {
            try {
                return GSONManager.fromJsonList(value, Long.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @TypeConverter
    public static String dateToTimestamp(List<Long> longs) {
        if (longs != null) {
            try {
                return GSONManager.toJson(longs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}