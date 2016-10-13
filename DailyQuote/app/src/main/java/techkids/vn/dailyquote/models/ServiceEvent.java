package techkids.vn.dailyquote.models;

import android.os.Bundle;

/**
 * Created by apple on 10/12/16.
 */

public class ServiceEvent {
    private Class aClass;

    public ServiceEvent(Class aClass) {
        this.aClass = aClass;
    }

    public Class getaClass() {
        return aClass;
    }
}
