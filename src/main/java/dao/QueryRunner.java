package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class QueryRunner {
  private Connection conn;

  public QueryRunner(Connection conn)
  {
    this.conn = conn;
  }

  public boolean runSelectStatement(String selectClause, String fromClause, String whereClause) throws DataAccessException {
    String sql = "SELECT " + selectClause + " FROM " + fromClause + " WHERE "  + whereClause + ";";
    ResultSet rs = null;

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      rs = stmt.executeQuery();
      this.printResultSet(rs);
    } catch (SQLException e) {
      System.out.println(sql);
      e.printStackTrace();
      throw new DataAccessException("Error encountered while running query on the database");
    } finally {
      if(rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return true;
  }

  private void printResultSet(ResultSet resultSet) throws SQLException {
    ResultSetMetaData rsmd = resultSet.getMetaData();
    int columnsNumber = rsmd.getColumnCount();
    while (resultSet.next()) {
      for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        String columnValue = resultSet.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
      }
      System.out.println("");
    }
  }
}
