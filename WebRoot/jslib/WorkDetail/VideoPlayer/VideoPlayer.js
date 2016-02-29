$(document).ready(function() {
	$('video').mediaelementplayer({
		alwaysShowControls: false,
		videoVolume: 'horizontal',
		features: ['playpause','progress','volume','fullscreen']
	});


	MediaElement('player1', {success: function(me) {
				me.addEventListener('timeupdate', function() {
			document.getElementById('VideoTime').innerHTML = me.currentTime;
		}, false);
	}});

	

});