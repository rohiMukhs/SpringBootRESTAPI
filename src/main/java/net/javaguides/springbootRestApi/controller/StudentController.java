package net.javaguides.springbootRestApi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootRestApi.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {
	
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent() {
		
		Student student = new Student(12,"Rahul","Bose");
		
		//return new ResponseEntity<>(student,HttpStatus.OK);
		//OR, 
		//return ResponseEntity.ok(student);
		
		return ResponseEntity.ok()
							 .header("custom-header", "rohit")
							 .body(student);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getStudents(){
		
		List<Student> students = new ArrayList<>();
		
		students.add(new Student(10,"Ramesh","Powar"));
		students.add(new Student(11,"Suresh","Singh"));
		students.add(new Student(12,"Mukesh","Powar"));
		students.add(new Student(13,"Dharmesh","Das"));
		students.add(new Student(14,"Teja","Reddy"));
		
		return ResponseEntity.ok(students);

	}
	
	
	// http://localhost:8080/students/14/Teja/Reddy
	@GetMapping("/{id}/{fname}/{lname}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
									   @PathVariable("fname") String firstName,
									   @PathVariable("lname") String lastName) {
		
		Student student = new Student(studentId, firstName, lastName);
		return ResponseEntity.ok(student);
		
	}
	
	// http://localhost:8080/students/query?id=14&firstName=Teja&lastName=Reddy
	@GetMapping("/query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
										  @RequestParam String firstName,
										  @RequestParam String lastName) {
		Student student = new Student(id, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/create")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return ResponseEntity.ok(student);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		System.out.println(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
	
	

}
