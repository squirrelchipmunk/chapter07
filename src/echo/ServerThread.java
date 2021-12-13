package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;
	
	public ServerThread() {}
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		//메세지 받기용 스트림
		try {
			System.out.println("[클라이언트가 연결되었습니다.]");
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			//메세지 보내기 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);


			while(true) {
				//메세지 받기
				String str = br.readLine();
				if(str == null) {
					System.out.println("[클라이언트 종료키 입력]");
					break;
				}
				System.out.println("받은 메세지: "+str);
				//메세지 보내기
				bw.write(str);
				bw.newLine();
				bw.flush();
			}

			bw.close();
			br.close();
			socket.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
