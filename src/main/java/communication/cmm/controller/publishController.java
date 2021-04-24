package communication.cmm.controller;

import communication.cmm.model.Question;
import communication.cmm.model.User;
import communication.cmm.service.QuestionService;
import communication.cmm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String publish1(@RequestParam(name="title",required = false) String title,
                           @RequestParam(name="description",required=false) String description,
                           @RequestParam(name="description",required = false) String tag,
                           HttpServletRequest request,
                           Model model
                           ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title ==null||title==""){
            model.addAttribute("error","标题信息不能为空");
            return "publish";
        }
        if(description ==null||description==""){
            model.addAttribute("error","内容信息不能为空");
            return "publish";
        }
        if(tag ==null||tag==""){
            model.addAttribute("error","标签信息不能为空");
            return "publish";
        }
        User user=null;
        Cookie[] cookies= request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                     user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if(user==null){
            model.addAttribute("error","请先登录");
            return "publish";
        }

            Question question=new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setTag(tag);
            question.setCreator(user.getId());
            questionService.create(question);

            return "redirect:/";


    }
}
