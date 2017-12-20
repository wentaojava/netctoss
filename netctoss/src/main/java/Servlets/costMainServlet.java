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
 * 
 * @author wentao
 */
public class costMainServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath=request.getServletPath();
	     //System.out.println(servletPath);
		if(servletPath.equals("/find.costmain")) {
		CostDao costs=new CostDao();
		List<Cost> costsList=costs.findAllCost();
		request.setAttribute("costsList", costsList);
		request.getRequestDispatcher("/WEB-INF/fee/fee_list.jsp").forward(request, response);
	    }
		if(servletPath.equals("/feeAdd.costmain")) {
			response.sendRedirect("WEB-INF/fee/fee_add.jsp");
		}
	}
}
