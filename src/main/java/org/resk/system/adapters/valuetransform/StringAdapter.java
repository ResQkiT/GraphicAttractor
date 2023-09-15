package org.resk.system.adapters.valuetransform;

public class StringAdapter extends BaseAdapter{
    @Override
    public String getApaptedValue(Object o) {
        return o.toString();
    }
}
