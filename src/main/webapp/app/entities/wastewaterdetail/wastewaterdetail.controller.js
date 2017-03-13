(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WastewaterdetailController', WastewaterdetailController);

    WastewaterdetailController.$inject = ['Wastewaterdetail'];

    function WastewaterdetailController(Wastewaterdetail) {

        var vm = this;

        vm.wastewaterdetails = [];

        loadAll();

        function loadAll() {
            Wastewaterdetail.query(function(result) {
                vm.wastewaterdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
