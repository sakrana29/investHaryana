(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impactdetailController', Environment_impactdetailController);

    Environment_impactdetailController.$inject = ['DataUtils', 'Environment_impactdetail'];

    function Environment_impactdetailController(DataUtils, Environment_impactdetail) {

        var vm = this;

        vm.environment_impactdetails = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Environment_impactdetail.query(function(result) {
                vm.environment_impactdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
