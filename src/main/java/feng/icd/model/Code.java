package feng.icd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author TCFeng
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class Code {

	@Id
	@GeneratedValue
	private Long id;
	
	@Relationship(type = "CHILD")
    private List<Code> childCodes;

	private String code;

	private String name;

	private String describe;

	public Code() {
	}

	public Code(String code, String name, String describe) {
		this.code = code;
		this.name = name;
		this.describe = describe;
	}

	public List<Code> getChildCodes() {
        return childCodes;
    }

    public void setChildCodes(List<Code> childCodes) {
        this.childCodes = childCodes;
    }
	
    public void beingChild(Code code) {
        if (childCodes == null) {
        	childCodes = new ArrayList<>();
        }
        childCodes.add(code);
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * @param describe
	 *            the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

}