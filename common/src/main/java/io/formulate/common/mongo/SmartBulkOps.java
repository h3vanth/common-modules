package io.formulate.common.mongo;

import com.mongodb.bulk.BulkWriteError;
import com.mongodb.bulk.BulkWriteInsert;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.BulkWriteUpsert;
import org.springframework.data.mongodb.BulkOperationException;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SmartBulkOps implements BulkOperations {
    private final BulkOperations bulkOps;

    private int operationCount;

    public SmartBulkOps(MongoTemplate mongoTemplate, BulkMode mode, Class<?> clazz) {
        bulkOps = mongoTemplate.bulkOps(mode, clazz);
    }

    @Override
    public BulkOperations insert(Object documents) {
        operationCount++;
        return bulkOps.insert(documents);
    }

    @Override
    public BulkOperations insert(List<?> documents) {
        operationCount++;
        return bulkOps.insert(documents);
    }

    @Override
    public BulkOperations updateOne(Query query, UpdateDefinition update) {
        operationCount++;
        return bulkOps.updateOne(query, update);
    }

    @Override
    public BulkOperations updateOne(List<Pair<Query, UpdateDefinition>> updates) {
        operationCount++;
        return bulkOps.updateOne(updates);
    }

    @Override
    public BulkOperations updateMulti(Query query, UpdateDefinition update) {
        operationCount++;
        return bulkOps.updateMulti(query, update);
    }

    @Override
    public BulkOperations updateMulti(List<Pair<Query, UpdateDefinition>> updates) {
        operationCount++;
        return bulkOps.updateMulti(updates);
    }

    @Override
    public BulkOperations upsert(Query query, UpdateDefinition update) {
        operationCount++;
        return bulkOps.upsert(query, update);
    }

    @Override
    public BulkOperations upsert(List<Pair<Query, Update>> updates) {
        operationCount++;
        return bulkOps.upsert(updates);
    }

    @Override
    public BulkOperations remove(Query remove) {
        operationCount++;
        return bulkOps.remove(remove);
    }

    @Override
    public BulkOperations remove(List<Query> removes) {
        operationCount++;
        return bulkOps.remove(removes);
    }

    @Override
    public BulkOperations replaceOne(Query query, Object replacement, FindAndReplaceOptions options) {
        operationCount++;
        return bulkOps.replaceOne(query, replacement, options);
    }

    @Override
    public SmartBulkWriteResult execute() {
        return executeSmartly();
    }

    public SmartBulkWriteResult executeSmartly() {
        if (operationCount == 0) {
            return new SmartBulkWriteResult();
        }

        BulkWriteResult bulkWriteResult;
        List<BulkWriteError> errors = null;

        try {
            bulkWriteResult = bulkOps.execute();
        } catch (BulkOperationException e) {
            bulkWriteResult = e.getResult();
            errors = e.getErrors();
        }

        return new SmartBulkWriteResult(bulkWriteResult, errors);
    }

    public static <T> List<T> filterFailed(SmartBulkWriteResult smartBulkWriteResult, List<T> entities) {
        List<BulkWriteError> errors = smartBulkWriteResult.getErrors();

        if (errors == null || errors.isEmpty()) {
            return entities;
        }

        List<Integer> failureIndexes = errors.stream().map(BulkWriteError::getIndex).toList();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            if (!failureIndexes.contains(i)) {
                list.add(entities.get(i));
            }
        }
        return list;
    }

    public static class SmartBulkWriteResult extends BulkWriteResult {
        private boolean acknowledged;
        private int insertedCount;
        private int matchedCount;
        private int deletedCount;
        private int modifiedCount;
        private List<BulkWriteInsert> inserts;
        private List<BulkWriteUpsert> upserts;
        private List<BulkWriteError> errors;

        public SmartBulkWriteResult() {}

        public SmartBulkWriteResult(BulkWriteResult bulkWriteResult, List<BulkWriteError> errors) {
            acknowledged = bulkWriteResult.wasAcknowledged();
            insertedCount = bulkWriteResult.getInsertedCount();
            matchedCount = bulkWriteResult.getMatchedCount();
            deletedCount = bulkWriteResult.getDeletedCount();
            modifiedCount = bulkWriteResult.getModifiedCount();
            inserts = bulkWriteResult.getInserts();
            upserts = bulkWriteResult.getUpserts();
            this.errors = errors;
        }

        @Override
        public boolean wasAcknowledged() {
            return acknowledged;
        }

        @Override
        public int getInsertedCount() {
            return insertedCount;
        }

        @Override
        public int getMatchedCount() {
            return matchedCount;
        }

        @Override
        public int getDeletedCount() {
            return deletedCount;
        }

        @Override
        public int getModifiedCount() {
            return modifiedCount;
        }

        @Override
        public List<BulkWriteInsert> getInserts() {
            return inserts;
        }

        @Override
        public List<BulkWriteUpsert> getUpserts() {
            return upserts;
        }

        public List<BulkWriteError> getErrors() {
            return errors;
        }
    }
}
