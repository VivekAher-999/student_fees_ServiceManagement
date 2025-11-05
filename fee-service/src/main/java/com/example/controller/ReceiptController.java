
package com.example.controller;
import com.example.client.StudentClient;
import com.example.dto.Student;
import com.example.model.Receipt;
import com.example.repository.ReceiptRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptRepository repo;
    private final StudentClient client;

    public ReceiptController(ReceiptRepository repo, StudentClient client) {
        this.repo = repo;
        this.client = client;
    }

    @PostMapping
    @CircuitBreaker(name = "studentService", fallbackMethod = "fallbackCollectFee")
    public Receipt collectFee(@RequestBody Receipt r) {
        Student s = client.getStudentById(Long.parseLong(r.getStudentId()));
        r.setStudentName(s.getStudentName());
        r.setGrade(s.getGrade());
        return repo.save(r);
    }

    public Receipt fallbackCollectFee(Receipt r, Throwable t) {
        r.setStudentName("Fallback Student");
        r.setGrade("N/A");
        return repo.save(r);
    }

    @GetMapping
    public List<Receipt> getAll() {
        return repo.findAll();
    }
}
