(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailController', ProjectsitedetailController);

    ProjectsitedetailController.$inject = ['Projectsitedetail'];

    function ProjectsitedetailController(Projectsitedetail) {

        var vm = this;

        vm.projectsitedetails = [];

        loadAll();

        function loadAll() {
            Projectsitedetail.query(function(result) {
                vm.projectsitedetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
