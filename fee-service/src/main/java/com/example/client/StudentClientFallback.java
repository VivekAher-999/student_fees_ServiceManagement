
package com.example.client;
import com.example.dto.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentClientFallback implements StudentClient {
    @Override
    public Student getStudentById(Long id) {
        Student s = new Student();
        s.setStudentId("N/A");
        s.setStudentName("Fallback Student");
        s.setGrade("N/A");
        s.setMobileNumber("N/A");
        s.setSchoolName("Unavailable");
        return s;
    }
}
