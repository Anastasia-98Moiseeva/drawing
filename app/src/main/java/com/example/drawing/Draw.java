package com.example.drawing;

import android.graphics.PointF;

public class Draw {
    private PointF origin;
    private PointF current;

    private int color;
    private Instrument instrument;

    public Draw(PointF origin, int color, Instrument instrument){
        this.origin = origin;
        this.current = origin;
        this.color = color;
        this.instrument = instrument;
    }

    public int getColor(){
        return color;
    }

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }

    public PointF getOrigin() {
        return origin;
    }

    public void setOrigin(PointF origin) {
        this.origin = origin;
    }

    public Instrument getInstrument() {
        return instrument;
    }
}
