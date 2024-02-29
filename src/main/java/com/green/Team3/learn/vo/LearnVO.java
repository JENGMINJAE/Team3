package com.green.Team3.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LearnVO {
    private int learnNum;
    private String learnStatus;
    private String learnConsult;
    private String consultStatus;
    private int classNum;
    private int atdNum;
    private int hwNum;
}
