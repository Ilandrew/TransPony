package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.util.Session;

import java.util.UUID;

public class SessionService {

    private Session session = new Session();

    public String generateSessionToken() {
        return UUID.randomUUID().toString();
    }

    public void saveUserAuthentication(int id, String token) {
        session.remove(id);
        session.put(String.valueOf(id), token);
    }

    public boolean sessionContainsToken(String token) {
        return session.containsKey(token);
    }

    public String getUserToken(int id) throws ServiceException {
        try {
            return (String) session.getValue(Integer.toString(id));
        } catch (Exception e) {
            throw new ServiceException("Куки не заданны");
        }
    }

    public void removeUser(int id) {
        session.remove(id);
    }

}

