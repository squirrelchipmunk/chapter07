package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();	
		System.out.println("<클라이언트 시작>");
		System.out.println("====================================");
		System.out.println("[서버에 연결을 요청합니다.]");
		
		socket.connect(new InetSocketAddress("192.168.0.219", 10001));
//		socket.connect(new InetSocketAddress("192.168.0.56", 10001));
		// 소켓에서 인풋스트림 아웃풋스트림 얻어온다
		System.out.println("[서버에 연결되었습니다.]");
		
		//메세지 보내기 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//메세지 받기 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 키보드 입력용
		Scanner sc = new Scanner(System.in);
		
		//
		BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		
		while(true) {
			//메세지 보내기
//			String str = sc.nextLine();
			String str = sbr.readLine();
			if("/q".equals(str)) { // 널포인터 예외 방지
				break;
			}
			bw.write("[이원준] "+str);
			bw.newLine();
			bw.flush();

			//메세지 받기
			String reMsg = br.readLine();
			System.out.println("server: ["+reMsg+"]");
		}
		
		System.out.println("============================");
//		System.out.println("<클라이언트 종료>");
		BufferedWriter pbw = new BufferedWriter(new OutputStreamWriter(System.out,"UTF-8"));
		pbw.write("<클라이언트 종료>");
		pbw.newLine();
		pbw.flush();
		pbw.close();
		
		sc.close();
		br.close();
		bw.close();
		socket.close();
	}

}
