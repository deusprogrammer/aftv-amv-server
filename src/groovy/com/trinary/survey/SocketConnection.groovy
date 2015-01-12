package com.trinary.survey

import grails.converters.JSON

class SocketConnection extends Thread {
	Socket socket
	boolean running = true
	
	SocketConnection(Socket socket) {
		this.socket = socket
	}
	
	public void kill() {
		running = false;
	}
	
	@Override
	public synchronized void start() {
		println("SOCKET CONNECTION STARTED")
		super.start();
	}
	
	@Override
	public void run() {
		socket.withStreams { input, output ->
			// Get initial request
			/*
			String[] lines = input.readLines()
			println (lines)
			*/
			
			// Send initial response
			output.write((new DummyObject() as JSON).toString().bytes)
			println("WRITING")
			output.write((new DummyObject() as JSON).toString().bytes)
			println("WRITING")
			
			while (running) {
				// When triggered, fire send a new entry downstream
				//socket.outputStream.write("");
			}
		}
	}
}
