package pp.javastart.budget;

import java.math.BigDecimal;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public static final String INSERT_SQL = "INSERT INTO transactions(type, description, amount, date) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE transactions SET type = ?, description = ?, amount =? , date = ? WHERE id=?";
    public static final String SELECT_BY_TYPE_SQL = "SELECT * FROM transactions WHERE type like ?";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM transactions WHERE id = ?";
    public static final String URL_DB = "jdbc:mysql://localhost:3306/budget?serverTimezone=UTC&characterEncoding=utf8";

    public void insert(Transaction transaction) {

        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = INSERT_SQL;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(transaction.getType()));
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu do bazy: " + e.getMessage());
        }
        closeStatement(preparedStatement);
        closeConnection(connection);

    }

    private void closeStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Niepowodzenia podczas zamkniecia prepareStatement" + e.getMessage());
        }
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
        String url = URL_DB;
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
            String sql = UPDATE_SQL;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(transaction.getType()));
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
            preparedStatement.setLong(5, transaction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas modyfikacji: " + e.getMessage());
        }
        closeStatement(preparedStatement);
        closeConnection(connection);
    }

    public void deleteById(long id) {
        Connection connection = connect();

        PreparedStatement preparedStatement = null;
        try {
            String sql = DELETE_BY_ID_SQL;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas usówania rekordu bazy: " + e.getMessage());
        }
        closeStatement(preparedStatement);
        closeConnection(connection);
    }

    public List<Transaction> searchByType(String type) {
        Connection connection = connect();
        List<Transaction> list = new ArrayList<>();
        Transaction transaction = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = SELECT_BY_TYPE_SQL;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                TransactionType typeFromDb = TransactionType.valueOf(resultSet.getString("type"));
                String description = resultSet.getString("description");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("date");

                transaction = new Transaction(id, typeFromDb, description, amount, date.toLocalDate());
                list.add(transaction);
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas wczytywania danych z bazy: " + e.getMessage());
        }
        if (preparedStatement != null) {
            closeStatement(preparedStatement);
        }
        closeConnection(connection);
        return list;
    }
}
