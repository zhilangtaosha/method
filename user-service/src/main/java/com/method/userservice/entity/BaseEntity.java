package com.method.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity {
    @Id
    private String id;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (!(id == null))
            map.put("_id", id);

        return map;
    }

}
