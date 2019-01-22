var vm = new Vue({
	el : '#app',
	data : {
		
	},
	methods : {
		openTab : function (title, url) {
			newTab(title, url);
		}
	}
});