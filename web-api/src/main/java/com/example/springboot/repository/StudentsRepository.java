package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.model.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    // ここにカスタムのデータ取得メソッドを定義することもできます
    Page<Students> findAllByTeacherId(int teacherId, Pageable pageable);
    List<Students> findAllByTeacherId(int teacherId);
}
