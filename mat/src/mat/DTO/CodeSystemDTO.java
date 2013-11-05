package mat.DTO;

import mat.client.codelist.HasListBox;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CodeSystemDTO implements IsSerializable, HasListBox {
   
	private String id;
	private String description;
	
	public CodeSystemDTO(){
	
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return id;
	}
	public String getItem() {
		return description;
	}

	@Override
	public int getSortOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
}
