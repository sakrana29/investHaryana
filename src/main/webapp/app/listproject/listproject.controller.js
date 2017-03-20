(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('listprojectController', listprojectController);

    listprojectController.$inject = ['$http', '$state'];

    function listprojectController($http, $state) {
        var vm = this;
        $http.get("/api/projectdetails").then(function(response) {
           vm.prdetail = response.data;
       });
    }
})();
