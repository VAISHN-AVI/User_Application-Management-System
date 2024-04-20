package jdbc_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserCRUD {                                                               
	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "9322vm");
		return connection;
	}

	public int signUpUser(User user) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?)");
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setLong(3, user.getPhone());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getPassword());
		preparedStatement.setString(6, user.getFacebook());
		preparedStatement.setString(7, user.getInstagram());
		preparedStatement.setString(8, user.getSnapchat());
		preparedStatement.setString(9, user.getTwitter());
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;

	}

	public String getPassword(String email) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		String password = null;
		while (resultSet.next()) {
			password = resultSet.getString("password");

		}
		connection.close();
		return password;

	}

	public User dispalyPerson(String email) throws Exception {
		Connection connection = getConnection();
		String select = "SELECT * FROM USER WHERE EMAIL = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(select);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = new User();
		while (resultSet.next()) {
			user.setId(resultSet.getInt("id"));
			user.setName(resultSet.getString("name"));
			user.setPhone(resultSet.getLong("phone"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			user.setFacebook(resultSet.getString("facebook"));
			user.setInstagram(resultSet.getString("instagram"));
			user.setSnapchat(resultSet.getString("snapchat"));
			user.setTwitter(resultSet.getString("twitter"));
		}
		connection.close();
		return user;

	}

	public int updatePassword(String email, String password) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USER SET PASSWORD=? WHERE EMAIL=?");
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, email);
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
	}

	void exitUser() throws Exception {
		Connection connection = getConnection();
	}

	public int deleteUser(String email) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		int result = preparedStatement.executeUpdate();
		connection.close();
		return result;
	}

}
