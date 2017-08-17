

 import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	
		System.out.println("Service starts....................................");// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
 		String address = request.getParameter("address");
		String aadhar = request.getParameter("aadhar");
		 		
    	System.out.println("username"+username);
        System.out.println("password"+password);
		System.out.println("name:"+name);
		System.out.println("mobile :"+mobile);
		System.out.println("email :"+email);
		System.out.println("address :"+address);
		System.out.println("aadhar"+aadhar);
		ServletContext sc = getServletContext();
		String url = sc.getInitParameter("url");
		String driver=sc.getInitParameter("driver");
		String user = sc.getInitParameter("user");
		String pass = sc.getInitParameter("pass");
		
		try{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,pass);
			PreparedStatement ps = conn.prepareStatement("insert into homepage(username,password,name,mobile,email,address,aadhar) values(?,?,?,?,?,?,?)");
			ps.setString(1,username);
			ps.setString(2,password);
			ps.setString(3,name);
			ps.setString(4,mobile);
			ps.setString(5,email);
		 
			ps.setString(6,address);
			ps.setString(7,aadhar);
	
			
			int i=ps.executeUpdate();
			if(i>0)
			{	
				out.print("Welcome "+username+"Succesfully Registered");
				RequestDispatcher rd=request.getRequestDispatcher("Homepage.html");
			    rd.forward(request, response);
			}else{
	    	out.print("Some Problem occurs.......Retry");
	        RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
	    	rd.include(request, response);
	        } 

			}catch(Exception e1){
	                           e1.printStackTrace();

		}


	}

	
	
	
	
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
