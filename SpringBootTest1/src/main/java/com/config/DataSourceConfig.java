package com.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;


@Configuration
public class DataSourceConfig {

	@Bean(name="Datasource1")
	@ConfigurationProperties(prefix="spring.datasource.druid.data1")
	public DataSource DataSouce1() {
		 return DruidDataSourceBuilder.create().build();
	}
	
	
	@Bean(name="Datasource2")
	@ConfigurationProperties(prefix="spring.datasource.druid.data2")
	public DataSource DataSouce2() {
		 return DruidDataSourceBuilder.create().build();
	}
	
	
	
	
/*

	  @Bean(name="sqlsessionFactory1")

	  @Primary public SqlSessionFactory
	 getSqlSessionFactory(@Qualifier("datasource1") DataSource datasource) throws
	  Exception { SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
	  bean.setDataSource(datasource); return bean.getObject(); }
*/

	

	/* @Bean(name="datasource2")

	  @ConfigurationProperties(prefix="spring.datasource.data2") public DataSource
	  getDataSouce1() { return DruidDataSourceBuilder.create().build(); }

	  @Bean(name="sqlsessionFactory2") public SqlSessionFactory
	  getSqlSessionFactory1(@Qualifier("datasource2") DataSource datasource) throws
	 Exception { SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
	 bean.setDataSource(datasource); return bean.getObject(); }*/

}
