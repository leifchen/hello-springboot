package com.chen.controller;

import com.chen.model.Student;
import com.chen.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * studentController
 * <p>
 * @Author LeifChen
 * @Date 2020-04-22
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public boolean insert(@RequestBody Student student) {
        log.debug("开始新增...");
        return studentService.insert(student);
    }

    @PutMapping("/student")
    public boolean update(@RequestBody Student student) {
        log.debug("开始更新...");
        return studentService.update(student);
    }

    @DeleteMapping("/student")
    public boolean delete(@RequestBody Student student) {
        log.debug("开始删除...");
        return studentService.delete(student);
    }

    @GetMapping("/student")
    public List<Student> findBystudent(Student student) {
        log.debug("开始查询...");
        return studentService.findByListEntity(student);
    }
}
