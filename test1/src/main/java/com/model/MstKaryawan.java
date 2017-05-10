package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_KARYAWAN database table.
 * 
 */
@Entity
@Table(name="MST_KARYAWAN")
@NamedQuery(name="MstKaryawan.findAll", query="SELECT m FROM MstKaryawan m")
public class MstKaryawan implements Serializable {
	private static final long serialVersionUID = 1L;
	private String kodeKaryawan;
	private String namaKaryawan;
	private String password;
	private String username;

	public MstKaryawan() {
	}


	@Id
	@Column(name="KODE_KARYAWAN")
	public String getKodeKaryawan() {
		return this.kodeKaryawan;
	}

	public void setKodeKaryawan(String kodeKaryawan) {
		this.kodeKaryawan = kodeKaryawan;
	}


	@Column(name="NAMA_KARYAWAN")
	public String getNamaKaryawan() {
		return this.namaKaryawan;
	}

	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}