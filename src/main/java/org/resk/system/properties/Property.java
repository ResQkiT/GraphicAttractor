package org.resk.system.properties;

import org.resk.system.adapters.valuetransform.BaseAdapter;
import org.resk.system.adapters.valuetransform.DoubleAdaper;
import org.resk.system.adapters.valuetransform.StringAdapter;

public class Property<T> {
    private String name;
    private T value;
    private BaseAdapter adapter;
    public String getName() {
        return name;
    }
    public T getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = (T)adapter.getApaptedValue(value);
    }

    public Property(String name, T value) {
        this.name = name;
        this.value = value;
        if(value instanceof String){
            adapter = new StringAdapter();
        }else if(value instanceof Double){
            adapter = new DoubleAdaper();
        }
    }
}
