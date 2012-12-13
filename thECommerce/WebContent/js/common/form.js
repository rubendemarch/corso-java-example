function dispatchPage(formId,formActionValue,actionId,actionValue){
	document.getElementById(formId+"_"+actionId).value=actionValue;
	document.getElementById(formId).action=formActionValue;
	document.getElementById(formId).submit();
}