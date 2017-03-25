(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectservicedetailController', ProjectservicedetailController);

    ProjectservicedetailController.$inject = ['Projectservicedetail'];

    function ProjectservicedetailController(Projectservicedetail) {

        var vm = this;

        vm.projectservicedetails = [];

        loadAll();

        function loadAll() {
            Projectservicedetail.query(function(result) {
                vm.projectservicedetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
