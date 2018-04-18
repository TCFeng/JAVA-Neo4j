# JAVA-Neo4j
JAVA Neo4j project

 Search API
 	GET http://localhost:8080/neo4j/icd/codes?code=A00&name=Ch&enDesc=test
 
 Add Custom Node
 	POST http://localhost:8080/neo4j/icd/custom
 	Data Type: JSON
 	Data Format Example: 
 	{
 		"Code": "A00",
 		"enDesc":"Test English Describe",
		"zhDesc":"測試中文描述"
 	}
 	
 Update Custom Node
 	PUT http://localhost:8080/neo4j/icd/custom
 	Data Type: JSON
 	Data Format Example: 
	{
		"id": 205740,
		"enDesc":"Test English Describe (Edit)",
		"zhDesc":"測試中文描述 (編輯)"
	}

 Delete Custom Node
 	DELETE http://localhost:8080/neo4j/icd/custom
 	Data Type: JSON
 	Data Format Example: 
	{
		"id": 205740
	}