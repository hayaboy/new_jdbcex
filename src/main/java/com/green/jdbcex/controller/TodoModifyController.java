package com.green.jdbcex.controller;

import com.green.jdbcex.dto.TodoDTO;
import com.green.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet(name = "todoModifyController", value = "/todo/modify")
@Log4j2
public class TodoModifyController  extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoDTO todoDTO = todoService.get(tno);
            req.setAttribute("dto", todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("수정화면으로 가기위해 번호 가져오는 중 예외");
            //throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/modify POST...");

        String finishedStr = req.getParameter("finished");
        log.info("finishedStr : "  + finishedStr);
        log.info(req.getParameter("dueDate"));

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER ))
                .finished( finishedStr !=null && finishedStr.equals("on")  )
                .build();


        log.info(todoDTO);
        try {
            todoService.modify(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/jdbcex/todo/list");

    }






    }

