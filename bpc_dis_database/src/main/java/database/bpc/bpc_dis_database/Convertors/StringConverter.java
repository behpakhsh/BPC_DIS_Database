package database.bpc.bpc_dis_database.Convertors;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

import serialization.bpc.bpc_dis_serialization.Gson.GSONManager;

public class StringConverter {

    @TypeConverter
    public String fromList(List<String> objects) {
        try {
            if (objects == null) {
                return (null);
            }
            return GSONManager.toJson(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @TypeConverter
    public List<String> toList(String json) {
        try {
            if (json == null || json.isEmpty()) {
                return (null);
            }
            return GSONManager.fromJsonList(json, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}