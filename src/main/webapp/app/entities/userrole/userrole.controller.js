(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UserroleController', UserroleController);

    UserroleController.$inject = ['Userrole'];

    function UserroleController(Userrole) {

        var vm = this;

        vm.userroles = [];

        loadAll();

        function loadAll() {
            Userrole.query(function(result) {
                vm.userroles = result;
                vm.searchQuery = null;
            });
        }
    }
})();
