var editcallbacks = {}, viewcallbacks = {}, bodyinputmodified = false, m4uiEscapingArr = [];

$(function() {
	"use strict";

	setvalidator();
	metaboot($('body'));
	addvalidation($('body'));

	if ($("ul.nav li").length == 1) { // indicates create screen as we are using only create button at create screen
		editmode($('body:visible'));
	} else {
		viewmode($('body:visible'));
	}

	/**
	 * All our pages should have block-ui div which is coming from commons.html file
	 * No need to write yourself, just use common-html fragment of commons.html in your page
	 */ 
	if ($("#block-ui").length > 0) { 
		$("#block-ui").css("display", "none");
	}
	/** 
	 * Triggering init event of html tag which has data-controller attribute.
	 * Every page should have an attribute data-controller which makes the page unique.
	 * If any page needs to do something after page loading, just
	 * write init event handling of the html tag which has data-controller attribute.
	*/
	$("[data-controller]").trigger("init");
	/**
	 * Archive/restore button click events binding
	 */
	m4.load.archiverestore();

	// Filling all search suggest descriptions
	$.each($('#mainform .typeahead'), m4.ui.search.fillDescription);
	// metafour-ui extension overridden function to remove viewmode call
	overrideDuringSubmitStuffDone($('body'));
	// Default mainpagesubmitintialise event handling
	$("#headerconfirmbutton").on("mainpagesubmitinitialise", m4.mainpagesubmitdefault);

	// block ui on add button click
	$("#addbutton").on("click", function(){m4.ui.block();});
});