/**
 * 
 */
package models.curriculum.question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;

/**
 * @author priscylla
 *
 */
@Entity
@Table(name = "Question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Required
	private String enunciation;
	
	@Required
	private String topic;
	
	private String solution;
	
	private String dica1;
	private String dica2;
	private String dica3;
	private String dica4;
	private String dica5;
	
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
	 * @return the enunciation
	 */
	public String getEnunciation() {
		return enunciation;
	}
	/**
	 * @param enunciation the enunciation to set
	 */
	public void setEnunciation(String enunciation) {
		this.enunciation = enunciation;
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
	 * @return the dica1
	 */
	public String getDica1() {
		return dica1;
	}
	/**
	 * @param dica1 the dica1 to set
	 */
	public void setDica1(String dica1) {
		this.dica1 = dica1;
	}
	/**
	 * @return the dica2
	 */
	public String getDica2() {
		return dica2;
	}
	/**
	 * @param dica2 the dica2 to set
	 */
	public void setDica2(String dica2) {
		this.dica2 = dica2;
	}
	/**
	 * @return the dica3
	 */
	public String getDica3() {
		return dica3;
	}
	/**
	 * @param dica3 the dica3 to set
	 */
	public void setDica3(String dica3) {
		this.dica3 = dica3;
	}
	/**
	 * @return the dica4
	 */
	public String getDica4() {
		return dica4;
	}
	/**
	 * @param dica4 the dica4 to set
	 */
	public void setDica4(String dica4) {
		this.dica4 = dica4;
	}
	/**
	 * @return the dica5
	 */
	public String getDica5() {
		return dica5;
	}
	/**
	 * @param dica5 the dica5 to set
	 */
	public void setDica5(String dica5) {
		this.dica5 = dica5;
	}

}