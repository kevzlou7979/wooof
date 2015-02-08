package gwt.material.design.demo.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialDemo extends Composite {

	private static MaterialDemoUiBinder uiBinder = GWT.create(MaterialDemoUiBinder.class);

	interface MaterialDemoUiBinder extends UiBinder<Widget, MaterialDemo> {
	}

	public MaterialDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
	}

	
	public static native void initSpy()/*-{
		$wmd.jQuery(document).ready(function(){
		    $wmd.jQuery('.scrollspy').scrollSpy();
		  });
	}-*/;
	
	
}
