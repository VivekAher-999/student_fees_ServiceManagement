
package com.example.client;
import com.example.dto.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service", url = "http://localhost:8081", fallback = StudentClientFallback.class)
public interface StudentClient {
    @GetMapping("/students/{id}")
    Student getStudentById(@PathVariable("id") Long id);
}
