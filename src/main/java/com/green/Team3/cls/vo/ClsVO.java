package com.green.Team3.cls.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ClsVO {
    private int classNum;
    private String className;
    private int classPayment;
    private int classPersonnel;
    private String classSdate;
    private String classEdate;
    private int teacherNum;
}
