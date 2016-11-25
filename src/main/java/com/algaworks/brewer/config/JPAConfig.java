package com.algaworks.brewer.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.Cervejas;

@Configuration
@EnableJpaRepositories(basePackageClasses = Cervejas.class)
public class JPAConfig {

	@Bean
	public DataSource dataSource() {
		
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/brewerDB");
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setShowSql(false);
		vendorAdapter.setGenerateDdl(false);                                      // Essa propriedade permite que o Hibernate gere as tabelas automaticamente, mas
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");  // como estamos usando o FlyWay para criar as tabelas, ela é setada para false
		
		return vendorAdapter;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter vendorAdapter) {
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setPackagesToScan(Cerveja.class.getPackage().getName()); // Ao invés de usarmos uma string com o nome do pacote a ser scaneado,  
		factoryBean.afterPropertiesSet();                                    // usamos métodos para chegar ao nome do pacote, pois caso o nome do pacote seja  
		                                                                     // alterado futuramente, este trecho não precisará ser alterado
		return factoryBean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory managerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(managerFactory);
		return transactionManager;
	}
}
