/**
 * 
 */
package models.curriculum.question;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author priscylla
 *
 */
@Entity
@Table(name = "Logexp")
public class Logexp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long idStudent;
	private String idTurma;
	private Long idQuestion;
	private String topic;
	private String solution;
	private String idDica;
	private boolean desistiu;
	private Calendar horario;
	private boolean correto;
	private String model;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the idStudent
	 */
	public Long getIdStudent() {
		return idStudent;
	}
	/**
	 * @param idStudent the idStudent to set
	 */
	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}
	/**
	 * @return the idTurma
	 */
	public String getIdTurma() {
		return idTurma;
	}
	/**
	 * @param idTurma the idTurma to set
	 */
	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}
	/**
	 * @return the idQuestion
	 */
	public Long getIdQuestion() {
		return idQuestion;
	}
	/**
	 * @param idQuestion the idQuestion to set
	 */
	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	/**
	 * @return the idDica
	 */
	public String getIdDica() {
		return idDica;
	}
	/**
	 * @param idDica the idDica to set
	 */
	public void setIdDica(String idDica) {
		this.idDica = idDica;
	}
	/**
	 * @return the desistiu
	 */
	public boolean isDesistiu() {
		return desistiu;
	}
	/**
	 * @param desistiu the desistiu to set
	 */
	public void setDesistiu(boolean desistiu) {
		this.desistiu = desistiu;
	}
	/**
	 * @return the horario
	 */
	public Calendar getHorario() {
		return horario;
	}
	/**
	 * @param horario the horario to set
	 */
	public void setHorario(Calendar horario) {
		this.horario = horario;
	}
	/**
	 * @return the correto
	 */
	public boolean isCorreto() {
		return correto;
	}
	/**
	 * @param correto the correto to set
	 */
	public void setCorreto(boolean correto) {
		this.correto = correto;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

}
