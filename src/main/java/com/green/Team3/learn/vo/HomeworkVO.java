package com.green.Team3.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeworkVO {
    private int hwNum;
    private String hwStatus;
    private String hwName;
    private String hwSdate;
    private String hwEdate;
    private int classNum;
}