package rpa.challenge.selenium.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rpa.challenge.selenium.model.Person;

public class ExcelController {
	
	private static Logger log = Logger.getLogger(ExcelController.class);
	
	public List<Person> readRowsExcel(){
		log.info("Reading excel rows");
		List<Person> personList = new ArrayList<>();
		
		File inputFile = new File(findExcel());
		try (FileInputStream fileInputStream = new FileInputStream(inputFile)){
			XSSFWorkbook fileWorkbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = fileWorkbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Person person = new Person();
				Row row = rowIterator.next();
				
				String firstCell = row.getCell(0).getStringCellValue();
				if (!firstCell.equals("") && !firstCell.contains("First")) {
					person.setFirstName(firstCell);
					person.setLastName(row.getCell(1).getStringCellValue());
					person.setCompanyName(row.getCell(2).getStringCellValue());
					person.setRoleInCompany(row.getCell(3).getStringCellValue());
					person.setAddress(row.getCell(4).getStringCellValue());
					person.setEmail(row.getCell(5).getStringCellValue());
					//person.setPhoneNumber(row.getCell(6).getNumericCellValue());

					log.info(person);
					personList.add(person);
				} else if(firstCell.equals("")) {
					break;
				}
			}
			
			fileWorkbook.close();
			
			log.info("Rows loaded with success!");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return personList;
	}
	
	private String findExcel() {
		String filePath = "c:\\Arquivos\\rpa.challenge\\";
		
		try {
			File filesInput = new File(filePath);
			String[] files = filesInput.list();
			for (String file : files) {
				if (file.contains(".xlsx")) {
					filePath = filePath + file;
				}
			}
			
			//Files.move(Paths.get(filePath), Paths.get("c:\\Arquivos\\rpa.challenge\\processando\\challenge.xlsx"), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return filePath;
	}
	
}
