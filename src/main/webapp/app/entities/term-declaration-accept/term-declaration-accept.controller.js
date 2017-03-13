(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Term_declaration_acceptController', Term_declaration_acceptController);

    Term_declaration_acceptController.$inject = ['DataUtils', 'Term_declaration_accept'];

    function Term_declaration_acceptController(DataUtils, Term_declaration_accept) {

        var vm = this;

        vm.term_declaration_accepts = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Term_declaration_accept.query(function(result) {
                vm.term_declaration_accepts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
