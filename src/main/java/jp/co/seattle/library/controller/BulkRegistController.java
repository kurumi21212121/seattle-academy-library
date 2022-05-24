package jp.co.seattle.library.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.service.BooksService;

@Controller // APIの入り口
public class BulkRegistController {
	final static Logger logger = (Logger) LoggerFactory.getLogger(BulkRegistController.class);
	@Autowired
	private BooksService booksService;
	/**
 	 * 書籍情報を登録する
 	 * @param locale ロケール情報
 	 * @param bookId bookId
 	 * @param model モデル
 	 * * @return 遷移先画面
 	 **/
	
	@RequestMapping(value = "/bulkRegist", method = RequestMethod.GET) // value＝actionで指定したパラメータ
	public String bulkRegist(Model model) {
		return "bulkRegist";
	}
	/**
 	 * 書籍情報を登録する(CSV一括登録)
 	 * @param uploadFile CSVファイル
 	 * @param model モデル
 	 * @return 遷移先画面
 	 **/
	@Transactional
	@RequestMapping(value = "/bulkRegist", method = RequestMethod.POST)
	public String bulkRegist(Locale locale, @RequestParam("file") MultipartFile file, Model model) {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			int lineCount = 0;
			List<String> errorMessages = new ArrayList<String>();
			List<BookDetailsInfo> bookLists = new ArrayList<BookDetailsInfo>();

			if (!br.ready()) {
				model.addAttribute("errorMessages", "CSVに書籍情報がありません");
				return "bulkRegist";
			}

			while ((line = br.readLine()) != null) {
				final String[] inputvalue = line.split(",", -1);


				// 行数カウントインクリメント
				lineCount++;

				if (inputvalue[0].isEmpty() || inputvalue[1].isEmpty() || inputvalue[2].isEmpty()
						|| inputvalue[3].isEmpty()
						
						|| (inputvalue[3].length() != 8 || !(inputvalue[3].matches("^[0-9]*$")))
						
						|| (inputvalue[3].length() != 8 || !(inputvalue[3].matches("^[0-9]*$")))
						||(inputvalue[4].length() != 10 && inputvalue[4].length() != 13 && inputvalue[4].length() != 0
						|| !(inputvalue[4].matches("^[0-9]*$")))) 
					
				
				{
				
				errorMessages.add(lineCount + "行目でバリデーションエラーが発生しました");
				} 
				
				else {
					BookDetailsInfo bookInfo = new BookDetailsInfo();
					bookInfo.setTitle(inputvalue[0]);
					bookInfo.setAuthor(inputvalue[1]);
					bookInfo.setPublisher(inputvalue[2]);
					bookInfo.setPublishDate(inputvalue[3]);
					bookInfo.setIsbn(inputvalue[4]);
					
					bookLists.add(bookInfo);
				}

			}
			// エラーメッセージあればrender
			if (CollectionUtils.isEmpty(errorMessages)) {
				bookLists.forEach(bookList -> booksService.bulkRegist(bookList));
				return "redirect:home";
			} else {
				model.addAttribute("errorMessages", errorMessages);
				return "bulkRegist";
			}
		} catch (Exception e) {

			List<String> errorMessages = new ArrayList<String>();
			errorMessages.add("ファイルが読み込めません");
			model.addAttribute("errorMessages", errorMessages);
			return "bulkRegist";

		}
	}
}
