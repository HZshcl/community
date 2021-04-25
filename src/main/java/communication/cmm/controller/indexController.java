package communication.cmm.controller;

import com.github.pagehelper.PageInfo;
import communication.cmm.dto.QuestionDTO;
import communication.cmm.model.Question;
import communication.cmm.model.User;
import communication.cmm.service.QuestionService;
import communication.cmm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                        @RequestParam(name="size",required = true,defaultValue = "4") Integer size
    ){

        if(page<=0){
            page=1;
        }

        if(size!=4){
            size=4;
        }
        Cookie[] cookies= request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<Question> question = questionService.findAllBypage(page,size);
        model.addAttribute("questions",question);
        PageInfo pageInfo=new PageInfo(question);

        model.addAttribute("questionPage",pageInfo);

       // 实现分业功能

        return "index" ;
    }

}
