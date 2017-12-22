package com.feed.ecp.common.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckcodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private int number; //显示多少个字符
	private String codes; //有哪些字符可供选择
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		width = Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
		number = Integer.parseInt(config.getInitParameter("number"));
		codes = config.getInitParameter("codes");
	}
	
	Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		request.getSession().removeAttribute("codes");
		// 创建一张图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		//生成随机类
		Random random = new Random();
		
		// 设定背景色
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);

		//设定字体
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		
		// 画黑边框
		//g.setColor(Color.BLACK);
		//g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160,200));
		for (int i=0;i<155;i++){
			int x = random.nextInt(width);
			int y = random.nextInt(height);
	        int xl = random.nextInt(12);
	        int yl = random.nextInt(12);
			g.drawLine(x,y,x+xl,y+yl);
		}

		// 每个字符占据的宽度
		int x = (width - 1) / number;
		int y = height - 4;

		StringBuffer sb = new StringBuffer();

		// 取随机产生的认证码(4位数字)
		//String sRand="";
		for (int i=0;i<4;i++){
			String code = String.valueOf(codes.charAt(random.nextInt(codes
					.length())));
		    //sRand+=code;
		    // 将认证码显示到图象中
		    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		    g.drawString(code,i * x + 1, y);
		    
		    sb.append(code);
		}
		
		// 随机生成字符
		/*
		for (int i = 0; i < number; i++) {
			String code = String.valueOf(codes.charAt(random.nextInt(codes
					.length())));
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));

			Font font = new Font("Arial", Font.PLAIN,
					random(height / 2, height));
			g.setFont(font);

			g.drawString(code, i * x + 1, y);

			sb.append(code);
		}
		*/
		
		// 将验证码串放到HTTP SESSION中
		request.getSession().setAttribute("codes", sb.toString());

		// 随机生成一些点
		/*
		for (int i = 0; i < 50; i++) {
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		*/
		
		OutputStream out = response.getOutputStream();
		// 将图片转换为JPEG类型
		/*20140407
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);

		out.flush();
		out.close();
		*/
		
		ImageIO.write(image,"JPEG",out);
		out.flush();
		out.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
