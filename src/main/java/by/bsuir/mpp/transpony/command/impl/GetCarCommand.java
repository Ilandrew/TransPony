package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.Car;
import by.bsuir.mpp.transpony.service.CarService;
import by.bsuir.mpp.transpony.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class GetCarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        List<Car> collection = new ArrayList<>();

        try {

            collection = CarService.getFreeCar();


        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("collection", collection);
        return "WEB-INF/carpage.jsp";
    }
}
