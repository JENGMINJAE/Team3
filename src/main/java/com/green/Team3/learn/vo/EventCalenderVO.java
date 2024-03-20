package com.green.Team3.learn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventCalenderVO {
    private int eventNum;
    private String title;
    private String start;
    private int eventTypeNum;
    private EventTypeVO eventTypeVO;
}
