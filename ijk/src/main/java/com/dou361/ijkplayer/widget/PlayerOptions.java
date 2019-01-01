package com.dou361.ijkplayer.widget;

import java.util.HashMap;
import java.util.Map;

public class PlayerOptions {
    public static final String KEY_BUFFER_TIME = "rtmp_buffer";
    public static final String KEY_FFLAGS = "fflags";
    public static final String VALUE_FFLAGS_NOBUFFER = "nobuffer";
    public static final String KEY_GET_AV_FRAME_TIMEOUT = "get-av-frame-timeout";
    public static final String KEY_LIVE_STREAMING = "live-streaming";
    public static final String KEY_MEDIACODEC = "mediacodec";
    private Map<String, Object> mMap = new HashMap();

    public PlayerOptions() {
    }

    public final boolean containsKey(String name) {
        return this.mMap.containsKey(name);
    }

    public final int getInteger(String name) {
        return ((Integer) this.mMap.get(name)).intValue();
    }

    public final int getInteger(String name, int defaultValue) {
        try {
            return this.getInteger(name);
        } catch (NullPointerException var4) {
            ;
        } catch (ClassCastException var5) {
            ;
        }

        return defaultValue;
    }

    public final long getLong(String name) {
        return ((Long) this.mMap.get(name)).longValue();
    }

    public final float getFloat(String name) {
        return ((Float) this.mMap.get(name)).floatValue();
    }

    public final String getString(String name) {
        return (String) this.mMap.get(name);
    }

    public final void setInteger(String name, int value) {
        this.mMap.put(name, new Integer(value));
    }

    public final void setLong(String name, long value) {
        this.mMap.put(name, new Long(value));
    }

    public final void setFloat(String name, float value) {
        this.mMap.put(name, new Float(value));
    }

    public final void setString(String name, String value) {
        this.mMap.put(name, value);
    }

    public Map<String, Object> getMap() {
        return this.mMap;
    }
}