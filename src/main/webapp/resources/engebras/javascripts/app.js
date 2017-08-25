$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content, .js-footer').toggleClass('is-toggled');
		event.preventDefault();
	});	
});

$("#zoom_lente").elevateZoom({
  zoomType : "lens",
  lensShape : "round",
  lensSize : 200
});

