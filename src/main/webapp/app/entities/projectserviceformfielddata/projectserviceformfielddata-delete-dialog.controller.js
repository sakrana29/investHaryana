(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectserviceformfielddataDeleteController',ProjectserviceformfielddataDeleteController);

    ProjectserviceformfielddataDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectserviceformfielddata'];

    function ProjectserviceformfielddataDeleteController($uibModalInstance, entity, Projectserviceformfielddata) {
        var vm = this;

        vm.projectserviceformfielddata = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectserviceformfielddata.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
