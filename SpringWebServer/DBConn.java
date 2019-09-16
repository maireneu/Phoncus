package com.phoncus.app;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class DBConn {
	private Connection con = null;
	PrintWriter out= null;
	public DBConn(PrintWriter out) {

		this.out = out;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phoncus?useUnicode=true&characterEncoding=utf8", "******", "*******");
		}

		catch (Exception e) {
		}

	}

	public String[] modeltoname(String model) {
		String query = "call Model_to_name(?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, model);
			ResultSet rs = pstmt.executeQuery();
			String[] str = new String[2];

			if (rs.next()) {
				str[0] = rs.getString("PETNAME");
				str[1] = rs.getString("MANUFACTURER");
				return str;
			}
			return null;
		} catch (Exception e) {
			out.println(e.getMessage());
			return null;
		}
	}

	public String[] modelconfirm(String petname) {
		String query = "SELECT PETNAME,SIZE,PERFORMANCE_RATING,PPI,CAMERA_RATING FROM `PHONE_DATA` WHERE PETNAME = ? LIMIT 1";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, petname);
			ResultSet rs = pstmt.executeQuery();
			String[] str = new String[5];

			if (rs.next()) {
				str[0] = rs.getString("PETNAME");
				str[1] = rs.getString("SIZE");
				str[2] = rs.getString("PERFORMANCE_RATING");
				str[3] = rs.getString("PPI");
				str[4] = rs.getString("CAMERA_RATING");
				return str;
			}
			return null;
		} catch (Exception e) {
			out.println(e.getMessage());
			return null;
		}
	}

	public boolean storage(String year, String month, String gender, String model,Float size,String os,Integer performance,Integer ppi,Integer camera) {
		String query = "insert into `customer2`(year, month, gender, model,size,os,grade,ppi,camera) values(?,?,?,?,?,?,?,?,?);";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, year);
			pstmt.setString(2, month);
			pstmt.setString(3, gender);
			pstmt.setString(4, model);
            pstmt.setFloat(5, size);
            pstmt.setString(6, os);
            pstmt.setInt(7, performance);
            pstmt.setInt(8, ppi);
            pstmt.setInt(9, camera);
			int rs = pstmt.executeUpdate();

			if (rs != 0) {
				return true;
			}

			return false;

		} catch (Exception e) {
			out.println(e.getMessage());

			return false;
		}
	}

	public Vector<String[]> curation(Float size,String os,Integer performance,
			Integer ppi,Integer camera,String manufacture,String batterysaperate,
			String wirelesscharge,String waterproof,String fingerprint,String stylus,String knockcode,String samsungpay,
			String button) {
		String query = "call Curation_main(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Vector<String[]> vec = new Vector<String[]>();

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setFloat(1, size);
            pstmt.setString(2, os);
            pstmt.setInt(3, performance);
            pstmt.setInt(4, ppi);
            pstmt.setInt(5, camera);
            pstmt.setString(6, manufacture);
            pstmt.setString(7, batterysaperate);
            pstmt.setString(8, wirelesscharge);
            pstmt.setString(9, waterproof);
            pstmt.setString(10, fingerprint);
            pstmt.setString(11, stylus);
            pstmt.setString(12, knockcode);
            pstmt.setString(13, samsungpay);
            pstmt.setString(14, button);
			ResultSet rs = pstmt.executeQuery();// SELECT

			if (rs.next()) {

				for(int i=1; i<4 ; i++){
					String[] str = new String[17];

					str[0] = rs.getString("(select PETNAME from PHONE_DATA where NUMBER = rank_"+i+")");
					str[1] = rs.getString("(select SIZE from PHONE_DATA where NUMBER = rank_"+i+")");
					str[2] = rs.getString("(select OS from PHONE_DATA where NUMBER = rank_"+i+")");
					str[3] = rs.getString("(select OS_VERSION from PHONE_DATA where NUMBER = rank_"+i+")");
					str[4] = rs.getString("(select PERFORMANCE_RATING from PHONE_DATA where NUMBER = rank_"+i+")");
					str[5] = rs.getString("(select PPI from PHONE_DATA where NUMBER = rank_"+i+")");
					str[6] = rs.getString("(select CAMERA_RATING from PHONE_DATA where NUMBER = rank_"+i+")");
					str[7] = rs.getString("(select MANUFACTURER from PHONE_DATA where NUMBER = rank_"+i+")");
					str[8] = rs.getString("(select BATTERY_SAPERATE from PHONE_DATA where NUMBER = rank_"+i+")");
					str[9] = rs.getString("(select WIRELESS_CHARGE from PHONE_DATA where NUMBER = rank_"+i+")");
					str[10] = rs.getString("(select WATERPROOF from PHONE_DATA where NUMBER = rank_"+i+")");
					str[11] = rs.getString("(select FINGERPRINT from PHONE_DATA where NUMBER = rank_"+i+")");
					str[12] = rs.getString("(select STYLUS from PHONE_DATA where NUMBER = rank_"+i+")");
					str[13] = rs.getString("(select KNOCKCODE from PHONE_DATA where NUMBER = rank_"+i+")");
					str[14] = rs.getString("(select SAMSUNGPAY from PHONE_DATA where NUMBER = rank_"+i+")");
					str[15] = rs.getString("(select BUTTON from PHONE_DATA where NUMBER = rank_"+i+")");
					str[16] = "rank_" + String.valueOf(i);

					vec.add(i-1,str);
				}

				if (vec.size() > 0) {
					return vec;
				}
				return null;

			}
			return null;
		} catch (Exception e) {
			out.println(e.getMessage());
			return null;
		}
	}

	public String curation_image(String petname) {
        String query = "select url from `PHONE_IMAGE` where petname=? and color_number=1";

        try {
           PreparedStatement pstmt = con.prepareStatement(query);
           pstmt.setString(1, petname);
           ResultSet rs = pstmt.executeQuery();

           String str = new String();

           if (rs.next()) {
              str = rs.getString("url");
              return str;
           }
           return null;
        } catch (Exception e) {
           out.println(e.getMessage());
           return null;
        }
     }

	public ArrayList<String> curation_image2(String petname) {
        String query = "select URL from `PHONE_IMAGE2` where PETNAME=? and (PHASE=1 or PHASE=2) and COLOR_NUMBER=1";
        ArrayList<String> data = new ArrayList<String>();



        try {
           PreparedStatement pstmt = con.prepareStatement(query);
           pstmt.setString(1, petname);
           ResultSet rs = pstmt.executeQuery();

           String str = new String();

           while (rs.next()) {

        	  if(rs.equals(null)){
        		  data.add("null");
        	  }else if(rs.getString("URL").equals(null)){
        		  data.add("null");
        	  }else{
        		  str = rs.getString("URL");
        		  data.add(str);
        	  }

           }
           return data;
        } catch (Exception e) {
           out.println(e.getMessage()+"a");
           return null;
        }
     }

	public ArrayList<String[]> getData(String initial, String imagenumber) {
		  ArrayList<String[]> data = new ArrayList<String[]>();
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;

		  try {
		   String sql = "select `PHONE_DATA`.PETNAME,URL,MANUFACTURER  from `PHONE_DATA` join `PHONE_IMAGE2` on `PHONE_IMAGE2`.PETNAME = `PHONE_DATA`.PETNAME where PHASE=1 and COLOR_NUMBER=1  order by `PHONE_DATA`.RELEASE_DATE DESC limit ?,?;";

		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, Integer.parseInt(initial));
		   pstmt.setInt(2, Integer.parseInt(imagenumber));
		   rs = pstmt.executeQuery();
		   while (rs.next()) {

			   String[] tmp = new String[3];
			   tmp[0] = rs.getString("PETNAME");
			   tmp[1] = rs.getString("URL");
			   tmp[2] = rs.getString("MANUFACTURER");

			   data.add(tmp);

		   }

		   return data;
		  } catch (Exception e) {
		   e.printStackTrace();
		   out.println(e.getMessage());
		  }
		  return null;
	}

	public ArrayList<String[]> phonelist(String manufacturer) {
		  ArrayList<String[]> data = new ArrayList<String[]>();
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;

		  try {
		   String sql=null;

			 if(manufacturer.equals("전체")){
				 sql = "select MANUFACTURER,PETNAME from `PHONE_DATA` where not MANUFACTURER='Apple'";
				 pstmt = con.prepareStatement(sql);
			 }else{
				 sql = "select MANUFACTURER,PETNAME from `PHONE_DATA` where MANUFACTURER=? ";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, manufacturer);
			 }

 		   rs = pstmt.executeQuery();
		   while (rs.next()) {

			   String[] tmp = new String[2];
			   tmp[0] = rs.getString("PETNAME");
			   tmp[1] = rs.getString("MANUFACTURER");

			   data.add(tmp);
		   }

		   return data;
		  } catch (Exception e) {
		   e.printStackTrace();
		   out.println(e.getMessage());
		  }
		  return null;
	}

}
