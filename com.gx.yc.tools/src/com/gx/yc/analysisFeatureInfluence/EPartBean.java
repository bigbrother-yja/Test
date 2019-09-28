package com.gx.yc.analysisFeatureInfluence;


public class EPartBean {
    private Long id; 				// 唯一识别码，在数据库里常为自动递增的ID列
    private String column_1; 	
    private String column_2; 	
    private String column_3; 	  	
    private String column_4; 	
    private String column_5;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColumn_1() {
		return column_1;
	}
	public void setColumn_1(String column_1) {
		this.column_1 = column_1;
	}
	public String getColumn_2() {
		return column_2;
	}
	public void setColumn_2(String column_2) {
		this.column_2 = column_2;
	}
	public String getColumn_3() {
		return column_3;
	}
	public void setColumn_3(String column_3) {
		this.column_3 = column_3;
	}
	public String getColumn_4() {
		return column_4;
	}
	public void setColumn_4(String column_4) {
		this.column_4 = column_4;
	}
	public String getColumn_5() {
		return column_5;
	}
	public void setColumn_5(String column_5) {
		this.column_5 = column_5;
	} 
    

}