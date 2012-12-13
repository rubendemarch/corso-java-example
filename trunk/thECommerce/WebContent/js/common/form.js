function dispatchPage(formId,formActionValue,actionId,actionValue){
	document.getElementById(actionId).value=actionValue;
	document.getElementById(formId).action=formActionValue;
	document.getElementById(formId).submit();
}