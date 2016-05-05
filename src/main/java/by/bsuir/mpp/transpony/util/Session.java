package by.bsuir.mpp.transpony.util;

import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * Created by vadim on 04.05.16.
 */
public class Session {
    private static Map<String, Object> sessionMap = ActionContext.getContext().getSession();

    public void put(String key, Object value) {
        sessionMap.put(key, value);
    }

    public Object getValue(String key) {
        return sessionMap.get(key);
    }

    public boolean containsKey(Object value) {
        return sessionMap.containsValue(value);
    }

    public void remove(int key) {
        sessionMap.remove(key);
    }

}
