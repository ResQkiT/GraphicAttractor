package org.resk.system.properties;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Properties{

    private LinkedHashMap<String, Property> data = new LinkedHashMap<>();
    public Properties registerProperties(Property property){
        data.put(property.getName(), property );
        return this;
    }
    public Property getByName(String name){
        return data.get(name);
    }

    public HashMap<String, Property> getList() {
        return data;
    }

    public void setByName(String name, Property property){
        data.put(name , property );
    }
}