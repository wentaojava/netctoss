package Dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DBUtil.DBUtil;
import Entity.Cost;
/**
 * �ʷ���Ϣ������
 * @author wentao
 */
public class CostDao implements Serializable {
	
	/**��ѯ�����ʷ���Ϣ
	 * @author wentao
	 * @param null
	 * @throws SQLException
	 * @return List<Cost> costs
	 */
	public List<Cost> findAllCost(){
		List<Cost> costs=new ArrayList<Cost>();
		Connection findCostConn=null;
		try {
			findCostConn=DBUtil.getConnection();
			findCostConn.setAutoCommit(false);
			String findCost="SELECT * FROM COST ORDER BY ?";
			PreparedStatement smt=findCostConn.prepareStatement(findCost);
			smt.setString(1, "cost_id");
			ResultSet rs=smt.executeQuery();
			while(rs.next()) {
				Cost cost=new Cost();
				cost.setCostID(rs.getInt("cost_id"));
				cost.setName(rs.getString("name"));
				cost.setBaseDuraction(rs.getInt("base_duration"));
				cost.setBaseCost(rs.getDouble("base_cost"));
				cost.setUnitCost(rs.getDouble("unit_cost"));
				cost.setStatus(rs.getString("status"));
				cost.setDescr(rs.getString("descr"));
				cost.setCreatime(rs.getTimestamp("creatime"));
				cost.setStartime(rs.getTimestamp("startime"));
				cost.setCostType(rs.getString("cost_type"));
				costs.add(cost);
			}
			findCostConn.commit();
			return costs;	
		} catch (SQLException e) {
			DBUtil.rollBack(findCostConn);
			e.printStackTrace();
			throw new RuntimeException("��ѯ�ʷ���Ϣʧ��",e);
		}finally{
			DBUtil.closeConnection(findCostConn);
		}	
	}
	
	/**�������Ʋ���
	 * @author wentao
	 * @param 
	 * @throws 
	 * @param Name
	 * @return boolean ���ƴ��ڷ���true
	 */
	public boolean findByName(String Name) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String findByNameSQL="select name from cost where name=?";
			PreparedStatement smt=conn.prepareStatement(findByNameSQL);
			smt.setString(1, Name);
			ResultSet isHasName=smt.executeQuery();
			conn.commit();
			if(isHasName.next()) {
				return true;
			}else {
				return false;
			}	
		} catch (SQLException e) {
			DBUtil.rollBack(conn);
			e.printStackTrace();
			throw new RuntimeException("��ѯ�����Ƿ��ظ�ʧ��",e);
		}finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public Cost findBycostID(String costID) {
		Integer ID=Integer.valueOf(costID);
		Connection findBycostIDconn=null;
		try {
			findBycostIDconn=DBUtil.getConnection();
			findBycostIDconn.setAutoCommit(false);
			String findBycostIDSQL="select name from cost where name=?";
			PreparedStatement smt=findBycostIDconn.prepareStatement(findBycostIDSQL);
			smt.setInt(1, ID);
			ResultSet result=smt.executeQuery();
			findBycostIDconn.commit();
			while(result.next()) {
				Cost c=new Cost();
				c.setName(result.getString(1));
			}
		} catch (SQLException e) {
			DBUtil.rollBack(findBycostIDconn);
			e.printStackTrace();
			throw new RuntimeException("����costID��ѯ��Ϣʧ��",e);
		}finally {
			DBUtil.closeConnection(findBycostIDconn);
		}
	}
	
	/**�����ʷѷ���
	 * @author wentao
	 * @param cost
	 */
	public void addCost(Cost cost){
		Connection addCostConn=null;
		try {
			addCostConn=DBUtil.getConnection();
			addCostConn.setAutoCommit(false);
			String addCoset="INSERT INTO COST (NAME,BASE_DURATION,BASE_COST,UNIT_COST,STATUS,DESCR,STARTIME,COST_TYPE) "
					+ "VALUES(?,?,?,?,'0',?,'0000-00-00',?)";
			PreparedStatement smt=addCostConn.prepareStatement(addCoset);
			smt.setString(1, cost.getName());
			//setInt,setDouble��������null,
			//��ʵ��ҵ���и��ֶ�ȴ�ǿ���Ϊnull,
			//�������ݿ�Ҳ֧��Ϊnull,���Խ�
			//�������ֶε���Object����
			smt.setObject(2, cost.getBaseDuraction());
			smt.setObject(3, cost.getBaseCost());
			smt.setObject(4, cost.getUnitCost());
			smt.setString(5, cost.getDescr());
			smt.setString(6,cost.getCostType());
			smt.executeUpdate();	
			addCostConn.commit();	
		} catch (SQLException e) {
			DBUtil.rollBack(addCostConn);
			e.printStackTrace();
			throw new RuntimeException("�����ʷ���Ϣʧ��",e);
		}finally{
			DBUtil.closeConnection(addCostConn);
		}
		
	}
}
