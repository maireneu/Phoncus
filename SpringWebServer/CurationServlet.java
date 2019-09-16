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


@WebServlet("/curation")
public class CurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConn db = null;   

    public CurationServlet() {
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

		String year = request.getParameter("YEAR");
		String mon = request.getParameter("MONTH");
		String gender = request.getParameter("GENDER");
		String model = request.getParameter("MODEL");
		Float size = Float.parseFloat(request.getParameter("SIZE"));
		String os = request.getParameter("OS");
		Integer performance = Integer.parseInt(request.getParameter("PERFORMANCE"));
		Integer ppi = Integer.parseInt(request.getParameter("PPI"));
		Integer camera = Integer.parseInt(request.getParameter("CAMERA"));
		String manufacturer = request.getParameter("MANUFACTURER");
		String batterysaperate = request.getParameter("BATTERYSAPERATE");
		String wirelesscharge = request.getParameter("WIRELESSCHARGE");
		String waterproof = request.getParameter("WATERPROOF");
		String fingerprint = request.getParameter("FINGERPRINT");
		String stylus = request.getParameter("STYLUS");		
		String knockcode = request.getParameter("KNOCKCODE");
		String samsungpay = request.getParameter("SAMSUNGPAY");				
		String button = request.getParameter("BUTTON");
		
		os = new String(os.getBytes("8859_1"),"UTF-8");
		manufacturer = new String(manufacturer.getBytes("8859_1"),"UTF-8");
		model = new String(model.getBytes("8859_1"),"UTF-8");
		button = new String(button.getBytes("8859_1"),"UTF-8");
		
		boolean save = db.storage(year,mon,gender,model,size,os,performance,ppi,camera);
	    
		Vector<String[]> vec = db.curation(size, os, performance, ppi, camera,manufacturer,batterysaperate,wirelesscharge,waterproof,fingerprint,stylus,knockcode,samsungpay,button);
		
		JSONArray jarr = new JSONArray();
		ArrayList<String> data = new ArrayList<String>();
		
		try{
			for(int i=0; i<3; i++){
				String[] str = new String[17];
				str = vec.elementAt(i);
				JSONObject jobj = new JSONObject();
				jobj.put("PETNAME", str[0]);
				jobj.put("SIZE", str[1]);
				jobj.put("OS", str[2]);
				jobj.put("OS_VERSION", str[3]);
				jobj.put("PERFORMANCE", str[4]);
				jobj.put("PPI", str[5]);
				jobj.put("CAMERA", str[6]);
				jobj.put("MANUFACTURER", str[7]);
				jobj.put("BATTERYSAPERATE", str[8]);
				jobj.put("WIRELESSCHAREGE", str[9]);
				jobj.put("WATERPROOF", str[10]);
				jobj.put("FINGERPRINT", str[11]);
				jobj.put("STYLUS", str[12]);
				jobj.put("KNOCKCODE", str[13]);
				jobj.put("SAMSUNGPAY", str[14]);
				jobj.put("BUTTON", str[15]);
				jobj.put("RANK", str[16]);
				
				data = db.curation_image2(str[0]);
				
				if(data.size()==0){
				}else if(data.get(0).equals("null")){
				}else{
					jobj.put("IMAGE_URL1", data.get(0));
					jobj.put("IMAGE_URL2", data.get(1));
				}
				
				data = null;
				
				jarr.put(jobj);
				str = null;
			}
		}catch (Exception e) {
			out.println(e.getMessage()+"b");
		}
		
		if(save == true && vec != null){
			out.print(jarr.toString());
		}
		else if (save == false) {
			JSONObject jobj = new JSONObject();
			jobj.put("save", new Boolean(false));
			
			out.print(jobj.toString());
		}
		else {
			JSONObject jobj = new JSONObject();
			jobj.put("vec", new Boolean(false));
			out.print(jobj.toString());
		}
			
	}

}