package hu.brbrt.transaction.repository;

import hu.brbrt.transaction.Transaction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Transaction()
                .setId(rs.getInt("id"))
                .setDescription(rs.getString("description"))
                .setDate(rs.getDate("date").toLocalDate())
                .setAmount(rs.getDouble("amount"))
                .setAccountId(rs.getInt("account_id"))
                .setToAccountId(getInteger(rs, "to_account_id"))
                .setComment(rs.getString("comment"));
    }

    private Integer getInteger(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }

}
