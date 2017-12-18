package test;

import java.sql.Connection;
import java.sql.SQLException;

import DBUtil.DBUtil;

public class test {

	public static void main(String[] args) throws SQLException {
		Connection conn=DBUtil.getConnection();
    	System.out.println(conn);
    	DBUtil.closeConnection(conn);

	}

}
