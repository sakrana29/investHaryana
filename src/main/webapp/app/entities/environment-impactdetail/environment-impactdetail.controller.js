(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impactdetailController', Environment_impactdetailController);

    Environment_impactdetailController.$inject = ['Environment_impactdetail'];

    function Environment_impactdetailController(Environment_impactdetail) {

        var vm = this;

        vm.environment_impactdetails = [];

        loadAll();

        function loadAll() {
            Environment_impactdetail.query(function(result) {
                vm.environment_impactdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
