package io.formulate.common.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public abstract class AbstractMongoClientConfig extends AbstractMongoClientConfiguration {
    protected static final String DB_NAME = "DB_NAME";
    protected static final String DB_URI = "DB_URI";

    protected abstract String getUri();

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(getUri()));
    }
}
