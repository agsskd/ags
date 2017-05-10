package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="MST_CUSTOMER")
@NamedQuery(name="MstCustomer.findAll", query="SELECT m FROM MstCustomer m")
public class MstCustomer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String kodeCustomer;
	private String alamatCustomer;
	private String emailCustomer;
	private String jenisKelamin;
	private String kodeKota;
	private String namaCustomer;

	public MstCustomer() {
	}


	@Id
	@Column(name="KODE_CUSTOMER")
	public String getKodeCustomer() {
		return this.kodeCustomer;
	}

	public void setKodeCustomer(String kodeCustomer) {
		this.kodeCustomer = kodeCustomer;
	}


	@Column(name="ALAMAT_CUSTOMER")
	public String getAlamatCustomer() {
		return this.alamatCustomer;
	}

	public void setAlamatCustomer(String alamatCustomer) {
		this.alamatCustomer = alamatCustomer;
	}


	@Column(name="EMAIL_CUSTOMER")
	public String getEmailCustomer() {
		return this.emailCustomer;
	}

	public void setEmailCustomer(String emailCustomer) {
		this.emailCustomer = emailCustomer;
	}


	@Column(name="JENIS_KELAMIN")
	public String getJenisKelamin() {
		return this.jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}


	@Column(name="KODE_KOTA")
	public String getKodeKota() {
		return this.kodeKota;
	}

	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}


	@Column(name="NAMA_CUSTOMER")
	public String getNamaCustomer() {
		return this.namaCustomer;
	}

	public void setNamaCustomer(String namaCustomer) {
		this.namaCustomer = namaCustomer;
	}

}