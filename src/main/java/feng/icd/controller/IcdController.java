package feng.icd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TCFeng on 2018/3/7.
 */
@Controller
@RequestMapping(value = "/icd")
public class IcdController {

	@RequestMapping(path = "/getData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getList() {

		Map<String, String> testMap = new HashMap<String, String>();

		testMap.put("QueryData", "OK");

		return testMap;
	}

}
