package com.green.Team3;

import com.green.Team3.admin.service.AdminService;
import com.green.Team3.board.service.BoardService;
import com.green.Team3.board.service.BoardServiceImpl;
import com.green.Team3.board.vo.BoardTypeVO;
import com.green.Team3.learn.service.CalenderServiceImpl;
import com.green.Team3.learn.service.ConsultServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Resource(name = "consultService")
    private ConsultServiceImpl consultService;
    @Resource(name = "calenderService")
    private CalenderServiceImpl calenderService;
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "adminService")
    private AdminService adminService;
    @GetMapping("/")
    public String firstPage(@RequestParam(value = "errorMsg",required = false,defaultValue = "success")String errorMsg, Model model
                            , HttpSession session) {
        model.addAttribute("errorMsg",errorMsg);
        consultService.autoDeleteConsult();
        calenderService.autoDeleteCalender();
        adminService.updateClassEnter();
        List<BoardTypeVO> boardType = boardService.selectType();
        session.setAttribute("boardTypes", boardType);

        return "content/member/firstPage";
    }
}