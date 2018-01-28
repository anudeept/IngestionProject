package com.iheartradio.IngestionProject.configuration;


import com.iheartradio.IngestionProject.domain.*;
import com.iheartradio.IngestionProject.repository.TrackRepository;
import com.mongodb.MongoClient;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;
import java.util.Map;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * @author Maruti Anudeep Thirumalasetti
 */
@Configuration
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TrackRepository trackRepository;


    @Bean
    public MongoTemplate mongoTemplate(MongoMappingContext context) {
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "TrackBundle");
        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(simpleMongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(simpleMongoDbFactory, converter);
        return mongoTemplate;
    }

    @Bean
    @Scope(value = "prototype")
    public StaxEventItemReader<Track> trackItemReader(String fileName) {

        XStreamMarshaller unmarshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        //aliases.put("customer", Customer.class);
        aliases.put("TRACK", Track.class);
        aliases.put("PRODUCTINFO", ProductInfo.class);
        aliases.put("LANGUAGE", Language.class);
        aliases.put("GENRE", Genre.class);
        aliases.put("FILEINFO", FileInfo.class);
        aliases.put("FILE", File.class);
        unmarshaller.setAliases(aliases);

        StaxEventItemReader<Track> reader = new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource("/data/" + fileName));
        reader.setFragmentRootElementName("TRACK");
        reader.setUnmarshaller(unmarshaller);
        return reader;
    }



    @Bean
    public ItemWriter<Track> trackItemWriter() {
        return items -> {
            for (Track item : items) {
                Track track = trackRepository.findByKey(item.getPRODUCTINFO().getLANGUAGE().getAMWKEY());
                if (track == null) {
                    mongoTemplate.insert(item);
                    System.out.println(item.toString());
                }

            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Track, Track>chunk(10)
                .reader(trackItemReader("ingestion_sample_a.xml"))
                .writer(trackItemWriter())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Track, Track>chunk(10)
                .reader(trackItemReader("ingestion_sample_b.xml"))
                .writer(trackItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build();
    }
}
