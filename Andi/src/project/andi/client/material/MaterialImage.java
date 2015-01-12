package project.andi.client.material;

import com.google.gwt.user.client.ui.Image;

public class MaterialImage extends Image{

	public MaterialImage() {
		this.addStyleName("materialboxed");
	}
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		onInitMaterialDesign();
		
	}
	
	public static native void onInitMaterialDesign()/*-{
		$wnd.jQuery(document).ready(function(){
	    	$wnd.jQuery('.materialboxed').materialbox();
	    });
	}-*/;
}
