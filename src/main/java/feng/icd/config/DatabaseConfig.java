package feng.icd.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.neo4j.ogm.config.ClasspathConfigurationSource;

@Configuration
@EnableNeo4jRepositories(basePackages = "feng.icd.dao")
@EnableTransactionManagement
public class DatabaseConfig {

	@Bean
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "feng.icd.model");
	}

	@Bean
	public Neo4jTransactionManager transactionManager() throws Exception {
		return new Neo4jTransactionManager(getSessionFactory());
	}

	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
		ClasspathConfigurationSource configurationSource = new ClasspathConfigurationSource("/application.properties");
		org.neo4j.ogm.config.Configuration.Builder builder = new org.neo4j.ogm.config.Configuration.Builder(configurationSource);
		org.neo4j.ogm.config.Configuration configuration = builder.build();
		return configuration;
	}

}
