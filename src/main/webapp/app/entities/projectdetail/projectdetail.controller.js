(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailController', ProjectdetailController);

    ProjectdetailController.$inject = ['Projectdetail'];

    function ProjectdetailController(Projectdetail) {

        var vm = this;

        vm.projectdetails = [];

        loadAll();

        function loadAll() {
            Projectdetail.query(function(result) {
                vm.projectdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
