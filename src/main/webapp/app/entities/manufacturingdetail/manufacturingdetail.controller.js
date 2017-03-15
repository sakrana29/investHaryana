(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingdetailController', ManufacturingdetailController);

    ManufacturingdetailController.$inject = ['DataUtils', 'Manufacturingdetail'];

    function ManufacturingdetailController(DataUtils, Manufacturingdetail) {

        var vm = this;

        vm.manufacturingdetails = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Manufacturingdetail.query(function(result) {
                vm.manufacturingdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
