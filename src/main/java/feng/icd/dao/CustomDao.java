package feng.icd.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import feng.icd.model.Custom;


/**
 * @author TCFeng
 */
@Repository
public interface CustomDao extends Neo4jRepository<Custom, Long>{

	@Query("MATCH (c:Custom) WHERE c.enDesc = {enDesc} return c LIMIT 1")
	public Custom findCustomByEnDesc(@Param("enDesc") String enDesc);
	
	@Query("MATCH (c:Custom) WHERE ID(c) = {id} return c")
	public Custom findCustomById(@Param("id") Long id); 
	
	@Query("MATCH (c1:Code), (c2:Custom) WHERE c1.code = {code} and c2.enDesc = {customEnDesc} CREATE (c2) - [r:DESCRIBE] -> (c1) return r")
	public void createDescribeRelation(@Param("code") String code, @Param("customEnDesc") String customEnDesc);
	
	@Query("MATCH (c:Custom) WHERE ID(c) = {id} OPTIONAL MATCH(c)-[r:DESCRIBE]->() DELETE r,c ")
	public Custom deleteCustomById(@Param("id") Long id); 
}
