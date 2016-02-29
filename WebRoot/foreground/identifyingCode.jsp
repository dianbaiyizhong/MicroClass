<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<%!Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	int i_width = 60;
	int i_height = 20;
	BufferedImage image = new BufferedImage(i_width, i_height,
			BufferedImage.TYPE_INT_RGB);

	Graphics g = image.getGraphics();

	Random random = new Random();

	g.setColor(getRandColor(200, 250));
	g.fillRect(0, 0, i_width, i_height);

	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 155; i++) {
		int i_x = random.nextInt(i_width);
		int i_y = random.nextInt(i_height);
		int i_xl = random.nextInt(12);
		int i_yl = random.nextInt(12);
		g.drawLine(i_x, i_y, i_x + i_xl, i_y + i_yl);
	}

	String s_Rand = "";
	for (int i = 0; i < 4; i++) {
		String rand = String.valueOf(random.nextInt(10));
		s_Rand += rand;

		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		g.drawString(rand, 13 * i + 6, 16);
	}

	session.setAttribute("Rand", s_Rand);

	g.dispose();

	ImageIO.write(image, "JPEG", response.getOutputStream());
	out.clear();
    out = pageContext.pushBody();
	
%>
