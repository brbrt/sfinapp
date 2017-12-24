package hu.brbrt.overview;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OverviewItemRowMapper implements RowMapper<OverviewItem> {

    @Override
    public OverviewItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OverviewItem()
                .setTransactionId(rs.getInt("transaction_id"))
                .setDescription(rs.getString("description"))
                .setDate(rs.getDate("date").toLocalDate())
                .setAmount(rs.getDouble("amount"))
                .setAccountName(rs.getString("account_name"))
                .setToAccountName(rs.getString("to_account_name"))
                .setTagName(rs.getString("tag_name"));
    }

}
