

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class withdrawl
 */
public class withdrawl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public withdrawl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		String no = request.getParameter("acno");
		String Clientbal =  request.getParameter("acbal");
		
		System.out.println("Ac no Test:"+no);
		System.out.println("Ac bal Test :"+Clientbal); 
		
		String Acstatus="";
		String DataAcbal="";
		int finalbal=0;

	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
			

			//FOR CHECKING USER AVAILBLITY WE USE THIS ONE
			PreparedStatement ps = conn.prepareStatement("Select * from acopen where Acno=?"); 
	 
			ps.setString(1,no);
			
	       ResultSet rs=ps.executeQuery();
  	       if(rs.next())
  	       {
	        Acstatus=rs.getString(6);
	        DataAcbal=rs.getString(7);
	       }
	       
	        
	      
	    System.out.println("Acstatus  "+Acstatus);
	  System.out.println("Acbal  "+Clientbal);
  	  if(Acstatus.equals("N"))
	  {
  		out.print("New Account............Deposit Balance First");
  		RequestDispatcher rd=request.getRequestDispatcher("Withdrawl.html");
	     rd.include(request, response);
  
	  }
  	  
  	  if(Acstatus.equals("C"))
	  {
  		  
  		  
	  }
	
	 if(Acstatus.equals("O")){
		if(Integer.parseInt(Clientbal)>Integer.parseInt(DataAcbal)){
			out.print("Insufficent Balance...............Please Enter a valid Amount");
			RequestDispatcher rd=request.getRequestDispatcher("Withdrawl.html");
		     rd.include(request, response);
		  
			
		}else{
		    finalbal = Integer.parseInt(DataAcbal)-Integer.parseInt(Clientbal);
			PreparedStatement ps2=conn.prepareStatement("update acopen set Acbal=? where Acno=?");
		    ps2.setInt(1,finalbal);
		    ps2.setString(2,no);
		    int i=ps2.executeUpdate();
		    
		    if(i>0){
		      out.print("successfully Amount is debited.................................");
		      RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
			  rd.forward(request, response);
		    }	
		}
	  
	 }
	 
	 }catch(Exception e){
		
		
		out.print(e);
	}
}	/**
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
