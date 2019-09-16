package com.phoncus.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/modeltoname")
public class ModelToNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConn db = null;

    public ModelToNameServlet() {
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
		
		String model = request.getParameter("MODEL");		
	    String str[] = db.modeltoname(model);
	    
		JSONObject jobj = new JSONObject();
		
		if(str != null){
			jobj.put("PETNAME", str[0]);
			jobj.put("MANUFACTURER", str[1]);
		}
		else {
			jobj.put("ID", new Boolean(false));
		}
		out.print(jobj.toString());
	}

}