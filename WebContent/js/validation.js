function searchLogic(checkboxes, fields, nameFields) {
    var valid = true;

    for (var i = 0; i < checkboxes.length; i++) {
        valid = checkIfEmpty(checkboxes[i], fields[i], nameFields[i]);
        if (valid == false) break;
    }

    return valid;
}

function checkIfEmpty(checked, field, fillField) {
    if (checked == true && field == "") {
        alert("Morate da popunite polje " + fillField.substring(0, fillField.length - 1) + "!");
        return false;
    }

    return true;
}

function searchValidation() {
    var titleCb = form.title_checkbox.checked;
    var authorCb = form.author_checkbox.checked;
    var keywordCb = form.keyword_checkbox.checked;
    var contentCb = form.content_checkbox.checked;

    var titleField = form.title.value;
    var authorField = form.author.value;
    var keywordField = form.keyword.value;
    var contentField = form.content.value;

    var titleName = document.getElementById("titleError").textContent;
    var authorName = document.getElementById("authorError").textContent;
    var keywordName = document.getElementById("keywordError").textContent;
    var contentName = document.getElementById("contentError").textContent;

    var checkboxes = [titleCb, authorCb, keywordCb, contentCb];
    var fields = [titleField, authorField, keywordField, contentField];
    var nameFields = [titleName, authorName, keywordName, contentName];
    
    /*if(titleCb == false && authorCb == false && keywordCb == false && contentCb == false){
    	alert("Morate da unesete neki kriterijum!");
    	return false;
    }*/
    
    return searchLogic(checkboxes, fields, nameFields);
}

function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
}

function checkFile(){
	if( form.fileUpload.files.length == 0 ){
	    alert("You have to input some file!");
	    return false;
	}else if(getExtension(form.fileUpload.files[0].name) != 'pdf'){
		alert("Wrong format! You have to input a pdf file!")
		return false;
	}
	
	return true;
}