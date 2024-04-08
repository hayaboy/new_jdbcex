package com.green.jdbcex.controller;

import com.green.jdbcex.dto.TodoDTO;
import com.green.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController  extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;



    //
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tno=req.getParameter("tno");
        log.info("tno:" + tno);

        //Long.parseLong(tno);
        try {
            TodoDTO todoDTO= todoService.get(Long.parseLong(tno));
            //모델 담기
            req.setAttribute("dto", todoDTO);

//            log.info("요청시 가져온 쿠키들 : " + req.getCookies().toString());
//            Cookie cookies[]=req.getCookies();
//            for( Cookie cookie: cookies ){
//                log.info("쿠키 :" + cookie.getName());
//            }

            //최근 본 글 하루동안 저장하기위한 사용자 정의 쿠키 구현 후 찾음
            // 'viewTodos'라는 이름의 쿠키를 찾고, 쿠키의 내용물을 검사한 후에 만일 조회한 적이 없는 번호라면 쿠키의
            //내용물을 갱신해서 브라우저로 보내주는 것, 쿠키를 변경할 때는 다시 경로나 유효시간을 세팅해야 하는 점을 유의해야함
            Cookie viewTodoCookie  =  findCookie(req.getCookies(), "viewTodos");
            log.info("찾은 쿠키의 이름 :" + viewTodoCookie.getName());
            log.info("찾은 쿠키의 값 :" + "[" +viewTodoCookie.getValue() + " ]");
//
            String todoListStr=viewTodoCookie.getValue();

            log.info("indexOf의 결과 : "  +todoListStr.indexOf(tno+"-"));

            boolean exist=false;
            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0){
                exist=true;
            }
            log.info("exist : "+exist);

            if(!exist){
                todoListStr += tno+"-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

            log.info("todoListStr : "  + todoListStr);

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            log.info("글 조회시 예외 발생");
        }
    }


    //쿠키가 여러개이므로 찾아야 함
    private Cookie findCookie(Cookie[] cookies, String cookieName){
        Cookie targetCookie=null;
        if(cookies !=null &&  cookies.length >0 ){
            for( Cookie cookie: cookies ){
                if(cookie.getName().equals(cookieName)){
                    targetCookie=cookie;
                    break;
                }

            }
        }

        if(targetCookie==null){
            targetCookie= new Cookie(cookieName,"");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }



        return targetCookie;
    }


}
