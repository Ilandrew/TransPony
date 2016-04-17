package by.bsuir.mpp.transpony.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String USER_ID_ATTRIBUTE = "user_id";

    String execute(HttpServletRequest request);
}
