package jdbcProject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Jdbcpcall {
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
		private static final String USERNAME = "scott";
		private static final String PWD = "tiger";

		public static void invokeProcedure() {
			Connection connection = null;
			CallableStatement cstmt = null;
			try {
				// a.�������������ؾ����������
				Class.forName("oracle.jdbc.OracleDriver");// ���ؾ����������
				// b.�����ݿ⽨������
				connection = DriverManager.getConnection(URL, USERNAME, PWD);
				
				
				// c.����sql��ִ��(��ɾ�ġ���)  num1+num2 ->num3
				 cstmt = 	connection.prepareCall(   "{ call test3(?,?,?) }" ) ;
				 cstmt.setInt(1, 30);
				 cstmt.setInt(2, 40);
				
				 cstmt.registerOutParameter(3, Types.INTEGER);
				 cstmt.execute() ;//num1+num2 ,execute()֮ǰ���� ��������Լ�����������ͣ�֮������������ֵ
				 //�����������������
				
				 int result = cstmt.getInt(3) ;//��ȡ������ 
				System.out.println(result);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					 if(cstmt!=null) cstmt.close();// ����.����
					 if(connection!=null)connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

	public static void main(String[] args) {
		invokeProcedure();
	}

}
