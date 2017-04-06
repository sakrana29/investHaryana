(function() {
    'use strict';
    angular
        .module('investhryApp')
//        .factory('getProjectServiceDetailsByService', getProjectServiceDetailsByService);

    .provider('getProjectServiceDetailsByService', function() {
      return {
    	  this.$get = function($http) {
    		var service = this;
    		this.user = {};
    		this.login = function(email, pwd) {
    		  $http.get('/auth',{ username: email, password: pwd}).success(function(data){
    			service.user = data;
    		  });
    		};
    		this.register = function(newuser) {
    		  return $http.post('/users', newuser);
    		};
    	  }
      }
    });
})();
