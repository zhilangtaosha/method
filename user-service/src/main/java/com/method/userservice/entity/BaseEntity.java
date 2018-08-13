package com.method.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {
    //    @BsonIgnore
//    @BsonId
    @Id
    private ObjectId id;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (id != null)
            map.put("_id", id);
        return map;
    }

}
