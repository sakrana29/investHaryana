(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectproductController', ProjectproductController);

    ProjectproductController.$inject = ['Projectproduct'];

    function ProjectproductController(Projectproduct) {

        var vm = this;

        vm.projectproducts = [];

        loadAll();

        function loadAll() {
            Projectproduct.query(function(result) {
                vm.projectproducts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
