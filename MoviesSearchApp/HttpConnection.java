// this class handles the http request to get json file using the API of 'themoviedb.org'
// adding the name of a movie we search to the API's URL (the query) and the page of the results

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

public class HttpConnection {
	//my api key = 0f0fcaacac1a9eaf3ee4a205dc3f0697
	private static final String BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key=0f0fcaacac1a9eaf3ee4a205dc3f0697&language=en-US&query=";

	public String callConnection(String name,int page) throws IOException, InterruptedException {
	HttpClient client =  HttpClient.newHttpClient();
	
		HttpRequest request = HttpRequest.newBuilder()
			.GET()
			.header("accept", "application/json")
			.uri(URI.create(BASE_URL+ name +"&page=" + page + "&include_adult=false"))
			.build();
	 
	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
	}
}