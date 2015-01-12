package com.trinary.survey

import java.util.regex.Matcher
import java.util.regex.Pattern

class SocketManager extends Thread {
	
	static Map<String, List<Socket>> clientSockets = new HashMap<String, List<Socket>>()
	
	ServerSocket socket
	int port
	boolean running = true
	Pattern pidPattern = Pattern.compile("notifyOnChange:[ ]*(.*)")
	
	SocketManager(int port) {
		this.port = port
	}
	
	public void kill() {
		running = false;
	}
	
	static public void broadcastEvent(String pid, String event) {
		def deadSockets = []
		println "BROADCASTING ${event} FOR ${pid}"
		List<Socket> sockets = clientSockets.get(pid)
		sockets.each { Socket client ->
			try {
				client.getOutputStream().write("${event}\n".bytes)
			} catch (Exception e) {
				println "Dead socket being removed"
				deadSockets.add(client)
			}
		}
		
		if (sockets && !deadSockets.isEmpty()) {
			sockets.removeAll(deadSockets)
		}
	}
	
	@Override
	public synchronized void start() {
		println "LISTENER STARTED"
		super.start();
	}

	@Override
	public void run() {
		socket = new ServerSocket(port)
		while(running) {
			Socket client = socket.accept()
			println "NEW USER CONNECTED"
		
			Thread.start {
				// Read in request
				BufferedReader bin = new BufferedReader(new InputStreamReader(client.inputStream))
				String line = bin.readLine()
				
				// Add socket mapping
				Matcher pidMatcher = pidPattern.matcher(line)
				if (pidMatcher.find()) {
					String pid = pidMatcher.group(1)
					if (!clientSockets.get(pid)) {
						clientSockets.put(pid, new ArrayList<Socket>())
					}
					
					clientSockets.get(pid).add(client)
					print "Added socket for pid ${pid}"
				}
			}
		}
	}
}
