package Dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Entity.Cost;
import Utils.DBUtil;
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
	
	/**����ID��ѯ�ʷ���Ϣ��name,cost_type,base_duration,base_cost,unit_cost,descr
	 * @author wentao
	 * @param costID
	 * @return Cost����
	 */
	public Cost findBycostID(String costID) {
		Integer ID=Integer.valueOf(costID);
		Connection findBycostIDconn=null;
		try {
			findBycostIDconn=DBUtil.getConnection();
			findBycostIDconn.setAutoCommit(false);
			String findBycostIDSQL="select name,cost_type,base_duration,base_cost,unit_cost,descr from cost where cost_id=?";
			PreparedStatement smt=findBycostIDconn.prepareStatement(findBycostIDSQL);
			smt.setInt(1, ID);
			ResultSet result=smt.executeQuery();
			findBycostIDconn.commit();
			Cost c=new Cost();
			if(result.next()) {
				c.setCostID(ID);
				c.setName(result.getString("name"));
				c.setCostType(result.getString("cost_type"));
				c.setBaseDuraction(result.getInt("base_duration"));
				c.setBaseCost(result.getDouble("base_cost"));
				c.setUnitCost(result.getDouble("unit_cost"));
				c.setDescr(result.getString("descr"));	
			}
			return c;
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
	
	/**�����ʷ�ID�޸��ʷ���Ϣ����
	 * @author wentao
	 * @param cost
	 */
	public void editCost(Cost cost){
		Connection editCostConn=null;
		try {
			editCostConn=DBUtil.getConnection();
			editCostConn.setAutoCommit(false);
			String editCoset="UPDATE COST SET NAME=?,BASE_DURATION=?,BASE_COST=?,UNIT_COST=?,DESCR=?,COST_TYPE=? "
					+ "WHERE COST_ID=?";
			PreparedStatement smt=editCostConn.prepareStatement(editCoset);
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
			smt.setInt(7, cost.getCostID());
			smt.executeUpdate();	
			editCostConn.commit();	
		} catch (SQLException e) {
			DBUtil.rollBack(editCostConn);
			e.printStackTrace();
			throw new RuntimeException("�޸��ʷ���Ϣʧ��",e);
		}finally{
			DBUtil.closeConnection(editCostConn);
		}	
	}

	/**����IDɾ���ʷ���Ϣ
	 * @author wentao
	 * @param id
	 */
	public void deleteFee(Integer id) {
		//System.out.println("deleteFee");
		Connection deleteCostConn=null;
		try {
			deleteCostConn=DBUtil.getConnection();
			deleteCostConn.setAutoCommit(false);
			String deleteCostSQL="DELETE FROM COST WHERE COST_ID=?";
			PreparedStatement smt=deleteCostConn.prepareStatement(deleteCostSQL);
			smt.setInt(1, id);
			if(smt.executeUpdate()==1){
				String resetCostidSQL="ALTER TABLE COST AUTO_INCREMENT=1";
				PreparedStatement smt1=deleteCostConn.prepareStatement(resetCostidSQL);
				smt1.executeUpdate();
			}
			deleteCostConn.commit();
		} catch (SQLException e) {
			DBUtil.rollBack(deleteCostConn);
			e.printStackTrace();
			throw new RuntimeException("ɾ���ʷ���Ϣʧ��",e);
		}finally{
			DBUtil.closeConnection(deleteCostConn);
		}
		
	}
	
	
}
