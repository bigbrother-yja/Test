package com.gx.yc.analysisFeatureInfluence;

import java.util.ArrayList;

import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.soa.internal.client.TccsTransport;

public class InfluenceBean {
	
	private TCComponentBOMLine bomline = null;
	
	private ArrayList<TCComponent> supplierCode_list = null;

	public TCComponentBOMLine getBomline() {
		return bomline;
	}

	public void setBomline(TCComponentBOMLine bomline) {
		this.bomline = bomline;
	}

	public ArrayList<TCComponent> getSupplierCode_list() {
		return supplierCode_list;
	}

	public void setSupplierCode_list(ArrayList<TCComponent> supplierCode_list) {
		this.supplierCode_list = supplierCode_list;
	}

	
	

}
