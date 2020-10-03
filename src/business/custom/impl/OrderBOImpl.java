package business.custom.impl;

import business.custom.OrderBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.QueryDAO;
import db.HibernateUtil;
import entity.CustomEntity;
import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.CustomerTM;
import util.SearchOrderTM;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    private static OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);
    private static OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOType.ORDERDETAIL);
    private static ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
    private static QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);

    public void saveOrder(String id, Date date, CustomerTM customer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            orderDAO.save(new Order(id,date,new Customer(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress())));
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public void saveOrderDetail(String orderId, String itemCode, int qty, double unitPrice){
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDetailDAO.setSession(session);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            orderDetailDAO.save(new OrderDetail(orderId,itemCode,qty,BigDecimal.valueOf(unitPrice)));
            tx.commit();
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    public String generateNewOrderId(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String lastOrderId = orderDAO.lastOrderId();
            tx.commit();
            int lastNumber = Integer.parseInt(lastOrderId.substring(2, 5));
            if (lastNumber<=0) {
                lastNumber++;
                return "OD001";
            } else if (lastNumber<9) {
                lastNumber++;
                return "OD00" +lastNumber;
            } else if (lastNumber<99) {
                lastNumber++;
                return "OD0" +lastNumber;
            }
            else{
                lastNumber++;
                return "OD" +lastNumber;
            }
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
    public boolean saveNewQty(int orderQty,String code){
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx =  null;
        try {
            tx = session.beginTransaction();
            int qty = itemDAO.getQty(code);
            int newQtyOnHand = qty - orderQty;
            itemDAO.updateQty(newQtyOnHand,code);
            tx.commit();
            //============================= TODO: come back to this later!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ============================================
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<SearchOrderTM> getOrderDetails(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        queryDAO.setSession(session);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<SearchOrderTM> searchOrderTMS = new ArrayList<>();
            List<CustomEntity> orderDetails = queryDAO.getOrderDetails();
            tx.commit();
            for (CustomEntity orderDetail : orderDetails) {
                searchOrderTMS.add(new SearchOrderTM(orderDetail.getOrderId(),orderDetail.getOrderDate().toString(),orderDetail.getCustomerId(),orderDetail.getCustomerName(),orderDetail.getTotal().doubleValue()));
            }
            return searchOrderTMS;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
