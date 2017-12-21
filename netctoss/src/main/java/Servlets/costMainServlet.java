/**
 * 
 */
package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CostDao;
import Entity.Cost;

/**
 * �����ʷ�(fee)����������
 * @author wentao
 */
public class costMainServlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath=request.getServletPath();
	     //System.out.println(servletPath);
		if(servletPath.equals("/find.costmain")) {
		    findList(request,response);
	    }else if(servletPath.equals("/toFeeAdd.costmain")) {
	    	request.getRequestDispatcher("WEB-INF/fee/fee_add.jsp").forward(request, response);
		}else if(servletPath.equals("/feeAdd.costmain")){
			feeAdd(request,response);
		}else if(servletPath.equals("/toFeeEdit.costmain")) {
			toFeeEdit(request,response);
		}
	}
	
	/**
	 * �����ѯ�ʷ�����
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		CostDao costs=new CostDao();
		List<Cost> costsList=costs.findAllCost();
		request.setAttribute("costsList", costsList);
		request.getRequestDispatcher("WEB-INF/fee/fee_list.jsp").forward(request, response);
	}
	
	/**�����ʷ���Ϣ
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void feeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		CostDao c=new CostDao();
		boolean isExit=c.findByName(request.getParameter("name"));
		if(isExit) {
			request.setAttribute("isExit", isExit);
			request.getRequestDispatcher("WEB-INF/fee/fee_add.jsp").forward(request, response);
		}else {
		Cost cost=new Cost();
		cost.setName(request.getParameter("name"));
		cost.setCostType(request.getParameter("costType"));
		cost.setBaseDuraction(Integer.valueOf(request.getParameter("baseDuration")));
		cost.setBaseCost(Double.valueOf(request.getParameter("baseCost")));
		cost.setUnitCost(Double.valueOf(request.getParameter("unitCost")));
		cost.setDescr(request.getParameter("descr"));
		c.addCost(cost);
		response.sendRedirect("find.costmain");
		}
	}
	

	private void toFeeEdit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("costID"));
	
	}
	
}
