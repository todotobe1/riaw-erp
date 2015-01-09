var StringUtils = {

	leftPadding : function(value, mark, length) {
		var vLength = value.length;
		if (vLength >= length)
			return value;
		for (var i = 0; i < length - vLength; i++) {
			value = mark + "" + value;
		}
		return value;
	}

};