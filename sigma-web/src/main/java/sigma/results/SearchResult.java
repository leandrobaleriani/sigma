package sigma.results;

import java.util.List;

public class SearchResult<E> {

	private List<E> data;
	private int totalResults;

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	
	public void setTotalResults(long totalResults) {
		this.totalResults = (int)totalResults;
	}

}
