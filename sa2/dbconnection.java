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
		        //1.��������
		        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        System.out.println("���������ɹ���");
		    }catch(Exception e) {
		        e.printStackTrace();
		        System.out.println("��������ʧ�ܣ�");
		    }
		    try {  
		        //2.����
		        ct = DriverManager.getConnection(url,user,password);
		        System.out.println("�������ݿ�ɹ���");
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
		        System.out.println("�������ݿ�ʧ�ܣ�");
		    }
		    return rowData;
		}
}
