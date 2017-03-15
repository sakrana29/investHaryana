(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_phaseController', Project_phaseController);

    Project_phaseController.$inject = ['Project_phase'];

    function Project_phaseController(Project_phase) {

        var vm = this;

        vm.project_phases = [];

        loadAll();

        function loadAll() {
            Project_phase.query(function(result) {
                vm.project_phases = result;
                vm.searchQuery = null;
            });
        }
    }
})();
