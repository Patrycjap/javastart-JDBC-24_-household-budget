package pp.javastart.budget;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public void insert(Transaction transaction) {

        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO transactions(type, description, amount, date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }
        closeConnection(connection);
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie przy zamknieciu połączenia " + e.getMessage());
        }
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/budget?serverTimezone=UTC&characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            System.out.println("Niepowodzenie przy połączeniu do bazy " + e.getMessage());
        }
        return null;
    }

    public void update(Transaction transaction) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE transactions SET type = ?, description = ?, amount =? , date = ? WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas modyfikacji: " + e.getMessage());
        }
        closeConnection(connection);
    }

    public void deleteById(long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM transactions WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas usówania rekordu bazy: " + e.getMessage());
        }
        closeConnection(connection);
    }

    public List<Transaction> searchByType(String type) {
        Connection connection = connect();
        List<Transaction> list = new ArrayList<>();
        Transaction transaction = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transactions WHERE type like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String typeFromDb = resultSet.getString("type");
                String description = resultSet.getString("description");
                int amount = resultSet.getInt("amount");
                Date date = resultSet.getDate("date");

                transaction = new Transaction(id, typeFromDb, description, amount, date.toLocalDate());
                list.add(transaction);
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas wczytywania danych z bazy: " + e.getMessage());
        }
        closeConnection(connection);
        return null;
    }
}
