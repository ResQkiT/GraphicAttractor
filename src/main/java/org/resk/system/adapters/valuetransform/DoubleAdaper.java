package org.resk.system.adapters.valuetransform;

public class DoubleAdaper extends BaseAdapter{
    @Override
    public Double getApaptedValue(Object object) {
        return Double.parseDouble(object.toString());
    }
}
