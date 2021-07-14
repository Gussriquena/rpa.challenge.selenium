package rpa.challenge.selenium.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rpa.challenge.selenium.constants.PageEnum;
import rpa.challenge.selenium.model.Person;

public class ExcelController {
	
	private static Logger log = Logger.getLogger(ExcelController.class);
	
	private String outputFileName = PageEnum.NAME_EXCEL_FILE.getValue();
	private String processingPath = PageEnum.PATH_EXCEL_PROCESSING.getValue() + outputFileName;
	private String resultMessage;
	
	public List<Person> readRowsExcel(){
		log.info("Reading excel rows");
		List<Person> personList = new ArrayList<>();
		
		try (FileInputStream fileInputStream = new FileInputStream(new File(findExcel()))){
			XSSFWorkbook fileWorkbook = new XSSFWorkbook(fileInputStream);
			Iterator<Row> rowIterator = fileWorkbook.getSheetAt(0).iterator();
			
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
					person.setPhoneNumber(getConvertedCellData(row.getCell(6)));

					personList.add(person);
				} else if("".equals(firstCell)) {
					break;
				}
			}
			
			fileWorkbook.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		log.info("Rows loaded with success!");
		return personList;
	}
	
	public void writeOutputFile(List<Person> persons) {
		try {
			FileInputStream fileInputStream = new FileInputStream(processingPath);
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

			sheet.createRow(rowNumber).createCell(0).setCellValue(resultMessage);
			
			FileOutputStream fileOutputStream = new FileOutputStream(processingPath);
			fileWorkbook.write(fileOutputStream);
			fileWorkbook.close();
			fileInputStream.close();
			fileOutputStream.close();
			
			String outputFilePath 
					= PageEnum.PATH_EXCEL_OUTPUT.getValue() 
					+ PageEnum.NAME_EXCEL_FILE.getValue()
					+ "_" 
					+ new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date()) 
					+ ".xlsx";
			
			Files.move(Paths.get(processingPath), Paths.get(outputFilePath), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	private String getConvertedCellData(Cell cell) {
		String cellReturn = "";
		
		try {
			if (cell != null) {
				if (cell.getCellType().equals(CellType.NUMERIC)) {
					cellReturn = String.valueOf((Double) cell.getNumericCellValue());
				} else if(cell.getCellType().equals(CellType.STRING)) {
					cellReturn = cell.getStringCellValue();
				}
			}
			
		} catch (Exception e) {
			log.error("Was not possible to convert cell data " + e.getMessage());
		}

		return cellReturn.trim();
	}

	
	private String findExcel() {
		String filePath = PageEnum.PATH_EXCEL_INPUT.getValue();
		
		try {
			if (Files.exists(Paths.get(filePath))) {
				new File(filePath).mkdirs();
				new File(PageEnum.PATH_EXCEL_PROCESSING.getValue()).mkdirs();
				new File(PageEnum.PATH_EXCEL_OUTPUT.getValue()).mkdirs();
			}
			
			for (String file : new File(filePath).list()) {
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

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
