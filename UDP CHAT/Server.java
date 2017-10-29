import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Server{
	JFrame frame;
	JPanel panel;
	JButton button1,button2;
	JTextArea area;
	JScrollPane pane;
	Thread thread;
	DatagramSocket socket;

	public static void main(String[] args) {
		Server u = new Server();
	}
	public Server(){
		frame = new JFrame("Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		panel = new JPanel();
		panel.setLayout(null);
		area = new JTextArea();
		button1 = new JButton("Start");
		button1.setBounds(210, 10, 75, 40);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new StartThread();
			}
		});
		panel.add(button1);
		button2 = new JButton("Stop");
		button2.setBounds(300, 10, 75, 40);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent ae){
				thread.interrupted();
				socket.close();
				area.append("Server is stopped\n");
				button1.setEnabled(true);
				button2.setEnabled(false);
			}
		});
		button2.setEnabled(false);
		panel.add(button2);
		pane = new JScrollPane(area);
		pane.setBounds(10, 60, 365, 250);
		panel.add(pane);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	public class StartThread implements Runnable{
		StartThread(){
			thread = new Thread(this);
			thread.start();
			button1.setEnabled(false);
			button2.setEnabled(true);
		}
	public void run(){
			try{
				byte[] buffer = new byte[1024];
				int port = 8080;
				try{
				socket = new DatagramSocket(port);
				area.append("Server is started\n");
				while(true){
				try{
					
					//Receive request from client
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length );
					socket.receive(packet);
					InetAddress client = packet.getAddress();
					int client_port = packet.getPort();
					area.append(" Received "+new String(buffer)+"\n");
					
				}
				catch(UnknownHostException ue){}
				}
				}
				catch(java.net.BindException b){}
			}
			catch (IOException e){
				System.err.println(e);
			}
		}
	}
}
