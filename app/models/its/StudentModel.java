package models.its;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import models.users.*;


@Entity
public class StudentModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@OneToOne
	public StudentModelUnit studentModelUnitRoot;
	
	@OneToMany
	public List<StudentModelUnit> studentModelUnits;
	
	@ManyToOne
	public Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentModelUnit getStudentModelUnitRoot() {
		return studentModelUnitRoot;
	}

	public void setStudentModelUnitRoot(StudentModelUnit studentModelUnitRoot) {
		this.studentModelUnitRoot = studentModelUnitRoot;
	}

	public List<StudentModelUnit> getStudentModelUnits() {
		return studentModelUnits;
	}

	public void setStudentModelUnit(List<StudentModelUnit> studentModelUnits) {
		this.studentModelUnits = studentModelUnits;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	
}
