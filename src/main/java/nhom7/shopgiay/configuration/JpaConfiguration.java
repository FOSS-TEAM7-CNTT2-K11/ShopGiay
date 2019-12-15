package nhom7.shopgiay.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "nhom7.shopgiay.repository")
@PropertySource("classpath:appconfig.properties")
@EnableTransactionManagement
public class JpaConfiguration {

	@Autowired
	public Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dts = new DriverManagerDataSource();
		dts.setDriverClassName("com.mysql.jdbc.Driver");
		dts.setUrl(env.getProperty("datasource.url"));
		dts.setUsername(env.getProperty("datasource.username"));
		dts.setPassword(env.getProperty("datasource.password"));
		return dts;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("nhom7.shopgiay.entity");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaDialect(new HibernateJpaDialect());
		
		Properties prop = new Properties();
		prop.put("hibernate.show_sql", true);
		emf.setJpaProperties(prop);
		
		return emf;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
}
