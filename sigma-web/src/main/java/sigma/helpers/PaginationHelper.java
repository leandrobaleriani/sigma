package sigma.helpers;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import sigma.utils.SearchOrder;
import sigma.utils.SearchPage;




public class PaginationHelper {
	
	private int defaultPageSize;
	private String defaultSortField; 
	private String defaultOrder; 
	
	/**
	 * @see PaginationHelper#getPaginationData(HttpServletRequest, String, int)
	 */
	public SearchPage getSearchPage( HttpServletRequest request, String tableId )
	{
		SearchPage page = getSearchPage( request, tableId, getDefaultPageSize() );
		return page;
	}
	
	/**
	 * Obtiene los datos de paginacion correspondiente a la pagina solicitada.
	 *
	 * @param request 	HttpServletRequest
	 * @param tableId	ID asignado a la tabla que se está paginando
	 * @param pageSize	Cantidad de registros a mostrar por pagina
	 * @return SearchPage
	 *
	 * @see ar.com.osde.entities.prestaciones.commons.filter.SearchPage
	 */
	public static SearchPage getSearchPage( HttpServletRequest request,
											String tableId,
											int pageSize ) {
		SearchPage pageInfo = new SearchPage(pageSize);

		String pageParam = new ParamEncoder( tableId ).encodeParameterName( TableTagParameters.PARAMETER_PAGE );

		int pg = ( null != request.getParameter( pageParam ) ) ? Integer.parseInt( request.getParameter( pageParam ) ) : 1;

		pageInfo.setPage( pg );

		request.getSession().setAttribute( pageParam, pg );
		return pageInfo;
	}
	
	/**
	 * Obtiene la informacion sobre el ordenamiento especificado para un listado.
	 *
	 * @param request	HttpServletRequest
	 * @param tableId	ID asignado a la tabla que se está paginando
	 * @return	SearchOrder
	 */
	public SearchOrder getSearchOrder (  HttpServletRequest request,
												String tableId)
	{
		// Inicializa el SearchOrder con valores por default
		SearchOrder orderInfo = new SearchOrder();
		orderInfo.setCampo( getDefaultSortField() );
		orderInfo.setOrden( getDefaultOrder());

		String columnSort	= new ParamEncoder( tableId ).encodeParameterName( TableTagParameters.PARAMETER_SORT );
		String sortValue	= request.getParameter( columnSort );

		// Si se clickeo sobre el ordenamiento de una columna....
		if(null != sortValue)
		{
			orderInfo.setCampo( sortValue );

			String sortOrder = new ParamEncoder( tableId ).encodeParameterName( TableTagParameters.PARAMETER_ORDER );
			String orderValue = request.getParameter( sortOrder );

			// Descending = 1, Ascending = 2.
			String order = ( orderValue.equals( "1" ) ) ? SearchOrder.DESC : SearchOrder.ASC;
			orderInfo.setOrden( order );
		}


		return orderInfo;
	}
	
	/**
	 * @return the defaultPageSize
	 */
	public int getDefaultPageSize() {
		return defaultPageSize;
	}

	 
	
	/**
	 * @param defaultPageSize the defaultPageSize to set
	 */
	public void setDefaultPageSize( int defaultPageSize ) {
		this.defaultPageSize = defaultPageSize;
	}

	
	
	/**
	 * @return the defaultSortField
	 */
	public String getDefaultSortField() {
		return defaultSortField;
	}

	
	
	/**
	 * @param defaultSortField the defaultSortField to set
	 */
	public void setDefaultSortField( String defaultSortField ) {
		this.defaultSortField = defaultSortField;
	}

	
	
	/**
	 * @return the defaultOrder
	 */
	public String getDefaultOrder() {
		return defaultOrder;
	}

	
	
	/**
	 * @param defaultOrder the defaultOrder to set
	 */
	public void setDefaultOrder( String defaultOrder ) {
		this.defaultOrder = defaultOrder;
	}

}
