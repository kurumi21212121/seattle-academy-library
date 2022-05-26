package jp.co.seattle.library.dto;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 書籍詳細情報格納DTO
 *
 */
@Configuration
@Data
public class DateBookinfo {

	private int id;

	private Date rent_date;

	private Date return_date;

	private String title;
    
   

    public DateBookinfo(){

    }

    public DateBookinfo(int id, int book_id, Date rent_date, Date return_date,
			String title) {
		this.id = id;
		this.rent_date = rent_date;
		this.return_date = return_date;
		this.title = title;
  
    }

	
	}

