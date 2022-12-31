package com.springbatchexp.springbatchexpample.mapper;

import com.springbatchexp.springbatchexpample.entity.Customers;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerMapper implements RowMapper<Customers> {

    @Override
    public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customers customers = new Customers();
        customers.setId(rs.getInt("id"));
        customers.setFirstName(rs.getString("first_name"));
        customers.setLastName(rs.getString("last_name"));
        customers.setCountry(rs.getString("country"));
        customers.setDob(rs.getString("dob"));
        customers.setEmail(rs.getString("email"));
        customers.setContactNo(rs.getString("contact_no"));
        customers.setGender(rs.getString("gender"));
        return customers;
    }
}
