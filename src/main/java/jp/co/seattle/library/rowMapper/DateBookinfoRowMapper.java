package jp.co.seattle.library.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import jp.co.seattle.library.dto.DateBookinfo;

@Configuration
public class DateBookinfoRowMapper implements RowMapper<DateBookinfo> {

    @Override
    public DateBookinfo mapRow(ResultSet rs,int rowNum) throws SQLException { 
        // Query結果（ResultSet rs）を、オブジェクトに格納する実装
    	DateBookinfo DateBookinfo = new DateBookinfo();
    	
    	DateBookinfo.setId(rs.getInt("id"));
    	DateBookinfo.setRent_date(rs.getDate("rent_date"));
    	DateBookinfo.setReturn_date(rs.getDate("return_date"));
    	DateBookinfo.setTitle(rs.getString("title"));
      
       
       
        return DateBookinfo;
    }

}