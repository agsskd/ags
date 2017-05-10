package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_KOTA database table.
 * 
 */
@Entity
@Table(name="MST_KOTA")
@NamedQuery(name="MstKota.findAll", query="SELECT m FROM MstKota m")
public class MstKota implements Serializable {
	private static final long serialVersionUID = 1L;
	private String kodeKota;
	private String kodeProvinsi;
	private String namaKota;

	public MstKota() {
	}


	@Id
	@Column(name="KODE_KOTA")
	public String getKodeKota() {
		return this.kodeKota;
	}

	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}


	@Column(name="KODE_PROVINSI")
	public String getKodeProvinsi() {
		return this.kodeProvinsi;
	}

	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}


	@Column(name="NAMA_KOTA")
	public String getNamaKota() {
		return this.namaKota;
	}

	public void setNamaKota(String namaKota) {
		this.namaKota = namaKota;
	}

}