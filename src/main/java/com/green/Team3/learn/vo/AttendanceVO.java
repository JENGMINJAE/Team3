package com.green.Team3.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.invoke.VarHandle;

@Getter
@Setter
@ToString
public class AttendanceVO {
    private int atdNum;
    private int stuNum;
    private String atdDate;
    private int atdtNum;
    private int classNum;
}
