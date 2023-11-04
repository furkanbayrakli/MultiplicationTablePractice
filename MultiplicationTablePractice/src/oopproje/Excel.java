package oopproje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class Excel {

	public Excel() {
		
	}
	public void writeExcel(Users users) throws ClassNotFoundException {
		XSSFWorkbook wb = new XSSFWorkbook() ;
		String[] row_heading = {"Sınav", "Başlangıç", "Bitiş","Puan"};
		String[] row_heading2 = {"Soru", "Doğru/Yanlış", "Ayrılan Süre"};
		users.getUsers();
		for(Child aChild : users.getChilderen()){
			XSSFSheet spreadsheet = wb.createSheet( aChild.getName());
			Row row= spreadsheet.createRow(0);

			for (int i=0; i < row_heading.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(row_heading[i]);
			}
			int i=1;
			for(Reports report: aChild.getReports()) {
				Row dataRow = spreadsheet.createRow(i);
				dataRow.createCell(0).setCellValue(report.getGameName());
				dataRow.createCell(1).setCellValue(report.getStartDate().getHours()+":"+report.getStartDate().getMinutes()+":"+report.getStartDate().getSeconds());
				dataRow.createCell(2).setCellValue(report.getEndDate().getHours()+":"+report.getEndDate().getMinutes()+":"+report.getEndDate().getSeconds());
				dataRow.createCell(3).setCellValue(report.getScore());
				i++;
				Row dataRow3 = spreadsheet.createRow(i);
				for (int j=0; j < row_heading2.length; j++) {
					Cell cell = dataRow3.createCell(j+1);
					cell.setCellValue(row_heading2[j]);
				}
				i++;
				for(Question q : report.getQuestions()){
					Row dataRow2 = spreadsheet.createRow(i);
					dataRow2.createCell(1).setCellValue(q.getQuestion()+q.getAnswer());
					if(q.getBool())
						dataRow2.createCell(2).setCellValue("doğru");
					else
						dataRow2.createCell(2).setCellValue("yanlış");
					dataRow2.createCell(3).setCellValue(q.getTime());
					i++;
				}
				i++;
			}
		}
		try{
			FileOutputStream out = new FileOutputStream(new File("Reports.xlsx"));
			wb.write(out);
			out.close();
		}catch(FileNotFoundException e){
			System.out.println("Dosya bulunamadı.");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("Excele yazma işlemleri sırasında bir hata.");
			e.printStackTrace();
		}
		System.out.println("Bilgiler excele başarı ile aktarıldı.");
	}
}
