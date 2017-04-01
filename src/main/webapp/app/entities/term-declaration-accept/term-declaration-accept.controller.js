(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Term_declaration_acceptController', Term_declaration_acceptController);

    Term_declaration_acceptController.$inject = ['Term_declaration_accept'];

    function Term_declaration_acceptController(Term_declaration_accept) {

        var vm = this;

        vm.term_declaration_accepts = [];

        loadAll();

        function loadAll() {
            Term_declaration_accept.query(function(result) {
                vm.term_declaration_accepts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
