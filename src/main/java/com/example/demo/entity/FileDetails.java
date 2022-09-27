package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="FileDetails")
public class FileDetails {
	
	@Id
	@GeneratedValue
	private int cid;
	
//	@Column(columnDefinition = "TEXT")
//	private String pom;

	private String jarname;
	private String jartype;
	@Lob
	private byte[] jar;
	
	
	private String srcname;
	private String srctype;
	@Lob
	private byte[] src;
	
	
	private String docname;
	private String doctype;
	@Lob
	private byte[] doc;
	
	
	
	
	public FileDetails() {
	}



	

	
	

	public FileDetails(String jarname, String jartype, byte[] jar, String srcname, String srctype, byte[] src,
			String docname, String doctype, byte[] doc) {
		super();
		this.jarname = jarname;
		this.jartype = jartype;
		this.jar = jar;
		this.srcname = srcname;
		this.srctype = srctype;
		this.src = src;
		this.docname = docname;
		this.doctype = doctype;
		this.doc = doc;
	}








	public FileDetails(String srcname, String srctype, byte[] src, String docname, String doctype,
			byte[] doc) {
		
		this.srcname = srcname;
		this.srctype = srctype;
		this.src = src;
		this.docname = docname;
		this.doctype = doctype;
		this.doc = doc;
	}


	
	

	

	public FileDetails(String jarname, String jartype, byte[] jar,String docname, String doctype,
			byte[] doc,boolean status) {

		this.jarname = jarname;
		this.jartype = jartype;
		this.jar = jar;
		this.docname = docname;
		this.doctype = doctype;
		this.doc = doc;
	}




	public int getCid() {
		return cid;
	}




	public void setCid(int cid) {
		this.cid = cid;
	}





	public String getJarname() {
		return jarname;
	}




	public void setJarname(String jarname) {
		this.jarname = jarname;
	}




	public String getJartype() {
		return jartype;
	}




	public void setJartype(String jartype) {
		this.jartype = jartype;
	}




	public byte[] getJar() {
		return jar;
	}




	public void setJar(byte[] jar) {
		this.jar = jar;
	}




	public String getSrcname() {
		return srcname;
	}




	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}




	public String getSrctype() {
		return srctype;
	}




	public void setSrctype(String srctype) {
		this.srctype = srctype;
	}




	public byte[] getSrc() {
		return src;
	}




	public void setSrc(byte[] src) {
		this.src = src;
	}




	public String getDocname() {
		return docname;
	}




	public void setDocname(String docname) {
		this.docname = docname;
	}




	public String getDoctype() {
		return doctype;
	}




	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}




	public byte[] getDoc() {
		return doc;
	}




	public void setDoc(byte[] doc) {
		this.doc = doc;
	}
	
	
	
	
	
	
	
	

}
