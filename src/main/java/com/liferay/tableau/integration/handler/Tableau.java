package com.liferay.tableau.integration.handler;

import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Tableau {

	public static String getTrustedTicket(String user, String tabURL) throws Exception {
		
		OutputStreamWriter out = null;
		BufferedReader in = null;
		try {
			StringBuffer data = new StringBuffer();
			data.append(URLEncoder.encode("username", "UTF-8"));
			data.append("=");
			data.append(URLEncoder.encode(user, "UTF-8"));

			String tmpURL = StringPool.BLANK;
			if(!tabURL.startsWith("http://")) {
				tmpURL = "http://" + tabURL;
			} else {
				tmpURL = tabURL;
			}
			if(!tmpURL.endsWith("/")) {
				tmpURL = tmpURL + "/";
			}
			
			URL url = new URL(tmpURL + "trusted");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data.toString());
			out.flush();

			StringBuffer rsp = new StringBuffer();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				rsp.append(line);
			}
			return rsp.toString();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}

}
