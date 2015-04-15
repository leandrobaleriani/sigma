
//cambia los links de displaytag asumiendo que está contenido 
//en un div con id = ajxDspId
function changeLinks()
{
    done=true;
    var linkData, queryArr, action, qryStr;

    //selector = 'span.pagelinks>a';
    selector = '.paginador  a';
    
    changeUsingSelector(selector, 'ajxDspId');

    //selector = 'table#dispTable>thead>tr>th>a';
    selector = '#ajxDspId  table>thead>tr>th>a';
    changeUsingSelector(selector, 'ajxDspId');
}

function changeUsingSelector(selector, containerID)
{
    $(selector).each(function()
    {
        linkData = $(this).attr("href");
        queryArr = linkData.split("?");
        action =  queryArr[0];
        qryStr = queryArr[1];
        newStr = "JavaScript:doAjax('"+action+"','"+qryStr+"','"+ containerID +"');";
        $(this).attr("href", newStr);
    });    
}
   

// actualiza los links de displaytag contenidos en un div específico
function updateDisplayTagLinks( containerID )
{
	//$.log.info(containerID)
	done=true;
    var linkData, queryArr, action, qryStr;

    //selector = 'span.pagelinks>a';
    //selector = '.paginador  a';
    selector = '#' + containerID + ' .paginador  a';
    
    
    changeUsingSelector(selector, containerID);
    //$.log.info(selector)
    //selector = 'table#dispTable>thead>tr>th>a';
    selector = '#' + containerID + ' table>thead>tr>th>a';
    //$.log.info(selector)
    changeUsingSelector(selector, containerID);
    
}


function doAjax(url, data, eleId)
{
    //if you need additional params to be passed - add to the data variable
    $.ajax
    ({    	
        url: url,
        data:  data,
        async: true,
        success: function(resp){
            $('#'+eleId).html(resp);
        }
    });
}



/*
jQuery(function($) {
   changeLinks();
}); 
*/


