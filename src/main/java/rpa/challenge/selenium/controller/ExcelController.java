package rpa.challenge.selenium.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.model.Person;

public class ExcelController {
	
	private static Logger log = Logger.getLogger(ExcelController.class);
	private String outputFileName = "challenge.xlsx";
	private String outputFilePath = PageEnum.PATH_EXCEL_OUTPUT.getValue() + outputFileName;
	private String processingPath = PageEnum.PATH_EXCEL_PROCESSING.getValue() + outputFileName;
	
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
					person.setPhoneNumber(String.valueOf((long) row.getCell(6).getNumericCellValue()));

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
	
	public void writeOutputFile(List<Person> persons) {
		try (FileInputStream fileInputStream = new FileInputStream(processingPath)){
			XSSFWorkbook fileWorkbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = fileWorkbook.getSheetAt(0);
			
			int rowNumber = 1;
			for (Person person : persons) {
				Row row = sheet.createRow(rowNumber++);
				
				row.createCell(0).setCellValue(person.getFirstName());
				row.createCell(1).setCellValue(person.getLastName());
				row.createCell(2).setCellValue(person.getCompanyName());
				row.createCell(3).setCellValue(person.getRoleInCompany());
				row.createCell(4).setCellValue(person.getAddress());
				row.createCell(5).setCellValue(person.getEmail());
				row.createCell(6).setCellValue(person.getPhoneNumber());
				row.createCell(7).setCellValue(person.isSuccessProcessed());
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(processingPath);
			fileWorkbook.write(fileOutputStream);
			fileWorkbook.close();
			
			Files.move(Paths.get(processingPath), Paths.get(outputFilePath), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private String findExcel() {
		String filePath = PageEnum.PATH_EXCEL_INPUT.getValue();
		
		try {
			if (Files.exists(Paths.get(filePath))) {
				new File(filePath).mkdirs();
				new File(processingPath).mkdirs();
				new File(outputFilePath).mkdirs();
			}
			
			File filesInput = new File(filePath);
			String[] files = filesInput.list();
			for (String file : files) {
				if (file.contains(".xlsx")) {
					filePath = filePath + file;
					Files.move(Paths.get(filePath), Paths.get(processingPath), StandardCopyOption.REPLACE_EXISTING);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return processingPath;
	}
	
}
