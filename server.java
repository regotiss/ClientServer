import java.io.*;
import java.net.*;
public class server
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ssock = new ServerSocket(6002);

		Socket sock=ssock.accept();

		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));


		OutputStream ostream = sock.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);


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
