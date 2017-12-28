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

	/**登录检查方法
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
	     //先检查验证码
	     HttpSession session_getimgcode = request.getSession();
	     String sessionImgcode=(String)session_getimgcode.getAttribute("imgcode");
	     if(code==null ||!code.equalsIgnoreCase(sessionImgcode)) {
	    	 request.setAttribute("error", "验证码错误");
	    	 request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	    	 return;
	     }
	     //再检查账号密码
	     userDao d=new userDao();
	     boolean result=d.checkUser(name, pwd);
	     if(result) {
	    	 //Cookie存储账号
	    	 Cookie user_name_cookie=new Cookie("user_name",name);
	    	 user_name_cookie.setPath("/netctoss");
	    	 response.addCookie(user_name_cookie);
	    	 //session存储密码
	    	 HttpSession session=request.getSession();
	    	 session.setAttribute("password", pwd);
	    	 //response.sendRedirect("index.html");
	    	request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	     }else {
	    	 request.setAttribute("result", result);
	    	 request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	     }
		
	}
	
	/**退出登录方法
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
	
	
	/**生成验证码图片
	 * @author wentao
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void creatImag(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		Object[] objs =ImageUtil.createImage();
		//将验证码存入session
		HttpSession session=request.getSession();
		session.setAttribute("imgcode", objs[0]);
		//将验证码图片发送给浏览器
		response.setContentType("image/png");
		// 获取字节输出流，输出目标为当前访问的浏览器
		OutputStream os=response.getOutputStream();
		BufferedImage image = (BufferedImage) objs[1];
		ImageIO.write(image, "png", os);
		os.close();
	}
	
}
