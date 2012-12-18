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
function validate(evt){
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]|[a-z]|[A-Z]|\ |\?/;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault){
			theEvent.preventDefault();
		}
	}
}