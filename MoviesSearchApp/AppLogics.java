// class which makes call to the http request class to get back json format
// use Gson library (by Google) through Gradle (multi-lang software dev tool) to parse it as MovieList object

import com.google.gson.Gson;

import java.io.IOException;

public class AppLogics {
		protected MovieList list;
	public AppLogics(String name, int page) throws IOException, InterruptedException {
		HttpConnection connection = new HttpConnection();

		name = name.replace(' ', '+');

		String jsonStrin = connection.callConnection(name, page);
		Gson gson = new Gson();
		list = gson.fromJson(jsonStrin, MovieList.class);
	}
}