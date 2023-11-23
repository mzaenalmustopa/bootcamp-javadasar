package com.aronsoft.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupModel {
    private String id;
    private String groups;
    private String code;
    private String name;
    private Integer position;
    private Boolean active;
}
