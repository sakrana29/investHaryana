(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailcombinecodesDeleteController',ProjectdetailcombinecodesDeleteController);

    ProjectdetailcombinecodesDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectdetailcombinecodes'];

    function ProjectdetailcombinecodesDeleteController($uibModalInstance, entity, Projectdetailcombinecodes) {
        var vm = this;

        vm.projectdetailcombinecodes = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectdetailcombinecodes.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
