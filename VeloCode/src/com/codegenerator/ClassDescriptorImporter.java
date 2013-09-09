package com.codegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ClassDescriptorImporter {

	private ArrayList classes = new ArrayList();

	public ArrayList getClasses() {
		return classes;
	}

	public ClassDescriptorImporter(String modelFile, String language) {
		FileInputStream file = null;
		HSSFWorkbook workbook = null;
		try {
			// read in the spreadsheet and fetch the first work sheet in the
			// file
			file = new FileInputStream(new File(modelFile));
			// Get the workbook instance for XLS file
			workbook = new HSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		String table = "";
		for (Row row : sheet) {
			String tableName = "";
			String tableDesc = "";
			String columnName = "";
			String columnType = "";
			String columnDesc = "";

			if (GeneratorUtility.isNotNull(row.getCell(0)))
				tableName = GeneratorUtility.replaceSpecial(row.getCell(0).getStringCellValue());
			if (GeneratorUtility.isNotNull(row.getCell(1)))
				tableDesc = GeneratorUtility.replaceBarN(row.getCell(1).getStringCellValue());
			if (GeneratorUtility.isNotNull(row.getCell(2)))
				columnName = GeneratorUtility.replaceSpecial(row.getCell(2).getStringCellValue());
			if (GeneratorUtility.isNotNull(row.getCell(3)))
				columnType = GeneratorUtility.getSpecificType(row.getCell(3).getStringCellValue(), language);
			if (GeneratorUtility.isNotNull(row.getCell(4)))
				columnDesc = GeneratorUtility.replaceBarN(row.getCell(4).getStringCellValue());

			if (hasFilter(columnName))
				continue;

			if (!table.equalsIgnoreCase(tableName.trim())) {
				table = tableName;
				ClassDescriptor cl = new ClassDescriptor();
				cl.setName(table);
				cl.setDescription(tableDesc);
				classes.add(cl);
			}
			// Setting column values
			if (!(columnName.trim().length() > 0 || columnType.trim().length() > 0))
				continue;
			AttributeDescriptor at = new AttributeDescriptor();
			at.setName(columnName);
			at.setType(columnType);
			at.setDescription(columnDesc);
			ClassDescriptor parent = (ClassDescriptor) classes.get(classes.size() - 1);
			parent.addAttribute(at);
		}

	}

	private boolean hasFilter(String columnName) {
		String conlumnFilter = "codigousuarioinclusaoregist " + "datahorainclusaoregistro " + "codigousuarioultimaatualiza " + "codigoterminalultimaatualiz " + "codigosucursalultimaatualiz "
				+ "datahoraultimaatualizacao";
		if (conlumnFilter.indexOf(columnName.trim().toLowerCase()) > 0)
			return true;
		return false;
	}

}