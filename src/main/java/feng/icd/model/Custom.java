package feng.icd.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class Custom {

	@Id
	@GeneratedValue
	private Long id;

	private String enDesc;
	
	private String zhDesc;

	public Custom() {
	}

	public Custom(String enDesc, String zhDesc) {
		this.enDesc = enDesc;
		this.zhDesc = zhDesc;
	}
	
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
