package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DBUtil.DBUtil;
import Dao.CostDao;
import Entity.Cost;

public class test {

	public static void main(String[] args) throws SQLException {
		CostDao dao=new CostDao();
		List<Cost> c=dao.findAllCost();
		for(Cost co:c) {
			System.out.println(co.getStartime());
		}

	}

}
