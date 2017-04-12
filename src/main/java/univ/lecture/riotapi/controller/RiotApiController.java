package univ.lecture.riotapi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;
import univ.lecture.riotapi.Calculator;
import univ.lecture.riotapi.model.Team10;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/calc")
@Log4j
public class RiotApiController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${riot.api.endpoint}")
	private String riotApiEndpoint;

	@Value("${riot.api.key}")
	private String riotApiKey;

	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Team10 queryTeam10(@RequestParam(value = "exp") String exp) {

		Calculator cal = new Calculator();
		double result = cal.calculate(cal.postfix(exp));
		long now = System.currentTimeMillis();
		Team10 team10 = new Team10(10, now, result);
		log.debug("interrupt");
		
		
		try {
			URL url = new URL("http://52.79.162.52:8080/api/v1/answer");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);

			conn.setRequestMethod("POST"); // 보내는 타입

			conn.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");

			//데이터 
			String param = Double.toString(result);

			// 전송

			OutputStreamWriter osw = new OutputStreamWriter(

			conn.getOutputStream());

			osw.write(param);

			osw.flush();

			// 응답

			BufferedReader br = null;

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line = null;

			while ((line = br.readLine()) != null) {

				System.out.println(line);

			}

			// 닫기

			osw.close();

			br.close();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (ProtocolException e) {

			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return team10;

	}

}