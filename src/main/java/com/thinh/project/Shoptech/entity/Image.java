package com.thinh.project.Shoptech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="image")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String url;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;
//	@Column(name = "name")
//	private String name;

//	@Column(name = "type")
//	private String type;
//	
//	@Column(name = "picByte", length = 1000)
//	private byte[] picByte;
//	
//	public Image() {
//		super();
//	}
//
//	public Image(String name, String type, byte[] picByte) {
//		this.name = name;
//		this.type = type;
//		this.picByte = picByte;
//	}
	
	
	
	
}
