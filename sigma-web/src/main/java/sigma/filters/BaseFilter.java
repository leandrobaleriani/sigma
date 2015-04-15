package sigma.filters;

import sigma.utils.SearchOrder;
import sigma.utils.SearchPage;

public abstract class BaseFilter {

	private SearchOrder searchOrder;
	private SearchPage searchPage;

	public SearchOrder getSearchOrder() {
		return searchOrder;
	}

	public void setSearchOrder(SearchOrder searchOrder) {
		this.searchOrder = searchOrder;
	}

	public SearchPage getSearchPage() {
		return searchPage;
	}

	public void setSearchPage(SearchPage searchPage) {
		this.searchPage = searchPage;
	}

}
