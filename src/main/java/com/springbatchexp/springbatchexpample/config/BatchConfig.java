package com.springbatchexp.springbatchexpample.config;


import com.springbatchexp.springbatchexpample.entity.Customers;
import com.springbatchexp.springbatchexpample.mapper.CustomerMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    private static final String QUERY_CUSTOMER = "select * from customers";

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemReader reader() {
        JdbcCursorItemReader<Customers> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql(QUERY_CUSTOMER);
        reader.setRowMapper(new CustomerMapper());
        return reader;
    }

    @Bean
    public ItemWriter writer() throws Exception {
        FlatFileItemWriter<Customers> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("D:/Finncub/batch/customers3_details.csv"));
        DelimitedLineAggregator<Customers> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<Customers> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob"});
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);
        writer.setHeaderCallback(writer1 -> writer1.write("id,firstName,lastName,email,gender,contactNo,country,dob"));
        return writer;
    }

    @Bean
    public Job ioSampleJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("ioSampleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("step1", jobRepository)
                .<Customers, Customers>chunk(100, transactionManager)
                .reader(reader())
                .writer(writer())
                .build();
    }

}
