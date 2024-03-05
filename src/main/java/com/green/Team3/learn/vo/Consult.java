package com.green.Team3.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Consult {
    private int consultNum;
    private String consultContent;
    private String consultDate;
    private int stuNum;
    private int classNum;
}
