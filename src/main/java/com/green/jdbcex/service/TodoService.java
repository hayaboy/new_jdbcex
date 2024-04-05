package com.green.jdbcex.service;

import com.green.jdbcex.dao.TodoDAO;
import com.green.jdbcex.domain.TodoVO;
import com.green.jdbcex.dto.TodoDTO;
import com.green.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


@Log4j2
public enum TodoService {

    INSTANCE;
    private TodoDAO todoDAO;
    private ModelMapper modelMapper;


    TodoService() {
        this.todoDAO = new TodoDAO();
        this.modelMapper=MapperUtil.INSTANCE.get();

    }

    //글 등록
    //서비스 계층에서 DTO를 매개변수로 해서 영속 계충으로 등록(연결)
   public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO=modelMapper.map(todoDTO, TodoVO.class);
//       System.out.println("todoVO: " + todoVO);
       log.info("todoVO: " + todoVO);

       todoDAO.insert(todoVO);
    }


    //글 목록 조회
    public List<TodoDTO> listAll()throws Exception {
        List<TodoVO> voList=todoDAO.selectAll();
        log.info(voList);

        //모델 mapper를 이용한 DTO로 변경
        //vo를 dto로 변경  modelMapper.map(vo, TodoDTO.class);

        //컬렉션이므로 스트림을 이용해서 map 메소드 이용
        List<TodoDTO> dtoList= voList.stream().map((vo)->{return modelMapper.map(vo, TodoDTO.class);})
                .collect(Collectors.toList());
        return dtoList;
    }

    //글 하나 조회
    public TodoDTO get(Long tno)throws Exception {
        log.info("tno: " + tno);

        TodoVO todoVO=todoDAO.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }


    //글 하나 수정
    public void modify(TodoDTO todoDTO)throws Exception {

        log.info("todoDTO: " + todoDTO );

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoDAO.update(todoVO);

    }



    //글 하나 삭제
    public void remove(Long tno)throws Exception {

        log.info("tno: " + tno );



        todoDAO.delete(tno);

    }

}
