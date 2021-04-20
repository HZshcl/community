package communication.cmm.controller;

import communication.cmm.dto.AccessTokenDTO;
import communication.cmm.dto.GithubUser;
import communication.cmm.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;


    private String clientId="0bdc6aa66b98c5b27cb7";


    private String clientSecret="f47ac7542f37a7b4b97339f60856c2322fbcba25";


    private String redirectUri="http://localhost:9090/callback";

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String token=githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user=githubProvider.getUser(token);
        System.out.println(user.getName());
        System.out.println(user.getBio());
        System.out.println(user.getId());
        return "index";
    }
}
