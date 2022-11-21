import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.dao.WifiDao;
import com.dto.WifiDto;
import com.openapi.OpenApiConnect;

public class ApiTest {

	public static void main(String[] args) throws IOException {
//		WifiController wifiController = new WifiController();
//		List<WifiDto> dtos = wifiController.getWifiInfoFromApi();

//		for(WifiDto item : dtos) {
//			System.out.println(item);
//		}

//		System.out.println("총 개수 : " + dtos.size());

		WifiDao dao = new WifiDao();
		List<WifiDto> list = dao.nearByWifiSelect("37.6015565", "126.7280587");
		System.out.println(list.size());
//		int res = dao.insert(dtos);
//		System.out.println(res);
//		if(res == dtos.size()) {
//			System.out.println("db save success");
//		}else {
//			System.out.println("db save fail");
//		}
//		System.out.println("왜 안돼~");
//		dao.select();

	}
//	public static void main(String[] args) throws IOException {
//		HttpURLConnection conn = OpenApiConnect.getConnection();
//		/* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
//		System.out.println("Response code: " + conn.getResponseCode());
//		BufferedReader rd;
//		
//		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		} else {
//			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		}
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while ((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		System.out.println(sb.toString());
//		
//		OpenApiConnect.closeConnection();
//	}

}
