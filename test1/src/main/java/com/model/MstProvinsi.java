package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_PROVINSI database table.
 * 
 */
@Entity
@Table(name="MST_PROVINSI")
@NamedQuery(name="MstProvinsi.findAll", query="SELECT m FROM MstProvinsi m")
public class MstProvinsi implements Serializable {
	private static final long serialVersionUID = 1L;
	private String kodeProvinsi;
	private String namaProvinsi;

	public MstProvinsi() {
	}


	@Id
	@Column(name="KODE_PROVINSI")
	public String getKodeProvinsi() {
		return this.kodeProvinsi;
	}

	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}


	@Column(name="NAMA_PROVINSI")
	public String getNamaProvinsi() {
		return this.namaProvinsi;
	}

	public void setNamaProvinsi(String namaProvinsi) {
		this.namaProvinsi = namaProvinsi;
	}

}