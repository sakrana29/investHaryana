(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectAttachemntController', ProjectAttachemntController);

    ProjectAttachemntController.$inject = ['ProjectAttachemnt'];

    function ProjectAttachemntController(ProjectAttachemnt) {

        var vm = this;

        vm.projectAttachemnts = [];

        loadAll();

        function loadAll() {
            ProjectAttachemnt.query(function(result) {
                vm.projectAttachemnts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
