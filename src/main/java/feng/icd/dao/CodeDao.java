package feng.icd.dao;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import feng.icd.model.Code;
import feng.icd.model.Child;


/**
 * @author TCFeng
 */
@Repository
public interface CodeDao extends Neo4jRepository<Code, Long>{

	@Query("MATCH (c:Code) WHERE c.code = {code} return c LIMIT 1")
	public Code findDataByCode(@Param("code") String code); 
	
	@Query("MATCH (co:Code) WHERE co.code =~ {code} AND co.name =~ {name} RETURN co")
	public  Collection<Code> searchCodes(@Param("code") String code, @Param("name") String name);
	
	@Query("MATCH (co:Code)-[r:DESCRIBE]-(cu:Custom) WHERE co.code =~ {code} AND co.name =~ {name} AND cu.enDesc =~ {enDesc} RETURN co")
	public Collection<Code> searchCodesWithEnDesc(@Param("code") String code, @Param("name") String name, @Param("enDesc") String enDesc);
	
	@Query("MATCH (co:Code) WHERE co.code in {codes} RETURN co")
	public Collection<Code> searchCodeInfoInCode(@Param("codes") String[] codes);
	
	@Query("MATCH (c1:Code), (c2:Code) WHERE c1.code = {parentCode} and c2.code = {childCode} CREATE (c2) - [r:CHILD] -> (c1) return r")
	public Child createChildRelation(@Param("parentCode") String parentCode, @Param("childCode") String childCode);
}
