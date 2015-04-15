package sigma.utils;


public class SearchPage {


	public SearchPage() {
	}

	public SearchPage(int pageSize) {
		this.pageSize = pageSize;
	}

	private int pageSize = 1000;
	private int page = 1;

	/**
	 * @return the from
	 */
	public int getFrom() {
		return ( page - 1 ) * pageSize;
	}

	/**
	 * @return the to
	 */
	public int getTo() {
		return getFrom() + pageSize;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize( int pageSize ) {
		this.pageSize = pageSize;
	}

    /**
     * @return the page
     */
    public int getPage() {
    	return page;
    }

	
    
    /**
     * @param page the page to set
     */
    public void setPage( int page ) {
    	this.page = page;
    }

	
}
