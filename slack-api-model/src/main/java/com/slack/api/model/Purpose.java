package com.slack.api.model;

import lombok.Data;

@Data
public class Purpose {
    private String value;
    private String creator;
    private Integer lastSet;
}
