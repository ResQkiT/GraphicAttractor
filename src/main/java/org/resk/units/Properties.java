package org.resk.units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Properties<T>{
    private LinkedHashMap<String,T> data = new LinkedHashMap<>();
    public Properties<T> registerProperties(String name, T value){
        data.put(name, value);
        return this;
    }
    public T getByName(String name){
        return data.get(name);
    }

    public HashMap<String, T> getList() {
        return data;
    }

    public void setByName(String name, T value){
        data.put(name , value);
    }
}