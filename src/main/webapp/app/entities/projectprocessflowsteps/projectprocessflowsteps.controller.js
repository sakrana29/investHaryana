(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectprocessflowstepsController', ProjectprocessflowstepsController);

    ProjectprocessflowstepsController.$inject = ['Projectprocessflowsteps'];

    function ProjectprocessflowstepsController(Projectprocessflowsteps) {

        var vm = this;

        vm.projectprocessflowsteps = [];

        loadAll();

        function loadAll() {
            Projectprocessflowsteps.query(function(result) {
                vm.projectprocessflowsteps = result;
                vm.searchQuery = null;
            });
        }
    }
})();
