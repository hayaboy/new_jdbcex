package com.green.jdbcex.controller;

import com.green.jdbcex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class ToDoRegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register GET .......");
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        log.info("/todo/register POST .......");
       String title= req.getParameter("title");
        String dueDate= req.getParameter("dueDate");
        log.info("title : " +  title);
        log.info("dueDate : " +  dueDate);
        //여기는 화면단에서 넘어돈 데이터를 서비스 계층으로 보내야함, 데이터보내기 위한 객체 DTO이므로
        //DTO에 데이터를 담아야 함
        TodoDTO todoDTO= TodoDTO.builder().title(title).dueDate(LocalDate.parse(dueDate)).build();
    }
}
