/* Check and initialize global namespace objects, if not ready */
/** Empower Office top-level namespace */
var m4 = m4 || {};
/** Empower Office UI namespace */
m4.ui = m4.ui || {};
/** Empower Office UI search suggest feature namespace */
m4.ui.search = m4.ui.search || {};
m4.ui.input = m4.ui.input || {};

/**
 * Fill up search suggest input box with description of object code (id).
 */
m4.ui.search.fillDescription = function() {
	var ob = $(this), obval = ob.val(), val = ob.siblings(".typeahead-val").val(), desc = ob.siblings(".typeahead-desc").val();
	if (val.length > 0 && (obval !== "" && obval === desc) === false) {
		var dependentfields = ob.attr("data-search-dependentfields");
		var urival = val;
		for (var item in m4uiEscapingArr) {
			urival = String(urival).replace(new RegExp(item, 'g'), m4uiEscapingArr[item]);
		}
		var others = "?search=" + val + "&exactMatch=true";
		if (dependentfields != undefined) {
			var paramArr = dependentfields.split(",");
			var paramname = paramArr[0]; 
			var paramval = $("#"+ paramArr[1]).val();
			others += "&" + paramname +"="+paramval;
		}
		$.get(ob.data('search-url') + urival + others, function(rs) {
			$(rs).each(function(i, dt){
				if (dt.key === val) {
					val = dt.value;
					return false;
				}
			});
			ob.val(val);
			ob.siblings(".typeahead-desc").val(val);
		});
	}
};


m4.load = {
	archiverestore : function() {
		/* archive, restore button click */
		$('.archive').off("click");
		$('.archive').on("click", function(e) {
			m4.ui.block();
			var $btn = $(this);
			$.post($(this).attr("href"), function(rs) {
				parseAndShowMessage(rs);
				if (rs.status == "success") {
					var bttxt = $btn.text(), ctxt = (bttxt == "Archive" ? "Restore" : "Archive"),
							hrf = $btn.attr("href"), hrftxt = bttxt.toLowerCase(), chrftxt = ctxt.toLowerCase();

					if (hrf.indexOf(hrftxt) === -1) {
						hrftxt = hrftxt.toUpperCase();
						chrftxt = chrftxt.toUpperCase();
					}
					$btn.attr("href", hrf.replace(hrftxt, chrftxt));
					$btn.text(ctxt);
					var nvbr = $("div.navbar-inner");
					nvbr.removeClass("navbar-archived");
					if (ctxt === "Restore") nvbr.addClass("navbar-archived");
				}
				m4.ui.unblock();
			});
			return false;
		});
	},
};

/** Blocks ui options */
m4.ui.blockoptions = {
	message : $('#block-ui'),
	baseZ : 1055,
	overlayCSS : { opacity : 0.5, backgroundColor : 'silver' },
	css: { left: '0px', width: '100%'}
};

/**
 * Blocks ui
 */
m4.ui.block = function(target) {
	if ($(".blockUI.blockPage")) m4.ui.unblock($(".blockUI.blockPage").parent());
	if (target) target.block(m4.ui.blockoptions);
	else $.blockUI(m4.ui.blockoptions);
};

/**
 * Unblocks the ui
 */
m4.ui.unblock = function(target) {
	if (target) target.unblock();
	else $.unblockUI();
};



/**
 * Default mainpage submit fucntion for this project.<br>
 * If you required you can still write event handler for event 
 * mainpagesubmitpre and do operatons for a specific page
 * 
 * @param e Event reference
 * @param submitconfig JSON array of submit related datas
 * @returns {Boolean} Either true or false
 */
m4.mainpagesubmitdefault = function(e, submitconfig) {

	submitconfig.data = $('form#mainform').serialize();
	submitconfig.success = m4.mainpagesubmitsuccess;
	submitconfig.error = m4.mainpagesubmiterror;

	// event to customize page specific mainpage submit
	var preev = $.Event("mainpagesubmitpre");
	$(this).trigger(preev, submitconfig);
	if (preev.isDefaultPrevented()) return false;

	m4.ui.block();
};

/**
 * Default main page submit success function
 * 
 * @param data
 * @param textStatus
 * @param jqXHR
 */
m4.mainpagesubmitsuccess = function(data, textStatus, jqXHR) {
	if (typeof data.redirect !== 'string') {
		m4.ui.unblock();
		$("input.error").removeClass("error");
		if (data.status === "success") viewmode($("body"));
	}
};

/**
 * Default main page submit error function
 * 
 * @param jqXHR
 * @param textStatus
 * @param errorThrown
 */
m4.mainpagesubmiterror = function(jqXHR, textStatus, errorThrown) {
	console.log("hello buddy");
	try {
		var jsonObject = $.parseJSON(jqXHR.responseText);
		var errMsg = jsonObject.message + ".";
		if (jsonObject.hasOwnProperty("errors")) {
			$.each(jsonObject.errors, function(idx, item){
				if (idx === 0) {
					errMsg += " Errors: ";
				}
				var field = $("[name='" + item.field + "']");
				if (field && field.is(".typeahead-val")) field = field.siblings(".typeahead");
				if (field) field.addClass("error");
				errMsg += item.field + " = " + item.message + ", ";
			});
			showError(errMsg);
		}
	} catch(e) {
		console.log("Failed to parse error ===> " + e);
	}
	m4.ui.unblock();
};

function overrideDuringSubmitStuffDone(target) {
	$('button.viewmode, a.viewmode', target).off("duringsubmitstuffdone");
	$('button.viewmode, a.viewmode', target).on("duringsubmitstuffdone", viewcallbacks, function(event) {
		if (event.data.viewsubmitdone) {
			event.data.viewsubmitdone($(this));
		}
	});
}

// Temporary metafour-ui functions overridden
// We don't need escaping
function escapeHtml(string) {
	return string;
}