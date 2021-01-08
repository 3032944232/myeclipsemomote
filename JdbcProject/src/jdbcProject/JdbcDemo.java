package jdbcProject;

import java.sql.Connection;
//hello world!
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
	
	private static final String URL="jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String user = "scott";
	private static final String PWD = "tiger";
		
	public static void update() {//增删改
			//导入驱动
		Connection connection = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(URL,user,PWD);
			stmt = connection.createStatement();
			String sql="insert into student values (2,'ls',97)";
			int count = stmt.executeUpdate(sql);
			if(count > 0) {
				System.out.println("操作成功");
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null)stmt.close();
				if(connection!=null)connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void query() {
		// 导入驱动
		ResultSet rs = null ;
		Connection connection = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(URL, user, PWD);
			stmt = connection.createStatement();
			String sql="select id,name,age from student";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				System.out.println(id+"===="+name+"==="+age);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
//		update();
		query();
	}
}