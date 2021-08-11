// class which holds the attributes of the json file we parse, we make an MovieList object for each page we handle
// MovieList object holds the results page attribute, and an array of movies we get as result for each page
// for each page max of 20 movies


public class MovieList {
	protected int page;
	protected Movies results[];

	public class Movies {
		protected String poster_path;
		protected String overview;
		protected String release_date;
		protected String title;
		protected float vote_average;
	}
}