package net.drvsolutions.starter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DRVStarterServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		String action = req.getRequestURI();
		if (action.equalsIgnoreCase("/DRVStarter/register")) {
			doRegister(req,res);
		}else if (action.equalsIgnoreCase("/DRVStarter/login")) {
			doLogin(req,res);
		}
	}
	
	public void doRegister(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		String uname,uemail,upass,errmsg;
		boolean isAlreadyRegistered = false;
		errmsg = "";
		boolean flag = false;
		try{  
			if(null !=req.getParameter("uname") && !req.getParameter("uname").isEmpty()) {
				uname = req.getParameter("uname");
				if(null !=req.getParameter("uemail") && !req.getParameter("uemail").isEmpty()) {
					uemail = req.getParameter("uemail");
					upass = String.format("%04d", new Random().nextInt(10000));
					Connection con = getDBConnection();
					if(null != con) {
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery("select * from drvusers"); 
						if(null != rs)
							while(rs.next())  
								if(rs.getString("uemail").equals(uemail)) 
									isAlreadyRegistered = true;
						if(!isAlreadyRegistered) {
							stmt.executeUpdate("insert into drvusers(uemail,uname,upassword,utype) values('"+uemail+"','"+uname+"','"+upass+"',3)");
							String msg = "New member registered with username = "+uemail+" and password = "+upass;
							Mailer.send(uemail, "DRV - New Account Activation Details", msg); 
							errmsg = "You will shortly receive an email with your login credentials";
							flag = true;
						}else
							errmsg = "User alredy registered! Please check your email for login credentials or reset your password!";
						con.close();
					}else {
						errmsg = "DB Connection failure!";
					}
				}else {
					errmsg = "Email Id cannot be blank!";
				}
			}else {
				errmsg = "Name cannot be blank!";
			}
			System.out.println(errmsg);
		}catch(Exception e){ System.out.println(e);}
		res.setContentType("text/html");
		req.setAttribute("errmsg", errmsg);
		if(flag) {
			req.getRequestDispatcher("login.jsp").forward(req, res);	
		}else{
			req.getRequestDispatcher("register.jsp").forward(req, res);	
		}	
	}

	public void doLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		String uemail,upassword,errmsg;
		boolean isVaildUser = false; 
		errmsg = "";
		try{  
			if(null !=req.getParameter("uemail") && !req.getParameter("uemail").isEmpty()) {
				uemail = req.getParameter("uemail");
				upassword = req.getParameter("upassword");
				Connection con = getDBConnection();
				if(null != con) {
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from drvusers"); 
					if(null != rs)
						while(rs.next())  
							if(rs.getString("uemail").equals(uemail) && rs.getString("upassword").equals(upassword)) {
								isVaildUser = true;
								req.setAttribute("uname", rs.getString("uname"));
							}
					if(isVaildUser) {
						System.out.println("User Logged in");
					}else {
						errmsg = "Invalid email id or password! Please reset your password if forgotten!";
					}
				}else {
					errmsg = "DB Connection failure!";
				}
				con.close();
			}else {
				errmsg = "Email Id cannot be blank!";
			}
			System.out.println(errmsg);
		}catch(Exception e){ System.out.println(e);}
		if(isVaildUser) {
			res.setContentType("text/html");
			req.getRequestDispatcher("welcome.jsp").forward(req, res);
		}else {
			res.setContentType("text/html");
			req.setAttribute("errmsg", errmsg);
			req.getRequestDispatcher("login.jsp").forward(req, res);
			//res.sendRedirect("index.jsp"); 
		}
	}
	
	public Connection getDBConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@drvmain.ch0wnfl2ldib.us-east-2.rds.amazonaws.com:1521:ORCL","drv","98230drv");  
		return con;
	}
}
