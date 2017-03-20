(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('acceptanceController', acceptanceController);

    acceptanceController.$inject = ['$http', '$state'];

    function acceptanceController($http, $state) {
        var vm = this;
        $http.get("/api/projectdetails/ByUserLogin/").then(function(response) {
           vm.prdetail = response.data;
       });
    }
})();
