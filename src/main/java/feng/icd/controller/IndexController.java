package feng.icd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by TCFeng on 2018/3/7.
 */
@Controller
public class IndexController {


	/**
     * HTML INDEX
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "resources/index.html";
	}
}
