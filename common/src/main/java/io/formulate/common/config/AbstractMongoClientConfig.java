package io.formulate.common.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import io.formulate.common.ws.converter.DateToLocalDateConverter;
import io.formulate.common.ws.converter.LocalDateToDateConverter;
import io.formulate.common.ws.converter.LocalTimeToStringConverter;
import io.formulate.common.ws.converter.StringToLocalTimeConverter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

public abstract class AbstractMongoClientConfig extends AbstractMongoClientConfiguration {
    protected static final String DB_NAME = "DB_NAME";
    protected static final String DB_URI = "DB_URI";

    protected abstract String getUri();

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(getUri()));
    }

    @Override
    protected void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter converterConfigurationAdapter) {
        converterConfigurationAdapter.registerConverter(new LocalDateToDateConverter());
        converterConfigurationAdapter.registerConverter(new DateToLocalDateConverter());
        converterConfigurationAdapter.registerConverter(new LocalTimeToStringConverter());
        converterConfigurationAdapter.registerConverter(new StringToLocalTimeConverter());
    }
}
