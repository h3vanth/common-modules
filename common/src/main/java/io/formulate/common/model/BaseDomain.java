package io.formulate.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public abstract class BaseDomain {
    public static final String ID = "id";

    @Id
    protected String id;
}
