package com.yangcheng.initial.entity.domain;

import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name="favour")
@Data
public class Favour implements Serializable {
//    收藏id
    private String id;
    private String EssayId;
    private String userId;
    @Serial
    private static final long serialVersionUID=1L;
}
