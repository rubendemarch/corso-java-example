function loadAction(commonFormActionValue){
	loadActionAndSubAction(commonFormActionValue,'');
}
function loadActionAndSubAction(commonFormActionValue,commonActionValue){
	loadAll(commonFormActionValue,commonActionValue,'');
}
function loadAll(commonFormActionValue,commonActionValue,commonIdValue){
	document.getElementById('commonAction').value=commonActionValue;
	document.getElementById('commonId').value=commonIdValue;
	document.getElementById('commonForm').action=commonFormActionValue;
	document.getElementById('commonForm').submit();
}