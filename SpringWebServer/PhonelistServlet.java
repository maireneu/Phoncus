package com.phoncus.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/phonelist")
public class PhonelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConn db = null;   

    public PhonelistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		db = new DBConn(out);

		String manufacturer = request.getParameter("MANUFACTURER");
		manufacturer = new String(manufacturer.getBytes("8859_1"),"UTF-8");
	
		ArrayList<String[]> data = db.phonelist(manufacturer);
		
		JSONArray jarr = new JSONArray();
		
		try{
			for(int i=0; i<data.size(); i++){
				String[] tmp = data.get(i);
				JSONObject jobj = new JSONObject();
				jobj.put("PETNAME", tmp[0]);
				jobj.put("MANUFACTURER", tmp[1]);
				jarr.put(jobj);
				
				tmp = null;
			}
		}catch (Exception e) {
			out.println(e.getMessage());
		}
		
		if( data != null){
			out.print(jarr.toString());
		}
		else{
			out.print("somethings wrong");
		}
	}
			
}

