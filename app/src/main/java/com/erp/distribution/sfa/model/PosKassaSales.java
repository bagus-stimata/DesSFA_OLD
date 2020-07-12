package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.*;


 
@Entity(tableName="posKassaSales")
public class PosKassaSales {

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;

	private Date trDate=new Date();

	//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private boolean kassaStatusOpen=false;
	
	private String description="";
	
	private Date timeOpen=new Date();
	private Date timeClose=new Date();

	private String userOpen="";
	private String userClose="";
	
	private Double totalSales=0.0;
	

	private Integer modalAwal100Ribu =0;
	private Integer modalAwal50Ribu =0;
	private Integer modalAwal20Ribu =0;
	private Integer modalAwal10Ribu =0;
	private Integer modalAwal5Ribu =0;
	private Integer modalAwal2Ribu =0;
	private Integer modalAwal1Ribu =0;
	private Integer modalAwal500 =0;
	private Integer modalAwal200 =0;
	private Integer modalAwal100 =0;
        
	private Double modalAwalTotal =0.0;

	private Integer closing100Ribu =0;
	private Integer closing50Ribu =0;
	private Integer closing20Ribu =0;
	private Integer closing10Ribu =0;
	private Integer closing5Ribu =0;
	private Integer closing2Ribu =0;
	private Integer closing1Ribu =0;
	private Integer closing500 =0;
	private Integer closing200 =0;
	private Integer closing100 =0;
	
	private Double closingTotal =0.0;


//	@ManyToOne
//	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID")
//	private FSalesman fsalesmanBean;
	private Integer fsalesmanBean = 0;



	
}