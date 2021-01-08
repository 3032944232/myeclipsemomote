package jdbcProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcPstmt {
	private static final String URL="jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String user = "scott";
	private static final String PWD = "tiger";
		
	public static void update() throws SQLException, ClassNotFoundException {//��ɾ��
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection = DriverManager.getConnection(URL,user,PWD);
		String sql = "insert into student values (?,?,?)";//ͨ�^��ռλ�����攵��
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setInt(1, 3);
		prepareStatement.setString(2, "zs");
		prepareStatement.setInt(3, 1);
		int count = prepareStatement.executeUpdate();
		if(count>0) {
			System.out.println("�����ɹ���");
		}
		if(prepareStatement!=null)
			prepareStatement.close();
		if(connection!=null) 
			connection.close();
		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		update();
	}
}
