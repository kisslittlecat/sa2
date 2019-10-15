package sa2;

import java.sql.*;

import java.util.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;



public class dbconnection {
	private static ArrayList<ArrayList<String>> rowData;
	private static Vector columnName;
	private static JTable jt = null;
	private static JScrollPane jsp = null;

	public static ArrayList<ArrayList<String>> get_data()
	{
		CallableStatement cs = null;
		PreparedStatement ps=null;
	    Connection ct = null;
		ResultSet rs = null;
	    
	    String url = "jdbc:sqlserver://localhost:1433;databaseName = Northwind"; 
	    String user = "sa";
	    String password = "123456";
			try {
		        //1.加载驱动
		        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        System.out.println("加载驱动成功！");
		    }catch(Exception e) {
		        e.printStackTrace();
		        System.out.println("加载驱动失败！");
		    }
		    try {  
		        //2.连接
		        ct = DriverManager.getConnection(url,user,password);
		        System.out.println("连接数据库成功！");
		        ps = ct.prepareStatement("select * from data");
				rs = ps.executeQuery();
				rowData = new ArrayList<ArrayList<String>>();
				while(rs.next()){
					ArrayList<String> line1 = new ArrayList<String>();
					line1.add(rs.getString(1));
					line1.add(rs.getString(2));	
					rowData.add(line1);
					//System.out.println(rowData);1999/11/10,174085100 [1999/11/10, 174085100]
				}
		    }catch(Exception e) {
		        e.printStackTrace();
		        System.out.println("连接数据库失败！");
		    }
		    return rowData;
		}
}
