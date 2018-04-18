package feng.icd.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feng.icd.dao.CodeDao;
import feng.icd.model.Code;


@Service
@Transactional(rollbackFor = Exception.class)
public class CodeService {

	@Autowired
	private CodeDao codeDao;

	@Transactional(readOnly = true)
	public Map<String, Code> searchCodes(String code, String name, String enDesc) {
		Map<String, Code> resultMap = new HashMap<String, Code>();
		String regexCode = ".*(?i)" + code.trim() + ".*";
		String regexName = ".*(?i)" + name.trim() + ".*";
		String regexEnDesc = ".*(?i)" + enDesc.trim() + ".*";
		Collection<Code> codeIndexs = null;
		if("".equals(enDesc.trim())) {
			codeIndexs = codeDao.searchCodes(regexCode, regexName);
		} else {
			codeIndexs = codeDao.searchCodesWithEnDesc(regexCode, regexName, regexEnDesc);
		}
		
		String [] codes = new String[codeIndexs.size()]; 
		int index = 0;
		for(Code rcode: codeIndexs){
			codes[index++] = rcode.getCode();
		}
		
		Collection<Code> result = codeDao.searchCodeInfoInCode(codes);
		
		for(Code rcode: result){
			resultMap.put(rcode.getCode(), rcode);
		}
		
		return resultMap;
	}

	public void importExcel() throws IOException {
		FileInputStream fis = null;
		Workbook wb = null;
		String filePath = "FilePath";
		try {

			fis = new FileInputStream(filePath);

			if (isExcel2003(filePath)) {
				wb = new HSSFWorkbook(fis);
			} else {
				wb = new XSSFWorkbook(fis);
			}

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				System.out.println("Number of Row:" + sheet.getPhysicalNumberOfRows());
				for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j = j + 2) {
					
					Row firstRow = sheet.getRow(j);
					Row secondRow = sheet.getRow(j + 1); 
					String code = "";
					String name = "";
					String describe = "";

					if (firstRow != null) {

						code = firstRow.getCell(0) != null ? firstRow.getCell(0).toString().trim() : "";
						name = firstRow.getCell(1) != null ? firstRow.getCell(1).toString().trim() : "";
						describe = secondRow.getCell(1) != null ? secondRow.getCell(1).toString().trim() : "";

						Code newCode = new Code(code, name, describe);

						if (code.length() == 3) {
							codeDao.save(newCode);
						} else if (code.length() > 3) {
							
							String category = code.split("\\.")[0];
							String otherVital = code.split("\\.")[1];

							String parentCodeStr = otherVital.length() > 1 ? category + "." + otherVital.substring(0, otherVital.length() - 1) : category;
							codeDao.save(newCode);
							Code parentCode = codeDao.findDataByCode(parentCodeStr);
							if (parentCode != null) {
								codeDao.createChildRelation(parentCode.getCode(), newCode.getCode());
							}
						}
					} // End for if
				} // End for getPhysicalNumberOfRows
			} // End for getNumberOfSheets
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} finally {
			fis.close();// Close File
		}
	}

	/**
	 * Check is 2003 excel
	 * 
	 * @param filePath
	 * @return
	 */
	private boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * Check is 2007 excel
	 * 
	 * @param filePath
	 * @return
	 */
	private boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
