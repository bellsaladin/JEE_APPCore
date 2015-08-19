package com.seosoft.erp.model.system;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import com.seosoft.erp.model.entity.Ecole;

@Entity(name = "_removejournal")
public class RemoveJournal implements Serializable {
	private static final long serialVersionUID = 8484336044220671659L;

	// ============================== Attributs ==============================
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Length(max = 30)
	private String tableName;

	@Length(max = 15)
	private String rowId;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "ecole_id")
	private Ecole ecole;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime removeDate;

	// =========================== getter et setter ==========================
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getRowId() {
		return rowId;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	public Ecole getEcole() {
		return ecole;
	}

	public void setRemoveDate(DateTime removeDate) {
		this.removeDate = removeDate;
	}

	public DateTime getRemoveDate() {
		return removeDate;
	}

}
