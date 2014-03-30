package hu.rbr.sfinapp.service;

import hu.rbr.sfinapp.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountService extends AbstractService {
	
	public List<Account> getAllAccounts() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			List<Account> accounts = new ArrayList<>();
			
			Connection conn = dbProvider.getConnection();
			
			ps = conn.prepareStatement("SELECT * FROM accounts");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				
				acc.setId(rs.getInt("acc_id"));
				acc.setName(rs.getString("acc_name"));
				acc.setDescription(rs.getString("acc_description"));
				
				accounts.add(acc);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
