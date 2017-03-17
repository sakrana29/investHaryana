(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailController', CompanydetailController);

    CompanydetailController.$inject = ['Companydetail'];

    function CompanydetailController(Companydetail) {

        var vm = this;

        vm.companydetails = [];

        loadAll();

        function loadAll() {
            Companydetail.query(function(result) {
                vm.companydetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
