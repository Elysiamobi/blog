package com.yangcheng.initial.entity.domain;

import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Table(name="forward")
@Data
public class Forward implements Serializable {
    private String id;
    private String essayId;
    private String user_id;
    @Serial
    private static final long serialVersionUID=1L;

}
