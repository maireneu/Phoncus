package com.phoncus.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/modelconfirm")
public class ModelConfrimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConn db = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModelConfrimServlet() {
        super();
        // TODO Auto-generated constructor stub
       
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		
		
		PrintWriter out = response.getWriter();
		
		db = new DBConn(out);
		
		String petname = request.getParameter("PETNAME");		
		//petname = URLDecoder.decode(petname, "UTF-8");
		
		petname= new String(petname.getBytes("8859_1"),"UTF-8");
		
		
		
	    String[] str = db.modelconfirm(petname);
	    
		JSONObject jobj = new JSONObject();
		
		if(str != null){
			jobj.put("PETNAME", str[0]);
			jobj.put("SIZE", str[1]);
			jobj.put("PERFORMANCE", str[2]);
			jobj.put("PPI", str[3]);
			jobj.put("CAMERA", str[4]);
	
		}
		else {
			jobj.put("ID", petname);
		}
		out.print(jobj.toString());
	}

}