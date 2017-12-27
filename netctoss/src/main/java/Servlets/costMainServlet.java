/**
 * 
 */
package Servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CostDao;
import Entity.Cost;

/**
 * 处理资费(fee)的所有请求
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
		}else if(servletPath.equals("/feeEdit.costmain")){
			feeEdit(request,response);
		}else if(servletPath.equals("/FeeDelete.costmain")){
			//System.out.println("costMainServlet");
			feeDelete(request,response);
		}
	}
	
	/**
	 * 处理查询资费请求
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
	
	/**新增资费信息
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
	

	/**根据ID查询出资费信息后转发至资费修改页面
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void toFeeEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//System.out.println(request.getParameter("costID"));
		Cost cost=new Cost();
		CostDao cd=new CostDao();
		cost=cd.findBycostID(request.getParameter("costID"));
		request.setAttribute("cost", cost);
		request.getRequestDispatcher("WEB-INF/fee/fee_edit.jsp").forward(request, response);
	}
	
	
	/**修改资费信息
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void feeEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		CostDao c=new CostDao();
		boolean isExit=c.findByName(request.getParameter("name"));
		if(isExit) {
			request.setAttribute("isExit", isExit);
			request.getRequestDispatcher("WEB-INF/fee/fee_edit.jsp").forward(request, response);
		}else {
		Cost cost=new Cost();
		cost.setCostID(Integer.valueOf(request.getParameter("costID")));
		cost.setName(request.getParameter("name"));
		cost.setCostType(request.getParameter("costType"));
		cost.setBaseDuraction(Integer.valueOf(request.getParameter("baseDuration")));
		cost.setBaseCost(Double.valueOf(request.getParameter("baseCost")));
		cost.setUnitCost(Double.valueOf(request.getParameter("unitCost")));
		cost.setDescr(request.getParameter("descr"));
		c.editCost(cost);
		response.sendRedirect("find.costmain");
		}
		
	}
	
	
	/**根据资费ID删除对应资费信息
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void feeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//System.out.println("feeDelete");
		request.setCharacterEncoding("utf-8");
		Integer id=Integer.valueOf(request.getParameter("costID"));
		CostDao c=new CostDao();
		c.deleteFee(id);
		response.sendRedirect("find.costmain");
	}
}
