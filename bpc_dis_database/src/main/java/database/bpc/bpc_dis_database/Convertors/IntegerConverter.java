package database.bpc.bpc_dis_database.Convertors;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

import serialization.bpc.bpc_dis_serialization.Gson.GSONManager;

public class IntegerConverter {

    @TypeConverter
    public String fromList(List<Integer> objects) {
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
    public List<Integer> toList(String json) {
        try {
            if (json == null || json.isEmpty()) {
                return (null);
            }
            return GSONManager.fromJsonList(json, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}