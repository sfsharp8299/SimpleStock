package com.jpm.supersimple.instrument;


public class Symbol {
	
	private final String code;
	
	public Symbol(final String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Symbol symbol = (Symbol)obj;
		return symbol.code == code;
	}

	@Override
	public String toString() {
		return "Symbol [code=" + code + "]";
	}

}
