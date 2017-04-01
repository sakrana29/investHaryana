(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectserviceformfielddataController', ProjectserviceformfielddataController);

    ProjectserviceformfielddataController.$inject = ['Projectserviceformfielddata'];

    function ProjectserviceformfielddataController(Projectserviceformfielddata) {

        var vm = this;

        vm.projectserviceformfielddata = [];

        loadAll();

        function loadAll() {
            Projectserviceformfielddata.query(function(result) {
                vm.projectserviceformfielddata = result;
                vm.searchQuery = null;
            });
        }
    }
})();
