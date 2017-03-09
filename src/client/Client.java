package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket=new Socket("169.254.2.33", 8888);
            //2.获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
        	PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
        	byte[] bt = new byte[100000];
            int a=1;
            while (a!=0){
            	pw.write(new String(bt));
            	pw.flush();
            	a++;
            	if (a%1000==0)
            		System.out.print(a);
            	if (a%1==0){
            		Thread.sleep(0);
            	}
            }
            socket.shutdownOutput();//关闭输出流
        	pw.close();
            os.close();
            //3.获取输入流，并读取服务器端的响应信息
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String info=null;
            while((info=br.readLine())!=null){
                System.out.println("我是客户端，服务器说："+info);
            }
            //4.关闭资源
            br.close();
            is.close();

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
