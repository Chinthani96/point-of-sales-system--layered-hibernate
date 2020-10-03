package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CustomerDAO;
import db.HibernateUtil;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private static CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    public List<CustomerTM> getAllCustomers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;

        List<Customer> allCustomers = null;
        List<CustomerTM> customerTMS = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            allCustomers = customerDAO.findAll();
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
        for (Customer customer : allCustomers) {
            customerTMS.add(new CustomerTM(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerTMS;
    }

    public void saveCustomer(String id, String name, String address) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            customerDAO.save(new Customer(id,name,address));
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }

    }

    public void updateCustomer(String id, String name, String address) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            customerDAO.update(new Customer(id, name, address));
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }
    }

    public void deleteCustomer(String id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            customerDAO.delete(id);
            tx.commit();
        } catch (Throwable t) {
            tx.rollback();
            throw t;
        }
    }
    public String generateNewCustomerId(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String lastCustomerId = customerDAO.getLastCustomerId();
            tx.commit();
            int lastNumber = Integer.parseInt(lastCustomerId.substring(1, 4));
            if (lastNumber==0) {
//                lastNumber++;
                return "C001";
            } else if (lastNumber<9) {
                lastNumber++;
                return "C00" +lastNumber;
            } else if (lastNumber<99) {
                lastNumber++;
                return "C0" +lastNumber;
            }
            else{
                lastNumber++;
                return "C" +lastNumber;
            }
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
}
