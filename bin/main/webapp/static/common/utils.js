/**
 * 金额格式判断
 * @param o
 * @returns
 */
function isMoney(o) {
	return /^\+?[0-9]{1,8}(\.[0-9]{1,2})?$/.test(o);
}

/**
 * 判断是否为空
 * @param o
 * @returns {Boolean}
 */
function isEmpty(o){
	if(o==null||o=='undefined'||o==''){
		return true;
	}else{
		return false;
	}
}