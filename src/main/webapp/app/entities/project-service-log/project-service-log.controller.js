(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceLogController', ProjectServiceLogController);

    ProjectServiceLogController.$inject = ['ProjectServiceLog'];

    function ProjectServiceLogController(ProjectServiceLog) {

        var vm = this;

        vm.projectServiceLogs = [];

        loadAll();

        function loadAll() {
            ProjectServiceLog.query(function(result) {
                vm.projectServiceLogs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
