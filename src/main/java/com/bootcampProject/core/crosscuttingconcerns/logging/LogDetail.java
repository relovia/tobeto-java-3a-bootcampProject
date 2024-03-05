package com.bootcampProject.core.crosscuttingconcerns.logging;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "log_details")
public class LogDetail {
    @Id
    private ObjectId id;
    @Field("method_name")
    private String methodName;

    @Field("user")
    private String user;

    private List<LogParameter> logParameterList;
}
