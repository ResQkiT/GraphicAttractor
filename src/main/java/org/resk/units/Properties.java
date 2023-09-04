package org.resk.units;

import java.util.ArrayList;
import java.util.HashMap;

public class Properties<T>{
    private HashMap<String,T> data = new HashMap<>();
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