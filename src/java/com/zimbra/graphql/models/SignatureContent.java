package com.zimbra.graphql.models;

public class SignatureContent {

    private String type;
    private String _content;
    
	public SignatureContent(String type, String _content) {
		this.type = type;
		this._content = _content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}
}
