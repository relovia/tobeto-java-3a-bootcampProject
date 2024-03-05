package com.bootcampProject.core.crosscuttingconcerns.logging.configurations.mongodb;

import com.bootcampProject.core.crosscuttingconcerns.logging.LogDetail;
import com.bootcampProject.core.crosscuttingconcerns.logging.LogParameter;
import com.bootcampProject.core.crosscuttingconcerns.logging.LoggerServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDbLogger extends LoggerServiceBase {
    private MongoTemplate mongoTemplate;

    @Autowired
    public MongoDbLogger(MongoTemplate mongoTemplate) {
        super(MongoDbLogger.class);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void log(String methodName, List<LogParameter> logParameterList, String username) {
        LogDetail logDetail = new LogDetail();
        logDetail.setMethodName(methodName);
        logDetail.setLogParameterList(logParameterList);
        logDetail.setUser(username);
        info(logDetail.toString());
        mongoTemplate.save(logDetail);
    }
}
