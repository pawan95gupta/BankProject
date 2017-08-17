

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
 * Servlet implementation class deposit
 */
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deposit() {
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
	
		
		// TODO Auto-generated method stub
        System.out.println("In the service..");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String no = request.getParameter("acno");
		String bal = request.getParameter("acbal");
		
		System.out.println("Ac no Test:"+no);
		System.out.println("Ac bal :"+bal); 
		
		String Acstatus="";
		String Acbal="";

	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
			

			//FOR CHECKING USER AVAILBLITY WE USE THIS ONE
			PreparedStatement ps = conn.prepareStatement("Select * from acopen where Acno=?"); 
	 
			ps.setString(1,no);
			
	       ResultSet rs=ps.executeQuery();
	       
	       if(rs.next()){
	        Acstatus=rs.getString(6);
	       Acbal=rs.getString(7);
	       }
	       
	       
	 int totalamount=0; 
	 totalamount=Integer.parseInt(bal)+Integer.parseInt(Acbal);
	
	 if(Acstatus.equals("N")){
	 PreparedStatement ps1=conn.prepareStatement("update acopen set Acbal=?, Acstatus=? where Acno=?");
	 ps1.setInt(1,totalamount);
	 ps1.setString(2,"O");
	 ps1.setString(3,no);	
	
	 int i = ps1.executeUpdate();
	 if(i>0){
		out.print("Successfully deposit");
		RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
		rd.include(request, response);
	 } 
	 }   
	
	//out.print("toatalamount"+totalamount);
	
	if(Acstatus.equals("O")){
	   PreparedStatement ps2=conn.prepareStatement("update acopen set Acbal=? where Acno=?");
    ps2.setInt(1,totalamount);
    ps2.setString(2,no);
    int i=ps2.executeUpdate();
    
    if(i>0){
    	out.print("successfully Accbal is updated");
    RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
	rd.include(request, response);
    } 
	 }
  	
	if(Acstatus.equals("c")){
		out.println("Transaction Failed...(Closed_Account)");
	     RequestDispatcher rd = request.getRequestDispatcher("Deposit.html");
	     rd.include(request, response);
		
	}
	}
		catch(Exception e){
		out.print(e);
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
