package com.green.Team3.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String memberId;
    private int typeNum;
    private int boardCnt;
}
