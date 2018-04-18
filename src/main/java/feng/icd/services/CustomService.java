package feng.icd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feng.icd.dao.CustomDao;
import feng.icd.model.Custom;
import feng.icd.vo.CustomRequestVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomService {

	@Autowired
	private CustomDao customDao;

	public void addCustom(CustomRequestVO customRequest) {
		Custom custom = new Custom(customRequest.getEnDesc(), customRequest.getZhDesc());
		Custom customData = customDao.findCustomByEnDesc(customRequest.getEnDesc());
		// Check Node Exists
		if (customData == null) {
			customDao.save(custom);
		}
		customDao.createDescribeRelation(customRequest.getCode(), customRequest.getEnDesc());
	}

	public void updateCustom(CustomRequestVO customRequest) {
		Custom custom = new Custom(customRequest.getEnDesc(), customRequest.getZhDesc());
		Custom customData = customDao.findCustomById(customRequest.getId());

		if (customData != null) {
			custom.setId(customData.getId());
			customDao.save(custom);
		}
	}

	public void deleteCustom(CustomRequestVO customRequest) {
		customDao.deleteCustomById(customRequest.getId());
	}
}
