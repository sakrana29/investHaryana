(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceReportInfoDeleteController',ProjectServiceReportInfoDeleteController);

    ProjectServiceReportInfoDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProjectServiceReportInfo'];

    function ProjectServiceReportInfoDeleteController($uibModalInstance, entity, ProjectServiceReportInfo) {
        var vm = this;

        vm.projectServiceReportInfo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProjectServiceReportInfo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
