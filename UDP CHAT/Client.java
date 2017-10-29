import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Client{
	JFrame frame;
	JPanel panel;
	//JTextField field1, field2;
	JTextArea area;
	JScrollPane pane;
	//JLabel label;
	JButton button;
	public static void main(String[] args) {
		Client u = new Client();
	}
	public Client(){
		frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		panel = new JPanel();
		panel.setLayout(null);
		//label = new JLabel("Desination IP:");
		//label.setBounds(10, 20, 100, 30);
		//panel.add(label);
		//field1 = new JTextField(20);
		//field1.setBounds(125, 25, 150, 20);
		//panel.add(field1);
		//label = new JLabel("Destination Port:");
		//label.setBounds(10, 50, 100, 30);
		//panel.add(label);
		//field2 = new JTextField(10);
		//field2.setBounds(125, 55, 100, 20);
		//panel.add(field2);
		area = new JTextArea();
		pane = new JScrollPane(area);
		pane.setBounds(10, 60, 365, 250);
		panel.add(pane);
		button = new JButton("Send");
		button.setBounds(235, 310, 75, 30);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new SendRequest();
			}
		});
		panel.add(button);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	public class SendRequest{
		SendRequest(){
			try{
				DatagramSocket socket;
				DatagramPacket packet;
				InetAddress address;
				socket = new DatagramSocket();
				String dip = "127.0.0.1";
				address = InetAddress.getByName(dip);
				String port = "8080";
				int pnum = Integer.parseInt(port);
				String mess = area.getText();
				byte message[] = mess.getBytes();
				packet = new DatagramPacket(message, message.length, address, pnum);
				socket.send(packet);
				area.setText("");
				socket.close();
			}
			catch(IOException io){}
		}
	}
}
