package com.sxau.soft.andsix;
public final class MyTextShow implements android.text.TextWatcher {
	private com.google.android.material.textfield.TextInputLayout father;
	private android.widget.EditText child;
	public MyTextShow(com.google.android.material.textfield.TextInputLayout _father, android.widget.EditText _child) {
		super();
		this.father = _father;
		this.child = _child;
	}
	public void init() {
		this.child.addTextChangedListener(this);
	}
	@Override public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
		this.father.setErrorEnabled(false);
	}
	@Override public void afterTextChanged(android.text.Editable s){}
}
