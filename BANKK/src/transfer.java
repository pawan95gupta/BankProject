

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
 * Servlet implementation class transfer
 */
public class transfer extends HttpServlet {
	      private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public transfer() {
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
		String Sac = request.getParameter("sacno");
		String Rac = request.getParameter("racno"); 
		String bal = request.getParameter("acbal");
		
		System.out.println("Ac no Test:"+Sac);
		System.out.println("Ac bal Test :"+Rac); 
		System.out.println("Amount is"+bal);
		String Acstatus="";
		String Acbal="";
		

	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
			

			//FOR CHECKING USER AVAILBLITY WE USE THIS ONE
			PreparedStatement ps=conn.prepareStatement("select * from acopen where Acno=?");
			ps.setString(1,Sac);

			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				Acstatus=rs.getString(6);
				Acbal=rs.getString(7);
				
			}
			 if(Integer.parseInt(Acbal)<=0){
		    	 out.print("Insufficient bal");
		    	 RequestDispatcher rd=request.getRequestDispatcher("transfer.html");
					rd.include(request, response);
		    	 
		     }
			
			
			int totalamount2=0;
			totalamount2=Integer.parseInt(Acbal)-Integer.parseInt(bal);
			System.out.println("totalamount"+totalamount2);
			 
			
			if(Acstatus.equals("O") || Acstatus.equals("N") &&Acbal.equals(0)){
			   PreparedStatement ps2=conn.prepareStatement("update acopen set Acbal=?,Acstatus=? where Acno=?");
		    ps2.setInt(1,totalamount2);
		    ps2.setString(2,"O");
		    ps2.setString(3,Sac);
		   

		    int i=ps2.executeUpdate();
		    if(i>0){
		    	out.print("Transaction is Done successfully ");
			}
		    
		    PreparedStatement ps3=conn.prepareStatement("select * from acopen where Acno=?");
			ps.setString(1,Rac);

		    ResultSet rs1=ps.executeQuery();
			if(rs1.next()){
				Acstatus=rs1.getString(6);
				Acbal=rs1.getString(7);
				
			}
			
			
			int totalamount=0; 
			 totalamount=Integer.parseInt(bal)+Integer.parseInt(Acbal);
			 //out.print("toatalamount"+totalamount);
			
			if(Acstatus.equals("O") || Acstatus.equals("N") ){
				PreparedStatement ps4=conn.prepareStatement("update acopen set Acbal=?,Acstatus=?  where Acno=?");
				ps4.setInt(1,totalamount);
				ps4.setString(2,"O");
				ps4.setString(3,Rac);
		   
			
		    int i1=ps4.executeUpdate();
		   RequestDispatcher rd=request.getRequestDispatcher("sign in.html");
			rd.include(request, response);
			 
			 }else{
				 RequestDispatcher rd=request.getRequestDispatcher("transfer.html");
				 rd.include(request, response);
			 }
			}
		}
		catch(Exception e){
			System.out.println(e);
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
