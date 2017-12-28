/**
 * 
 */
package Dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.DBUtil;

/**首页登录处理
 * 
 * @author wentao
 */
public class userDao implements Serializable {

	/**查询用户名是否存在，检查密码是否正确
	 * 该方法后续需要优化
	 * @author wentao
	 * @param 
	 * @throws 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public boolean checkUser(String name,String pwd) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String findUser="SELECT USER_PASSWORD FROM USER WHERE USER_NAME=?";
			PreparedStatement smt=conn.prepareStatement(findUser);
			smt.setString(1, name);
			ResultSet rs=smt.executeQuery();
			conn.commit();
			if(rs.next()) {
				String n=rs.getString("user_password");
				if(n.equals(pwd)) {
				   return true;
				}else {
					return false;
				}
				
			}else {
				return false;
			}
		
		} catch (SQLException e) {
			DBUtil.rollBack(conn);
			e.printStackTrace();
			throw new RuntimeException("查询用户失败",e);
		}finally {
			DBUtil.closeConnection(conn);
		}
	}
}
