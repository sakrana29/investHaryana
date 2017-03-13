(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Regular_electrict_load_typeController', Regular_electrict_load_typeController);

    Regular_electrict_load_typeController.$inject = ['Regular_electrict_load_type'];

    function Regular_electrict_load_typeController(Regular_electrict_load_type) {

        var vm = this;

        vm.regular_electrict_load_types = [];

        loadAll();

        function loadAll() {
            Regular_electrict_load_type.query(function(result) {
                vm.regular_electrict_load_types = result;
                vm.searchQuery = null;
            });
        }
    }
})();
