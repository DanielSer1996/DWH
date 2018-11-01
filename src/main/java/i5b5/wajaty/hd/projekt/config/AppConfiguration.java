package i5b5.wajaty.hd.projekt.config;

import i5b5.wajaty.hd.projekt.AppMain;
import i5b5.wajaty.hd.projekt.mybatis.mappers.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:ds/ds-config.properties")
@EnableTransactionManagement
@ComponentScan(value = "i5b5.wajaty.hd.projekt")
public class AppConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public AppMain appMain() {
        return new AppMain();
    }

    @Bean(name = "dwhSessionFactory", destroyMethod = "")
    public SqlSessionFactoryBean dwhSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dwhDataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("i5b5/wajaty/hd/projekt/mybatis/mappers/mybatis-dwh-config.xml"));

        final SqlSessionFactory object = sqlSessionFactoryBean.getObject();
//        object.getConfiguration().addMapper(DwhMapper.class);
        object.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        object.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<DwhMapper> dwhMapper() throws Exception {
        MapperFactoryBean<DwhMapper> factoryBean = new MapperFactoryBean<>(DwhMapper.class);
        factoryBean.setSqlSessionFactory(dwhSqlSessionFactory().getObject());

        return factoryBean;
    }

    @Bean(name = "source1SessionFactory")
    public SqlSessionFactoryBean firstSourceSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(source1DataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("i5b5/wajaty/hd/projekt/mybatis/mappers/mybatis-sourc1-config.xml"));

        final SqlSessionFactory object = sqlSessionFactoryBean.getObject();
//        object.getConfiguration().addMapper(NetworkMapper.class);
        object.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        object.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<NetworkMapper> source1Mapper() throws Exception {
        MapperFactoryBean<NetworkMapper> factoryBean = new MapperFactoryBean<>(NetworkMapper.class);
        factoryBean.setSqlSessionFactory(firstSourceSessionFactory().getObject());

        return factoryBean;
    }

    @Bean(name = "source2SessionFactory")
    public SqlSessionFactoryBean secondSourceSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(source2DataSource());
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("i5b5/wajaty/hd/projekt/mybatis/mappers/mybatis-sourc2-config.xml"));

        final SqlSessionFactory object = sqlSessionFactoryBean.getObject();
        object.getConfiguration().addMapper(MessageMapper.class);
        object.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        object.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<MessageMapper> source2Mapper() throws Exception{
        MapperFactoryBean<MessageMapper> factoryBean = new MapperFactoryBean<>(MessageMapper.class);
        factoryBean.setSqlSessionFactory(secondSourceSessionFactory().getObject());

        return factoryBean;
    }

    @Bean(name = "source3SessionFactory")
    public SqlSessionFactoryBean thirdSourceSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(source3DataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("i5b5/wajaty/hd/projekt/mybatis/mappers/mybatis-sourc3-config.xml"));

        final SqlSessionFactory object = sqlSessionFactoryBean.getObject();
//        object.getConfiguration().addMapper(CallMapper.class);
        object.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        object.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<CallMapper> source3Mapper() throws Exception{
        MapperFactoryBean<CallMapper> factoryBean = new MapperFactoryBean<>(CallMapper.class);
        factoryBean.setSqlSessionFactory(thirdSourceSessionFactory().getObject());

        return factoryBean;
    }

    @Bean(name = "source4SessionFactory")
    public SqlSessionFactoryBean fourthSourceSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(source4DataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("i5b5/wajaty/hd/projekt/mybatis/mappers/mybatis-sourc4-config.xml"));

        final SqlSessionFactory object = sqlSessionFactoryBean.getObject();
//        object.getConfiguration().addMapper(TvMapper.class);
        object.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        object.getConfiguration().setMapUnderscoreToCamelCase(true);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<TvMapper> source4Mapper() throws Exception{
        MapperFactoryBean<TvMapper> factoryBean = new MapperFactoryBean<>(TvMapper.class);
        factoryBean.setSqlSessionFactory(fourthSourceSessionFactory().getObject());

        return factoryBean;
    }

    @Bean(name = "dwh")
    public DataSource dwhDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(environment.getProperty("dwh.url"));
        dataSource.setUsername(environment.getProperty("dwh.username"));
        dataSource.setPassword(environment.getProperty("dwh.password"));
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }

    @Bean(name = "source1")
    public DataSource source1DataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(environment.getProperty("source1.url"));
        dataSource.setUsername(environment.getProperty("source1.username"));
        dataSource.setPassword(environment.getProperty("source1.password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }

    @Bean(name = "source2")
    public DataSource source2DataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(environment.getProperty("source2.url"));
        dataSource.setUsername(environment.getProperty("source2.username"));
        dataSource.setPassword(environment.getProperty("source2.password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }

    @Bean(name = "source3")
    public DataSource source3DataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(environment.getProperty("source3.url"));
        dataSource.setUsername(environment.getProperty("source3.username"));
        dataSource.setPassword(environment.getProperty("source3.password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }

    @Bean(name = "source4")
    public DataSource source4DataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(environment.getProperty("source4.url"));
        dataSource.setUsername(environment.getProperty("source4.username"));
        dataSource.setPassword(environment.getProperty("source4.password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }

    @Bean(name = "dwhTxMan")
    public PlatformTransactionManager dwhPlatformTransactionManager() {
        return new DataSourceTransactionManager(dwhDataSource());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JdbcTemplate dhwJdbcTemplate(){
        return new JdbcTemplate(dwhDataSource());
    }

    @Bean
    public JdbcTemplate networkJdbcTemplate(){
        return new JdbcTemplate(source1DataSource());
    }

    @Bean
    public JdbcTemplate messageJdbcTemplate(){
        return new JdbcTemplate(source2DataSource());
    }

    @Bean
    public JdbcTemplate callJdbcTemplate(){
        return new JdbcTemplate(source3DataSource());
    }

    @Bean
    public JdbcTemplate tvJdbcTemplate(){
        return new JdbcTemplate(source4DataSource());
    }

}
