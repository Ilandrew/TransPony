package by.bsuir.mpp.transpony.dao.impl.mysql;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.IWaybill;
import by.bsuir.mpp.transpony.entity.*;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlWaybill implements IWaybill{


    @Override
    public List<Waybill> getAll() throws DaoException {

        Connection connection = null;
        Statement statement = null;
        Waybill waybill = null;
        List<Waybill> waybills = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT wl.id_waybill AS id_waybill,\n" +
                    "        wl.profit AS profit,\n" +
                    "        co.id_cargo AS id_cargo,\n" +
                    "        co.name AS name_cargo,\n" +
                    "        co.id_cargo_type AS id_cargo_type,\n" +
                    "        ct.name AS cargo_type,\n" +
                    "        co.price AS prise,\n" +
                    "        co.volume AS volume,\n" +
                    "        co.weight AS weight,\n" +
                    "        pr.id_provider as id_provider,\n" +
                    "        pr.name as name_provider,\n" +
                    "        pr.address as address_provider,\n" +
                    "        pr.phone as phone_provider,\n"+
                    "        pr.email as email_provider,\n"+
                    "        rr.id_reciever AS id_receiver,\n" +
                    "        rr.name AS name_receiver,\n" +
                    "        rr.address AS address_receiver,\n" +
                    "        rr.email AS email_receiver,\n" +
                    "        rr.phone AS phone_receiver,\n" +
                    "        dp.id_delivery_point AS id_delivery_point,\n" +
                    "        dp.address AS address_delivery_point\n" +
                    "\n" +
                    "FROM WAYBILL wl\n" +
                    "LEFT JOIN CARGO co ON wl.id_cargo = co.id_cargo\n" +
                    "LEFT JOIN RECIEVER rr ON wl.id_reciever = rr.id_reciever\n" +
                    "LEFT JOIN DELIVERY_POINT dp ON wl.id_delivery_point = dp.id_delivery_point\n" +
                    "LEFT JOIN CARGO_TYPE ct ON co.id_cargo_type = ct.id_cargo_type\n" +
                    "LEFT JOIN PROVIDER pr ON co.id_provider = pr.id_provider\n" +
                    "\n");

            while (result.next()) {

                waybill = new Waybill();
                Cargo cargo = new Cargo();
                Receiver receiver = new Receiver();
                DeliveryPoint  deliveryPoint = new DeliveryPoint();
                Provider provider = new Provider();

                waybill.setId(result.getInt("id_waybill"));
                waybill.setProfit(result.getBigDecimal("profit"));
                cargo.setId(result.getInt("id_cargo"));
                cargo.setCargoType(result.getString("cargo_type"));
                cargo.setName(result.getString("name_cargo"));
                cargo.setPrise(result.getBigDecimal("prise"));
                cargo.setVolume(result.getInt("volume"));
                cargo.setWeight(result.getInt("weight"));
                provider.setId(result.getInt("id_provider"));
                provider.setName("name_provider");
                provider.setAddress("address_provider");
                provider.setPhone("phone_provider");
                provider.setEmail("email_provider");
                cargo.setProvider(provider);
                waybill.setCargo(cargo);
                receiver.setId(result.getInt("id_receiver"));
                receiver.setName(result.getString("name_receiver"));
                receiver.setAddress(result.getString("address_receiver"));
                receiver.setEmail(result.getString("email_receiver"));
                receiver.setPhone(result.getString("phone_receiver"));
                waybill.setReceiver(receiver);
                deliveryPoint.setId(result.getInt("id_delivery_point"));
                deliveryPoint.setAddress(result.getString("address_delivery_point"));
                waybill.setDeliveryPoint(deliveryPoint);
                waybills.add(waybill);

            }
        } catch (SQLException|NamingException e) {
            throw new DaoException("can't get all waybill", e);
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return waybills;
    }

    @Override
    public void delete(Waybill waybill) {

    }

    @Override
    public void update(Waybill waybill) {

    }

    @Override
    public void add(Waybill waybill) {

    }

    @Override
    public List<Receiver> getAllReceiver() {
        return null;
    }

    @Override
    public List<DeliveryPoint> getAllDeliveryPoints() {
        return null;
    }


}
