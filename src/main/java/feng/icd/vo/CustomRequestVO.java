package feng.icd.vo;

public class CustomRequestVO extends BasicVO{

	/**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3584116508049471546L;
	
    private Long id;
    
    private String code;
    
    private String enDesc;
    
    private String zhDesc;

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
    
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the enDesc
	 */
	public String getEnDesc() {
		return enDesc;
	}

	/**
	 * @param enDesc the enDesc to set
	 */
	public void setEnDesc(String enDesc) {
		this.enDesc = enDesc;
	}

	/**
	 * @return the zhDesc
	 */
	public String getZhDesc() {
		return zhDesc;
	}

	/**
	 * @param zhDesc the zhDesc to set
	 */
	public void setZhDesc(String zhDesc) {
		this.zhDesc = zhDesc;
	}
    
    
}
