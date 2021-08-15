package com.maven.community.controller;

import com.maven.community.dto.GitHubUser;
import com.maven.community.dto.AccessTokenDto;
import com.maven.community.pojo.User;
import com.maven.community.provider.GitHubProvider;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-14 21:09
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;
    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response)
    {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        if(user != null)
        {
            //登录成功，写cookie和session
            User storeUser = new User();
            storeUser.setToken(UUID.randomUUID().toString());
            storeUser.setName(user.getName());
            storeUser.setAccountId(String.valueOf(user.getId()));
            storeUser.setGmtCreate(System.currentTimeMillis());
            storeUser.setGmtModified(storeUser.getGmtCreate());
            userService.storeUser(storeUser);
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }else{
            //登录不成功，重新登录
            return "redirect:/";
    }
    }
}
