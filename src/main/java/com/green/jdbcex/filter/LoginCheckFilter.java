package com.green.jdbcex.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//필터 적용시 @WebFilter추가, 특정 경로를 지정해서 해당 경로의 요청에 대해서 doFilter()를 실행, todo라는 경로를 통해서는 중요한 자료가 있어서 로그인 여부를 체크하도록 할 수 있다.
@WebFilter(urlPatterns = {"/todo/*"})  // /todo/...   로 시작하는 모든 경로에 대해서 필터링을 시도
@Log4j2
public class LoginCheckFilter implements Filter {

    //필터링이 필요한 로직을 구현
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Login check filter....");



        //ServletRequest의 하위인 HttpServletRequest의 객체에서 세션을 얻어와야함

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession httpSession= httpServletRequest.getSession();
        //세션에서 얻어온 로그인 정보가 없을 경우에는 로그인 화면으로 가도록

        if(httpSession.getAttribute("loginInfo")==null){
            httpServletResponse.sendRedirect("/jdbcex/login");
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
