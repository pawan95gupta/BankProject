

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class acopen
 */
public class acopen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public acopen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	
		System.out.println("Service starts....................................");// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        
		
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		 String address = request.getParameter("address");
		String aadhar = request.getParameter("aadhar");
		 		
		
		System.out.println("name:"+name);
		System.out.println("mobile :"+mobile);
		 System.out.println("address :"+address);
		System.out.println("aadhar"+aadhar);
		 
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
			PreparedStatement ps = conn.prepareStatement("insert into acopen(acname,mobile,acaddress,aadhar,acstatus,acbal) values(?,?,?,?,?,?)");
			  
			ps.setString(1,name);
			ps.setString(2,mobile);
		 	ps.setString(3,address);
			ps.setString(4,aadhar);
			ps.setString(5,"N");
			ps.setString(6,"0");
			
			int i=ps.executeUpdate();
			if(i>0)
			{	
				out.print("Succesfully Opened ur Account");
				RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
			    rd.include(request, response);
			}

			}catch(Exception e1){
	        e1.printStackTrace();

		}


	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
