package com.phatdo.resource_server.Configuration.Mongo;

import com.phatdo.resource_server.Converter.ZonedDatetime.ZonedDateTimeReadConverter;
import com.phatdo.resource_server.Converter.ZonedDatetime.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfig {
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()
        ));
    }
}
