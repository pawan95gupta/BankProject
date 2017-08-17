import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class close
 */
public class close extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public close() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	System.out.println("service starts here..............................");
	 
	          PrintWriter out=response.getWriter();
	          String closeac=request.getParameter("acno");
              System.out.println("Ac no to  delete  "+closeac); 
         
 
           try{
 	            Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
 	PreparedStatement ps=conn.prepareStatement("select * from acopen where Acno=?");
		ps.setString(1,closeac);
		
	ResultSet rs=ps.executeQuery();
	if(rs.next()){
		
		String Acstatus=rs.getString(6);
		if(Acstatus.equals("C")){
			out.print("Your Account no. is already closed");
			
		}
		else{
			PreparedStatement ps1=conn.prepareStatement("update acopen set Acstatus=? where Acno=?");
			ps1.setString(1,"C");
			ps1.setString(2,closeac);
		
		int i=ps1.executeUpdate();
		if(i>0)
		{	
			out.print("Succesfully Closed ur Account No. "+closeac);
			RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
		    rd.include(request, response);
		}}}
	   }catch(Exception e){
			 System.out.println(e);
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
