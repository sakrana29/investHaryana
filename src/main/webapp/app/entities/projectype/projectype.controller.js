(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectypeController', ProjectypeController);

    ProjectypeController.$inject = ['Projectype'];

    function ProjectypeController(Projectype) {

        var vm = this;

        vm.projectypes = [];

        loadAll();

        function loadAll() {
            Projectype.query(function(result) {
                vm.projectypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
