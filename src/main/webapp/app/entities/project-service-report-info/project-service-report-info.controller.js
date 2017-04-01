(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceReportInfoController', ProjectServiceReportInfoController);

    ProjectServiceReportInfoController.$inject = ['ProjectServiceReportInfo'];

    function ProjectServiceReportInfoController(ProjectServiceReportInfo) {

        var vm = this;

        vm.projectServiceReportInfos = [];

        loadAll();

        function loadAll() {
            ProjectServiceReportInfo.query(function(result) {
                vm.projectServiceReportInfos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
