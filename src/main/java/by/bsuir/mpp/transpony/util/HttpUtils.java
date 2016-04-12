package by.bsuir.mpp.transpony.util;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class HttpUtils {
    public static String extractCommandFromQuery(String query, String commandPostfix) {
        String result = query.replaceAll(commandPostfix + ".*", "");
        result = result.replaceAll(".*/", "");
        return result.toUpperCase();
    }

    public static void cleanSession(HttpSession session) {
        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()) {
            session.removeAttribute(attributes.nextElement());
        }
    }

    public static int[] convertToIntParameters(String[] params) {
        if (params == null) return new int[0];
        int[] intParams = new int[params.length];
        for (int i = 0; i < params.length; i++) {
            try {
                intParams[i] = Integer.parseInt(params[i]);
            } catch (NumberFormatException e) {
                intParams[i] = 0;
            }
        }
        return intParams;
    }
}
