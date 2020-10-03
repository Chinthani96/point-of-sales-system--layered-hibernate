package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.OrderDetailDAO;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail,String> implements OrderDetailDAO {
}

