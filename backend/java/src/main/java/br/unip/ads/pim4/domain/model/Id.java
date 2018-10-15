package br.unip.ads.pim4.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
// import javax.persistence.GeneratedValue;

// import org.hibernate.annotations.GenericGenerator;

@Embeddable
public class Id implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, updatable = false, length = 36)
	private String id;

	public Id() {
		// Persistence
	}

	public Id(String id) {
		this.id = id;
	}

	public String toString() {
		return id;
	}
	
	public static String proximo() {
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
