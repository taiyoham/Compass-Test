package com.example.springboot.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.springboot.model.Students;
import com.example.springboot.repository.StudentsRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RestController
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/students")
   public Map<String, Object> getStudents(
        @RequestParam("facilitator_id") int facilitatorId,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "limit", defaultValue = "5") int limit,
        @RequestParam(value = "sort", defaultValue = "id") String sort,
        @RequestParam(value = "order", defaultValue = "asc") String order,
        @RequestParam Map<String, String> params
   ) {

        // Pageable pageable = new PageRequest(page, limit);

        Sort sortOrder = null;
        if("asc".equals(order)) {
            sortOrder = Sort.by(
                Sort.Order.asc(sort)
            );

        }else if("desc".equals(order)) {
            sortOrder = Sort.by(
                Sort.Order.desc(sort)
            );

        }else {
            sortOrder = Sort.by(
                Sort.Order.asc(sort)
            );
        }

        PageRequest pageRequest = PageRequest.of(page-1, limit, sortOrder);

        Page<Students> studentsPageLimit = studentsRepository.findAllByTeacherId(facilitatorId, pageRequest);

        // 学生データを取得する
        List<Students> students = studentsPageLimit.getContent();
        // List<Students> students = studentsRepository.findAllByTeacherId(facilitatorId);
    
        // 学生リストを作成
        List<Map<String, Object>> studentList = new ArrayList<>();
        for(var s:students) {

            // サンプルの学生データを作成
            Map<String, Object> student = new LinkedHashMap<>();
            student.put("id", s.getId());
            student.put("name", s.getName());
            student.put("loginId", s.getLoginId());

            Map<String, Object> classroom = new LinkedHashMap<>();
            var c = s.getClasses();
            classroom.put("id", c.getId());
            classroom.put("name", c.getName());

            student.put("classroom", classroom);

            studentList.add(student);
        }
 
        // レスポンスを構築
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("students", studentList);
        response.put("totalCount", studentList.size());


        return response;
    }

}
