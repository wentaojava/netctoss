/**
 * 
 */
package Servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.userDao;
import Utils.ImageUtil;

/**
 * 
 * @author wentao
 */
public class loginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath=request.getServletPath();
	     //System.out.println(servletPath);
		if(servletPath.equals("/loginCheck.loginmain")) {
		    loginCheck(request,response);
	    } else if(servletPath.equals("/login.loginmain")){
	    	boolean flag=true;
	    	 request.setAttribute("result", flag);
	    	request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	    } else if(servletPath.equals("/logout.loginmain")) {
	    	logout(request,response);
	    } else if(servletPath.equals("/getImag.loginmain")) {
	    	creatImag(request,response);
	    }
		
	}

	/**��¼��鷽��
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginCheck(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
	     //System.out.println(request.getParameter("user_name"));
	     String name=request.getParameter("user_name");
	     String pwd=request.getParameter("user_password");
	     String code=request.getParameter("code");
	     //�ȼ����֤��
	     HttpSession session_getimgcode = request.getSession();
	     String sessionImgcode=(String)session_getimgcode.getAttribute("imgcode");
	     if(code==null ||!code.equalsIgnoreCase(sessionImgcode)) {
	    	 request.setAttribute("error", "��֤�����");
	    	 request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	    	 return;
	     }
	     //�ټ���˺�����
	     userDao d=new userDao();
	     boolean result=d.checkUser(name, pwd);
	     if(result) {
	    	 //Cookie�洢�˺�
	    	 Cookie user_name_cookie=new Cookie("user_name",name);
	    	 user_name_cookie.setPath("/netctoss");
	    	 response.addCookie(user_name_cookie);
	    	 //session�洢����
	    	 HttpSession session=request.getSession();
	    	 session.setAttribute("password", pwd);
	    	 //response.sendRedirect("index.html");
	    	request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	     }else {
	    	 request.setAttribute("result", result);
	    	 request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	     }
		
	}
	
	/**�˳���¼����
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}
	
	
	/**������֤��ͼƬ
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void creatImag(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		Object[] objs =ImageUtil.createImage();
		//����֤�����session
		HttpSession session=request.getSession();
		session.setAttribute("imgcode", objs[0]);
		//����֤��ͼƬ���͸������
		response.setContentType("image/png");
		// ��ȡ�ֽ�����������Ŀ��Ϊ��ǰ���ʵ������
		OutputStream os=response.getOutputStream();
		BufferedImage image = (BufferedImage) objs[1];
		ImageIO.write(image, "png", os);
		os.close();
	}
	
}
