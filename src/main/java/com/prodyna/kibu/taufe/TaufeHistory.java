package com.prodyna.kibu.taufe;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "taufe_history")
@NamedQuery(name = "TaufeHistoryByTaufe", query = "select th from TaufeHistory th where th.taufe = :t")
public class TaufeHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	public TaufeHistory() {
		super();
	}

	public TaufeHistory(Taufe t) {
		this.taufe = t;
		this.name = t.getName(); // Das, was wir historisiern
		this.added = new Date();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Taufe taufe;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	private Date added;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Taufe getTaufe() {
		return taufe;
	}

	public void setTaufe(Taufe taufe) {
		this.taufe = taufe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((taufe == null) ? 0 : taufe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaufeHistory other = (TaufeHistory) obj;
		if (added == null) {
			if (other.added != null)
				return false;
		} else if (!added.equals(other.added))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (taufe == null) {
			if (other.taufe != null)
				return false;
		} else if (!taufe.equals(other.taufe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaufeHistory [id=" + id + ", taufe=" + taufe + ", name=" + name
				+ ", added=" + added + "]";
	}

}
