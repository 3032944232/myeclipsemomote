package jdbcProject;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JdbClob {
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
		private static final String USERNAME = "scott";
		private static final String PWD = "tiger";

		public static void clobDemo() {
			Connection connection = null;
			CallableStatement cstmt = null;
			try {
				// a.�������������ؾ����������
				Class.forName("oracle.jdbc.OracleDriver");// ���ؾ����������
				// b.�����ݿ⽨������
				connection = DriverManager.getConnection(URL, USERNAME, PWD);
				String sql="insert into mynovel values(?,?)";
				PreparedStatement ptemt = connection.prepareStatement(sql);
				ptemt.setInt(1, 1);
				File file = new File("D:\\С˵.txt");
				InputStream in = new FileInputStream(file);
				Reader reader = new InputStreamReader(in, "utf-8");
				ptemt.setCharacterStream(2, reader,(int)file.length() );
				int count = ptemt.executeUpdate();
				if(count>0) {
					System.out.println("�����ɹ���");
				}
				reader.close();
				in.close();
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
		public static void clobQ() {
			Connection connection = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			try {
				// a.�������������ؾ����������
				Class.forName("oracle.jdbc.OracleDriver");// ���ؾ����������
				// b.�����ݿ⽨������
				connection = DriverManager.getConnection(URL, USERNAME, PWD);
				String sql="select novel from mynovel where id = ?";
				PreparedStatement ptemt = connection.prepareStatement(sql);
				ptemt.setInt(1, 1);
				rs = ptemt.executeQuery();
				if(rs.next()) {
					Reader reader = rs.getCharacterStream("novel");
					Writer writer = new FileWriter("src/С�h.txt");
					char[] chs = new char[100];
					int len = -1;
					while((len=reader.read(chs))!=-1) {
						writer.write(chs,0,len);
					}
					writer.close();
					reader.close();
				}
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
//		clobDemo();
		clobQ();
	}

}

