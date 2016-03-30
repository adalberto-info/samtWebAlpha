$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content, .js-footer').toggleClass('is-toggled');
		event.preventDefault();
	});	
});
