package by.bsuir.mpp.transpony.servise;

import by.bsuir.mpp.transpony.dao.CredentialDao;
import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.factory.DaoFactory;
import by.bsuir.mpp.transpony.entity.User;

public class CredentialService  {
    private static final CredentialDao dao = DaoFactory.getDaoFactory().getCredentialDao();

    public static Integer getForCredential(String login, String pass) throws ServiceException {
        try {
            return dao.getForCredential(login, pass);
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static User getForCredentialUser(String login, String pass) throws ServiceException {
        try {
            return UserService.getForIndex(dao.getForCredential(login, pass));
        } catch (DaoException e) {
            throw new ServiceException("", e);
        }
    }

    public static Integer getRole(Integer index) {
        switch (index) {
            case 1: return 1;//Логист
            case 2: return 2;//Руководитель
            case 3: return 3;//Администратор
            case 4: return 4;//Водитель
            case 5: return 5;//Бухгалтер
            case 6: return 4;//Водитель
            case 7: return 4;//Водитель
            default: return 0;
        }
    }
}
