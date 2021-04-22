package communication.cmm.controller;

import communication.cmm.dto.AccessTokenDTO;
import communication.cmm.dto.GithubUser;
import communication.cmm.model.User;
import communication.cmm.provider.GithubProvider;
import communication.cmm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String token=githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user=githubProvider.getUser(token);
        if(user!=null){
            User user1=new User();
            user1.setToken(UUID.randomUUID().toString());
            user1.setName(user.getName());
            user1.setAccountId(String.valueOf(user.getId()));
            user1.setQmtCreate(System.currentTimeMillis());
            user1.setQmtModified(user1.getQmtCreate());
            userService.insert(user1);
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }
}
