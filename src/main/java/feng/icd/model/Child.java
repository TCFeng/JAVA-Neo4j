package feng.icd.model;

import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author TCFeng
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "CHILD")
public class Child {

	@Id
	private Long id;

	private Collection<String> childs = new ArrayList<>();

	@StartNode
	private Code parentCode;

	@EndNode
	private Code childCode;

	public Child() {
	}

	public Child(Code parentCode, Code childCode) {
		this.parentCode = parentCode;
		this.childCode = childCode;
	}
	
	public Long getId() {
		return id;
	}

	public Collection<String> getChilds() {
		return childs;
	}

	public Code getParentCode() {
		return parentCode;
	}

	public Code getChildCode() {
		return childCode;
	}

	public void addChildName(String name) {
		this.childs.add(name);
	}
}
