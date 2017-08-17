import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Homepage
 */
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homepage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	
	System.out.println("IN iT starts here.........................");
	
	}

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
		// TODO Auto-generated method stub
	     System.out.println("In the service..");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("name:"+username);
		System.out.println("password :"+password);
		
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
			

			//FOR CHECKI[]=No USER AVAILBLITY WE USE THIS ONE
		PreparedStatement ps = conn.prepareStatement("Select * from homepage where username=? and password=?"); 
			ps.setString(1,username);
			ps.setString(2,password);
	ResultSet rs=ps.executeQuery();
	if(rs.next())
	{
		out.print("WELCOME"+username);
		RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
	    rd.forward(request, response);
	                         }
	else{
		out.print("First Register");
		RequestDispatcher  rd=request.getRequestDispatcher("Homepage.html");
	    rd.include(request, response);
	  
	}
		
		
		 //FOR UPDATING IN DATABASE WE USE THIS ONE
	/*		PreparedStatement ps = conn.prepareStatement("insert into homepage(username,password) values(?,?)");
		    ps.setString(1,username);
			ps.setString(2,password);
			ps. executeUpdate();
			out.print("WELCOME"+username+" Successfully Registered");
		RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
	    rd.forward(request, response);	*/ 
     
	
		 }
		
	 catch(Exception e1){
	e1.printStackTrace();
	
	}}

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
