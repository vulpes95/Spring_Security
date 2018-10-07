package eustrosoft.org.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="eustrosoft.org")
@PropertySource("classpath:persistence-mysql.properties")
public class Config {
	
	@Autowired
	private Environment env;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource secuityDataSource() {
		
		ComboPooledDataSource secuityDataSource = new ComboPooledDataSource();
		try {
			secuityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		logger.info(">>> jdbc.url="+ env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user="+ env.getProperty("jdbc.user"));
		
		secuityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		secuityDataSource.setUser(env.getProperty("jdbc.user"));
		secuityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		secuityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		secuityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		secuityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		secuityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return secuityDataSource;
	}
	
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
}















