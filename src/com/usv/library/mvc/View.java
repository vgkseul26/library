package com.usv.library.mvc;

public interface View {

	void start() throws Exception;

	void end();

	public void print(String... args);
}
