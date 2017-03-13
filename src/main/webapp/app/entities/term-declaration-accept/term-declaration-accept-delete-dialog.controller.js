(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Term_declaration_acceptDeleteController',Term_declaration_acceptDeleteController);

    Term_declaration_acceptDeleteController.$inject = ['$uibModalInstance', 'entity', 'Term_declaration_accept'];

    function Term_declaration_acceptDeleteController($uibModalInstance, entity, Term_declaration_accept) {
        var vm = this;

        vm.term_declaration_accept = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Term_declaration_accept.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
