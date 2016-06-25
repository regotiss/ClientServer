import java.io.*;
import java.net.*;
public class client
{
	public static void main(String[] args) throws Exception
	{
		Socket sock = new Socket("127.0.0.1", 6002);

		// reading from keyboard (keyRead object)
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

		// sending to client (pwrite object)
		OutputStream ostream = sock.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);

		// receiving from server ( receiveRead object)
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
		new Handler(receiveRead).start();
		String sendMessage; 
		while(true)
		{
			sendMessage = keyRead.readLine(); // keyboard reading
			pwrite.println(sendMessage); // sending to server
			pwrite.flush(); // flush the data
			
		} 
	}
	static class Handler extends Thread
	{
		BufferedReader receiveRead;
		String receiveMessage;
		Handler(BufferedReader rr){
			receiveRead=rr;
		}
		public void run(){

			try{
			while(true){
				if((receiveMessage = receiveRead.readLine()) != null || (receiveMessage = receiveRead.readLine())==" ") 
				{
					System.out.println(receiveMessage); // displaying at DOS prompt	
				} 	
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
