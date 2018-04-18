package feng.icd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import feng.icd.model.Code;
import feng.icd.services.CodeService;
import feng.icd.services.CustomService;
import feng.icd.vo.CustomRequestVO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TCFeng on 2018/3/7.
 */
@Controller
@RequestMapping(value = "/icd")
public class IcdController {

	@Autowired
	CodeService codeService;
	
	@Autowired
	CustomService customService;
	
	@RequestMapping(path = "/codes", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Code> searchCodes(
			@RequestParam(value="code", required=false, defaultValue="") String code,
			@RequestParam(value="name", required=false, defaultValue="") String name,
			@RequestParam(value="enDesc", required=false, defaultValue="") String enDesc) {

		Map<String, Code> result = new HashMap<String, Code>();
		try {
			result = codeService.searchCodes(code, name, enDesc);
		} catch (Exception e) {
			System.out.println("Error in search Code:"+e);
		}

		return result;
	}
	
	@RequestMapping(path = "/codes", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> importICDData() {

		Map<String, String> result = new HashMap<String, String>();
		try {
			codeService.importExcel();
		} catch (Exception e) {
			System.out.println("Error in import Data:"+e);
		}
		result.put("ImportCodeData", "SUCCESS");

		return result;
	}
	

	@RequestMapping(path = "/custom", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addCustom(@RequestBody CustomRequestVO customRequestVO) {

		Map<String, String> result = new HashMap<String, String>();
		try {
			customService.addCustom(customRequestVO);
		} catch (Exception e) {
			System.out.println("Error in add Custom:"+e);
		}
		result.put("AddCustomData", "SUCCESS");

		return result;
	}
	
	@RequestMapping(path = "/custom", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, String> updateCustom(@RequestBody CustomRequestVO customRequestVO) {

		Map<String, String> result = new HashMap<String, String>();
		try {
			customService.updateCustom(customRequestVO);
		} catch (Exception e) {
			System.out.println("Error in update Cuestom:"+e);
		}
		result.put("updateCustom", "SUCCESS");

		return result;
	}
	
	@RequestMapping(path = "/custom", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, String> deleteCustom(@RequestBody CustomRequestVO customRequestVO) {

		Map<String, String> result = new HashMap<String, String>();
		try {
			customService.deleteCustom(customRequestVO);
		} catch (Exception e) {
			System.out.println("Error in search Code:"+e);
		}
		result.put("deleteCustom", "SUCCESS");

		return result;
	}

}
